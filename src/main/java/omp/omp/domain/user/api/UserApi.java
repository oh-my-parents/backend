package omp.omp.domain.user.api;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.user.application.UserService;
import omp.omp.domain.user.dto.UserChildAnswerRequest;
import omp.omp.domain.user.dto.UserParentAnswerRequest;
import omp.omp.domain.user.dto.UserScoreRequest;
import omp.omp.domain.user.dto.UserScoreResponse;
import omp.omp.global.util.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @PostMapping("/api/v1/user/score")
    public Result<UserScoreResponse> confirmScore(@RequestBody UserScoreRequest userScoreRequest) {

        UserScoreResponse userScoreResponse = new UserScoreResponse(userService.confirmScore(userScoreRequest.getId(), userScoreRequest.getParentType()));
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, userScoreResponse);
    }

    @PostMapping("/api/v1/child/answer")
    public Result<Long> saveChildAnswer(@RequestBody UserChildAnswerRequest userChildAnswerRequest) {

        Long resultId = userService.saveChildAnswer(userChildAnswerRequest.getId(), userChildAnswerRequest.getParentType(), userChildAnswerRequest.getUserChildAnswers());
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, resultId);
    }

    @PostMapping("/api/v1/parent/answer")
    public Result<Long> saveParentAnswer(@RequestBody UserParentAnswerRequest userParentAnswerRequest) {

        Long resultId = userService.saveParentAnswer(userParentAnswerRequest.getId(), userParentAnswerRequest.getParentType(), userParentAnswerRequest.getUserParentAnswers());
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, resultId);
    }
}
