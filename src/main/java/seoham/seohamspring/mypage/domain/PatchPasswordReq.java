package seoham.seohamspring.mypage.domain;

import lombok.Data;

@Data
public class PatchPasswordReq {

    private String newPassword;

    public PatchPasswordReq(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
