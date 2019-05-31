package com.trs.zhq.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreadLocalMap {
    private ThreadLocalMap(){};
    private static ThreadLocalMap threadLocalMap=new ThreadLocalMap();

    private ThreadLocal<Map<String, List>> threadLocap =new ThreadLocal();
    public static List getValue(String key){
        return threadLocalMap.threadLocap.get().get(key);
    }
    public static void SetValue(String key, Object value){
        synchronized (threadLocalMap){
            Map<String, List> map = threadLocalMap.threadLocap.get();
            if(map==null){
                map=new HashMap();
                List list=new ArrayList();
                list.add(value);
                map.put(key,list);
            }else {
                map = threadLocalMap.threadLocap.get();
                List list = map.get(key);
                if(list==null){
                    list=new ArrayList();
                    list.add(value);
                    map.put(key,list);
                }else{
                    list.add(value);
                }
            }
            threadLocalMap.threadLocap.set(map);
        }
    }
    public static void remove(){
        threadLocalMap.threadLocap.remove();
    }
}
