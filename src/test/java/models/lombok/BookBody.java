package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookBody {
    private String author;
    private String id;
    private String name;
    private int year;
    private boolean IsElectronicBook;

    }


