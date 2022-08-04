package seoham.seohamspring.mypage.domain;

import lombok.Data;

@Data
public class DeleteUserReq {

    private int userIdx;

    public DeleteUserReq(int userId) {
        this.userIdx = userId;
    }

    public int getUserIdx() {
        return userIdx;
    }

    public void setUserIdx(int userIdx) {
        this.userIdx = userIdx;
    }
}
