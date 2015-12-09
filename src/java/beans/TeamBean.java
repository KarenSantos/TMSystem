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
import persistence.Student;
import persistence.Team;

/**
 *
 * @author karensantos
 */
@Named(value = "teamBean")
@SessionScoped
public class TeamBean implements Serializable {

    private String teamId;
    private String name;
    private Date creationDate;
    private int minTeamMembers;
    private int maxTeamMembers;
    private Student liaison;
    private String addstatus;
    private boolean isComplete;
    private List<Team> lookupResults;
    @PersistenceContext(unitName = "TMSystemPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    /**
     * Creates a new instance of TeamBean
     */
    public TeamBean() {
    }

    /**
     * @return the teamId
     */
    public String getTeamId() {
        return teamId;
    }

    /**
     * @param teamId the teamId to set
     */
    public void setTeamId(String teamId) {
        this.teamId = teamId;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date deadline) {
        this.creationDate = deadline;
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

    public Student getLiaison() {
        return liaison;
    }

    public void setLiaison(Student liaison) {
        this.liaison = liaison;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public String getAddstatus() {
        return addstatus;
    }

    public void setAddstatus(String addstatus) {
        this.addstatus = addstatus;
    }

    public void setLookupResults(List<Team> results) {
        this.lookupResults = results;
    }

    public List<Team> getLookupResults() {
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
     * Add the Team to the database
     *
     * @param actionEvent
     * @return
     */
    public String createTeam(ActionEvent actionEvent) {
        Team team = new Team(teamId, name, creationDate, minTeamMembers, maxTeamMembers, liaison);

        try {
            persist(team);
            String msg = "Course Added Successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
            FacesContext.getCurrentInstance().getExternalContext()
                    .getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
        } catch (RuntimeException e) {
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
