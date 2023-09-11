package com.onlineeducationsystem.app.entity;
import java.time.LocalDate;

 

 

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

 

 

import javax.persistence.GenerationType;

 

 

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

 

 

import javax.persistence.OneToOne;

 

 

import com.fasterxml.jackson.annotation.JsonFormat;

 

 

 

@Entity
public class CourseEntity {

 

 

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int coursecode;
    private String title;
    private String description;
    private String duration;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String instructorname;
    

 

 

    public CourseEntity() {

 

 

    }

 

    public CourseEntity(int id, int coursecode, String title, String description, String duration, LocalDate startDate,
            LocalDate endDate, String instructorname) {
        super();
        this.id = id;
        this.coursecode = coursecode;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructorname = instructorname;
       
            }

 

 

 

    public String getDescription() {
        return description;
    }

 

 

 

 

 

 

    public void setDescription(String description) {
        this.description = description;
    }

 

 

 

 

 

 

    public LocalDate getStartDate() {
        return startDate;
    }

 

 

 

 

 

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

 

 

 

 

 

    public LocalDate getEndDate() {
        return endDate;
    }

 

 

 

 

 

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

 

 

 

    public int getId() {

 

 

        return id;

 

 

    }

 

 

 

    public void setId(int id) {

 

 

        this.id = id;

 

 

    }

 

 

 

    public int getCoursecode() {

 

 

        return coursecode;

 

 

    }

 

 

 

    public void setCoursecode(int coursecode) {

 

 

        this.coursecode = coursecode;

 

 

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

 

 

 

    

 

 

 

    public String getInstructorname() {

 

 

        return instructorname;

 

 

    }

 

 

 

    public void setInstructorname(String instructorname) {

 

 

        this.instructorname = instructorname;

 

 

    }
    

 

 

   



	@Override
    public String toString() {
        return "CourseEntity [id=" + id + ", coursecode=" + coursecode + ", title=" + title + ", description="
                + description + ", duration=" + duration + ", startDate=" + startDate + ", endDate=" + endDate
                + ", instructorname=" + instructorname + "]";
    }

 

 

 

}