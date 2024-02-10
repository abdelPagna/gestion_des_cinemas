package org.mains.Cine.repositories;

import org.mains.Cine.domain.ProjectionFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectionFilmRepository extends JpaRepository<ProjectionFilm, String >{


}
