package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
public class Feedback {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean success;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String feedback;
    public Feedback setFeedBack(boolean success){
        this.success = success;
        this.feedback = success ?
                "Congratulations, you're right!\"":
                "Wrong answer! Please, try again.";
        return this;
    }
    public Feedback(boolean success) {
        setFeedBack(success);
    }
    public Feedback(){
        this(false);
    }

    public boolean isSuccess() {
        return success;
    }

    private void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    private void setFeedback(String feedback) {
        this.feedback = feedback;
    }

}
