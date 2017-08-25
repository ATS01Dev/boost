package bj.ats.devteam.afin.Repository;

import bj.ats.devteam.afin.Entity.Course;
import bj.ats.devteam.afin.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query("SELECT c from Course c where :t member of c.teachers")
    public List<Course> findByTeachers(@Param("t") Teacher teacher);


}
