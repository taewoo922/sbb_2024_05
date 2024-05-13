package com.sbs.sbb.Question;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Question findBySubject(String subject);

    Question findBySubjectAndContent(String subject, String content);
    Page<Question> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value="AlTER TABLE question AUTO_INCREMENT = 1", nativeQuery = true)
    void createAutoIncreament();
}
