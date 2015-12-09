/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.Course;
import persistence.Instructor;

/**
 *
 * @author karensantos
 */
@Named(value = "courseBean")
@SessionScoped
public class CourseBean implements Serializable {

    private String courseCode;
    private String name;
    private String description;
    private int minTeamMembers;
    private int maxTeamMembers;
    private String addstatus;
    private Date deadline;
    private Instructor instructor;
    private boolean isTeamCreationOpen;
    private List<Course> lookupResults;
    @PersistenceContext(unitName = "TMSystemPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

     /**
     * Creates a new instance of CourseBean
     */
    public CourseBean() {
    }
    
    /**
     * @return the courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode the courseCode to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the minTeamMembers
     */
    public int getMinTeamMembers() {
        return minTeamMembers;
    }

    /**
     * @param minTeamMembers the minTeamMembers to set
     */
    public void setMinTeamMembers(int minTeamMembers) {
        this.minTeamMembers = minTeamMembers;
    }

    public int getMaxTeamMembers() {
        return maxTeamMembers;
    }

    public void setMaxTeamMembers(int program) {
        this.maxTeamMembers = program;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public boolean isIsTeamCreationOpen() {
        return isTeamCreationOpen;
    }

    public void setIsTeamCreationOpen(boolean isTeamCreationOpen) {
        this.isTeamCreationOpen = isTeamCreationOpen;
    }

    public String getAddstatus() {
        return addstatus;
    }

    public void setAddstatus(String addstatus) {
        this.addstatus = addstatus;
    }

    public void setLookupResults(List<Course> results) {
        this.lookupResults = results;
    }
    
    public List<Course> getLookupResults() {
        return lookupResults;
    }
    // show results if any
    public boolean getShowResults() {
        return (lookupResults != null) && !lookupResults.isEmpty();
    }
    // show message if no result
    public boolean getShowMessage() {
        return (lookupResults != null) && lookupResults.isEmpty();
    }
    
    /**
     * Add the Course to the database
     * @param actionEvent
     * @return 
     */
    public String addCourse(ActionEvent actionEvent) {
        Course course = new Course(courseCode, name, description, minTeamMembers, maxTeamMembers, deadline, instructor);
       
        try {
           persist(course); 
           String msg = "Course Added Successfully";
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
           FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);
           FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
           FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
        } catch(RuntimeException e) {
           String msg = "Error While Adding Course";
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
           FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);
        }
        return null;
    }
    
    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
}
