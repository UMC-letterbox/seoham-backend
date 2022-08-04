package seoham.seohamspring.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import seoham.seohamspring.mypage.domain.DeleteUserReq;
import seoham.seohamspring.mypage.domain.PatchNicknameReq;
import seoham.seohamspring.mypage.domain.PatchPasswordReq;

import javax.sql.DataSource;

public class MypageRepositoryImpl implements MypageRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MypageRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public int chekcEmail(String email) {
        return 0;
    }

    @Override
    public int checkPassword(String password) {
        return 0;
    }

    @Override
    public String modifyNickname(PatchNicknameReq patchNicknameReq, int userId) {
        return null;
    }

    @Override
    public String modifyPassword(PatchPasswordReq patchPasswordReq, int userId) {
        return null;
    }

    @Override
    public String deleteUser(DeleteUserReq deleteUserReq, int userId) {
        return null;
    }
}
