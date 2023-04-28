package com.cooksys.quiz_api.controllers;

import java.util.List;

import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.model.QuestionRequestDto;
import com.cooksys.quiz_api.model.QuizRequestDto;
import com.cooksys.quiz_api.services.QuizService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController { // QuizController handles the Receiving & Sending of Requests and Responses
  //and converts to JSON

  private final QuizService quizService;

  @GetMapping
  public List<QuizResponseDto> getAllQuizzes() {
    return quizService.getAllQuizzes();
  }
  
  // TODO: Implement the remaining 6 endpoints from the documentation.


  //Create Quiz
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public QuizResponseDto createQuiz(@RequestBody QuizRequestDto quizRequestDto){
    return quizService.createQuiz(quizRequestDto);
  }

  //Delete quiz
  @DeleteMapping("/{id}")
  public ResponseEntity<QuizResponseDto> deleteQuiz(@PathVariable Long id){
    return quizService.deleteQuiz(id);
  }

  //UPDATE (PATCH) update name
  @PatchMapping("/{id}/rename/{newName}")
  public QuizResponseDto updateQuizName(@PathVariable Long id, @PathVariable String newName){
    return quizService.updateName(id, newName);
  }
//  public QuizResponseDto updateQuizName(@PathVariable Long id, @PathVariable QuizRequestDto newName){
//    return quizService.updateName(id, newName);
//  }


  //Get quiz by Id
  @GetMapping("/{id}")
  public ResponseEntity<QuizResponseDto> getQuizById(@PathVariable Long id) {
    return quizService.getQuizById(id);
  }

  @GetMapping("/{id}/random")
  public ResponseEntity<QuestionResponseDto> getRandomQuestion(@PathVariable Long id) {
    return quizService.getRandomQuestion(id);
  }
//  public ResponseEntity<QuestionResponseDto> getRandomQuestion(@PathVariable Long id) {
//    return quizService.getRandomQuestion(id);
//  }

  //PATCH Add a question to a quiz by id
  @PatchMapping("/{id}/add")
  public QuizResponseDto addQuestionToQuizById(@PathVariable Long id, @RequestBody QuestionRequestDto questionRequestDto){
    return quizService.addQuestion(id, questionRequestDto);
  }

  //Delete a question (questionID) from a quiz (quizID)
  @DeleteMapping("/{id}/delete/{questionID}")
  public ResponseEntity<QuestionResponseDto> deleteAQuestionFromAQuiz(@PathVariable Long id, @PathVariable Long questionID){
    return quizService.deleteQuestion(id, questionID);
  }

}
