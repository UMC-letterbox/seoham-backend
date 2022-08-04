package seoham.seohamspring.mypage;

import seoham.seohamspring.mypage.domain.DeleteUserReq;
import seoham.seohamspring.mypage.domain.PatchNicknameReq;
import seoham.seohamspring.mypage.domain.PatchPasswordReq;

public interface MypageService {

    //닉네임 중복검사
    int chekcEmail(String email);

    //비밀번호 확인
    int checkPassword(String password);

    //닉네임 수정
    String modifyNickname(PatchNicknameReq patchNicknameReq, int userId);

    //비밀번호 수정
    String modifyPassword(PatchPasswordReq patchPasswordReq, int userId);

    //회원 탈퇴
    String deleteUser(DeleteUserReq deleteUserReq, int userId);

}
