package seoham.seohamspring.user;


import seoham.seohamspring.user.domain.CreateUserRequest;
import seoham.seohamspring.user.domain.LoginUserRequest;

public interface UserRepository {

    int createUser(CreateUserRequest createUserRequest);

    public int checkEmail(String email);

    public int checkNickName(String nickName);


    int loginUser(LoginUserRequest loginUserRequest);


    public String findEmail(String nickName);
    public int findPassWord(String passWord);

}
