package at.kaindorf.trummer.springburgerapp.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ingredient {
    @Id
    private String id;
    private String name;
    private Type type;

    public enum Type {
        PATTY,
        VEGGIE,
        CHEESE;
    }
}
