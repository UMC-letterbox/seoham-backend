package seoham.seohamspring.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("posts") // /posts 경로로 들어오는 경우 아래의 Method들로 분기될 수 있도록 설정
public class PostController {

    @Autowired
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /*
     * 편지 작성 페이지
     */

    @GetMapping("/new")
    public String write(){
        return "posts/write"; //PostMaping("/posts/new") write 메소드로 연결된다.
    }

    //편지 작성을 한 후, Post Method로 DB에 저장
    //그 이후 /post/tag로 리디렉션을 해준다.
    @PostMapping("/new")
    public String write(Post post) throws Exception{
        postService.post(post);
        return "redirect:/posts/tag";
    }


    /*
    게시물 수정 페이지
     */
    @GetMapping("/edit/{postIdx}")
    public String edit(@PathVariable("postIdx") Long postIdx, Model model) {
        Optional<Post> post = postService.findByPostIdx(postIdx);

        model.addAttribute("post", post);
        return "posts/update";
    }

    // 위는 GET 메서드이며, PUT 메서드를 이용해 게시물 수정한 부분에 대해 적용

    @PutMapping("/edit/{postIdx}")
    public String update(Post post) {
        postService.post(post);

        return "redirect:/posts/tag";
    }



    /*
     * 게시물 삭제 페이지
     */
    @DeleteMapping("/{postIdx}")
    public String delete(@PathVariable("postIdx") int postIdx) {
        postService.delete(postIdx);

        return "redirect:/posts/tag";
    }

    /*
    *게시물 상세 조회 페이지
     */
    @GetMapping("/{postIdx}")
    public String detail(@PathVariable("postIdx") int postIdx, Model model) {
        Optional<Post> post = postService.findByPostIdx(postIdx);
        model.addAttribute("post", post);

        return "posts/detail";
    }

    /*
    게시물 태그 목록 조회 페이지
     */
    @GetMapping("/tags")
    public String tagList(Model model) {
        List<Tag> tags = postService.TagList();
        model.addAttribute("tags",tags);
        return "posts/tagList";
    }

    /*
    태그별 편지 조회
     */
    @GetMapping("/tags/{tagIdx}")
    public String listByTag(@PathVariable("tagIdx") int tagIdx,Model model){
        Optional<Post> postsByTag = postService.findByTag(tagIdx);

        return "posts/listByTag";
    }


    /*
    날짜별 편지 조회
     */

    @GetMapping("/date")
    public String listBydate(){
        return null;
    }

    /*
    게시물 보낸이 목록 조회 페이지
     */
    @GetMapping("/senders")
    public String senderList(Model model) {
        List<Sender> senders = postService.SenderList();
        model.addAttribute("senders",senders);
        return "posts/senderList";
    }

    /*
    보낸이별 편지 조회
     */
    @GetMapping("/tags/{sender}")
    public String listBySender(@PathVariable("sender") String sender,Model model){
        Optional<Post> postsBySender = postService.findBySender(sender);
        return "posts/listBySender";
    }



}
