package seoham.seohamspring.post;

import org.springframework.stereotype.Repository;
import seoham.seohamspring.post.domain.*;

import java.util.List;

@Repository
public interface PostRepository {


    //편지 저장, 수정, 삭제
    int savePost(int userIdx, CreatePostRequest createPostRequest);
    int updatePost(int userIdx, int postIdx, PatchPostRequest patchPostRequest);
    int deletePost(int postIdx);

    //편지 조회
    GetPostContextResponse selectPost(int postIdx);

    //태그 정보 저장, 수정, 삭제
    int saveTag(int userIdx, CreateTagRequest createTagRequest);
    int updateTag(int tagIdx, PatchTagRequest patchTagRequest);
    int deleteTag(int tagIdx);

    //보낸이 정보 저장, 삭제
    int updateSender(int userIdx, String sender, PatchSenderRequest patchSenderRequest);
    int deleteSender(String sender, int userIdx);

    //조건별(태그, 날짜, 보낸이) 편지 조회
    List<GetTagListResponse> selectTagList(int userIdx);
    List<GetPostResponse> selectPostByTag(int tagIdx);
    List<GetPostResponse> selectPostByTagName(int userIdx,String tagName);
    List<GetPostResponse> selectPostByDate(int userIdx);
    List<GetSenderListResponse> selectSenderList(int userIdx);
    List<GetPostResponse> selectPostBySender(String sender, int userIdx);




    //데이터 유무 확인
    int checkPostExist(int postIdx);
    int checkTagExist(int userIdx, String tagName);
    boolean checkTagsNotExist(int userIdx,List<Integer> tags);
    boolean checkTagNotExist(int userIdx,int tagIdx);
    int checkSenderExist(int userIdx, String sender);
    int checkPostUser(int userIdx, int postIdx);

}
