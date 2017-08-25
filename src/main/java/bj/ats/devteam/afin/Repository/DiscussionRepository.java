package bj.ats.devteam.afin.Repository;

import bj.ats.devteam.afin.Entity.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
}
