package com.hongxiang.context;

import java.util.HashMap;
import java.util.Map;

public class MethodContext {

    private Map<String, LocalVar> container;

    public MethodContext() {
        this.container = new HashMap<String, LocalVar>();
    }
    
    public void addLocalVar(String key,String type,Object value){
        this.container.put(key, new LocalVar(key,type,value));
    }
    
    public String getLocalType(String key) {
        return this.container.get(key) != null ? this.container.get(key).getType() : null;
    }
    
    public Object getLocalValue(String key) {
        return this.container.get(key) != null ? this.container.get(key).getValue() : null;
    }
    
    public void setLocalValue(String key,Object value){
        if(this.container.containsKey(key)){
            LocalVar localVar = this.container.get(key);
            localVar.setValue(value);
        }
    }
    
    public boolean containsLocalVar(String key) {
        return this.container.containsKey(key);
    }
    
    public void removeVar(String key){
         this.container.remove(key);
    }
    
}
 