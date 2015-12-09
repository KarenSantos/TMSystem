/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import javax.persistence.EntityManager;

/**
 *
 * @author karensantos
 */
public class DBHelper {
    public static User findUser(EntityManager em,String id) {
        User u = em.find(User.class, id);
        return u;
    }
    
    public static Course findCourse(EntityManager em,String id) {
        Course c = em.find(Course.class, id);
        return c;
    }
    
    public static Team findTeam(EntityManager em,String id) {
        Team t = em.find(Team.class, id);
        return t;
    }
}
