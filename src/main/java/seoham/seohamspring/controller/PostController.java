package seoham.seohamspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import seoham.seohamspring.domain.PostRequest;
import seoham.seohamspring.repository.PostRepository;
import seoham.seohamspring.service.PostService;

@Controller
@RequestMapping("/posts/*")
public class PostController {

    @Autowired
    private final PostService postService;

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    public PostController(PostService postService, PostRepository postRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
    }

    //편지 작성 페이지로 이동
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getPost() throws Exception {
        return "posts/new";
    }

    //편지 작성
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String Post(PostRequest postRequest) throws Exception{
        postService.post(postRequest);
        return "redirect";
    }
}
