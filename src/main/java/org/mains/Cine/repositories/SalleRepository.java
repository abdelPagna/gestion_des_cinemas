package org.mains.Cine.repositories;

import java.util.Optional;

import org.mains.Cine.domain.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalleRepository extends JpaRepository<Salle, String >{

	Optional<Salle> findByNameIgnoreCase(String name);

}
