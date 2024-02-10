package org.mains.Cine.repositories;

import java.util.Optional;

import org.mains.Cine.domain.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VilleRepository extends JpaRepository<Ville, String >{

	Optional<Ville> findByNameIgnoreCase(String name);

}
