package seoham.seohamspring.mypage;

import org.springframework.stereotype.Repository;
import seoham.seohamspring.mypage.domain.*;

@Repository
public interface MypageRepository {

    //닉네임 중복검사
    int checkNickname(PostCheckNicknameReq postCheckNicknameReq);

    //비밀번호 확인
    int checkPassword(PostCheckPasswordReq postCheckPasswordReq, int userIdx);

    //닉네임 수정
    int modifyNickname(PatchNicknameReq patchNicknameReq, int userIdx);

    //비밀번호 수정
    int modifyPassword(PatchPasswordReq patchPasswordReq, int userIdx);

    //회원 탈퇴
    int deleteUser(int userIdx);


}

