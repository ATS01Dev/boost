package bj.ats.devteam.afin.Entity;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Teacher extends Users implements Serializable{

    public Teacher() {
    }
    public Teacher(String names, String surnames){
        super(names,surnames);
    }

}
