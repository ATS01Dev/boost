package bj.ats.devteam.afin.Repository;

import bj.ats.devteam.afin.Entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information, Long> {
}
