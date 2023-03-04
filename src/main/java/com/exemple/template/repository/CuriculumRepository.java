package com.exemple.template.repository;

import com.exemple.template.entities.Curiculum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuriculumRepository extends JpaRepository<Curiculum, Long> {
    Page<Curiculum> findByNomContains(String kw, Pageable pageable);

}
