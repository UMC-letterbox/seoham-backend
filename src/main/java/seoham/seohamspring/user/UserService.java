package seoham.seohamspring.user;



import org.springframework.stereotype.Service;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.user.domain.*;
@Service
public interface UserService {
    //회원가입
    CreateUserResponse createUser(CreateUserRequest createUserRequest) throws BaseException;

    public boolean checkEmail(String email) throws BaseException;
    public boolean checkNickName(String nickName)throws BaseException;

    // 로그인
    public LoginUserResponse loginUser(LoginUserRequest loginUserRequest) throws BaseException;


    public FindEmailResponse findEmail(String nickName) throws BaseException;
    public FindPassWordResponse findPassWord(FindPassWordRequest findPassWordRequest) throws BaseException;

}