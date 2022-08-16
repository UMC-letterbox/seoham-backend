package seoham.seohamspring.post;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.config.BaseResponseStatus;
import seoham.seohamspring.post.domain.*;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

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
    public DeleteTagResponse deleteTag(int tagIdx) throws BaseException {
        if(postRepository.checkTagNotExist(tagIdx)==0){
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

    /*
    @Override
    public List<Tag> TagList() {
        return postRepository.getTagList();
    }

    @Override
    public Optional<Post> findByTag(int tagIdx) {
        return postRepository.findByTag(tagIdx);
    }

    @Override
    public Optional<Post> findByDate(int date) {
        return postRepository.findByDate(date);
    }

    @Override
    public List<Sender> SenderList() {
        return postRepository.getSenderList();
    }

    @Override
    public Optional<Post> findBySender(String sender) {
        return postRepository.findBySender(sender);
    }





    @Override
    public Optional<Post> findByPostIdx(long postIdx) {
        return postRepository.findByPostId(postIdx);
    }

     */
}
