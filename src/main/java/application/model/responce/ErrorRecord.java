package application.model.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorRecord {
    private HttpStatus status;
    private String message;

//    @JsonGetter("status")
//    public String getTheName() {
//        return name;
//    }
}
