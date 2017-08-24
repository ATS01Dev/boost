package bj.ats.devteam.afin.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Set;
@Entity
public class Course implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String domain;
    private String title;
    private ZonedDateTime opening;
    private ZonedDateTime closing;
    private ZonedDateTime registring ;
    @OneToMany
    private Set<CourseModule> courseModules;
    @OneToMany
    private Set<Teacher> teachers;

    public Course() {
    }

    public Course(String domain, String title) {
        this.domain = domain;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<CourseModule> getCourseModules() {
        return courseModules;
    }

    public void setCourseModules(Set<CourseModule> courseModules) {
        this.courseModules = courseModules;
    }

    public ZonedDateTime getOpening() {
        return opening;
    }

    public void setOpening(ZonedDateTime opening) {
        this.opening = opening;
    }

    public ZonedDateTime getClosing() {
        return closing;
    }

    public void setClosing(ZonedDateTime closing) {
        this.closing = closing;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public ZonedDateTime getRegistring() {
        return registring;
    }

    public void setRegistring(ZonedDateTime registring) {
        this.registring = registring;
    }
}
