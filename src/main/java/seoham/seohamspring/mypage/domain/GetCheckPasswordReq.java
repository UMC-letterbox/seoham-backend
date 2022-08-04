package seoham.seohamspring.mypage.domain;

import lombok.Data;

@Data
public class GetCheckPasswordReq {

    private int password;

    public GetCheckPasswordReq(int password) {
        this.password = password;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
