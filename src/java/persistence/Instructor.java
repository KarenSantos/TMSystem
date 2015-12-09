/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author karensantos
 */
@Entity
@Table(name = "InstructorTable7997484")
public class Instructor extends User implements Serializable{
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
    
    public Instructor(){}
    
    public Instructor(String emailId, String password, String nameLast, String nameGiven){
        super(emailId, password, nameLast, nameGiven);
    }
}
