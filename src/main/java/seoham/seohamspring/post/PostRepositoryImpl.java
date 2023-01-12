package seoham.seohamspring.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import seoham.seohamspring.post.domain.*;

import javax.sql.DataSource;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public PostRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /*
     * 편지 저장, 수정, 삭제
     */
    @Override
    public int savePost(int userIdx, CreatePostRequest createPostRequest) {
        String tagIdx = createPostRequest.getTagIdx().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
        String savePostQuery = "INSERT INTO post(userIdx, sender, date, tagIdx, content, letterIdx) VALUES (?,?,?,?,?,?)";
        Object[] savePostParams = new Object[]{userIdx, createPostRequest.getSender(), createPostRequest.getDate(),
                tagIdx, createPostRequest.getContent(), createPostRequest.getLetterIdx()};
        this.jdbcTemplate.update(savePostQuery, savePostParams);

        String lastSavePostIdxQuery = "select last_insert_id()";
        return jdbcTemplate.queryForObject(lastSavePostIdxQuery, int.class);
    }

    @Override
    public int updatePost(int userIdx, int postIdx, PatchPostRequest patchPostRequest) {
        String tagIdx = patchPostRequest.getTagIdx().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
        String updatePostQuery = "UPDATE post SET userIdx=?, sender=?, date=?, tagIdx=?, content=?, letterIdx=? WHERE postIdx = ?";
        Object[] updatePostParams = new Object[]{userIdx, patchPostRequest.getSender(), patchPostRequest.getDate(), tagIdx,
                patchPostRequest.getContent(), patchPostRequest.getLetterIdx(), postIdx};

        return this.jdbcTemplate.update(updatePostQuery, updatePostParams);
    }


    @Override
    public int deletePost(int postIdx) {
        String deletePostQuery = "DELETE FROM post WHERE postIdx = ?";
        Object[] deletePostParams = new Object[]{postIdx};

        return this.jdbcTemplate.update(deletePostQuery, deletePostParams);
    }


    //태그 정보 저장, 수정, 삭제
    @Override
    public int saveTag(int userIdx, CreateTagRequest createTagRequest) {
        String saveTagQuery = "INSERT INTO tag(tagName, tagColor,userIdx) VALUES (?,?,?)";
        Object[] saveTagParams = new Object[]{createTagRequest.getTagName(), createTagRequest.getTagColor(), userIdx};
        this.jdbcTemplate.update(saveTagQuery, saveTagParams);

        String lastSaveTagIdxQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastSaveTagIdxQuery, int.class);
    }



    @Override
    public int updateTag(int tagIdx, PatchTagRequest patchTagRequest) {
        String updateTagQuery = "UPDATE tag SET tagName = ?, tagColor = ? where tagIdx = ?";
        Object[] updateTagParams = new Object[]{patchTagRequest.getTagName(), patchTagRequest.getTagColor(), tagIdx};

        return this.jdbcTemplate.update(updateTagQuery, updateTagParams);
    }

    @Override
    public int deleteTag(int tagIdx) {
        //기존 post 테이블에서 tagIdx = 0으로 update
        String changePostTagQuery = "UPDATE post SET tagIdx = 0 where tagIdx = ?";
        this.jdbcTemplate.update(changePostTagQuery, tagIdx);

        //태그 삭제
        String deleteTagQuery = "DELETE FROM tag WHERE tagIdx = ?";

        return this.jdbcTemplate.update(deleteTagQuery, tagIdx);
    }



    /*
    보낸이 정보 수정, 삭제
     */
    @Override
    public int updateSender(int userIdx, String originalSender, PatchSenderRequest patchSenderRequest) {
        String updateSenderQuery = "UPDATE post SET sender = ? where userIdx = ? AND sender =?";
        Object[] updateSenderParams = new Object[]{patchSenderRequest.getChangedSender(), userIdx, originalSender};

        return this.jdbcTemplate.update(updateSenderQuery, updateSenderParams);

    }



    @Override
    public int deleteSender(String sender, int userIdx) {
        //기존 post 테이블에서 tagIdx = 0으로 update
        String changeSenderQuery = "UPDATE post SET sender= \"someone\" where userIdx= ? AND sender = ?";
        Object[] changeSenderParmas = new Object[]{userIdx, sender};
        return this.jdbcTemplate.update(changeSenderQuery, changeSenderParmas);
    }


    /*
    조건별 편지 조회
     */
    @Override
    public List<GetTagListResponse> selectTagList(int userIdx) {
        String selectTagListQuery = "SELECT * FROM tag WHERE userIdx =?";

        return this.jdbcTemplate.query(selectTagListQuery,
                (rs, rowNum) -> new GetTagListResponse(
                        rs.getInt("tagIdx"),
                        rs.getString("tagName"),
                        rs.getString("tagColor")
                ), userIdx);
    }

    /*
    태그별 편지 조회
     */
    @Override
    public List<GetPostByTagResponse> selectPostByTag(int userIdx, int tagIdx) {

        List<GetTagResponse> tagList= jdbcTemplate.query("select * from post where userIdx = ?",
                (rs, rowNum) -> new GetTagResponse(
                        rs.getInt("postIdx"),
                        rs.getString("tagIdx")
                ), userIdx);


        List<Integer> postIdxList = new ArrayList<>();
        for(GetTagResponse getTagResponse : tagList){
            String[] tags = getTagResponse.getTagIdx().split(" ");
            if(Arrays.asList(tags).contains(String.valueOf(tagIdx))){
                postIdxList.add(getTagResponse.getPostIdx());
            }
        }

        if(postIdxList.isEmpty()) return null;

        String inSql = String.join(",", Collections.nCopies(postIdxList.size(), "?"));
        return jdbcTemplate.query(String.format("SELECT * FROM post WHERE postIdx IN (%s)", inSql),
                postIdxList.toArray(),
                (rs, rowNum) -> new GetPostByTagResponse(
                        rs.getInt("postIdx"),
                        rs.getString("sender"),
                        rs.getTimestamp("date"),
                        rs.getInt("letterIdx")
                ));
    }


    /*
    태그 검색
     */
    @Override
    public List<GetPostByTagNameResponse> selectPostByTagName(int userIdx, String tagName) {
        GetTagListResponse tag = jdbcTemplate.queryForObject("select * from tag where tagName = ? and userIdx = ?",
                (rs,rowNum) -> new GetTagListResponse(
                        rs.getInt("tagIdx"),
                        rs.getString("tagName"),
                        rs.getString("tagColor")
                ),tagName, userIdx);

        List<GetPostByTagResponse> list = selectPostByTag(userIdx,tag.getTagIdx());


        List<GetPostByTagNameResponse> result = new ArrayList<>();
        for(GetPostByTagResponse e : list){
            GetPostByTagNameResponse e2 = new GetPostByTagNameResponse(e.getPostIdx(),
                    e.getSender(), e.getDate(), tag.getTagIdx(), tagName, tag.getTagColor(),
                    e.getLetterIdx());
            result.add(e2);
        }
        return result;

    }


    @Override
    public List<GetPostResponse> selectPostByDate(int userIdx) {

        List<GetPostInfoResponse> getPostInfoResponseList  = jdbcTemplate.query("select * from post where userIdx = ?",
                (rs, rowNum) -> new GetPostInfoResponse(
                        rs.getInt("postIdx"),
                        rs.getString("sender"),
                        rs.getTimestamp("date"),
                        rs.getString("tagIdx"),
                        rs.getInt("letterIdx"),
                        rs.getString("content")
                ), userIdx);

        List<GetPostResponse> getPostResponseList = new ArrayList<>();

        //postIdx에 해당하는 tagIdx 추출
        for(GetPostInfoResponse postInfoResponse : getPostInfoResponseList){
            List<String> tagIdx = List.of(postInfoResponse.getTagIdx().split(" "));
            List<String> tagName = new ArrayList<>();
            List<String> tagColor = new ArrayList<>();
            List<Integer> tagList = new ArrayList<>();
            for(String tag : tagIdx){
                TagInfoResponse tagInfoResponse = jdbcTemplate.queryForObject("select * from tag where tagIdx = ?",
                        (rs, rowNum) -> new TagInfoResponse(
                                rs.getInt("tagIdx"),
                                rs.getString("tagName"),
                                rs.getString("tagColor")
                        ),Integer.parseInt(tag));
                tagList.add(tagInfoResponse.getTagIdx());
                tagName.add(tagInfoResponse.getTagName());
                tagColor.add(tagInfoResponse.getTagColor());
            }
            GetPostResponse getPostResponse = new GetPostResponse(postInfoResponse.getPostIdx(), postInfoResponse.getSender(), postInfoResponse.getDate(),
                    tagList, tagName, tagColor, postInfoResponse.getLetterIdx());

            getPostResponseList.add(getPostResponse);

        }

        return getPostResponseList;
    }


    @Override
    public List<GetSenderListResponse> selectSenderList(int userIdx) {
        String selectSenderListQuery = "select sender, count(*) as count\n" +
                "from post\n" +
                "where userIdx = ?\n" +
                "group by sender;";

        return this.jdbcTemplate.query(selectSenderListQuery,
                (rs, rowNum) -> new GetSenderListResponse(
                        rs.getString("sender"),
                        rs.getInt("count")
                ), userIdx);
    }


    /*
    보낸이별 편지
     */
    @Override
    public List<GetPostContextResponse> selectPostBySender(String sender, int userIdx) {

        List<GetPostInfoResponse> List= jdbcTemplate.query("select * from post where userIdx = ? AND sender = ?",
                (rs, rowNum) -> new GetPostInfoResponse(
                        rs.getInt("postIdx"),
                        rs.getString("sender"),
                        rs.getTimestamp("date"),
                        rs.getString("tagIdx"),
                        rs.getInt("letterIdx"),
                        rs.getString("content")
                ), userIdx, sender);

        List<GetPostContextResponse> result = new ArrayList<>();
        for(GetPostInfoResponse e : List){
            result.add(selectPost(e.getPostIdx()));
        }

        return result;
    }


    /*
    편지 조회
     */
    @Override
    public GetPostContextResponse selectPost(int postIdx) {

        String selectPostInfoQuery = "select * from post where postIdx = ?";
        GetPostInfoResponse getPostInfoResponse = jdbcTemplate.queryForObject(selectPostInfoQuery,
                (rs, rowNum) -> new GetPostInfoResponse(
                        rs.getInt("postIdx"),
                        rs.getString("sender"),
                        rs.getTimestamp("date"),
                        rs.getString("tagIdx"),
                        rs.getInt("letterIdx"),
                        rs.getString("content")
                ),postIdx);


        //postIdx에 해당하는 tagIdx 추출
        List<String> tagIdx = List.of(getPostInfoResponse.getTagIdx().split(" "));
        List<String> tagName = new ArrayList<>();
        List<String> tagColor = new ArrayList<>();
        List<Integer> tagList = new ArrayList<>();
        for(String tag : tagIdx){
            TagInfoResponse tagInfoResponse = jdbcTemplate.queryForObject("select * from tag where tagIdx = ?",
                    (rs, rowNum) -> new TagInfoResponse(
                            rs.getInt("tagIdx"),
                            rs.getString("tagName"),
                            rs.getString("tagColor")
                    ),Integer.parseInt(tag));
            tagList.add(tagInfoResponse.getTagIdx());
            tagName.add(tagInfoResponse.getTagName());
            tagColor.add(tagInfoResponse.getTagColor());
        }


        return new GetPostContextResponse(
                getPostInfoResponse.getPostIdx(), getPostInfoResponse.getSender(), getPostInfoResponse.getDate(),
                tagList, tagName, tagColor,
                getPostInfoResponse.getLetterIdx(), getPostInfoResponse.getContent()
        );
    }



    /*
    데이터 유무 확인
     */
    @Override
    public int checkPostExist(int postIdx) {
        String checkPostExistQuery = "select exists(select postIdx from post where postIdx =?)";
        return this.jdbcTemplate.queryForObject(checkPostExistQuery, int.class, postIdx);
    }

    @Override
    public int checkTagExist(int userIdx, String tagName) {
        String checkTagExistQuery = "select exists(select tagIdx from tag where tagName =? AND userIdx = ?)";
        Object[] checkTagExistParams = new Object[]{tagName, userIdx};


        return this.jdbcTemplate.queryForObject(checkTagExistQuery, int.class, checkTagExistParams);
    }

    @Override
    public boolean checkTagsExist(int userIdx, List<Integer> tags) {
        //List<Integer> tags를 입력받아 하나씩 순회하면서 존재하고 있는지 확인한다.
        boolean TagsExist = true; //있다고 가정
        for (Integer tag : tags) {
            String checkTagsExistQuery = "select exists(select tagIdx from tag where tagIdx =? AND userIdx= ?)";
            Object[] checkTagsExistParams = new Object[]{tag, userIdx};
            TagsExist = this.jdbcTemplate.queryForObject(checkTagsExistQuery, boolean.class, checkTagsExistParams);
        }
        return TagsExist;
    }

    @Override
    public boolean checkTagExistById(int userIdx, int tagIdx) {
        //List<Integer> tags를 입력받아 하나씩 순회하면서 존재하고 있는지 확인한다.
        String checkTagExistByIdQuery = "select exists(select tagIdx from tag where tagIdx =? AND userIdx= ?)";
        Object[] checkTagExistByIdParams = new Object[]{tagIdx, userIdx};
        return this.jdbcTemplate.queryForObject(checkTagExistByIdQuery,boolean.class, checkTagExistByIdParams);
    }

    @Override
    public int checkSenderExist(int userIdx, String sender) {
        String checkSenderExistQuery = "select exists(select postIdx from post where userIdx = ? AND sender = ?)";
        Object[] checkSenderExistParams = new Object[]{userIdx, sender};

        return this.jdbcTemplate.queryForObject(checkSenderExistQuery, int.class, checkSenderExistParams);
    }

    @Override
    public int checkPostUser(int userIdx, int postIdx) {
        String checkPostUserQuery = "select exists(select postIdx from post where userIdx = ? AND postIdx = ?)";
        Object[] checkPostUserParams = new Object[]{userIdx, postIdx};

        return this.jdbcTemplate.queryForObject(checkPostUserQuery, int.class, checkPostUserParams);
    }

}





