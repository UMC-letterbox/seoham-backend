package seoham.seohamspring.mypage.domain;

import lombok.Data;

@Data
public class GetCheckNicknameReq {

    private String newNickname;

    public GetCheckNicknameReq(String newNickname) {
        this.newNickname = newNickname;
    }

    public String getNewNickname() {
        return newNickname;
    }

    public void setNewNickname(String newNickname) {
        this.newNickname = newNickname;
    }
}
