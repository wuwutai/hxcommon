package com.hongxiang.service;

/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: CacheService.java
 * @Package hongxiang.util
 * @Description: MemCached的缓存服务类
 * @Author: 赵聪
 * @date 2013-3-25 下午4:08:28
 */

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;
import org.springframework.dao.DataRetrievalFailureException;
import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
/**                              
 * @description :设置缓存工具类
 * @Author: 赵聪                 
 * @Date: 2013-3-25 下午4:08:28 
 */
public class CacheService {
    private Cache localCache; 

    String cacheServerList;

    String cacheServerWeights;

    boolean cacheCluster = false;

    int initialConnections = 10;

    int minSpareConnections = 5;

    int maxSpareConnections = 50;

    long maxIdleTime = 1000 * 60 * 30; // 30 minutes  

    long maxBusyTime = 1000 * 60 * 5; // 5 minutes  

    long maintThreadSleep = 1000 * 5; // 5 seconds  

    int socketTimeOut = 1000 * 3; // 3 seconds to block on reads  

    int socketConnectTO = 1000 * 3; // 3 seconds to block on initial  

    boolean failover = false; // turn off auto-failover in event of server  

    // down 
    boolean nagleAlg = false; // turn off Nagle's algorithm on all sockets in  

    // pool  
    MemCachedClient mc;

    public CacheService()
    {
        mc = new MemCachedClient();
        mc.setCompressEnable(false);
    }

    public void setLocalCache(Cache localCache)
    {
        this.localCache = localCache;
    }
    public void setCacheServerList(String cacheServerList)
    {
        this.cacheServerList = cacheServerList;
    }
    public void setCacheServerWeights(String cacheServerWeights)
    {
        this.cacheServerWeights = cacheServerWeights;
    }
    public void setCacheCluster(boolean cacheCluster)
    {
        this.cacheCluster = cacheCluster;
    }
    /** 
     * 放入 
     *  
     */
    public void put(String key, Object obj)
    {       
        if (this.cacheCluster)
        {
            mc.set(key, obj);
        } else
        {
            Element element = new Element(key, (Serializable) obj);
            localCache.put(element);
        }
    }
    /** 
     * 删除  
     */
    public void remove(String key)
    {
        if (this.cacheCluster)
        {
            mc.delete(key);
        } else
        {
            localCache.remove(key);
        }
    }

    /** 
     * 得到 
     */
    public Object get(String key)
    {
        Object rt = null;
        if (this.cacheCluster)
        {
            rt = mc.get(key);
        } else
        {
            Element element = null;
            try
            {
                element = localCache.get(key);
            } catch (CacheException cacheException)
            {
                throw new DataRetrievalFailureException("Cache failure: "
                        + cacheException.getMessage());
            }
            if (element != null)
                rt = element.getValue();
        }
        return rt;
    }

    /** 
     * 判断是否存在 
     *  
     */
    public boolean exist(String key)
    {
        if (this.cacheCluster)
        {
            return mc.keyExists(key);
        } else
        {
            return this.localCache.isKeyInCache(key);
        }
    }

    private void init()
    {
        if (this.cacheCluster)
        {
            String[] serverlist = cacheServerList.split(",");
            //Integer[] weights = this.split(cacheServerWeights);  
            String[] temps = cacheServerWeights.split(",");
            Integer[] weights = new Integer[temps.length];
            for (int t = 0; t < temps.length; t++)
            {
                weights[t] = new Integer(temps[t]);
            }

            // initialize the pool for memcache servers  
            SockIOPool pool = SockIOPool.getInstance();
            pool.setServers(serverlist);
            pool.setWeights(weights);
            pool.setInitConn(initialConnections);
            pool.setMinConn(minSpareConnections);
            pool.setMaxConn(maxSpareConnections);
            pool.setMaxIdle(maxIdleTime);
            pool.setMaxBusyTime(maxBusyTime);
            pool.setMaintSleep(maintThreadSleep);
            pool.setSocketTO(socketTimeOut);
            pool.setSocketConnectTO(socketConnectTO);
            pool.setNagle(nagleAlg);
            pool.setHashingAlg(SockIOPool.NEW_COMPAT_HASH);
            pool.initialize();
        }

    }

    private void destory()
    {
        if (this.cacheCluster)
        {
            SockIOPool.getInstance().shutDown();
        }
    }

}
 