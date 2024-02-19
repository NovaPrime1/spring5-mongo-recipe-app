package guru.springframework.repository.reactive;

import guru.springframework.model.Recipe;
import guru.springframework.model.UnitOfMeasure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
}
