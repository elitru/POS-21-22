package at.eliastrummer.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateError {
    private Account account;
    private String errorMsg;
    private boolean error;
}
