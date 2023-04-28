package com.cooksys.quiz_api.services;

import java.util.List;

import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.model.QuestionRequestDto;
import com.cooksys.quiz_api.model.QuizRequestDto;
import org.springframework.http.ResponseEntity;

public interface QuizService { // an interface that provides the methods for the implementing classes to use (QuizServiceImplClass)

  List<QuizResponseDto> getAllQuizzes();

  QuizResponseDto createQuiz(QuizRequestDto quizRequestDto);

  ResponseEntity<QuizResponseDto> getQuizById(Long id);

  QuizResponseDto addQuestion(Long id, QuestionRequestDto questionRequestDto);

  QuizResponseDto updateName(Long id, String newName);
//QuizResponseDto updateName(Long id, QuizRequestDto newName);

  ResponseEntity<QuizResponseDto> deleteQuiz(Long id);

  ResponseEntity<QuestionResponseDto> deleteQuestion(Long id, Long questionID);


  ResponseEntity<QuestionResponseDto> getRandomQuestion(Long id);
}
