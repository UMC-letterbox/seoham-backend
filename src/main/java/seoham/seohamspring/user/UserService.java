package seoham.seohamspring.user;


import seoham.seohamspring.user.CreateUserRequest;
import seoham.seohamspring.user.CreateUserResponse;

public interface UserService {
    //회원가입
    CreateUserResponse createUser(CreateUserRequest createUserRequest);

    public int checkEmail(String email);
    public int checkNickName(String nickName);

    // 로그인
    LoginUserResponse loginUser(LoginUserRequest loginUserRequest);




    public String findEmail(String nickName);
    public int findPassWord(String passWord);
}