package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.PrimitiveIterator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String Id;
    private String username;
    private String email;
    private String Password;
}
