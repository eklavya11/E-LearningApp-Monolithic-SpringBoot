package com.onlineeducationsystem.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class TestEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	private String duration;

	private int marks;

	// default constructor
	public TestEntity() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "TestEntity [id=" + id + ", title=" + title + ", duration=" + duration + ", marks=" + marks+ "]";
	}

	public TestEntity(int id, String title, String duration, int marks) {
		super();
		this.id = id;
		this.title = title;
		this.duration = duration;
		this.marks = marks;
	}

}
