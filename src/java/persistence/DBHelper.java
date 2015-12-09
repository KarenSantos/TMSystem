/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
    
    public static List findAllCourses(EntityManager em) {
        Query query = em.createQuery("SELECT i FROM Course i");
        return performQuery(query);
    }
    
    public static Team findTeam(EntityManager em,String id) {
        Team t = em.find(Team.class, id);
        return t;
    }
    
    private static List performQuery(final Query query) {
        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        List<Course> results = new ArrayList<>();
        results.addAll(resultList);
        return results;
    }
}
