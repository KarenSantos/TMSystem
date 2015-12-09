/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import beans.CourseBean;
import beans.UserInfoBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author karensantos
 */
@Entity
@Table(name = "CourseTable7997484")
public class Course implements Serializable {
    static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    
    @Id
    private String courseCode;
    private String name;
    private String description;
    private int minTeamMembers;
    private int maxTeamMembers;
    private Date deadline;
    private boolean isTeamCreationOpen;
    private List<Team> teams;
    private List<Student> students;
    private Instructor instructor;
    
    public Course(){}
    
    public Course(String courseCode, String name, String description, int min, int max, Date deadline, Instructor instructor){
        this.courseCode = courseCode;
        this.name = name;
        this.description = description;
        this.minTeamMembers = min;
        this.maxTeamMembers = max;
        this.deadline = deadline;
        this.isTeamCreationOpen = false;
        this.teams = new ArrayList<>();
        this.instructor = instructor;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinTeamMembers() {
        return minTeamMembers;
    }

    public void setMinTeamMembers(int minTeamMembers) {
        this.minTeamMembers = minTeamMembers;
    }

    public int getMaxTeamMembers() {
        return maxTeamMembers;
    }

    public void setMaxTeamMembers(int maxTeamMembers) {
        this.maxTeamMembers = maxTeamMembers;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isIsTeamCreationOpen() {
        return isTeamCreationOpen;
    }

    public void setIsTeamCreationOpen(boolean isTeamCreationOpen) {
        this.isTeamCreationOpen = isTeamCreationOpen;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    
    /**
     * @param courseBean
     * @return true if this Course matches the courseBean bean
     */
    public boolean matches(CourseBean courseBean) {
        if (!"".equals(courseBean.getCourseCode()) && !this.getCourseCode().trim().equals(courseBean.getCourseCode().trim())) {
            return false;
        } 
        return true;
    }
    
}
