package com.bytebeats.code.serialization.kryo;

import com.bytebeats.code.serialization.model.Author;
import com.bytebeats.code.serialization.util.IoUtils;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class KryoByteSerializationDemo {

	private Kryo kryo = new Kryo();
	
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

		KryoByteSerializationDemo demo = new KryoByteSerializationDemo();
		
		byte[] data = demo.serialize(author);
		System.out.println(data.length);
		System.out.println(demo.deserialize(data));
	}

	public byte[] serialize(Author author) {

		// Write Obj to File
		Output output = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			output = new Output(baos);
			kryo.writeObject(output, author);
			output.flush();

			return baos.toByteArray();
		} catch (KryoException e) {
			e.printStackTrace();
		} finally {
			IoUtils.closeQuietly(output);
			IoUtils.closeQuietly(baos);
		}
		return null;
	}

	public Author deserialize(byte[] data) {

		// Read Obj from File
		Input input = null;
		try {
			input = new Input(new ByteArrayInputStream(data));
			return kryo.readObject(input, Author.class);
		} catch (KryoException e) {
			e.printStackTrace();
		} finally {
			IoUtils.closeQuietly(input);
		}
		
		return null;
	}

}
