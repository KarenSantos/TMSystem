/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

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
@Table(name = "TeamTable7997484")
class Team implements Serializable {

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
    private String teamId;
    private String name;
    private Date creationDate;
    private int minTeamMembers;
    private int maxTeamMembers;
    private boolean isComplete;
    private List<Student> students;
    private List<Student> wantToBeStudents;
    private Student liaison;
    
    public Team(){}
    
    public Team(String teamId, String name, Date creationDate, int min, int max, Student liaison){
        this.teamId = teamId;
        this.name = name;
        this.creationDate = creationDate;
        this.minTeamMembers = min;
        this.maxTeamMembers = max;
        this.liaison = liaison;
        this.students = new ArrayList<>();
        this.wantToBeStudents = new ArrayList<>();
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public boolean isIsComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getWantToBeStudents() {
        return wantToBeStudents;
    }

    public void setWantToBeStudents(List<Student> wantToBeStudents) {
        this.wantToBeStudents = wantToBeStudents;
    }

    public Student getLiaison() {
        return liaison;
    }

    public void setLiaison(Student liaison) {
        this.liaison = liaison;
    }
    
    
}
