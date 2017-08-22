package bj.ats.devteam.afin.Repository;

import bj.ats.devteam.afin.Entity.HwQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<HwQuestion, Long> {
}
