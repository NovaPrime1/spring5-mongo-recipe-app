package guru.springframework.services;

import guru.springframework.model.Recipe;
import guru.springframework.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile (String recipeId, MultipartFile file ){

        try{
            Recipe recipe = recipeRepository.findById(recipeId).get();

            // convert it to a byte object because the multipart is going to return a primitive and need to convert it to a wrapper class byObject
            // ( hibernate recommendation to use the boxing object. Primitives can't be null)
            Byte[] byteObjects = new Byte[file.getBytes().length];

            // Set up an iterator to iterate over the array of byte objects
            int i = 0;

            // convert from a java primitive to a byte object. No autoboxing for arrays inside of java.
            for (byte b: file.getBytes()){
                byteObjects[i++] = b;
            }

            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            //todo handle exceptions better.
            log.error("error occurred", e);

            e.printStackTrace();
        }
    }


}
