/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import beans.UserInfoBean;
import beans.CourseBean;
import beans.TeamBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.DBHelper;
import persistence.User;
import persistence.Course;
import persistence.Team;

/**
 *
 * @author karensantos
 */
@Named(value = "lookupControl")
@RequestScoped
public class LookupControl implements Serializable {

    @Inject
    private UserInfoBean userInfoBean;
    @Inject
    private CourseBean courseBean;
    @Inject
    private TeamBean teamBean;
    @PersistenceContext
    EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    /**
     * Creates a new instance of UserControl
     */
    public LookupControl() {
    }

    public void lookupUser() {
        List<User> results = new ArrayList();
        if (!"".equals(userInfoBean.getEmailId())) {
            // lookup by id
            results = getUserByEmailId(em, userInfoBean);
        }
        userInfoBean.setLookupResults(results);
    }

    /**
     * Find a user by id and check if any that the other fields are valid
     */
    private List<User> getUserByEmailId(EntityManager em, UserInfoBean userInfoBean) {
        List<User> result = new ArrayList<>();
        User user = DBHelper.findUser(em, userInfoBean.getEmailId());
        if (user != null && user.matches(userInfoBean)) {
            result.add(user);
        }
        return result;
    }
    
    public void lookupCourse() {
        List<Course> results = new ArrayList();
        if (!"".equals(courseBean.getCourseCode())) {
            // lookup by id
            results = getCourseByCode(em, courseBean);
        }
        courseBean.setLookupResults(results);
    }

    /**
     * Find a course by id and check if any that the other fields are valid
     */
    private List<Course> getCourseByCode(EntityManager em, CourseBean courseBean) {
        List<Course> result = new ArrayList<>();
        Course course = DBHelper.findCourse(em, courseBean.getCourseCode());
        if (course != null && course.matches(courseBean)) {
            result.add(course);
        }
        return result;
    }

    public void lookupTeam() {
        List<Team> results = new ArrayList();
        if (!"".equals(teamBean.getTeamId())) {
            // lookup by id
            results = getTeamById(em, teamBean);
        }
        teamBean.setLookupResults(results);
    }

    /**
     * Find a team by id and check if any that the other fields are valid
     */
    private List<Team> getTeamById(EntityManager em, TeamBean teamBean) {
        List<Team> result = new ArrayList<>();
        Team team = DBHelper.findTeam(em, teamBean.getTeamId());
        if (team != null && team.matches(teamBean)) {
            result.add(team);
        }
        return result;
    }
}
