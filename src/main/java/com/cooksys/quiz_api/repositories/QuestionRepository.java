package com.cooksys.quiz_api.repositories;

import com.cooksys.quiz_api.entities.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// You may think you don't need this Repository, but remember each Repository interface
// only allows you to interact with the 1 table it maps to, so in order to save or retrieve
// questions directly you need to use this interface. You can still access Questions stored on a Quiz
// without using this interface.
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

  // TODO: Do you need any derived queries? If so add them here.

    //@Query(value = "SELECT * FROM quiz_api.question where quiz_api.question.quiz_id = :id", nativeQuery = true)
    //List<Question> findAllByQuizId(@Param("id") Long id);

    @Query(value = "SELECT * FROM quiz_api.question q where q.quiz_id = :id", nativeQuery = true)
    List<Question> findByQuizId(@Param("id") Long id);
}
