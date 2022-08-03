package seoham.seohamspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import seoham.seohamspring.domain.CreateUserRequest;
import seoham.seohamspring.domain.CreateUserResponse;
import seoham.seohamspring.domain.PostRequest;
import seoham.seohamspring.repository.PostRepository;
import seoham.seohamspring.repository.UserRepository;
import seoham.seohamspring.service.PostService;
import seoham.seohamspring.service.UserService;

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

    /**
     * 회원가입 API
     * [POST] /user
     * @return BaseResponse<CreateUserResponse>
     */
    // Body
    @ResponseBody
    @RequestMapping(value = "/join", method = RequestMethod.POST)
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

}
