package bj.ats.devteam.afin.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;
@Entity
public class Discussion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String ask;
    private String username ;
    @OneToMany
    private Set<Solutions> solutions;
    private ZonedDateTime registering;

    public Discussion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public Set<Solutions> getSolutions() {
        return solutions;
    }

    public void setSolutions(Set<Solutions> solutions) {
        this.solutions = solutions;
    }

    public ZonedDateTime getRegistering() {
        return registering;
    }

    public void setRegistering(ZonedDateTime registering) {
        this.registering = registering;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
