package com.cooksys.quiz_api.model;

import com.cooksys.quiz_api.entities.Answer;
import com.cooksys.quiz_api.entities.Quiz;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@NoArgsConstructor
@Data
public class QuestionRequestDto {
    private String text;
    private List<AnswerRequestDto> answers;
}
