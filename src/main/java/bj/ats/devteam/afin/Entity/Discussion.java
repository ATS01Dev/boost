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
    @OneToMany
    private Set<Solutions> solutions;
    private ZonedDateTime registring;

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

    public ZonedDateTime getRegistring() {
        return registring;
    }

    public void setRegistring(ZonedDateTime registring) {
        this.registring = registring;
    }
}
