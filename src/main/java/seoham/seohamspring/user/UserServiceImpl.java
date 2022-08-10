package seoham.seohamspring.user;


import org.springframework.beans.factory.annotation.Autowired;

import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.user.domain.*;

import static seoham.seohamspring.config.BaseResponseStatus.*;


public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //회원가입
    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) throws BaseException {
        try{
            int userIdx = userRepository.createUser(createUserRequest);
            return new CreateUserResponse(userIdx);
        } catch (Exception exception) {
            //System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
    /*
    이메일 중복검사
     */
    public CheckEmailResponse checkEmail(String email) throws BaseException{
        try{
            int possible = userRepository.checkEmail(email);
            return new CheckEmailResponse(possible);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    /*
    닉네임 중복검사
     */
    public CheckNickNameResponse checkNickName(String nickName) throws BaseException{
        try{
            int possible = userRepository.checkNickName(nickName);
            return new CheckNickNameResponse(possible);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }


    //로그인
    public LoginUserResponse loginUser(LoginUserRequest loginUserRequest) throws BaseException {
        try{
            int userIdx = userRepository.loginUser(loginUserRequest);
            return new LoginUserResponse(userIdx);
        } catch (Exception exception) {
            //System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /*
    이메일 찾기
     */
    public FindEmailResponse findEmail(String nickName) throws BaseException{
        try{
            String email = userRepository.findEmail(nickName);
            return new FindEmailResponse(email);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /*
    비밀번호 찾기
     */
    public FindPassWordResponse findPassWord(String passWord) throws BaseException{
        try{
            int success = userRepository.findPassWord(passWord);
            return new FindPassWordResponse(success);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
