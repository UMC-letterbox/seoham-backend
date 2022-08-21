package seoham.seohamspring.mypage;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.mypage.domain.*;
public interface MypageService {

    //닉네임 중복검사
    int chekcNickname(PostCheckNicknameReq postCheckNicknameReq) throws BaseException;

    //비밀번호 확인
    int checkPassword(PostCheckPasswordReq postCheckPasswordReq, int userIdx) throws BaseException;

    //닉네임 수정
    String modifyNickname(PatchNicknameReq patchNicknameReq, int userIdx) throws BaseException;

    //비밀번호 수정
    String modifyPassword(PatchPasswordReq patchPasswordReq, int userIdx) throws BaseException;

    //회원 탈퇴
    String deleteUser(int userIdx) throws BaseException;

}
