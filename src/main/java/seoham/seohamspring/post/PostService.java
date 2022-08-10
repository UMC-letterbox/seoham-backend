package seoham.seohamspring.post;

<<<<<<< HEAD
import com.fasterxml.jackson.databind.ser.Serializers;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.post.domain.*;
=======
import org.springframework.stereotype.Service;
>>>>>>> 5a12ce1c2bd7eef06011995f838c5ba941d0d21e

import java.util.List;
import java.util.Optional;
@Service
public interface PostService {

    //게시물 작성
    CreatePostResponse createPost(int userIdx, CreatePostRequest createPostRequest) throws BaseException;
    //게시물 수정
    void modifyPost(int userIdx,int postIdx,  PatchPostRequest patchPostRequest) throws BaseException;
    //게시물 삭제
    void deletePost(int postIdx) throws BaseException;
    /*
    List<Tag> TagList();
    Optional<Post> findByTag(int tagIdx);
    Optional<Post> findByDate(int date);
    List<Sender> SenderList();
    Optional<Post> findBySender(String sender);
    Optional<Post> findByPostIdx(long postIdx);

     */



}
