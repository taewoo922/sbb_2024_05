package com.sbs.sbb.Question;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@Controller
@RequiredArgsConstructor
//@Validated 컨트롤러에서는 이 부분 생략가능
public class QuestionController {
    private final QuestionService questionService;


    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question q = this.questionService.getQuestion(id);

        model.addAttribute("question", q);

        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate() {
        return "question_form";
    }

    @PostMapping("/create")
    // QuestionForm 값을 바인딩 할 떄 유효성 체크를 해라!
    public String questionCreate(@Valid QuestionForm questionform) {
        Question q = this.questionService.create(questionform.getSubject(), questionform.getSubject());

        return "redirect:/question/list";
    }
}
