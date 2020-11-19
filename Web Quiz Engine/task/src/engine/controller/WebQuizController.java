package engine.controller;

import engine.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WebQuizController {
    List<Question> questionList;
    WebQuizController(){
        questionList    =   new ArrayList<>();
    }

    @PostMapping(value = "/api/quizzes", consumes = "application/json")
    public Question addQuiz(@RequestBody Question question){
        if(
            question.getText()==null ||
            question.getText().isEmpty() ||
            question.getTitle()==null||
            question.getTitle().isEmpty()||
            question.getOptions()==null ||
            question.getOptions().size()<2
        ){
            System.out.println(question);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Missing Details");
        }

        question.setId(questionList.size());
        System.out.println("Inserted "+question);
        questionList.add(question);

        return question;
    }

    @GetMapping(value ="/api/quizzes/{id}")
    public Question getQuiz(@PathVariable int id){
        if(id >= questionList.size()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return questionList.get(id);
    }
    @GetMapping(value ="/api/quizzes")
    public List<Question> getAllQuizes(){
        return questionList;
    }
    @PostMapping(value = "/api/quizzes/{id}/solve", consumes = "application/json")
    public Feedback solveQuiz(@PathVariable int id,@RequestBody Feedback feedback){
        System.out.println(id+" "+feedback.getAnswer());
        try {
            Question question = getQuiz(id);
            return feedback.setFeedBack(
                    question.getAnswer().size() == feedback.getAnswer().size() &&
                    question.getAnswer().containsAll(feedback.getAnswer()));
        }catch(ResponseStatusException rse) {
            throw rse;
        }
    }


}
