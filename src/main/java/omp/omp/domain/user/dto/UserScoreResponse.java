package omp.omp.domain.user.dto;

import lombok.Data;

@Data
public class UserScoreResponse {

    private int score;

    public UserScoreResponse(int score) {
        this.score = score;
    }
}
