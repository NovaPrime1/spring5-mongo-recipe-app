package guru.springframework.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface ImageService {

    void saveImageFile(String recipeId, MultipartFile file);

}
