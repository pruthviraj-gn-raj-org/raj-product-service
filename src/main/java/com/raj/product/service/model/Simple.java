package com.raj.product.service.model;

public class Simple {

	private int id;
	private String name;
	private String gender;
	private int port;
	
	
	
	public Simple(int id, String name, String gender, int port) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.port = port;
	}



	@Override
	public String toString() {
		return "Simple [id=" + id + ", name=" + name + ", gender=" + gender + ", port=" + port + "]";
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



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



	public int getPort() {
		return port;
	}



	public void setPort(int port) {
		this.port = port;
	}



	public Simple() {
		
	}
	
}
