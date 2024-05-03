package com.sbs.sbb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Answer {
    @Id//Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Integer id;

    @Column(columnDefinition = "TEXT") // text
    private String content;

    private LocalDateTime createDate;

    //Many= Answer, one=Question
    //필수
    @ManyToOne
    private Question question;

    @OneToMany(mappedBy = "question")
    private List<Answer> answerList;

}