package com.example.anonym.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.anonym.entity.Question;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    
    List<Question> findAllByAnsweringId(Integer answeringId);
}
