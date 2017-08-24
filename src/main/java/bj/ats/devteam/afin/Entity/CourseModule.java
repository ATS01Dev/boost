package bj.ats.devteam.afin.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
public class CourseModule implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String sommary;
    @OneToMany
    private Set<ModuleMaterial> moduleMaterials;
    @OneToMany
    private Set<Homework> homework;
    private ZonedDateTime opening;

    public CourseModule() {
    }

    public CourseModule(String title, String sommary) {
        this.title = title;
        this.sommary = sommary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSommary() {
        return sommary;
    }

    public void setSommary(String sommary) {
        this.sommary = sommary;
    }

    public Set<ModuleMaterial> getModuleMaterials() {
        return moduleMaterials;
    }

    public void setModuleMaterials(Set<ModuleMaterial> moduleMaterials) {
        this.moduleMaterials = moduleMaterials;
    }

    public ZonedDateTime getOpening() {
        return opening;
    }

    public void setOpening(ZonedDateTime opening) {
        this.opening = opening;
    }

    public Set<Homework> getHomework() {
        return homework;
    }

    public void setHomework(Set<Homework> homework) {
        this.homework = homework;
    }
}
