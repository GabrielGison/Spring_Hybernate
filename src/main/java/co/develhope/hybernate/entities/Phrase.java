package co.develhope.hybernate.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "phrases")
public class Phrase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "line1", nullable = false)
    private String line1;

    @Column(name = "line2")
    private String line2;

    @Column(name = "line3")
    private String line3;

    @Column(name = "line4")
    private String line4;

    @Column(name = "line5")
    private String line5;

    @Column(name = "author", nullable = false)
    private String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public String getLine4() {
        return line4;
    }

    public void setLine4(String line4) {
        this.line4 = line4;
    }

    public String getLine5() {
        return line5;
    }

    public void setLine5(String line5) {
        this.line5 = line5;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
