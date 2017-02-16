package com.bytebeats.codelab.serialization.kryo;

import com.bytebeats.codelab.serialization.model.Author;
import com.bytebeats.codelab.serialization.util.IoUtils;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class KryoFieldSerializerDemo {

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
		
		Kryo kryo = new Kryo();
	    FieldSerializer<Author> someClassSerializer = new FieldSerializer<Author>(kryo, Author.class);
	    CollectionSerializer listSerializer = new CollectionSerializer();
	    listSerializer.setElementClass(String.class, kryo.getSerializer(String.class));
	    listSerializer.setElementsCanBeNull(false);
	    someClassSerializer.getField("tag").setClass(ArrayList.class, listSerializer);
	    
	    kryo.register(Author.class, someClassSerializer);
	    
	    KryoFieldSerializerDemo demo = new KryoFieldSerializerDemo();
	    byte[] data = demo.serialize(kryo, author);
	    
	    System.out.println(data.length);
	    
	    System.out.println(demo.deserialize(kryo, data));
	    
	}

	public byte[] serialize(Kryo kryo, Author author) {

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

	public Author deserialize(Kryo kryo, byte[] data) {

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
