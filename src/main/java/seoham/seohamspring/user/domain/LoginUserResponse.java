package seoham.seohamspring.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class LoginUserResponse {

    private long userIdx;
    private String jwt;
}