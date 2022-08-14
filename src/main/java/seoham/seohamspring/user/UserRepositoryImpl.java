package seoham.seohamspring.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import seoham.seohamspring.user.domain.CreateUserRequest;
import seoham.seohamspring.user.domain.FindPassWordRequest;
import seoham.seohamspring.user.domain.LoginUserRequest;
import seoham.seohamspring.user.domain.User;

import javax.sql.DataSource;
@Repository
public class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    //public void setDataSource(DataSource dataSource){
    //    this.jdbcTemplate = new JdbcTemplate(dataSource);
    //}
    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int createUser(CreateUserRequest createUserRequest){
        String createUserQuery = "insert into user (email, passWord, nickName) VALUES (?,?,?)";
        Object[] createUserParams = new Object[]{createUserRequest.getEmail(), createUserRequest.getPassWord(), createUserRequest.getNickName()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }
    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select email from user where email = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);
    }

    public int checkNickName(String nickName){
        String checkNickNameQuery = "select exists(select nickName from user where nickName = ?)";
        String checkNickNameParams = nickName;
        return this.jdbcTemplate.queryForObject(checkNickNameQuery,
                int.class,
                checkNickNameParams);
    }

    public int loginUser(LoginUserRequest loginUserRequest){
        String loginUserQuery = "";
        Object[] loginUserParams = new Object[]{loginUserRequest.getEmail(), loginUserRequest.getPassWord()};
        this.jdbcTemplate.update(loginUserQuery, loginUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }


    public String findEmail(String nickName){
        String findEmailQuery = "SELECT u.email as email\n" +
                "        FROM user as u\n" +
                "        WHERE u.nickName = ?;";
        String findEmailParams = nickName;
        return this.jdbcTemplate.queryForObject(findEmailQuery,
                String.class,
                findEmailParams);
    }

    public int findPassWord(FindPassWordRequest findPassWordRequest){
        String findPassWordQuery = "UPDATE user set passWord = ? where email = ?;";
        Object[] findPassWordParams = new Object[]{findPassWordRequest.getPassWord(), findPassWordRequest.getEmail()};
        //System.out.println(findPassWordParams[0]);
        //System.out.println(findPassWordParams[1]);
        return this.jdbcTemplate.update(findPassWordQuery,findPassWordParams);
    }


    public User getUser(LoginUserRequest loginUserRequest){
        String getPwdQuery = "SELECT userIdx,email , passWord,nickName FROM User WHERE email=?";
        String getPwdParam = loginUserRequest.getEmail();
        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum) -> new User(
                        rs.getInt("userIdx"),
                        rs.getString("email"),
                        rs.getString("passWord"),
                        rs.getString("nickName")
                ),
                getPwdParam);
    }
}
