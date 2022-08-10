package seoham.seohamspring.mypage.domain;

import lombok.Data;

@Data
public class PostCheckNicknameReq {

    private String newNickname;

    public PostCheckNicknameReq(String newNickname) {
        this.newNickname = newNickname;
    }

    public String getNewNickname() {
        return newNickname;
    }

    public void setNewNickname(String newNickname) {
        this.newNickname = newNickname;
    }
}
