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

    //게시물 작성
    @Override
    public CreatePostResponse createPost(CreatePostRequest createPostRequest) throws BaseException {
        if(postRepository.checkTagNotExist(createPostRequest.getUserIdx(),createPostRequest.getTagIdx())==0){
            throw new BaseException(POST_EMPTY_TAG_IDX);
        }
        try{
            int postIdx = postRepository.savePost(createPostRequest);
            return new CreatePostResponse(postIdx);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Override
    public PatchPostResponse modifyPost(int userIdx,int postIdx, PatchPostRequest patchPostRequest) throws BaseException {
        if(postRepository.checkPostExist(postIdx) == 0){
            throw new BaseException(POST_EMPTY_POST_IDX);
        }
        try{
            int success = postRepository.updatePost(postIdx, patchPostRequest);
            if(success == 0){
                throw new BaseException(MODIFY_FAIL_POST);
            }
            return new PatchPostResponse(success);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }

    }

    @Override
    public DeletePostResponse deletePost(int postIdx) throws BaseException {
        if(postRepository.checkPostExist(postIdx) == 0){
            throw new BaseException(POST_EMPTY_POST_IDX);
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

    @Override
    public CreateTagResponse createTag(int userIdx, CreateTagRequest createTagRequest) throws BaseException {
        //태그 정보 추가시, 태그 중복 확인함.
        if(postRepository.checkTagExist(userIdx, createTagRequest.getTagName()) == 1){
            throw new BaseException(POST_TAGS_EXIST);
        }
        try{
            int tagIdx = postRepository.saveTag(createTagRequest);
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
            int success = postRepository.updateSender(originalSender, patchSenderRequest);
            if(success == 0){
                throw new BaseException(MODIFY_FAIL_SENDER);
            }
            return new PatchSenderResponse(success);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Override
    public DeleteSenderResponse deleteSender(String sender, DeleteSenderRequest deleteSenderRequest) throws BaseException {
        if(postRepository.checkSenderExist(deleteSenderRequest.getUserIdx(), sender)==0){
            throw new BaseException(POST_EMPTY_SENDER);
        }
        try{
            int success = postRepository.deleteSender(sender, deleteSenderRequest);
            if(success == 0){
                throw new BaseException(DELETE_FAIL_SENDER);
            }
            return new DeleteSenderResponse(success);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }



    @Override
    public List<GetTagListResponse> readTagList(int userIdx) throws BaseException {
        try{
            List<GetTagListResponse> getTagList = postRepository.selectTagList(userIdx);
            System.out.println(getTagList.size());

            return getTagList;
        }catch (Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Override
    public List<GetPostResponse> readPostByTag(int tagIdx) throws BaseException {
        try{
            List<GetPostResponse> getPostResponse = postRepository.selectPostByTag(tagIdx);
            return getPostResponse;
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }


    @Override
    public List<GetPostResponse> readPostByDate(int userIdx) throws BaseException {
        try{
            List<GetPostResponse> getPostResponse = postRepository.selectPostByDate(userIdx);
            return getPostResponse;
        }catch (Exception exception){
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
    public GetPostContextResponse readPost(int postIdx) throws BaseException {
        try {
            GetPostContextResponse getPostContextResponse = postRepository.selectPost(postIdx);
            return getPostContextResponse;
        } catch (Exception exception) {
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
