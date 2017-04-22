package com.bytebeats.codelab.serialization.protostuff;

import com.bytebeats.codelab.serialization.model.Address;
import com.bytebeats.codelab.serialization.model.Person;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-04-22 15:05
 */
public class RuntimeSchemaDemo {

    public static void main(String[] args) {

        Person person = new Person("ricky", "feng", "ricky_feng@163.com");
        person.setFriends(Arrays.asList("Paul", "Kobe", "James"));
        person.setAddress(new Address("湖北省", "武汉市", "武昌区", "珞喻路"));
        Map<String, String> tag = new HashMap<>();
        tag.put("aa", "abc");
        person.setTag(tag);

        Schema<Person> schema = RuntimeSchema.getSchema(Person.class);

        LinkedBuffer buffer = LinkedBuffer.allocate();
        byte[] protostuff = null;
        // 序列化
        try {
            protostuff = ProtostuffIOUtil.toByteArray(person, schema, buffer);
            System.out.println("bytes len:"+protostuff.length);
        } finally {
            buffer.clear();
        }

        // 反序列化
        Person p = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(protostuff, p, schema);
        System.out.println(p);
    }
}
