package bj.ats.devteam.afin.Repository;

import bj.ats.devteam.afin.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
