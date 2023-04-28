package com.cooksys.quiz_api.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Quiz { // implements Serializable

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private boolean deleted = false;

  @OneToMany(mappedBy = "quiz")
  private List<Question> questions;

//  public Quiz(Long id, String name, List<Question> questions) {
//    this.id = id;
//    this.name = name;
//    this.questions = questions;
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
//  public String getName() {
//    return name;
//  }
//
//  public void setName(String name) {
//    this.name = name;
//  }
//
//  public List<Question> getQuestions() {
//    return questions;
//  }
//
//  public void setQuestions(List<Question> questions) {
//    this.questions = questions;
//  }
//
//  @Override
//  public boolean equals(Object o) {
//    if (this == o) return true;
//    if (!(o instanceof Quiz)) return false;
//    Quiz quiz = (Quiz) o;
//    return Objects.equals(getId(), quiz.getId()) && Objects.equals(getName(), quiz.getName()) && Objects.equals(getQuestions(), quiz.getQuestions());
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(getId(), getName(), getQuestions());
//  }
}
