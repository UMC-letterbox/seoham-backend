package seoham.seohamspring.mail.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PostCheckAuthReq {

    @NotEmpty(message = "email을 입력해주세요")
    public String email;

    @NotEmpty(message = "AuthCode를 입력해주세요")
    public String authCode;

}
