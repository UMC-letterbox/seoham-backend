package seoham.seohamspring.user;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.config.BaseResponse;
import seoham.seohamspring.util.*;
import seoham.seohamspring.user.domain.*;

import static seoham.seohamspring.config.BaseResponseStatus.*;
import static seoham.seohamspring.util.ValidationRegex.isRegexEmail;
import static seoham.seohamspring.util.ValidationRegex.isRegexNickName;
import seoham.seohamspring.util.JwtService;
@Controller
@Api(tags = "user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final JwtService jwtService;

    //@Autowired
    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    // 회원가입 API
    // Body
    @ResponseBody
    @PostMapping("/join")
    public BaseResponse<CreateUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        if(createUserRequest.getEmail() == ""){    // 입력 안했을때도 email 컬럼을 넘겨줄지 프론트와 이야기  넘겨준다면 ""로 변경하기
            return new BaseResponse<>(CREATE_USER_EMPTY_EMAIL);
        }
        if(createUserRequest.getPassWord() == ""){
            return new BaseResponse<>(CREATE_USER_EMPTY_PASSWORD);
        }
        if(createUserRequest.getNickName() == ""){
            return new BaseResponse<>(CREATE_USER_EMPTY_NICKNAME);
        }
        try {
            CreateUserResponse createUserResponse = userService.createUser(createUserRequest);
            return new BaseResponse<>(createUserResponse);
        }catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


     //이메일 중복검사 회원가입용
    // Body
    @ResponseBody
    @GetMapping("/check-join-email")
    public BaseResponse<CheckJoinResponse> checkJoinEmail(@RequestParam String email) {
        if(email == ""){    // 입력 안했을때도 email 컬럼을 넘겨줄지 프론트와 이야기  넘겨준다면 ""로 변경하기
            return new BaseResponse<>(CHECK_USER_EMPTY_EMAIL);
        }
        if(!isRegexEmail(email)){
            return new BaseResponse<>(CHECK_USER_INVALID_EMAIL);
        }
        try{
            boolean vaild = userService.checkEmail(email);
            CheckJoinResponse checkJoinResponse = new CheckJoinResponse(vaild);
            return new BaseResponse<>(checkJoinResponse);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //닉네임 중복검사 회원가입용
    // Body
    @ResponseBody
    @GetMapping("/check-join-nickname")
    public BaseResponse<CheckJoinResponse> checkJoinNickName(@RequestParam String nickName) {
        if(nickName == ""){
            return new BaseResponse<>(CHECK_USER_EMPTY_NICKNAME);
        }
        if(!isRegexNickName(nickName)){
            return new BaseResponse<>(CHECK_USER_INVALID_NICKNAME);
        }
        try{
            boolean vaild = userService.checkNickName(nickName);
            CheckJoinResponse checkJoinResponse = new CheckJoinResponse(vaild);
            return new BaseResponse<>(checkJoinResponse);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    //이메일 중복검사  비밀번호 찾기용
    // Body
    @ResponseBody
    @GetMapping("/check-find-password")
    public BaseResponse<CheckFindResponse> checkFindEmail(@RequestParam String email) {
        if(email == ""){    // 입력 안했을때도 email 컬럼을 넘겨줄지 프론트와 이야기  넘겨준다면 ""로 변경하
            return new BaseResponse<>(CHECK_USER_EMPTY_EMAIL);
        }
        if(!isRegexEmail(email)){
            return new BaseResponse<>(CHECK_USER_INVALID_EMAIL);
        }
        try{
            boolean exist = !userService.checkEmail(email);
            CheckFindResponse checkFindResponse = new CheckFindResponse(exist);
            return new BaseResponse<>(checkFindResponse);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //닉네임 중복검사  아이디찾기용
    // Body
    @ResponseBody
    @GetMapping("/check-find-email")
    public BaseResponse<CheckFindResponse> checkFindNickName(@RequestParam String nickName) {
        if(nickName == ""){
            return new BaseResponse<>(CHECK_USER_EMPTY_NICKNAME);
        }
        if(!isRegexNickName(nickName)){
            return new BaseResponse<>(CHECK_USER_INVALID_NICKNAME);
        }
        try{
            boolean exist = !userService.checkNickName(nickName);
            CheckFindResponse checkFindResponse = new CheckFindResponse(exist);
            return new BaseResponse<>(checkFindResponse);

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
            return new BaseResponse<>(FIND_USER_EMPTY_NICKNAME);
        }
        if(!isRegexNickName(nickName)){
            return new BaseResponse<>(FIND_USER_INVALID_NICKNAME);
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
            return new BaseResponse<>(FIND_USER_EMPTY_EMAIL);
        }
        if(!isRegexEmail(findPassWordRequest.getEmail())){
            return new BaseResponse<>(FIND_USER_INVALID_EMAIL);
        }
        if(findPassWordRequest.getPassWord() == null){
            return new BaseResponse<>(FIND_USER_EMPTY_PASSWORD);
        }
        try{
            FindPassWordResponse findPassWordResponse = userService.findPassWord(findPassWordRequest);
            return new BaseResponse<>(findPassWordResponse);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /*
    로그인
     */

    @ResponseBody
    @PostMapping("/login")
    public BaseResponse<LoginUserResponse> loginUser(@RequestBody LoginUserRequest loginUserRequest){
        try{
            if(loginUserRequest.getEmail() == ""){
                return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
            }
            if(!isRegexEmail(loginUserRequest.getEmail())){
                return new BaseResponse<>(CREATE_USER_INVALID_EMAIL);
            }

            if(loginUserRequest.getPassWord()==""){
                return new BaseResponse<>(CREATE_USER_EMPTY_PASSWORD);
            }
            LoginUserResponse loginUserResponse = userService.loginUser(loginUserRequest);
            return new BaseResponse<>(loginUserResponse);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }


    // 인증코드 확인용
    // Body
    @ResponseBody
    @PostMapping("/check-code")
    public BaseResponse<CheckCodeResponse> checkCode(@RequestBody CheckCodeRequest checkCodeRequest) {
        boolean valid = false;
        if(checkCodeRequest.getNum() == 123456){valid = true; System.out.println("true");}
        CheckCodeResponse checkCodeResponse = new CheckCodeResponse(valid);
        return new BaseResponse<>(checkCodeResponse);
    }





}


