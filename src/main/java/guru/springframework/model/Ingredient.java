package guru.springframework.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Document
public class Ingredient {

    private String Id = UUID.randomUUID().toString();
    private String description;
    private BigDecimal amount;

    @DBRef
    private UnitOfMeasure uom;
    // No cascading type here. default action is none.
    private Recipe recipe;

    public Ingredient() {
    }

    // Added a helper constructor for this one so I can take in ingredient
    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }


    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }

}
