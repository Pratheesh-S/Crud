package com.diatoz.task1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "college")
public class CollegeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer collegeId;
    private String collegeName;
    private String collegeLocation;

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCollegeLocation() {
        return collegeLocation;
    }

    public void setCollegeLocation(String collegeLocation) {
        this.collegeLocation = collegeLocation;
    }

}
