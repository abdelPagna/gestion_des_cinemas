package org.mains.Cine.repositories;

import java.util.Optional;

import org.mains.Cine.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, String >{

	Optional<Film> findByTitreIgnoreCase(String titre);

}
