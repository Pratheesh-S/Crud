package com.diatoz.task1.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "student")
@Schema(title = "Student Record")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description ="Id will auto generated")
    private Integer studentId;
    private String studentName;
    private String studentLocation;

    @Temporal(TemporalType.DATE)
    private Date StudentJoinYear;

    private boolean graduated;

    @ManyToOne
    @JoinColumn(name = "collegeId",referencedColumnName = "collegeId")
    @Schema(description = "college data should be present in database")
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

    public Date getStudentJoinYear() {
        return StudentJoinYear;
    }

    public void setStudentJoinYear(Date studentJoinYear) {
        StudentJoinYear = studentJoinYear;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }
}
