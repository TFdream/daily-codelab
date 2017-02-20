package com.bytebeats.codelab.collection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-20 13:49
 */
public class WeakHashMapDemo {

    public static void main(String[] args) {

        String k1 = "a";
        String k2 = "b";

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(k1, "aaa");
        hashMap.put(k2, "bbb");

        Map<String, String> weakMap = new WeakHashMap<>();
        weakMap.put(k1, "aaa");
        weakMap.put(k2, "bbb");

        hashMap.remove(k1);

        System.gc();

        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry en = (Map.Entry) it.next();
            System.out.println("hashMap:"+en.getKey()+":"+en.getValue());
        }

        it = weakMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry en = (Map.Entry) it.next();
            System.out.println("weakMap:"+en.getKey()+":"+en.getValue());

        }
    }
}
