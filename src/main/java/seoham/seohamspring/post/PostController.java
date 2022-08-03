package seoham.seohamspring.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import seoham.seohamspring.post.Post;
import seoham.seohamspring.post.PostService;

import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    /**
     * 편지 작성 페이지
     */

    @GetMapping("/new")
    public String write(){
        return "posts/write";
    }

    //편지 작성을 한 후, Post Method로 DB에 저장
    //그 이후 /post/tag로 리디렉션을 해준다.
    @PostMapping("/new")
    public String write(Post post) throws Exception{
        postService.post(post);
        return "redirect:/posts/tag";
    }


    /**
     * 게시물 수정 페이지

    @GetMapping("/edit/{postIdx}")
    public String edit(@PathVariable("postIdx") int postIdx, Model model){
        Post post = postService.findByPostIdx(postIdx);
        return "success";
    }
     */


    /**
     * 게시물 삭제 페이지
     */



    @GetMapping("/posts/edit/{postIdx}")
    public String edit(@PathVariable("postIdx") int postIdx, Model model) {
        Optional<Post> post = postService.findByPostIdx(postIdx);

        model.addAttribute("post", post);
        return "posts/update";
    }

    // 위는 GET 메서드이며, PUT 메서드를 이용해 게시물 수정한 부분에 대해 적용

    @PutMapping("/posts/edit/{postIdx}")
    public String update(Post post) {
        postService.post(post);

        return "redirect:/posts/tag";
    }

    // 게시물 삭제는 deletePost 메서드를 사용하여 간단하게 삭제할 수 있다.

    @DeleteMapping("/post/{postIdx}")
    public String delete(@PathVariable("postIdx") int postIdx) {
        postService.delete(postIdx);

        return "redirect:/board/list";
    }
}
