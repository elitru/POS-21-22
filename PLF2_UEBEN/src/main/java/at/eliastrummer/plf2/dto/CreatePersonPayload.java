package at.eliastrummer.plf2.dto;

import at.eliastrummer.plf2.utils.JsonDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CreatePersonPayload {
    private String name;
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private LocalDate birthdate;
    private List<String> hobbies = new ArrayList<>();
}
