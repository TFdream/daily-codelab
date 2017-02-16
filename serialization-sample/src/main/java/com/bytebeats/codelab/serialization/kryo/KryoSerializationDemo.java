package com.bytebeats.codelab.serialization.kryo;

import com.bytebeats.codelab.serialization.model.Author;
import com.bytebeats.codelab.serialization.util.IoUtils;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class KryoSerializationDemo {

	public static void main(String[] args) {

		Author author = new Author();
		author.setName("Ricky");
		author.setGender("male");
		author.setAge(26);
		author.setDesc("No zuo no die!");
		
		ArrayList<String> tag = new ArrayList<>();
		tag.add("IT");
		tag.add("屌丝");
		author.setTag(tag);
		
		System.out.println(author);
		
		File file = new File("author.bin");

		Kryo kryo = new Kryo();
		// Write Obj to File
		Output output = null;
		try {
			output = new Output(new FileOutputStream(file));
			kryo.writeObject(output, author);
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (KryoException e) {
			e.printStackTrace();
		}finally{
			IoUtils.closeQuietly(output);
		}

		// Read Obj from File
		Input input = null;
		try {
			input = new Input(new FileInputStream(file));
			Author newAuthor = kryo.readObject(input, Author.class);
			System.out.println(newAuthor);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (KryoException e) {
			e.printStackTrace();
		}finally{
			IoUtils.closeQuietly(input);
		}
	}

}
