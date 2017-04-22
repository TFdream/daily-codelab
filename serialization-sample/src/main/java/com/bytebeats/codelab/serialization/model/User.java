package com.bytebeats.codelab.serialization.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String password;
	private int age;
	private List<String> hobbies;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", age=" + age +
				", hobbies=" + hobbies +
				'}';
	}
}
