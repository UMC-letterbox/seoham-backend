package seoham.seohamspring.mail;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PostCheckAuthReq {

    @NotEmpty(message = "AuthCode를 입력해주세요")
    public String authCode;

}
