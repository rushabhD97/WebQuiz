package engine.controller;

import engine.model.*;
import engine.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/quizzes")
public class WebQuizController {
    @Autowired
    QuestionRepository questionRepository;

    WebQuizController(){

    }

    @PostMapping( consumes = "application/json")
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

        questionRepository.save(question);
        return question;
    }

    @GetMapping(value ="/{id}")
    public Question getQuiz(@PathVariable int id){
        Optional<Question> question = questionRepository.findById(id);
        if(!question.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return question.get();
    }
    @GetMapping()
    public List<Question> getAllQuizes(){
        return questionRepository.findAll();
    }
    @PostMapping(value = "/{id}/solve", consumes = "application/json")
    public Feedback solveQuiz(@PathVariable int id,@RequestBody Answer answer){
        try {
            Question question = questionRepository.findById(id).get();
            System.out.println(question+" "+question.getAnswer()+" "+Arrays.toString(answer.getAnswer()));
            return new Feedback(question.getAnswer().size() == List.of(answer.getAnswer()).size() &&
                    question.getAnswer().containsAll(List.of(answer.getAnswer())));
        }catch(ResponseStatusException rse) {
            throw rse;
        }
    }


}
