package lab;

import jakarta.persistence.*;

import java.util.List;

@Entity
@SequenceGenerator(
        name = "seq",
        sequenceName = "words_seq",
        allocationSize = 1
)
@Table(name = "Words")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private int id;

    private String word;
    private List<String> translations;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<String> getTranslations() {
        return translations;
    }

    public void setTranslations(List<String> translations) {
        this.translations = translations;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", translations=" + translations +
                '}';
    }
}
