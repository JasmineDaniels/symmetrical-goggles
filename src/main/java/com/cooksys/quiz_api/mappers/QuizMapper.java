package com.cooksys.quiz_api.mappers;

import java.util.List;

import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Quiz;

import com.cooksys.quiz_api.model.QuizRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { QuestionMapper.class })
public interface QuizMapper {

  Quiz requestToEntity(QuizRequestDto quizRequestDto); //turn this requestDto to a quiz entity
  QuizResponseDto entityToDto(Quiz entity); // turn this entity to a response dto

  List<QuizResponseDto> entitiesToDtos(List<Quiz> entities); // turn these entities to responseDtos



}
