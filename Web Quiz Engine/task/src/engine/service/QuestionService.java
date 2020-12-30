package engine.service;

import engine.model.Question;

import java.util.List;

public interface QuestionService {

    Question save(Question q,String email);

    Question findById(int id);

    List<Question> findAll();

    void deleteById(int id,String email);


}
