package seoham.seohamspring.mail;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.config.BaseResponse;
import seoham.seohamspring.mail.domain.PostCheckAuthReq;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Controller
@RequiredArgsConstructor
@Api(tags = "mail")
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;
    private final RedisUtil redisUtil;
    @ResponseBody
    @PostMapping("/send")
    public void mailConfirm(@RequestBody EmailAuthRequestDto emailDto) throws MessagingException, UnsupportedEncodingException {

        String authCode = mailService.sendEmail(emailDto.getEmail());
        redisUtil.setDataExpire(authCode, emailDto.getEmail(), 60 * 5L);
//        return authCode;
    }

    @ResponseBody
    @PostMapping("/check")
    public BaseResponse<Boolean> CheckAuth(@RequestBody PostCheckAuthReq postCheckAuthReq) {

        try {
            return new BaseResponse<>(mailService.checkAuth(postCheckAuthReq));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

    }


}
