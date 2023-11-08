package org.tp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.tp.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
