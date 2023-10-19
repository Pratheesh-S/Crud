package com.diatoz.task1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    private String studentName;
    private String studentLocation;

    @ManyToOne
    @JoinColumn(name = "collegeId",referencedColumnName = "collegeId")
    private CollegeEntity college;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentLocation() {
        return studentLocation;
    }

    public void setStudentLocation(String studentLocation) {
        this.studentLocation = studentLocation;
    }

    public CollegeEntity getCollege() {
        return college;
    }

    public void setCollege(CollegeEntity college) {
        this.college = college;
    }
}
