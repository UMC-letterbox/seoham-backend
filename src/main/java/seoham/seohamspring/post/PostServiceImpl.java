package seoham.seohamspring.post;

import org.springframework.beans.factory.annotation.Autowired;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.post.domain.*;
import org.springframework.stereotype.Service;


import java.util.List;

import static seoham.seohamspring.config.BaseResponseStatus.*;

@Service

public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    /*
    게시물 작성
     */
    @Override
    public CreatePostResponse createPost(int userIdx, CreatePostRequest createPostRequest) throws BaseException {
        if(postRepository.checkTagNotExist(userIdx,createPostRequest.getTagIdx())==0){
            throw new BaseException(POST_EMPTY_TAG_IDX);
        }
        try{
            int postIdx = postRepository.savePost(userIdx, createPostRequest);
            return new CreatePostResponse(postIdx);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /*
    게시물 수정
     */
    @Override
    public PatchPostResponse modifyPost(int userIdx,int postIdx, PatchPostRequest patchPostRequest) throws BaseException {
        if(postRepository.checkPostExist(postIdx) == 0){
            throw new BaseException(POST_EMPTY_POST_IDX);
        }
        if(postRepository.checkTagNotExist(userIdx,patchPostRequest.getTagIdx())==0){
            throw new BaseException(POST_EMPTY_TAG_IDX);
        }
        if(postRepository.checkPostUser(userIdx, postIdx) == 0){
            throw new BaseException(INVALID_USER_JWT);
        }

        try{
            int success = postRepository.updatePost(userIdx, postIdx, patchPostRequest);
            if(success == 0){
                throw new BaseException(MODIFY_FAIL_POST);
            }
            return new PatchPostResponse(success);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }

    }

    /*
    게시물 삭제
     */
    @Override
    public DeletePostResponse deletePost(int userIdx, int postIdx) throws BaseException {
        if(postRepository.checkPostExist(postIdx) == 0){
            throw new BaseException(POST_EMPTY_POST_IDX);
        }
        if(postRepository.checkPostUser(userIdx, postIdx) == 0){
            throw new BaseException(INVALID_USER_JWT);
        }
        try{
            int success = postRepository.deletePost(postIdx);
            if(success == 0){
                throw new BaseException(DELETE_FAIL_POST);
            }
            return new DeletePostResponse(success);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /*
    편지조회
    */
    @Override
    public GetPostContextResponse readPost(int userIdx, int postIdx) throws BaseException {
        if(postRepository.checkPostUser(userIdx, postIdx) == 0){
            throw new BaseException(INVALID_USER_JWT);
        }
        try {
            GetPostContextResponse getPostContextResponse = postRepository.selectPost(postIdx);
            return getPostContextResponse;
        } catch (Exception exception) {
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /*
    태그 목록 조회
     */
    @Override
    public List<GetTagListResponse> readTagList(int userIdx) throws BaseException {
        try{
            List<GetTagListResponse> getTagList = postRepository.selectTagList(userIdx);

            return getTagList;
        }catch (Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /*
    태그별 편지 조회
     */
    @Override
    public List<GetPostResponse> readPostByTag(int userIdx, int tagIdx) throws BaseException {
        if(postRepository.checkTagNotExist(userIdx, tagIdx) == 0){
            throw new BaseException(INVALID_USER_JWT);
        }
        try{
            List<GetPostResponse> getPostResponse = postRepository.selectPostByTag(tagIdx);
            return getPostResponse;
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /*
    태그 검색
     */
    @Override
    public List<GetPostResponse> readPostByTagName(int userIdx, String tagName) throws BaseException {
        if(postRepository.checkTagExist(userIdx, tagName) == 0){
            throw new BaseException(SELECT_FAIL_TAG);
        }
        try{
            List<GetPostResponse> getPostResponse = postRepository.selectPostByTagName(userIdx, tagName);
            return getPostResponse;
        }catch (Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }


    /*
    태그 검색
     */
    @Override
    public List<GetPostResponse> readPostByDate(int userIdx) throws BaseException {
        try{
            List<GetPostResponse> getPostResponse = postRepository.selectPostByDate(userIdx);
            return getPostResponse;
        }catch (Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }



    @Override
    public List<GetSenderListResponse> readSenderList(int userIdx) throws BaseException {
        try{
            List<GetSenderListResponse> getSenderList = postRepository.selectSenderList(userIdx);

            return getSenderList;
        }catch (Exception exception){

            throw new BaseException(DATABASE_ERROR);
        }
    }


    @Override
    public List<GetPostResponse> readPostBySender(String sender, int userIdx) throws BaseException {
        try {
            List<GetPostResponse> getPostResponse = postRepository.selectPostBySender(sender, userIdx);
            return getPostResponse;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Override
    public CreateTagResponse createTag(int userIdx, CreateTagRequest createTagRequest) throws BaseException {
        //태그 정보 추가시, 태그 중복 확인함.
        if(postRepository.checkTagExist(userIdx, createTagRequest.getTagName()) == 1){
            throw new BaseException(POST_TAGS_EXIST);
        }

        try{
            int tagIdx = postRepository.saveTag(userIdx, createTagRequest);
            return new CreateTagResponse(tagIdx);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Override
    public PatchTagResponse modifyTag(int userIdx, int tagIdx, PatchTagRequest patchTagRequest) throws BaseException {
        if(postRepository.checkTagExist(userIdx, patchTagRequest.getTagName()) == 1){
            throw new BaseException(POST_TAGS_EXIST);
        }
        try{
            int success = postRepository.updateTag(tagIdx, patchTagRequest);
            if(success == 0){
                throw new BaseException(MODIFY_FAIL_TAG);
            }
            return new PatchTagResponse(success);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }

    }

    @Override
    public DeleteTagResponse deleteTag(int userIdx, int tagIdx) throws BaseException {
        if(postRepository.checkTagNotExist(userIdx, tagIdx)==0){
            throw new BaseException(POST_EMPTY_TAG_IDX);
        }
        try{
            int success = postRepository.deleteTag(tagIdx);
            if(success == 0){
                throw new BaseException(DELETE_FAIL_TAG);
            }
            return new DeleteTagResponse(success);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Override
    public PatchSenderResponse modifySender(int userIdx, String originalSender, PatchSenderRequest patchSenderRequest) throws BaseException {
        if(postRepository.checkSenderExist(userIdx, originalSender)==0){
            throw new BaseException(POST_EMPTY_SENDER);
        }
        try{
            int success = postRepository.updateSender(userIdx, originalSender, patchSenderRequest);
            if(success == 0){
                throw new BaseException(MODIFY_FAIL_SENDER);
            }
            return new PatchSenderResponse(success);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Override
    public DeleteSenderResponse deleteSender(String sender, int userIdx) throws BaseException {
        if(postRepository.checkSenderExist(userIdx, sender)==0){
            throw new BaseException(POST_EMPTY_SENDER);
        }
        try{
            int success = postRepository.deleteSender(sender, userIdx);
            if(success == 0){
                throw new BaseException(DELETE_FAIL_SENDER);
            }
            return new DeleteSenderResponse(success);
        }catch (Exception exception){

            throw new BaseException(DATABASE_ERROR);
        }
    }








}
