package seoham.seohamspring.user.domain;

import lombok.Data;

@Data
public class TokenResponse {
    private String accessToken;
    private String tokenType;
}
