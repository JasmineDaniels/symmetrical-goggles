package com.cooksys.quiz_api.services.impl;

import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Answer;
import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.mappers.AnswerMapper;
import com.cooksys.quiz_api.mappers.QuestionMapper;
import com.cooksys.quiz_api.mappers.QuizMapper;
import com.cooksys.quiz_api.model.AnswerRequestDto;
import com.cooksys.quiz_api.model.QuestionRequestDto;
import com.cooksys.quiz_api.model.QuizRequestDto;
import com.cooksys.quiz_api.repositories.AnswerRepository;
import com.cooksys.quiz_api.repositories.QuestionRepository;
import com.cooksys.quiz_api.repositories.QuizRepository;
import com.cooksys.quiz_api.services.QuizService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService { // handles business logic "algorithmic"

  private final QuizRepository quizRepository;
  private final QuizMapper quizMapper;

  private final QuestionRepository questionRepository;
  private final QuestionMapper questionMapper;

  private final AnswerRepository answerRepository;
  private final AnswerMapper answerMapper;

  public Quiz getQuiz(Long id){
    Optional <Quiz> optionalQuiz = quizRepository.findByIdAndDeletedFalse(id);
    if (optionalQuiz.isEmpty()){
      try {
        throw new NotFoundException("No quiz found with id: " + id + " No updates were made.");
      } catch (NotFoundException e) {
        throw new RuntimeException(e);
      }
    }
    return optionalQuiz.get(); //if you found the entity, return it

  }



  @Override
  public List<QuizResponseDto> getAllQuizzes() {
    return quizMapper.entitiesToDtos(quizRepository.findAllByDeletedFalse());

  }

  @Override
  public QuizResponseDto createQuiz(QuizRequestDto quizRequestDto) {
    //Map the request DTO to a Quiz Entity
    Quiz quizEntity = quizMapper.requestToEntity(quizRequestDto); // takes the request and creates a quiz object
    quizRepository.saveAndFlush(quizEntity);

    for (Question question : quizEntity.getQuestions()){
      question.setQuiz(quizEntity);
      questionRepository.saveAndFlush(question);
      for(Answer answer : question.getAnswers()){
          //answer.setCorrect(answer.isCorrect());
          answer.setQuestion(question);
          answerRepository.saveAndFlush(answer);
      }
    }
    quizEntity.setDeleted(false);
    return quizMapper.entityToDto(quizEntity); // saveAndFlush() returns the new entity**)
  }


  @Override
  public ResponseEntity<QuizResponseDto> getQuizById(Long id) {
    return new ResponseEntity<>(quizMapper.entityToDto(quizRepository.getById(id)), HttpStatus.OK); //takes the response body DTO and the status code.
  }

  @Override
  public ResponseEntity<QuestionResponseDto> getRandomQuestion(Long id) {
    List<Question> quizEntityQuestions = quizRepository.getById(id).getQuestions();

    int randomIndex = (int)(Math.random()*quizEntityQuestions.size());
    QuestionResponseDto randomQuestion = questionMapper.entityToDto(quizEntityQuestions.get(randomIndex));

    return new ResponseEntity<>(randomQuestion, HttpStatus.OK);
  }

  @Override
  public QuizResponseDto addQuestion(Long id, QuestionRequestDto questionRequestDto) {
    Quiz quizEntity = getQuiz(id);
    Question questionEntity = questionMapper.requestToEntity(questionRequestDto); // takes the request and creates a question entity
    questionEntity.setQuiz(quizEntity);
    questionMapper.entityToDto(questionRepository.saveAndFlush(questionEntity));

    for(Answer answer : questionEntity.getAnswers()){
      answer.setQuestion(questionEntity);
      answerRepository.saveAndFlush(answer);
    }

    return quizMapper.entityToDto(quizEntity);
  }

  @Override
  public QuizResponseDto updateName(Long id, String newName) {
    Quiz quizEntity = getQuiz(id);
    quizEntity.setName(newName);

    //handle errors
    return quizMapper.entityToDto(quizRepository.saveAndFlush(quizEntity));

  }

//  public QuizResponseDto updateName(Long id, QuizRequestDto newName) {
//    Quiz quizEntity = getQuiz(id);
//    quizEntity.setName(newName.getName());
//
//    //handle errors
//    return quizMapper.entityToDto(quizRepository.saveAndFlush(quizEntity));
//
//  }

  @Override
  public ResponseEntity<QuizResponseDto> deleteQuiz(Long id) {
    Quiz quizEntity = getQuiz(id);
    //quizEntity.setDeleted(true);

    List<Question> questionList = quizEntity.getQuestions();

    for (Question question : questionList){
      for(Answer answer : question.getAnswers()){
        //answer.setDeleted(true);
        answerRepository.delete(answer);
      }
      //question.setDeleted(true);
      questionRepository.delete(question);
    }

    quizRepository.delete(quizEntity);
    return new ResponseEntity<>(quizMapper.entityToDto(quizEntity), HttpStatus.OK);

  }

  @Override
  public ResponseEntity<QuestionResponseDto> deleteQuestion(Long id, Long questionID) {
    Question result = questionRepository.getById(questionID);

      for(Answer answer : result.getAnswers()) {
        answerRepository.delete(answer);
      }

      Quiz quizEntity = quizRepository.getById(id);
      for (Question question : quizEntity.getQuestions()){
        if (question == result){
          questionRepository.delete(question);
        }
      }
      return new ResponseEntity<>(questionMapper.entityToDto(result), HttpStatus.OK);
  }

}
