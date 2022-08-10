package seoham.seohamspring.post;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.config.BaseResponseStatus;
import seoham.seohamspring.post.domain.*;
=======
import org.springframework.stereotype.Service;
>>>>>>> 5a12ce1c2bd7eef06011995f838c5ba941d0d21e

import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
import static seoham.seohamspring.config.BaseResponseStatus.MODIFY_FAIL_POST;


=======
@Service
>>>>>>> 5a12ce1c2bd7eef06011995f838c5ba941d0d21e
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    //게시물 작성
    @Override
    public CreatePostResponse createPost(int userIdx, CreatePostRequest createPostRequest) throws BaseException {
        try{
            int postIdx = postRepository.savePost(userIdx, createPostRequest);
            return new CreatePostResponse(postIdx);
        }catch (Exception exception){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    @Override
    public void modifyPost(int userIdx,int postIdx, PatchPostRequest patchPostRequest) throws BaseException {

        try{
            int result = postRepository.updatePost(userIdx, patchPostRequest);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_POST);
            }
        }catch (Exception exception){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }

    }

    @Override
    public void deletePost(int postIdx) throws BaseException {
        try{
            int result = postRepository.deletePost(postIdx);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_POST);
            }
        }catch (Exception exception){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
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
