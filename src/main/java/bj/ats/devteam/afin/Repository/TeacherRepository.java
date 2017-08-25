package bj.ats.devteam.afin.Repository;

import bj.ats.devteam.afin.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
   // @Query("SELECT c from Course c where c. ")
    //public List<Teacher> findTeacherCourse(@Param("x") Long id);
}
