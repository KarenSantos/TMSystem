/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.Course;
import persistence.DBHelper;
import persistence.Instructor;

/**
 *
 * @author karensantos
 */
@Named(value = "tmsystem")
@RequestScoped
public class TMSystem {
    
    @PersistenceContext
    EntityManager em;
    
    public List<Course> getAllCourses(){
        return DBHelper.findAllCourses(em);
    }
    
    public List<Course> getInstructorCourses(Instructor instructor){
        return instructor.getCourses();
    }
}
