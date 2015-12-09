/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import persistence.Instructor;
import persistence.Student;
import persistence.User;
import persistence.Section;

/**
 *
 * @author ssome
 */
@Named(value = "userInfoBean")
@RequestScoped
public class UserInfoBean implements Serializable {
    
    private String emailId;
    private String password;
    private String nameLast;
    private String nameGiven;
    private String program;
    private Section section;
    private String addstatus;
    private List<User> lookupResults;
    @PersistenceContext(unitName = "TMSystemPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    /**
     * Creates a new instance of UserProfileBean
     */
    public UserInfoBean() {
    }
    
    /**
     * @return the emailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId the emailId to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the nameLast
     */
    public String getNameLast() {
        return nameLast;
    }

    /**
     * @param nameLast the nameLast to set
     */
    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    /**
     * @return the nameGiven
     */
    public String getNameGiven() {
        return nameGiven;
    }

    /**
     * @param nameGiven the nameGiven to set
     */
    public void setNameGiven(String nameGiven) {
        this.nameGiven = nameGiven;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    /**
     * Sets all attributes of a UserProfileBean from a UserProfile
     * @param user The UserProfile
     */
    public void setAll(User user){
        this.emailId = user.getEmailId();
        this.password = user.getPassword();
        this.nameLast = user.getNameLast();
        this.nameGiven = user.getNameGiven();

        //TODO load pictures
    }
    
    public void clearAll(){
        this.emailId = "";
        this.password = "";
        this.nameLast = "";
        this.nameGiven = "";
    }

    public String getAddstatus() {
        return addstatus;
    }

    public void setAddstatus(String addstatus) {
        this.addstatus = addstatus;
    }

    public void setLookupResults(List<User> results) {
        this.lookupResults = results;
    }
    
    public List<User> getLookupResults() {
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
     * Add the student to the database
     * @param actionEvent
     * @return 
     */
    public String doRegisterStudent(ActionEvent actionEvent) {
        User user = new Student(emailId, password, nameLast, nameGiven, program, section);
       
        try {
           persist(user); 
           String msg = "User Profile Created Successfully";
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
           FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);
           FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
           FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
        } catch(RuntimeException e) {
           String msg = "Error While Creating User Profile";
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
           FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);
        }
        return null;
    }
    
    /**
     * Add the instructor to the database
     * @param actionEvent
     * @return 
     */
    public String doRegisterInstructor(ActionEvent actionEvent) {
        User user = new Instructor(emailId, password, nameLast, nameGiven);
       
        try {
           persist(user); 
           addstatus = "User Profile Created Successfully";
//           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
//           FacesContext.getCurrentInstance().getExternalContext()
//                .getFlash().setKeepMessages(true);
//           FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//           FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
        } catch(RuntimeException e) {
           addstatus = "Error While Creating User Profile";
//           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
//           FacesContext.getCurrentInstance().getExternalContext()
//                .getFlash().setKeepMessages(true);
        }
        return null;
    }

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
}
