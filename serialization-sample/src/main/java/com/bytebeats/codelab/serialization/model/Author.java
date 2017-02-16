package com.bytebeats.codelab.serialization.model;

import java.util.ArrayList;
import java.util.List;

public class Author {
	private String name;
	private String gender;
	private int age;
	private String desc;
	private ArrayList<String> tag;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<String> getTag() {
		return tag;
	}
	public void setTag(ArrayList<String> tag) {
		this.tag = tag;
	}
	
	@Override
	public String toString() {
		return "Author [name=" + name + ", gender=" + gender + ", age=" + age
				+ ", desc=" + desc + ", tag=" + tag + "]";
	}
	
}
