package seoham.seohamspring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    // 회원가입 API
    // Body
    @ResponseBody
    @PostMapping("/join")
    public BaseResponse<CreateUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        if(createUserRequest.getEmail() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
        }
        try{
            CreateUserResponse createUserResponse = userService.createUser(createUserRequest);
            return new BaseResponse<>(createUserResponse);
        } catch(BaseException exception){
            //return new BaseResponse<>((exception.getStatus()));
        }
    }


     //이메일 중복검사 인증
    // Body
    @ResponseBody
    @PostMapping("/check/{email}")
    public BaseResponse<CreateUserResponse> createUser(@PathVariable("email")String email) {
        if(email == null){
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
        }
        try{
            int vaild = userService.checkEmail(email);
            return new BaseResponse<>(createUserResponse);
        } catch(BaseException exception){
            //return new BaseResponse<>((exception.getStatus()));
        }
    }



    //닉네임 중복검사
    // Body
    @ResponseBody
    @PostMapping("/check/{nickname}")
    public int checkNickName(@PathVariable("nickname")String nickName) {
        if(nickName == null){
            return new BaseResponse<>(POST_USERS_EMPTY_NICKNAME);
        }
        try{
            int vaild = userService.checkNickName(nickName);
            return vaild ;

        } catch(BaseException exception){
            //return new BaseResponse<>((exception.getStatus()));
        }
    }


    // * 로그인 API
    // Body
    @ResponseBody
    @PostMapping("/login")
    public BaseResponse<LoginUserResponse> loginUser(@RequestBody LoginUserRequest loginUserRequest) {
        if(loginUserRequest.getEmail() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
        }
        if(loginUserRequest.getPassWord() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD);
        }
        try{
            LoginUserResponse loginUserResponse = userService.loginUser(loginUserRequest);
            return new BaseResponse<>(loginUserResponse);
        } catch(BaseException exception){
            //return new BaseResponse<>((exception.getStatus()));
        }
    }



     // 닉네임찾기
    // Body
    @ResponseBody
    @GetMapping("/find/{nickname}")
    public String findEmail(@RequestParam("nickname")String nickName) {
        if(nickName == null){
            return new BaseResponse<>(POST_USERS_EMPTY_NICKNAME);
        }
        try{
            String email = userService.findEmail(nickName);
            return email ;

        } catch(BaseException exception){
            //return new BaseResponse<>((exception.getStatus()));
        }
    }



     // 비밀번호 수정
    // Body
    @ResponseBody
    @PatchMapping("/find/{password}")//비밀번호 body로?
    public int findEmail(@RequestParam("password")String passWord) {
        if(passWord == null){
            return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD);
        }
        try{
            int success = userService.findPassWord(passWord);
            return success ;

        } catch(BaseException exception){
            //return new BaseResponse<>((exception.getStatus()));
        }
    }




}
