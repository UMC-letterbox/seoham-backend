package seoham.seohamspring.mypage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCountInfoRes {
    private int letter;
    private String email;
    private String nickname;
}
