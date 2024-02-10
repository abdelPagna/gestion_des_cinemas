package org.mains.Cine.repositories;

import java.util.Optional;

import org.mains.Cine.domain.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, String >{

	Optional<Cinema> findByNameIgnoreCase(String name);

}
