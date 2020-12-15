package engine.model;


import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class Answer {
    private Integer[] answer;
    public Answer(Integer[] answer){
        this.answer = answer;
    }
    public Answer(){}
    public Integer[] getAnswer() {
        return answer;
    }
}
