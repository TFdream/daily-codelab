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
import java.util.List;

public class KryoListSerializationDemo {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		List<Author> list = new ArrayList<>();
		for(int i=0;i<3;i++){
			Author author = new Author();
			author.setName("Ricky P"+i);
			author.setGender("male");
			author.setAge(25+i);
			author.setDesc("No zuo no die!");
			
			ArrayList<String> tag = new ArrayList<>();
			tag.add("IT");
			tag.add("屌丝");
			author.setTag(tag);
			
			list.add(author);
		}
		
		System.out.println(list);
		
		File file = new File("author_list.bin");

		Kryo kryo = new Kryo();
		// Write Obj to File
		Output output = null;
		try {
			output = new Output(new FileOutputStream(file));
			kryo.writeObject(output, list);
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
			List<Author> author_list = kryo.readObject(input, ArrayList.class);
			System.out.println(author_list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (KryoException e) {
			e.printStackTrace();
		}finally{
			IoUtils.closeQuietly(input);
		}
	}

}
