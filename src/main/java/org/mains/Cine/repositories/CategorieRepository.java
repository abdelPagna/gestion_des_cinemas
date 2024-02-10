package org.mains.Cine.repositories;

import java.util.Optional;

import org.mains.Cine.domain.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, String>{

	Optional<Categorie> findByNameIgnoreCase(String name);

}
