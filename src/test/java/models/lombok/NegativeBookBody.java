package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NegativeBookBody {
    private String authors;
    private String names;
    private int years;
    private boolean electronicBooks;
    }


