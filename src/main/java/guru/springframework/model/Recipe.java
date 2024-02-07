package guru.springframework.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Document
public class Recipe {

//    Note all the hibernate and JPA relational database annotation went away for MongoDB. Now simple POJO


    @Id
    private String id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<Ingredient> ingredients = new HashSet<>(); // From recipe to ingredient one of a two way relationship also initializing to a new hashset to avoid a NPE
    private Byte[] image;
    private Difficulty difficulty;
    private Notes notes;

    @DBRef

    private Set<Category> categories = new HashSet<>();  // Same as above initializing to avoid a NPE ( little trick just in case you forget to set it.


    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
        }
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
