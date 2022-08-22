package seoham.seohamspring.mypage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PatchNicknameReq {

    private String newNickname;

    public PatchNicknameReq(String newNickname) {
        this.newNickname = newNickname;
    }

    public String getNewNickname() {
        return newNickname;
    }

    public void setNewNickname(String newNickname) {
        this.newNickname = newNickname;
    }
}
