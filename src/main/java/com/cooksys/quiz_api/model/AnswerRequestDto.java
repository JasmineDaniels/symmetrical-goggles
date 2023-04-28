package com.cooksys.quiz_api.model;

import com.cooksys.quiz_api.entities.Question;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Data
public class AnswerRequestDto {

    private String text;

    private boolean correct = false;
}
