package lab;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
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

    public int getId() {
        return id;
    }

    public void setTranslations(List<String> translations) {
        if(this.translations == null){
            this.translations = new ArrayList<>();
        }
        this.translations.addAll(translations);
    }

    public void setTranslations(String[] translations) {
        if(this.translations == null){
            this.translations = new ArrayList<>();
        }
        this.translations.addAll(Arrays.asList(translations));
    }

    @Override
    public String toString() {
        return "id=" + id +
                "; word='" + word + '\'' +
                "; translations=" + translations;
    }
}
