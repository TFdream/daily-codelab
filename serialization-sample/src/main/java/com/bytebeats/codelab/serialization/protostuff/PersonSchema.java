package com.bytebeats.codelab.serialization.protostuff;

import com.bytebeats.codelab.serialization.model.Person;
import io.protostuff.Input;
import io.protostuff.Output;
import io.protostuff.Schema;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-04-22 14:51
 */
public class PersonSchema implements Schema<Person> {

    private static final HashMap<String,Integer> fieldMap = new HashMap<>();
    static
    {
        fieldMap.put("email", 1);
        fieldMap.put("firstName", 2);
        fieldMap.put("lastName", 3);
        fieldMap.put("friends", 4);
    }

    @Override
    public String getFieldName(int number) {
        switch(number) {
            case 1:
                return "email";
            case 2:
                return "firstName";
            case 3:
                return "lastName";
            case 4:
                return "friends";
            default:
                return null;
        }
    }

    @Override
    public int getFieldNumber(String name) {
        Integer number = fieldMap.get(name);
        return number == null ? 0 : number.intValue();
    }

    @Override
    public boolean isInitialized(Person person) {
        return false;
    }

    @Override
    public Person newMessage() {
        return new Person();
    }

    @Override
    public String messageName() {
        return Person.class.getSimpleName();
    }

    @Override
    public String messageFullName() {
        return Person.class.getSimpleName();
    }

    @Override
    public Class<? super Person> typeClass() {
        return Person.class;
    }

    @Override
    public void mergeFrom(Input input, Person person) throws IOException {

    }

    @Override
    public void writeTo(Output output, Person person) throws IOException {

    }
}
