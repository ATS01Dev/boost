package bj.ats.devteam.afin.Repository;

import bj.ats.devteam.afin.Entity.ModuleMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<ModuleMaterial,Long> {
}
