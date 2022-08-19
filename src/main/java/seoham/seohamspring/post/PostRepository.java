package seoham.seohamspring.post;

import org.springframework.stereotype.Repository;
import seoham.seohamspring.post.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PostRepository {


    //편지 저장, 수정, 삭제
    int savePost(CreatePostRequest createPostRequest);
    int updatePost(int userIdx, int postIdx, PatchPostRequest patchPostRequest);
    int deletePost(int postIdx);

    //태그 정보 저장, 수정, 삭제
    int saveTag(CreateTagRequest createTagRequest);
    int updateTag(int tagIdx, PatchTagRequest patchTagRequest);
    int deleteTag(int tagIdx);

    //보낸이 정보 저장, 삭제
    int updateSender(String sender, PatchSenderRequest patchSenderRequest);
    int deleteSender(String sender, DeleteSenderRequest deleteSenderRequest);

    //조건별(태그, 날짜, 보낸이) 편지 조회
    List<GetTagListResponse> selectTagList(int userIdx);
    List<GetPostResponse> selectPostByTag(int tagIdx);
    List<GetPostResponse> selectPostByDate(int userIdx);
    List<GetSenderListResponse> selectSenderList(int userIdx);
    List<GetPostResponse> selectPostBySender(String sender, int userIdx);

    //편지 조회
    GetPostContextResponse selectPost(int postIdx);


    //데이터 유무 확인
    int checkPostExist(int postIdx);
    int checkTagExist(int userIdx, String tagName);
    int checkTagNotExist(int userIdx,int tagIdx);
    int checkSenderExist(int userIdx, String sender);

}
