package guru.springframework.repository.reactive;

import guru.springframework.model.Category;
import guru.springframework.model.UnitOfMeasure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, String> {

    Mono<Category> findByDescription(String description);
}
