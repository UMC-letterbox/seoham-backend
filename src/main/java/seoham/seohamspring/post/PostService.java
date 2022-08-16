package seoham.seohamspring.post;


import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.post.domain.*;
import org.springframework.stereotype.Service;

@Service
public interface PostService {

    //게시물 작성
    CreatePostResponse createPost(CreatePostRequest createPostRequest) throws BaseException;
    //게시물 수정
    PatchPostResponse modifyPost(int userIdx,int postIdx,  PatchPostRequest patchPostRequest) throws BaseException;
    //게시물 삭제
    DeletePostResponse deletePost(int postIdx) throws BaseException;
    //태그 정보 추가
    CreateTagResponse createTag(int userIdx, CreateTagRequest createTagRequest) throws BaseException;
    //태그 정보 수정
    PatchTagResponse modifyTag(int userIdx, int tagIdx, PatchTagRequest patchTagRequest) throws BaseException;
    //태그 정보 삭제
    DeleteTagResponse deleteTag(int tagIdx) throws  BaseException;
    //보낸이 정보 수정
    PatchSenderResponse modifySender(int userIdx, String originalSender, PatchSenderRequest patchSenderRequest) throws BaseException;
    DeleteSenderResponse deleteSender(String sender, DeleteSenderRequest deleteSenderRequest) throws BaseException;


    /*
    List<Tag> TagList();
    Optional<Post> findByTag(int tagIdx);
    Optional<Post> findByDate(int date);
    List<Sender> SenderList();
    Optional<Post> findBySender(String sender);
    Optional<Post> findByPostIdx(long postIdx);

     */


}
