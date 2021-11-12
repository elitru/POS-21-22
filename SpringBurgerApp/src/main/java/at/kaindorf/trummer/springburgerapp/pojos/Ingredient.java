package at.kaindorf.trummer.springburgerapp.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private String id;
    private String name;
    private Type type;

    public enum Type {
        PATTY,
        VEGGIE,
        CHEESE;
    }
}
