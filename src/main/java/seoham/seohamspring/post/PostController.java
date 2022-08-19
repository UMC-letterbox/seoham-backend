package seoham.seohamspring.post;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.config.BaseResponse;
import seoham.seohamspring.post.domain.*;

import java.util.List;

import static seoham.seohamspring.config.BaseResponseStatus.*;

@Controller
@RequestMapping("/posts")
@Api(tags = "post")
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
        if (createPostRequest.getContent().length() >450) {//게시물 길이
            return new BaseResponse<>(POST_POSTS_INVALID_CONTENT);
        }
        if (createPostRequest.getSender().length() >20) {//보낸이 길이
            return new BaseResponse<>(POST_SENDER_INVALID_CONTENT);
        }
        try{
            CreatePostResponse createPostResponse = postService.createPost(createPostRequest);
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
    public BaseResponse<PatchPostResponse> modifyPost(@PathVariable ("postIdx") int postIdx, @RequestBody PatchPostRequest patchPostRequest){
        if (patchPostRequest.getContent().length() >450) {//게시물 길이
            return new BaseResponse<>(POST_POSTS_INVALID_CONTENT);
        }
        if (patchPostRequest.getSender().length() >20) {//보낸이 길이
            return new BaseResponse<>(POST_SENDER_INVALID_CONTENT);
        }
        try{
            PatchPostResponse patchPostResponse = postService.modifyPost(patchPostRequest.getUserIdx(),postIdx,patchPostRequest);
            return new BaseResponse<>(patchPostResponse);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }


    /*
     * 편지 삭제 페이지
     */
    @ResponseBody
    @DeleteMapping("/delete/{postIdx}")
    public BaseResponse<DeletePostResponse> deletePost(@PathVariable ("postIdx") int postIdx){
        try{
            DeletePostResponse deletePostResponse = postService.deletePost(postIdx);
            return new BaseResponse<>(deletePostResponse);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    /*
    *편지 조회
     */
    @ResponseBody
    @GetMapping("/{postIdx}")
    public BaseResponse<GetPostContextResponse> getPost(@PathVariable("postIdx") int postIdx) {
        try{
            GetPostContextResponse getPostContextResponse = postService.readPost(postIdx);
            return new BaseResponse<>(getPostContextResponse);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /*
    태그 목록 조회 페이지
     */

    @ResponseBody
    @GetMapping("/tags")
    public BaseResponse<List<GetTagListResponse>> getTagList(@RequestParam int userIdx) {
        try{
            List<GetTagListResponse> getTagListResponse = postService.readTagList(userIdx);
            return new BaseResponse<>(getTagListResponse);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /*
    태그별 편지 조회
     */
    @ResponseBody
    @GetMapping("/tags/{tagIdx}")
    public BaseResponse<List<GetPostResponse>> getPostByTag(@PathVariable("tagIdx") int tagIdx) {
        try{
            List<GetPostResponse> getPostResponse = postService.readPostByTag(tagIdx);
            return new BaseResponse<>(getPostResponse);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /*
    날짜별 편지 조회
     */
    @ResponseBody
    @GetMapping("/date")
    public BaseResponse<List<GetPostResponse>> getPostByDate(@RequestParam int userIdx) {
        try{
            List<GetPostResponse> getPostResponse = postService.readPostByDate(userIdx);
            return new BaseResponse<>(getPostResponse);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /*
    게시물 보낸이 목록 조회 페이지
     */

    @ResponseBody
    @GetMapping("/senders")
    public BaseResponse<List<GetSenderListResponse>> getSenderList(@RequestParam int userIdx) {
        try{
            List<GetSenderListResponse> getSenderListResponse = postService.readSenderList(userIdx);
            return new BaseResponse<>(getSenderListResponse);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }




    /*
    보낸이별 편지 조회
     */
    @ResponseBody
    @GetMapping("/senders/{sender}")
    public BaseResponse<List<GetPostResponse>> getPostBySender(@PathVariable("sender") String sender, @RequestParam int userIdx) {
        try{
            List<GetPostResponse> getPostResponse = postService.readPostBySender(sender, userIdx);
            return new BaseResponse<>(getPostResponse);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /*
    태그 정보 추가
     */
    @ResponseBody
    @PostMapping("/tags/new")
    public BaseResponse<CreateTagResponse> createTag(@RequestBody CreateTagRequest createTagRequest){
        if (createTagRequest.getTagName().length() > 20) {//태그 길이
            return new BaseResponse<>(POST_TAGS_INVALID_CONTENT);
        }
        try{
            CreateTagResponse createTagResponse = postService.createTag(createTagRequest.getUserIdx(), createTagRequest);
            return new BaseResponse<>(createTagResponse);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    /*
    태그 정보 수정
     */
    @ResponseBody
    @PatchMapping("/tags/edit/{tagIdx}")
    public BaseResponse<PatchTagResponse> modifyTag(@PathVariable ("tagIdx") int tagIdx, @RequestBody PatchTagRequest patchTagRequest){
        if (patchTagRequest.getTagName().length() >20) {//게시물 길이
            return new BaseResponse<>(POST_TAGS_INVALID_CONTENT);
        }
        try{
            PatchTagResponse patchTagResponse = postService.modifyTag(patchTagRequest.getUserIdx(),tagIdx,patchTagRequest);
            return new BaseResponse<>(patchTagResponse);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    /*
    태그 정보 삭제
     */
    @ResponseBody
    @DeleteMapping("/tags/delete/{tagIdx}")
    public BaseResponse<DeleteTagResponse> deleteTag(@PathVariable ("tagIdx") int tagIdx, @RequestParam int userIdx){
        try{
            DeleteTagResponse deleteTagResponse = postService.deleteTag(userIdx, tagIdx);
            return new BaseResponse<>(deleteTagResponse);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    /*
    보낸이 정보 수정
     */
    @ResponseBody
    @PatchMapping("/senders/edit/{sender}")
    public BaseResponse<PatchSenderResponse> modifySender(@PathVariable ("sender") String originalSender,@RequestBody PatchSenderRequest patchSenderRequest){
        if (patchSenderRequest.getChangedSender().length() >20) {//게시물 길이
            return new BaseResponse<>(POST_SENDER_INVALID_CONTENT);
        }
        try{
            PatchSenderResponse patchSenderResponse = postService.modifySender(patchSenderRequest.getUserIdx(),originalSender,patchSenderRequest);
            return new BaseResponse<>(patchSenderResponse);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }


    /*
    보낸이 정보 삭제
     */
    @ResponseBody
    @DeleteMapping("/senders/delete/{sender}")
    public BaseResponse<DeleteSenderResponse> deleteSender(@PathVariable ("sender") String sender, @RequestBody DeleteSenderRequest deleteSenderRequest){
        try{
            DeleteSenderResponse deleteSenderResponse = postService.deleteSender(sender, deleteSenderRequest);
            return new BaseResponse<>(deleteSenderResponse);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }
}
