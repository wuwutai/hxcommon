package com.hongxiang.mybatis.scanner;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.hongxiang.util.FileUtil;

/**                              
 * @description : 扩展了mybatis的扫描方法,将原有的包类型扫描变成了可用通配符扩展的方式
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 12:27:17 +0800 
 */
public class MapperScannerConfig implements BeanFactoryPostProcessor, InitializingBean {

    private String basePackage;

    private boolean addToConfig = true;

    private SqlSessionFactory sqlSessionFactory;

    private SqlSessionTemplate sqlSessionTemplate;

    private Class<? extends Annotation> annotationClass;

    private Class<?> markerInterface;

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setAddToConfig(boolean addToConfig) {
        this.addToConfig = addToConfig;
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public void setMarkerInterface(Class<?> superClass) {
        this.markerInterface = superClass;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    /**
     * {@inheritDoc}
     */
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.basePackage, "Property 'basePackage' is required");
    }

    /**
     * {@inheritDoc}
     */
    /*
    * <p>Title: postProcessBeanFactory</p>
    * <p>Description: 此函数中调用了根据通配符扫描包的方法</p>
    * @param beanFactory
    * @see org.springframework.beans.factory.config.BeanFactoryPostProcessor#postProcessBeanFactory(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)
    */
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        Scanner scanner = new Scanner((BeanDefinitionRegistry) beanFactory);
        //通过通配符获取package名并用半角逗号分割
        String basePackages = FileUtil.getPackagePathByWildcards(this.basePackage, ",");
        scanner.scan(StringUtils.tokenizeToStringArray(basePackages,
                ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
    }

    private final class Scanner extends ClassPathBeanDefinitionScanner {

        public Scanner(BeanDefinitionRegistry registry) {
            super(registry);
        }

        /**
         * Configures parent scanner to search for the right interfaces. It can search for all
         * interfaces or just for those that extends a markerInterface or/and those annotated with
         * the annotationClass
         */
        @Override
        protected void registerDefaultFilters() {
            boolean acceptAllInterfaces = true;

            // if specified, use the given annotation and / or marker interface
            if (MapperScannerConfig.this.annotationClass != null) {
                addIncludeFilter(new AnnotationTypeFilter(MapperScannerConfig.this.annotationClass));
                acceptAllInterfaces = false;
            }

            // override AssignableTypeFilter to ignore matches on the actual marker interface
            if (MapperScannerConfig.this.markerInterface != null) {
                addIncludeFilter(new AssignableTypeFilter(MapperScannerConfig.this.markerInterface) {
                    @Override
                    protected boolean matchClassName(String className) {
                        return false;
                    }
                });
                acceptAllInterfaces = false;
            }

            if (acceptAllInterfaces) {
                // default include filter that accepts all classes
                addIncludeFilter(new TypeFilter() {
                    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
                            throws IOException {
                        return true;
                    }
                });
            }

            // always exclude interfaces with no methods
            addExcludeFilter(new TypeFilter() {
                public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
                        throws IOException {
                    ClassMetadata classMetadata = metadataReader.getClassMetadata();
                    Class<?> candidateClass = null;

                    try {
                        candidateClass = getClass().getClassLoader().loadClass(classMetadata.getClassName());
                    } catch (ClassNotFoundException ex) {
                        return false;
                    }

                    if (candidateClass.getMethods().length == 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        }

        /**
         * Calls the parent search that will search and register all the candidates. Then the
         * registered objects are post processed to set them as MapperFactoryBeans
         */
        @Override
        protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
            Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

            if (beanDefinitions.isEmpty()) {
                logger.warn("No MyBatis mapper was found in '" + MapperScannerConfig.this.basePackage
                        + "' package. Please check your configuration.");
            } else {
                for (BeanDefinitionHolder holder : beanDefinitions) {
                    GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();

                    if (logger.isDebugEnabled()) {
                        logger.debug("Creating MapperFactoryBean with name '" + holder.getBeanName() + "' and '"
                                + definition.getBeanClassName() + "' mapperInterface");
                    }

                    // the mapper interface is the original class of the bean
                    // but, the actual class of the bean is MapperFactoryBean
                    definition.getPropertyValues().add("mapperInterface", definition.getBeanClassName());
                    definition.setBeanClass(MapperFactoryBean.class);

                    definition.getPropertyValues().add("addToConfig", MapperScannerConfig.this.addToConfig);

                    if (MapperScannerConfig.this.sqlSessionFactory != null) {
                        definition.getPropertyValues().add("sqlSessionFactory",
                        		MapperScannerConfig.this.sqlSessionFactory);
                    }

                    if (MapperScannerConfig.this.sqlSessionTemplate != null) {
                        definition.getPropertyValues().add("sqlSessionTemplate",
                        		MapperScannerConfig.this.sqlSessionTemplate);
                    }
                }
            }

            return beanDefinitions;
        }

        @Override
        protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
            return (beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent());
        }

        @Override
        protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) throws IllegalStateException {
            if (super.checkCandidate(beanName, beanDefinition)) {
                return true;
            } else {
                logger.warn("Skipping MapperFactoryBean with name '" + beanName + "' and '"
                        + beanDefinition.getBeanClassName() + "' mapperInterface"
                        + ". Bean already defined with the same name!");
                return false;
            }
        }
    }

}

 