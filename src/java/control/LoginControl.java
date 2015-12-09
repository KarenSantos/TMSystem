/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import beans.UserInfoBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.DBHelper;
import persistence.Instructor;
import persistence.Student;
import persistence.User;

/**
 *
 * @author karensantos
 */
@Named(value = "loginControl")
@RequestScoped
public class LoginControl {

    private String emailId;
    private String password;
    private boolean submited;
    private boolean validated;
    private String status;
    @Inject
    private UserInfoBean userInfoBean;
    @PersistenceContext
    EntityManager em;

    public String entrar() {
        return "index";
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getShowMessage() {
        return (submited && !validated);
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public String login() {
        User user = DBHelper.findUser(em, emailId);

        if (user != null) {
            if (password.equals(user.getPassword())) {
                if (user instanceof Instructor) {
                    return "instructorMenu";
                } else if (user instanceof Student) {
                    return "studentMenu";
                }
            }
        }
        validated = true;
        FacesMessage msg = new FacesMessage("errado");
        FacesContext.getCurrentInstance().addMessage("status", msg);
        status = "incorrect";
        return "unauthorized";
    }
}
