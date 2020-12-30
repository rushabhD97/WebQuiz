package engine.controller;

import engine.model.*;
import engine.repository.QuestionRepository;
import engine.service.QuestionService;
import engine.service.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/quizzes")
public class WebQuizController {
    @Autowired
    QuestionService questionService;

    WebQuizController(){

    }

    @PostMapping( consumes = "application/json")
    public Question addQuiz(@RequestBody Question question){
        questionService.save(question, SecurityContextHolder.getContext().getAuthentication().getName());
        return question;
    }

    @GetMapping(value ="/{id}")
    public Question getQuiz(@PathVariable int id){
        return questionService.findById(id);
    }
    @GetMapping()
    public List<Question> getAllQuizes(){
        return questionService.findAll();
    }
    @PostMapping(value = "/{id}/solve", consumes = "application/json")
    public Feedback solveQuiz(@PathVariable int id,@RequestBody Answer answer){
        Question question = questionService.findById(id);
        if(question == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        System.out.println(question+" "+question.getAnswer()+" "+Arrays.toString(answer.getAnswer()));
        return new Feedback(question.getAnswer().size() == List.of(answer.getAnswer()).size() &&
                    question.getAnswer().containsAll(List.of(answer.getAnswer())));
    }

    @DeleteMapping(value = "{id}")
    public void deleteQuiz(@PathVariable int id){
        questionService.deleteById(id,
                SecurityContextHolder.getContext().getAuthentication().getName());
    }


}
