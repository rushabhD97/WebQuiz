package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Feedback {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean success;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String feedback;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> answer;
    public Feedback setFeedBack(boolean success){
        this.success = success;
        this.feedback = success ?
                "Congratulations, you're right!\"":
                "Wrong answer! Please, try again.";
        return this;
    }
    private Feedback(boolean success,String feedback) {
        this.success = success;
        this.feedback= feedback;
    }
    private Feedback(){
        this(false,null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
