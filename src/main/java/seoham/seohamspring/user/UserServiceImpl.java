package seoham.seohamspring.user;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.config.BaseResponse;
import seoham.seohamspring.user.domain.*;
import seoham.seohamspring.util.JwtService;
import seoham.seohamspring.util.SHA256;

import static seoham.seohamspring.config.BaseResponseStatus.*;
import seoham.seohamspring.util.JwtService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtService jwtService){

        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    //회원가입
    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) throws BaseException {
/*
        try{
            int userIdx = userRepository.createUser(createUserRequest);
            return new CreateUserResponse(userIdx);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
*/

        if(!checkEmail(createUserRequest.getEmail())){
            throw new BaseException(EXIST_EMAIL);
        }
        String pwd;
        try{
            pwd = new SHA256().encrypt(createUserRequest.getPassWord());
            createUserRequest.setPassWord(pwd);
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
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
    public boolean checkEmail(String email) throws BaseException{
        try{
            boolean valid = true;
            int exist = userRepository.checkEmail(email);
            if (exist != 0){
                valid = false;
            }
            return valid;

        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    /*
    닉네임 중복검사
     */
    public boolean checkNickName(String nickName) throws BaseException{
        try{
            boolean valid  = true;
            int exist = userRepository.checkNickName(nickName);
            if (exist != 0){
                valid = false;
            }
            return valid;
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }


    /*
    이메일 찾기
     */
    public FindEmailResponse findEmail(String nickName) throws BaseException{
        if(userRepository.checkNickName(nickName) != 1){
            throw new BaseException(FIND_USER_NO_NICKNAME);
        }
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

        String pwd;

        if(userRepository.checkEmail(findPassWordRequest.getEmail()) != 1){
            throw new BaseException(FIND_USER_NO_EMAIL);
        }
        try{
            pwd = SHA256.encrypt(findPassWordRequest.getPassWord());
            findPassWordRequest.setPassWord(pwd);
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


    /*
    로그인
     */

    public LoginUserResponse loginUser(LoginUserRequest loginUserRequest) throws BaseException {
        User user=userRepository.getUser(loginUserRequest);
        String encryptPwd;
        try{
            encryptPwd=new SHA256().encrypt(loginUserRequest.getPassWord());
        } catch(Exception exception) {
            System.out.println(exception);
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        if(user.getPassWord().equals(encryptPwd)){
            int userIdx=user.getUserIdx();
            System.out.println(userIdx);
            String jwt =jwtService.createJwt(userIdx);
            return new LoginUserResponse(userIdx,jwt);
        }
        else
            throw new BaseException(FAILED_TO_LOGIN);
    }

}
