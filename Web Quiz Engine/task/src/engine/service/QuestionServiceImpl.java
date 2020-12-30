package engine.service;

import engine.model.Question;
import engine.repository.QuestionRepository;
import engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Question save(Question question, String email) {
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
        question.setAuthor(userRepository.findByEmailIgnoreCase(email));
        questionRepository.save(question);
        return null;
    }

    @Override
    public Question findById(int id) {
        Optional<Question> question = questionRepository.findById(id);
        if(!question.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return question.get();
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public void deleteById(int id, String email) {
        Question q = findById(id);
        if(q.getAuthor()!=userRepository.findByEmailIgnoreCase(email))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        questionRepository.deleteById(id);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);

    }
}
