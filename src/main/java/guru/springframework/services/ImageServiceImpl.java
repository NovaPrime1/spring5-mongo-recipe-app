package guru.springframework.services;

import guru.springframework.model.Recipe;
import guru.springframework.repository.reactive.RecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeReactiveRepository recipeReactiveRepository;

    public ImageServiceImpl(RecipeReactiveRepository recipeReactiveRepository) {
        this.recipeReactiveRepository = recipeReactiveRepository;
    }

    @Override
    public Mono <Void> saveImageFile(String recipeId, MultipartFile file) {

        Mono<Recipe> recipeMono = recipeReactiveRepository.findById(recipeId)
                .map(recipe -> {

                    // convert it to a byte object because the multipart is going to return a primitive and need to convert it to a wrapper class byObject
                    // ( hibernate recommendation to use the boxing object. Primitives can't be null)

                    Byte[] byteObjects = new Byte[0];
                    try {
                        // Set up an iterator to iterate over the array of byte objects
                        byteObjects = new Byte[file.getBytes().length];

                        int i = 0;

//                  for (int i = 0; i < 5; i++) {
//                        System.out.println(i);
//                    }
//                  convert from a java primitive to a byte object. No autoboxing for arrays inside of java.

                        for (byte b : file.getBytes()) {
                            byteObjects[i++] = b;
                        }

                        recipe.setImage(byteObjects);
                        return recipe;

                    } catch (IOException e) {
                        log.error("error occurred", e);
                        e.printStackTrace();
                        throw new RuntimeException(e);

                    }
                });

        recipeReactiveRepository.save(recipeMono.block()).block();

        return Mono.empty();

    }
}
