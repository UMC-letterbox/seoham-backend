package seoham.seohamspring.user;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.user.domain.*;

import static seoham.seohamspring.config.BaseResponseStatus.*;

@Service
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
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
    /*
    이메일 중복검사
     */
    public CheckEmailResponse checkEmail(String email) throws BaseException{
        try{
            boolean vaild = true;
            int exist = userRepository.checkEmail(email);
            if (exist != 0){
                vaild = false;
            }
            return new CheckEmailResponse(vaild);

        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    /*
    닉네임 중복검사
     */
    public CheckNickNameResponse checkNickName(String nickName) throws BaseException{
        try{
            boolean vaild  = true;
            int exist = userRepository.checkNickName(nickName);
            if (exist != 0){
                vaild = false;
            }
            return new CheckNickNameResponse(vaild);
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
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
    /*
    비밀번호 찾기
     */
    public FindPassWordResponse findPassWord(FindPassWordRequest findPassWordRequest) throws BaseException{
        try{
            boolean success = true;
            int result = userRepository.findPassWord(findPassWordRequest);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
            return new FindPassWordResponse(success);
        } catch (Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
