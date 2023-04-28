package com.cooksys.quiz_api.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Answer { // implements Serializable

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String text;

//  private boolean correct;
  private boolean correct = false;

  @ManyToOne // question foreign key
  @JoinColumn(name = "question_id")
  private Question question;

//  public Answer(Long id, String text, boolean correct, Question question) {
//    this.id = id;
//    this.text = text;
//    this.correct = correct;
//    this.question = question;
//  }
//
//  public Long getId() {
//    return id;
//  }
//
//  public void setId(Long id) {
//    this.id = id;
//  }
//
//  public String getText() {
//    return text;
//  }
//
//  public void setText(String text) {
//    this.text = text;
//  }
//
//  public boolean isCorrect() {
//    return correct;
//  }
//
//  public void setCorrect(boolean correct) {
//    this.correct = correct;
//  }
//
//  public Question getQuestion() {
//    return question;
//  }
//
//  public void setQuestion(Question question) {
//    this.question = question;
//  }
//
//  @Override
//  public boolean equals(Object o) {
//    if (this == o) return true;
//    if (!(o instanceof Answer)) return false;
//    Answer answer = (Answer) o;
//    return isCorrect() == answer.isCorrect() && Objects.equals(getId(), answer.getId()) && Objects.equals(getText(), answer.getText()) && Objects.equals(getQuestion(), answer.getQuestion());
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(getId(), getText(), isCorrect(), getQuestion());
//  }
}
