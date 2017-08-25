package bj.ats.devteam.afin.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
public class Solutions implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private ZonedDateTime registring;
    @OneToOne
    private Users users ;
    public Solutions() {
    }

    public Long getId() {
        return id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ZonedDateTime getRegistring() {
        return registring;
    }

    public void setRegistring(ZonedDateTime registring) {
        this.registring = registring;
    }
}
