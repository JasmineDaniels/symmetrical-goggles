package com.cooksys.quiz_api.model;

import com.cooksys.quiz_api.entities.Question;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class QuizRequestDto {

    private String name;
    private List<QuestionRequestDto> questions;
}
