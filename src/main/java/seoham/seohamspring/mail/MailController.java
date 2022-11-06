package seoham.seohamspring.mail;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import seoham.seohamspring.config.BaseResponse;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Controller
@RequiredArgsConstructor
@Api(tags = "mail")
@RequestMapping("/mail")
public class MailController {


    private final MailServcie mailServcie;

    @ResponseBody
    @PostMapping("/send")
    public String mailConfirm(@RequestBody EmailAuthRequestDto emailDto) throws MessagingException, UnsupportedEncodingException {

        String authCode = mailServcie.sendEmail(emailDto.getEmail());
        return authCode;
    }

}
