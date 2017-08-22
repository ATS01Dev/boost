package org.ats.devteam.afin.Entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;
@Entity
public class Student extends Users implements Serializable{
    @OneToMany
    private Set<Course> courses;
    @OneToMany
    private Set<Homework> homework;
    public Student() {
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Homework> getHomework() {
        return homework;
    }

    public void setHomework(Set<Homework> homework) {
        this.homework = homework;
    }
}
