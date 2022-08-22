package seoham.seohamspring.post;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.config.BaseResponse;
import seoham.seohamspring.post.domain.*;
import seoham.seohamspring.user.UserService;
import seoham.seohamspring.util.JwtService;

import java.util.List;

import static seoham.seohamspring.config.BaseResponseStatus.*;

@Controller
@Api(tags = "post")
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private final PostService postService;
    @Autowired
    private final JwtService jwtService;



    @Autowired
    public PostController(PostService postService, JwtService jwtService) {
        this.postService = postService;
        this.jwtService = jwtService;
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
            int userIdx = jwtService.getUserIdx();
            CreatePostResponse createPostResponse = postService.createPost(userIdx, createPostRequest);
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
            int userIdx = jwtService.getUserIdx();
            PatchPostResponse patchPostResponse = postService.modifyPost(userIdx,postIdx,patchPostRequest);
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
    public BaseResponse<List<GetTagListResponse>> getTagList() {
        try{
            int userIdx = jwtService.getUserIdx();
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
    public BaseResponse<List<GetPostResponse>> getPostByDate() {
        try{
            int userIdx = jwtService.getUserIdx();
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
    public BaseResponse<List<GetSenderListResponse>> getSenderList() {
        try{
            int userIdx = jwtService.getUserIdx();
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
    public BaseResponse<List<GetPostResponse>> getPostBySender(@PathVariable("sender") String sender) {
        try{
            int userIdx = jwtService.getUserIdx();
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
    public BaseResponse<CreateTagResponse> createTag(@RequestParam int userIdx, @RequestBody CreateTagRequest createTagRequest){
        if (createTagRequest.getTagName().length() > 20) {//태그 길이
            return new BaseResponse<>(POST_TAGS_INVALID_CONTENT);
        }
        try{
            //int userIdx = jwtService.getUserIdx();
            CreateTagResponse createTagResponse = postService.createTag(userIdx, createTagRequest);
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
            int userIdx = jwtService.getUserIdx();
            PatchTagResponse patchTagResponse = postService.modifyTag(userIdx, tagIdx,patchTagRequest);
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
    public BaseResponse<DeleteTagResponse> deleteTag(@PathVariable ("tagIdx") int tagIdx){
        try{
            int userIdx = jwtService.getUserIdx();
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
            int userIdx = jwtService.getUserIdx();
            PatchSenderResponse patchSenderResponse = postService.modifySender(userIdx,originalSender,patchSenderRequest);
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
    public BaseResponse<DeleteSenderResponse> deleteSender(@PathVariable ("sender") String sender){
        try{
            int userIdx = jwtService.getUserIdx();
            DeleteSenderResponse deleteSenderResponse = postService.deleteSender(sender, userIdx);
            return new BaseResponse<>(deleteSenderResponse);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }
}
