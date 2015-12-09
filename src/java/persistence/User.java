/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import beans.UserInfoBean;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author karensantos
 */
@Entity
@Table(name="UserTable7997484")
public abstract class User implements Serializable {
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
    private String emailId;
    private String password;
    private String nameLast;
    private String nameGiven;
    
    public User(){
    }
    
    public User(String emailId, String password, String nameLast, 
            String nameGiven) {
        this.emailId = emailId;
        this.password = password;
        this.nameLast = nameLast;
        this.nameGiven = nameGiven;
    }
    
    public String getEmailId() {
        return emailId;
    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emailId != null ? emailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.emailId == null && other.emailId != null) || (this.emailId != null && !this.emailId.equals(other.emailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.UserProfile[ emailId=" + emailId + " ]";
    }

    /**
     * 
     * @param userInfoBean
     * @return true if this User matches the userProfileBean bean
     */
    public boolean matches(UserInfoBean userInfoBean) {
        if (!"".equals(userInfoBean.getEmailId()) && !this.getEmailId().trim().equals(userInfoBean.getEmailId().trim())) {
            return false;
        } 
        return true;
    }
}
