package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.model.Recipe;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */

public interface RecipeService {

    Flux<Recipe> getRecipes();

    Mono <Recipe> findById(String id);

    Mono <RecipeCommand> findCommandById(String id);

    Mono <RecipeCommand> saveRecipeCommand(RecipeCommand command);

    Mono <Void> deleteById(String idToDelete);

//    Flux<Object> listRecipes();
}
