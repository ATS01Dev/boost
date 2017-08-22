package org.ats.devteam.afin.Repository;

import org.ats.devteam.afin.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
