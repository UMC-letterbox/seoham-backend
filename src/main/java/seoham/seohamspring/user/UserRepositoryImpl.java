package seoham.seohamspring.user;

import seoham.seohamspring.user.CreateUserRequest;
import seoham.seohamspring.user.UserRepository;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public int createUser(CreateUserRequest createUserRequest){
        String createUserQuery = "insert into User (email, passWord, nickName) VALUES (?,?,?)";
        Object[] createUserParams = new Object[]{createUserRequest.getEmail(), createUserRequest.getPassWord(), createUserRequest.getNickName()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }


    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select email from User where email = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);
    }

    public int checkNickName(String nickName){
        String checkNickNameQuery = "select exists(select nickName from User where nickName = ?)";
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
        String findEmailQuery = "";
        String findEmailParams = nickName;
        return this.jdbcTemplate.queryForObject(findEmailQuery,
                int.class,
                findEmailParams);
    }

    public int findPassWord(String passWord){
        String findPassWordQuery = "";
        String findPassWordParams = passWord;
        return this.jdbcTemplate.queryForObject(findPassWordQuery,
                int.class,
                findPassWordParams);
    }
}
