package guru.springframework.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;



@Getter
@Setter
public class Notes {

    @Id
    private String Id;
    private String recipeNotes;

}

//    private Recipe recipe;