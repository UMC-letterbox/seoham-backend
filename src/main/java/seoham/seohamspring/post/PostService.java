package seoham.seohamspring.post;


import com.fasterxml.jackson.databind.ser.Serializers;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.post.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface PostService {

    //게시물 작성
    CreatePostResponse createPost(CreatePostRequest createPostRequest) throws BaseException;
    //게시물 수정
    PatchPostResponse modifyPost(int userIdx,int postIdx,  PatchPostRequest patchPostRequest) throws BaseException;
    //게시물 삭제
    DeletePostResponse deletePost(int postIdx) throws BaseException;
    /*
    List<Tag> TagList();
    Optional<Post> findByTag(int tagIdx);
    Optional<Post> findByDate(int date);
    List<Sender> SenderList();
    Optional<Post> findBySender(String sender);
    Optional<Post> findByPostIdx(long postIdx);

     */
    CreateTagResponse createTag(CreateTagRequest createTagRequest) throws BaseException;
    PatchTagResponse modifyTag(int tagIdx, PatchTagRequest patchTagRequest) throws BaseException;



}
