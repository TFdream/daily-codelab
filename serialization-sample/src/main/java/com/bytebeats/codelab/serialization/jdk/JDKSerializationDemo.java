package com.bytebeats.codelab.serialization.jdk;

import com.bytebeats.codelab.serialization.model.User;
import com.bytebeats.codelab.serialization.util.IoUtils;

import java.io.*;
import java.util.Arrays;

public class JDKSerializationDemo {

	public static void main(String[] args) {
		
		User user = new User();
		user.setId(1);
        user.setName("hollis");
        user.setGender("male");
        user.setAge(23);
        user.setHobby(Arrays.asList("Music", "Basketball"));
        System.out.println(user);
        
        File objectFile = new File("user.bin");
        
		//Write Obj to File
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(objectFile));
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtils.closeQuietly(oos);
        }
        
        //Read Obj from File
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(objectFile));
            User newUser = (User) ois.readObject();
            System.out.println(newUser);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            IoUtils.closeQuietly(ois);
        }
	}

}
