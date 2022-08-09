package seoham.seohamspring.mypage.domain;

import lombok.Data;

@Data
public class PostCheckPasswordReq {

    private int password;

    public PostCheckPasswordReq(int password) {
        this.password = password;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
