package omp.omp.domain.user.api;

import lombok.RequiredArgsConstructor;
import omp.omp.domain.user.application.UserService;
import omp.omp.domain.user.dto.UserChildAnswerRequest;
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

    @PostMapping("/api/v1/child/question")
    public Result<Long> saveChildAnswer(@RequestBody UserChildAnswerRequest userChildAnswerRequest) {

        Long resultId = userService.saveChildAnswer(userChildAnswerRequest.getId(), userChildAnswerRequest.getParentType(), userChildAnswerRequest.getUserChildAnswer());
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, resultId);
    }
}
