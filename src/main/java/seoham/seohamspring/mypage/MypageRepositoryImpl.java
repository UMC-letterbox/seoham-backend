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




//    닉네임 중복검사
    @Override
    public int chekcEmail(String email) {
        String query = "select exists(select nickName from user where nickName = ?)";
        Object[] params = new Object[]{email};
        return jdbcTemplate.queryForObject(query, int.class, params);
    }

//    비밀번호 확인
    @Override
    public int checkPassword(String password, int userIdx) {
        String query = "select exists(select userIdx, passWord from user\n" +
                "where userIdx = ? and passWord = ?)";
        Object[] params = new Object[]{userIdx, password};

        return jdbcTemplate.queryForObject(query, int.class, params);
    }

    @Override
    public int modifyNickname(PatchNicknameReq patchNicknameReq, int userIdx) {
        String query = "update user set nickName = ? where userIdx = ?";
        Object[] params = new Object[]{patchNicknameReq.getNewNickname(), userIdx};
        return jdbcTemplate.update(query);
    }

    @Override
    public int modifyPassword(PatchPasswordReq patchPasswordReq, int userIdx) {
        String query = "update user set passWord = ? where userIdx = ?";
        Object[] params = new Object[]{patchPasswordReq.getNewPassword(), userIdx};
        return jdbcTemplate.update(query);

    }

    @Override
    public int deleteUser(DeleteUserReq deleteUserReq, int userIdx) {
        String query = "delete u, p from user as u\n" +
                "left join post as p\n" +
                "on u.userIdx = p.userIdx\n" +
                "where u.userIdx = ?";
        String fkCheck0 = "set foreign_key_checks = 0;";
        String fkCheck1 = "set foreign_key_checks = 1;";
        Object[] params = new Object[]{userIdx};

        jdbcTemplate.queryForObject(fkCheck0, int.class);
        Integer result = jdbcTemplate.queryForObject(query, int.class, params);
        jdbcTemplate.queryForObject(fkCheck1, int.class);

        return result;
    }


}

