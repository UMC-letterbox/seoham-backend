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
