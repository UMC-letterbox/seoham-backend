package seoham.seohamspring.post;

import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.post.domain.*;

import java.util.List;
import java.util.Optional;

public interface PostService {

    //게시물 작성
    CreatePostResponse createPost(int userIdx, CreatePostRequest createPostRequest) throws BaseException;
    //게시물 삭제
    void delete(int postIdx);
    /*
    List<Tag> TagList();
    Optional<Post> findByTag(int tagIdx);
    Optional<Post> findByDate(int date);
    List<Sender> SenderList();
    Optional<Post> findBySender(String sender);
    Optional<Post> findByPostIdx(long postIdx);

     */



}
