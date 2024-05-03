package com.sbs.sbb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @ManyToOne
    //private Integer questionId;
    private Question question;

}