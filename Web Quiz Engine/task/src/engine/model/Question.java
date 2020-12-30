package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
public class Question {
    @Column
    private String title;
    @Column
    private String text;

    @ElementCollection
    private List<String> options;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    private int id;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    private List<Integer> answer;

    @ManyToOne
    @JsonIgnore
    private User author;
    public Question() {

    }
    public Question(String title, String text,User author, List<String> options, List<Integer> answer){
        this.title      = title;
        this.text       = text;
        this.author     = author;
        this.options    = options;
        this.answer     = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                ", answer=" + answer +
                '}';
    }
}
