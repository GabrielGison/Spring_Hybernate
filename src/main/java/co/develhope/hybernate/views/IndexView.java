package co.develhope.hybernate.views;
import co.develhope.hybernate.services.PhraseService;

public class IndexView {

    public IndexView(PhraseService service, Integer id){
        String phrase = service.getPhrase(id).getPhrase();
        String author = service.getPhrase(id).getAuthor();
        String htmlResponse = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body style=\"background-color:black;\">\n" +
              "<div style=\"background-color:black;color:white;padding:20px;\">\n" +
                "  <h2>" + phrase + "</h2>\n" +
                "  <p>" + author + "</p>\n" +
                "</div> \n" +
               "</body>\n" +
                "</html>\n";
        html = htmlResponse;
    }
    private String html;

    public String getHtml(){

        html = html + "";
        return html; }
}
