package co.develhope.hybernate.DTO;
import lombok.Data;

@Data
public class PhraseDTO {

    private Integer id;

    private String line1 = "";

    private String line2 = "";

    private String line3 = "";

    private String line4 = "";

    private String line5 = "";

    private String author = "Unknown";

}
