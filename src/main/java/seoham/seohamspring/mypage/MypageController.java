package seoham.seohamspring.mypage;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.config.BaseResponse;
import seoham.seohamspring.mypage.domain.*;
import seoham.seohamspring.util.JwtService;

import static seoham.seohamspring.config.BaseResponseStatus.*;


@Controller
@Api(tags = "mypage")
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    private final MypageService mypageService;

    @Autowired
    private final JwtService jwtService;

    public MypageController(MypageService mypageService, JwtService jwtService) {
        this.mypageService = mypageService;
        this.jwtService = jwtService;
    }

    @ResponseBody
    @GetMapping("/jwt/user")
    public int jwtToUser() throws BaseException {
        return jwtService.getUserIdx();

    }

    // 닉네임 중복검사
    // OK
    @ResponseBody
    @PostMapping("/nickname/check")
    public BaseResponse<PostCheckValidRes> checkNickname(@Validated @RequestBody PostCheckNicknameReq postCheckNicknameReq) {
        if (postCheckNicknameReq.getNewNickname() == null) {
            return new BaseResponse<>(POST_MYPAGE_EMPTY_NICKNAME);
        }

        try {
            return new BaseResponse<>(mypageService.chekcNickname(postCheckNicknameReq));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

    }

    // 비밀번호 확인
    // OK
    @ResponseBody
    @PostMapping("/password/check")
    public BaseResponse<PostCheckValidRes> checkPassword(@RequestBody PostCheckPasswordReq postCheckPasswordReq) {

        if (postCheckPasswordReq == null) {
            return new BaseResponse<>(POST_MYPAGE_EMPTY_PASSWORD);
        }

        try {
            int userIdx = jwtService.getUserIdx();
            return new BaseResponse<>(mypageService.checkPassword(postCheckPasswordReq, userIdx));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }


    //닉네임 수정
    // OK
    @ResponseBody
    @PatchMapping(value = "/nickname/modify")
    public BaseResponse<String> modifyNickname(@Validated @RequestBody PatchNicknameReq patchNicknameReq) {
        if (patchNicknameReq.getNewNickname() == null) {
            return new BaseResponse<>(PATCH_MYPAGE_EMPTY_NICKNAME);
        }

        try {
            int userIdx = jwtService.getUserIdx();
            return new BaseResponse<>(mypageService.modifyNickname(patchNicknameReq, userIdx));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

    }


    //비밀번호 수정
    // OK
    @ResponseBody
    @PatchMapping("/password/modify")
    public BaseResponse<String> modifyPassword(@RequestBody PatchPasswordReq patchPasswordReq) {
        if (patchPasswordReq.getNewPassword() == null) {
            return new BaseResponse<>(PATCH_MYPAGE_EMPTY_PASSWORD);
        }

        try {
            int userIdx = jwtService.getUserIdx();
            return new BaseResponse<>(mypageService.modifyPassword(patchPasswordReq, userIdx));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

    }


    //회원 탈퇴
    @ResponseBody
    @DeleteMapping("/delete")
    public BaseResponse<String> deleteUser() {
        try {
            int userIdx = jwtService.getUserIdx();
            return new BaseResponse<>(mypageService.deleteUser(userIdx));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/info")
    public BaseResponse<GetCountInfoRes> userInfo() {
        try {
            int userIdx = jwtService.getUserIdx();
            return new BaseResponse<>(mypageService.userInfo(userIdx));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

    }
}
