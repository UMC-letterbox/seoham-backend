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
        String query_string = "select count(*) from user where email = ?";
        Integer result = jdbcTemplate.queryForObject(query_string, Integer.class, email);

        if (result >= 1)
            return 0;
        else
            return 1;
    }

    @Override
    public int checkPassword(String password) {
        return 0;
    }

    @Override
    public String modifyNickname(PatchNicknameReq patchNicknameReq, int userIdx) {
        String query_string = "update user set email = ? where userIdx = ?";
        Integer result = jdbcTemplate.queryForObject(query_string, Integer.class, patchNicknameReq.getNewNickname(), userIdx);

        return null;
    }

    @Override
    public String modifyPassword(PatchPasswordReq patchPasswordReq, int userIdx) {
        return null;
    }

    @Override
    public String deleteUser(DeleteUserReq deleteUserReq, int userIdx) {

        return null;
    }


}

