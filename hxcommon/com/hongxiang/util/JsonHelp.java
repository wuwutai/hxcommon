package com.hongxiang.util;

import com.alibaba.fastjson.JSON;

public class JsonHelp {
    
    /**
     * 搴忓垪鍖�
     * @author 椹織閾�
     * @param 瀵硅薄
     * */
    public static <T> String JsonSerializer(T obj){
        return JSON.toJSONString(obj);
    }
    
    /**
     * 鍙嶅簭鍒楀寲
     * */
    public static <T> T JsonFSerializer(String json,Class<T> clz){
        try {
            return JSON.parseObject(json,clz);
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        return null;
    }
}
 