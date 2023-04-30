package omp.omp.domain.question.api;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.question.application.QuestionService;
import omp.omp.domain.question.dto.QuestionRequest;
import omp.omp.global.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionApi {

    private final QuestionService questionService;

    @GetMapping("api/v1/question")
    public Result<List<String>> findQuestionsWithParent(QuestionRequest questionRequest) {

        List<String> questionContents = questionService.findQuestionContentsWithParent(questionRequest.getParentType());

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, questionContents);
    }
}
