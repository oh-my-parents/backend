package omp.omp.domain.user.dto;

import lombok.Data;

@Data
public class UserSignInRequest {
    private String id;
    private String password;
}
