package seoham.seohamspring.post;


import com.fasterxml.jackson.databind.ser.Serializers;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.post.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

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
    DeleteTagResponse deleteTag(int userIdx, int tagIdx) throws  BaseException;
    //보낸이 정보 수정
    PatchSenderResponse modifySender(int userIdx, String originalSender, PatchSenderRequest patchSenderRequest) throws BaseException;
    //보낸이 정보 삭제
    DeleteSenderResponse deleteSender(String sender, DeleteSenderRequest deleteSenderRequest) throws BaseException;
    //태그 목록 조회
    List<GetTagListResponse> readTagList(int userIdx) throws BaseException;
    //태그별 편지 조회
    List<GetPostResponse> readPostByTag(int tagIdx) throws BaseException;
    //날짜별 편지조회
    List<GetPostResponse> readPostByDate(int userIdx) throws BaseException;
    //보낸이 목록 조회
    List<GetSenderListResponse> readSenderList(int userIdx) throws BaseException;
    //보낸이별 편지 조회
    List<GetPostResponse> readPostBySender(String sender, int userIdx) throws BaseException;



}
