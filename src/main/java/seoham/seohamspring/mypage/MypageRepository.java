package seoham.seohamspring.mypage;

import seoham.seohamspring.mypage.domain.DeleteUserReq;
import seoham.seohamspring.mypage.domain.PatchNicknameReq;
import seoham.seohamspring.mypage.domain.PatchPasswordReq;

public interface MypageRepository {

    //닉네임 중복검사
    int chekcEmail(String email);

    //비밀번호 확인
    int checkPassword(String password, int userIdx);

    //닉네임 수정
    int modifyNickname(PatchNicknameReq patchNicknameReq, int userIdx);

    //비밀번호 수정
    int modifyPassword(PatchPasswordReq patchPasswordReq, int userIdx);

    //회원 탈퇴
    int deleteUser(DeleteUserReq deleteUserReq, int userIdx);


}
