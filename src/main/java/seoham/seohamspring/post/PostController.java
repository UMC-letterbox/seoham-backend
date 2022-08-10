package seoham.seohamspring.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.config.BaseResponse;
import seoham.seohamspring.config.BaseResponseStatus;
import seoham.seohamspring.post.domain.*;

import static seoham.seohamspring.config.BaseResponseStatus.DATABASE_ERROR;

@Controller
@RequestMapping("/posts") // /posts 경로로 들어오는 경우 아래의 Method들로 분기될 수 있도록 설정
public class PostController {

    @Autowired
    private final PostService postService;

    //private final JwtService jwtService;


    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /*
     * 편지 작성 페이지
     */

    @ResponseBody
    @PostMapping("/new")
    public BaseResponse<CreatePostResponse> createPost(@RequestBody CreatePostRequest createPostRequest){
        try{
            if (createPostRequest.getContent().length() >450) {//게시물 길이
                return new BaseResponse<>(BaseResponseStatus.POST_POSTS_INVALID_CONTENT);

            }
            CreatePostResponse createPostResponse = postService.createPost(createPostRequest.getUserIdx(), createPostRequest);
            return new BaseResponse<>(createPostResponse);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    /*
    편지 수정 페이지
     */
    @ResponseBody
    @PatchMapping("/edit/{postIdx}")
    public BaseResponse<String> modifyPost(@PathVariable ("postIdx") int postIdx, PatchPostRequest patchPostRequest){
        try{
            if (patchPostRequest.getContent().length() >450) {//게시물 길이
                return new BaseResponse<>(BaseResponseStatus.POST_POSTS_INVALID_CONTENT);

            }

            postService.modifyPost(patchPostRequest.getUserIdx(),postIdx,patchPostRequest);
            String result = "편지 수정을 완료하였습니다.";
            return new BaseResponse<>(result);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }




    /*
     * 편지 삭제 페이지
     */
    @ResponseBody
    @DeleteMapping("/delete/{postIdx}")
    public BaseResponse<String> deletePost(@PathVariable ("postIdx") int postIdx){
        try{
            postService.deletePost(postIdx);
            String result = "편지 삭제를 완료하였습니다.";
            return new BaseResponse<>(result);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    /*
    *게시물 상세 조회 페이지
     */
    /*
    @GetMapping("/{postIdx}")
    public String detail(@PathVariable("postIdx") int postIdx, Model model) {
        Optional<Post> post = postService.findByPostIdx(postIdx);
        model.addAttribute("post", post);

        return "posts/detail";
    }

     */

    /*
    게시물 태그 목록 조회 페이지
     */
    /*
    @GetMapping("/tags")
    public String tagList(Model model) {
        List<Tag> tags = postService.TagList();
        model.addAttribute("tags",tags);
        return "posts/tagList";
    }

     */

    /*
    태그별 편지 조회
     */
    /*
    @GetMapping("/tags/{tagIdx}")
    public String listByTag(@PathVariable("tagIdx") int tagIdx,Model model){
        Optional<Post> postsByTag = postService.findByTag(tagIdx);

        return "posts/listByTag";
    }

     */


    /*
    날짜별 편지 조회
     */
    /*
    @GetMapping("/date")
    public String listBydate(){
        return null;
    }

     */

    /*
    게시물 보낸이 목록 조회 페이지
     */
    /*
    @GetMapping("/senders")
    public String senderList(Model model) {
        List<Sender> senders = postService.SenderList();
        model.addAttribute("senders",senders);
        return "posts/senderList";
    }

     */

    /*
    보낸이별 편지 조회
     */
    /*
    @GetMapping("/tags/{sender}")
    public String listBySender(@PathVariable("sender") String sender,Model model){
        Optional<Post> postsBySender = postService.findBySender(sender);
        return "posts/listBySender";
    }

     */



}
