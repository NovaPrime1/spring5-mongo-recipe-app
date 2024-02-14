package guru.springframework.repository;

import guru.springframework.model.Category;
import guru.springframework.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



public interface UnitOfMeasureRepository extends CrudRepository <UnitOfMeasure, String> {

    Optional<UnitOfMeasure> findByDescription(String description);

}
