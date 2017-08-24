package bj.ats.devteam.afin.Repository;

import bj.ats.devteam.afin.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query("SELECT c from Course c where c.title like :x")
    public List<Course> findByTitle(@Param("x") String kw);

}
