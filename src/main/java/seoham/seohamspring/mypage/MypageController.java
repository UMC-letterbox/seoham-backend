package seoham.seohamspring.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    private final MypageService mypageService;

    public MypageController(MypageService mypageService) {
        this.mypageService = mypageService;
    }

    public


}
