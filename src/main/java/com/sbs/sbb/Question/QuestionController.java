package com.sbs.sbb.Question;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    // QuestionForm 변수는 model.addAttribute 없이 바로 뷰에서 접근할 수 있다.
    // QuestionForm questionForm 써주는 이유 : question_form.html 에서 questionForm 변수가 없으면 실행이 안되기 때문에
    // 빈 객체라도 만든다.
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @PostMapping("/create")
    // QuestionForm 값을 바인딩 할 떄 유효성 체크를 해라!
    public String questionCreate(@Valid QuestionForm questionform, BindingResult bindingResult) {
        if (bindingResult.hasErrors() ) {
            // question_form.html 실행
            // 다시 작성하라는 의마로 응답에 폼을 싫어서 보냄
            return "question_form";
        }
        Question q = this.questionService.create(questionform.getSubject(), questionform.getSubject());

        return "redirect:/question/list";
    }
}
