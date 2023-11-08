package org.tp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.tp.domain.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
