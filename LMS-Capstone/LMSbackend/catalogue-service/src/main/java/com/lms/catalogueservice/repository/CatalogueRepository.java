package com.lms.catalogueservice.repository;

import com.lms.catalogueservice.model.Catalogue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CatalogueRepository extends JpaRepository<Catalogue, String> {
    List<Catalogue> findByDeletedFalse();
    Optional<Catalogue> findByTitleAndAuthor(String title, String author);
}
