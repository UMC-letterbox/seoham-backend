package seoham.seohamspring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.config.BaseResponse;
import seoham.seohamspring.user.domain.*;

import static seoham.seohamspring.config.BaseResponseStatus.*;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;

    //@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    // 회원가입 API
    // Body
    @ResponseBody
    @PostMapping("/join")
    public BaseResponse<CreateUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        if(createUserRequest.getEmail() == null){
            return new BaseResponse<>(USER_EMPTY_EMAIL);
        }
        try {
            CreateUserResponse createUserResponse = userService.createUser(createUserRequest);
            return new BaseResponse<>(createUserResponse);
        }catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


     //이메일 중복검사 인증
    // Body
    @ResponseBody
    @GetMapping("/check-email")
    public BaseResponse<CheckEmailResponse> checkEmail(@RequestParam String email) {
        if(email == null){
            return new BaseResponse<>(USER_EMPTY_EMAIL);
        }
        try{
            CheckEmailResponse checkEmailResponse = userService.checkEmail(email);
            return new BaseResponse<>(checkEmailResponse);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }



    //닉네임 중복검사
    // Body
    @ResponseBody
    @GetMapping("/check-nickname")
    public BaseResponse<CheckNickNameResponse> checkNickName(@RequestParam String nickName) {
        if(nickName == null){
            return new BaseResponse<>(USER_EMPTY_NICKNAME);
        }
        try{
            CheckNickNameResponse checkNickNameResponse = userService.checkNickName(nickName);
            return new BaseResponse<>(checkNickNameResponse);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    // * 로그인 API
    // Body
    @ResponseBody
    @PostMapping("/login")
    public BaseResponse<LoginUserResponse> loginUser(@RequestBody LoginUserRequest loginUserRequest) {
        if(loginUserRequest.getEmail() == null){
            return new BaseResponse<>(USER_EMPTY_EMAIL);
        }
        if(loginUserRequest.getPassWord() == null){
            return new BaseResponse<>(USER_EMPTY_PASSWORD);
        }
        try{
            LoginUserResponse loginUserResponse = userService.loginUser(loginUserRequest);
            return new BaseResponse<>(loginUserResponse);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }



     //  이메일 찾기
    // Body
    @ResponseBody
    @GetMapping("/find-email")
    public BaseResponse<FindEmailResponse> findEmail(@RequestParam String nickName) {
        if(nickName == null){
            return new BaseResponse<>(USER_EMPTY_NICKNAME);
        }
        try{
            FindEmailResponse findEmailResponse = userService.findEmail(nickName);
            return new BaseResponse<>(findEmailResponse);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }



     // 비밀번호 수정
    // Body
    @ResponseBody
    @PatchMapping("/find-password")//비밀번호 body로
    public BaseResponse<FindPassWordResponse> findPassWord(@RequestBody FindPassWordRequest findPassWordRequest) {
        if(findPassWordRequest.getEmail() == null){
            return new BaseResponse<>(USER_EMPTY_EMAIL);
        }
        if(findPassWordRequest.getPassWord() == null){
            return new BaseResponse<>(USER_EMPTY_PASSWORD);
        }
        try{
            FindPassWordResponse findPassWordResponse = userService.findPassWord(findPassWordRequest);
            return new BaseResponse<>(findPassWordResponse);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
