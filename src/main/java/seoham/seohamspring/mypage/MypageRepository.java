package seoham.seohamspring.mypage;

import org.springframework.jdbc.core.JdbcTemplate;
import seoham.seohamspring.mypage.domain.DeleteUserReq;
import seoham.seohamspring.mypage.domain.PatchNicknameReq;
import seoham.seohamspring.mypage.domain.PatchPasswordReq;

import javax.sql.DataSource;

public interface MypageRepository {

    //닉네임 중복검사
    public int chekcEmail(String email);

    //비밀번호 확인
    public int checkPassword(String password);

    //닉네임 수정
    public String modifyNickname(PatchNicknameReq patchNicknameReq, int userId);

    //비밀번호 수정
    public String modifyPassword(PatchPasswordReq patchPasswordReq, int userId);

    //회원 탈퇴
    public String deleteUser(DeleteUserReq deleteUserReq, int userId);


}
