package com.cooksys.quiz_api.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor //no need for default empty constructor
@Data
public class Question { // implements Serializable

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @ManyToOne //quiz foreign key
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

//    public Question(Long id, String text, Quiz quiz, List<Answer> answers) {
//        this.id = id;
//        this.text = text;
//        this.quiz = quiz;
//        this.answers = answers;
//    }
//
//        public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public Quiz getQuiz() {
//        return quiz;
//    }
//
//    public void setQuiz(Quiz quiz) {
//        this.quiz = quiz;
//    }
//
//    public List<Answer> getAnswers() {
//        return answers;
//    }
//
//    public void setAnswers(List<Answer> answers) {
//        this.answers = answers;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Question)) return false;
//        Question question = (Question) o;
//        return Objects.equals(getId(), question.getId()) && Objects.equals(getText(), question.getText()) && Objects.equals(getQuiz(), question.getQuiz()) && Objects.equals(getAnswers(), question.getAnswers());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getText(), getQuiz(), getAnswers());
//    }


}
