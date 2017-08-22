package org.ats.devteam.afin.Repository;

import org.ats.devteam.afin.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
