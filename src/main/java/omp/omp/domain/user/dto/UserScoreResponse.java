package omp.omp.domain.user.dto;

import lombok.Data;

@Data
public class UserScoreResponse {

    private String name;
    private int score;

    public UserScoreResponse(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
