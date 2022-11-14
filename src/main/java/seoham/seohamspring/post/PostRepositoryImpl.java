package seoham.seohamspring.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.post.domain.*;

import javax.sql.DataSource;
import javax.swing.text.html.HTML;


import org.springframework.stereotype.Repository;
import java.util.List;

import static seoham.seohamspring.config.BaseResponseStatus.INVALID_USER_JWT;

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
        String tagIdx = createPostRequest.getTagIdx().toString();
        String savePostQuery = "INSERT INTO post(userIdx, sender, date, tagIdx, content, letterIdx) VALUES (?,?,?,?,?,?)";
        Object[] savePostParams = new Object[]{userIdx, createPostRequest.getSender(), createPostRequest.getDate(),
                tagIdx, createPostRequest.getContent(), createPostRequest.getLetterIdx()};
        this.jdbcTemplate.update(savePostQuery, savePostParams);

        String lastSavePostIdxQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastSavePostIdxQuery, int.class);
    }

    @Override
    public int updatePost(int userIdx, int postIdx, PatchPostRequest patchPostRequest) {
        String tagIdx = patchPostRequest.getTagIdx().toString();
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
        int changePostTagParmas = tagIdx;
        this.jdbcTemplate.update(changePostTagQuery, changePostTagParmas);

        //태그 삭제
        String deleteTagQuery = "DELETE FROM tag WHERE tagIdx = ?";
        int deleteTagParams = tagIdx;

        return this.jdbcTemplate.update(deleteTagQuery, deleteTagParams);
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
    수정 해야함.
     */


    @Override
    public List<GetPostResponse> selectPostByTag(int tagIdx) {
        String selectPostByTagQeury = "select * from post where t";
        String selectPostByTagQuery = "select a.postIdx, a.sender, a.date, a.tagIdx, b.tagName, b.tagColor, a.letterIdx\n" +
                "from (select *\n" +
                "      from post\n" +
                "      where tagIdx=?) as a\n" +
                "left join tag as b\n" +
                "on a.tagIdx = b.tagIdx";
        return this.jdbcTemplate.query(selectPostByTagQuery,
                (rs, rowNum) -> new GetPostResponse(
                        rs.getInt("postIdx"),
                        rs.getString("sender"),
                        rs.getTimestamp("date"),
                        rs.getString("tagIdx"), //"1,2,3,4,5"
                        //rs.getString("tagName"),
                        //rs.getString("tagColor"),
                        rs.getInt("letterIdx")
                ), tagIdx);
    }

    @Override
    public List<GetPostResponse> selectPostByTagName(int userIdx, String tagName) {
        String selectPostByTagNameQuery = "select a.postIdx, a.sender, a.date, a.tagIdx, b.tagName, b.tagColor, a.letterIdx\n" +
                "from post as a\n" +
                "inner join (select * from tag where tagName = ? and userIdx = ?) as b\n" +
                "on a.tagIdx = b.tagIdx";
        return this.jdbcTemplate.query(selectPostByTagNameQuery,
                (rs, rowNum) -> new GetPostResponse(
                        rs.getInt("postIdx"),
                        rs.getString("sender"),
                        rs.getTimestamp("date"),
                        rs.getString("tagIdx"),
                        //rs.getString("tagName"),
                        //rs.getString("tagColor"),
                        rs.getInt("letterIdx")
                ), tagName, userIdx);
    }


    @Override
    public List<GetPostResponse> selectPostByDate(int userIdx) {
        String selectPostBySenderQuery = "select a.postIdx, a.sender, a.date, a.tagIdx, b.tagName, b.tagColor, a.letterIdx\n" +
                "from (select *\n" +
                "      from post\n" +
                "      where userIdx=?) as a\n" +
                "left join tag as b\n" +
                "on a.tagIdx = b.tagIdx;\n";
        return this.jdbcTemplate.query(selectPostBySenderQuery,
                (rs, rowNum) -> new GetPostResponse(
                        rs.getInt("postIdx"),
                        rs.getString("sender"),
                        rs.getTimestamp("date"),
                        rs.getString("tagIdx"),
                        //rs.getString("tagName"),
                        //rs.getString("tagColor"),
                        rs.getInt("letterIdx")
                ), userIdx);
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


    @Override
    public List<GetPostResponse> selectPostBySender(String sender, int userIdx) {
        String selectPostBySenderQuery = "select a.postIdx, a.sender, a.date, a.tagIdx, b.tagName, b.tagColor, a.letterIdx\n" +
                "from (select *\n" +
                "      from post\n" +
                "      where sender=? AND userIdx= ?) as a\n" +
                "left join tag as b\n" +
                "on a.tagIdx = b.tagIdx";


        return this.jdbcTemplate.query(selectPostBySenderQuery,
                (rs, rowNum) -> new GetPostResponse(
                        rs.getInt("postIdx"),
                        rs.getString("sender"),
                        rs.getTimestamp("date"),
                        rs.getString("tagIdx"),
                        //rs.getString("tagName"),
                        //rs.getString("tagColor"),
                        rs.getInt("letterIdx")
                ), sender, userIdx);
    }


    /*
    편지 조회
     */
    @Override
    public GetPostContextResponse selectPost(int postIdx) {

        String selectPostQuery = "select a.postIdx, a.sender, a.date, a.tagIdx, b.tagName, b.tagColor, a.letterIdx, a.content\n" +
                "from (select *\n" +
                "      from post\n" +
                "      where postIdx = ?) as a\n" +
                "left join tag as b\n" +
                "on a.tagIdx = b.tagIdx";

        int selectPostParams = postIdx;



        return this.jdbcTemplate.queryForObject(selectPostQuery,
                (rs, rowNum) -> new GetPostContextResponse(
                        rs.getInt("postIdx"),
                        rs.getString("sender"),
                        rs.getTimestamp("date"),
                        rs.getString("tagIdx"),
                        //rs.getString("tagName"),
                        //rs.getString("tagColor"),
                        rs.getInt("letterIdx"),
                        rs.getString("content")
                ),selectPostParams);

    }



    /*
    데이터 유무 확인
     */
    @Override
    public int checkPostExist(int postIdx) {
        String checkPostExistQuery = "select exists(select postIdx from post where postIdx =?)";
        int checkPostExistParams = postIdx;
        return this.jdbcTemplate.queryForObject(checkPostExistQuery, int.class, checkPostExistParams);
    }

    @Override
    public int checkTagExist(int userIdx, String tagName) {
        String checkTagExistQuery = "select exists(select tagIdx from tag where tagName =? AND userIdx = ?)";
        Object[] checkTagExistParams = new Object[]{tagName, userIdx};


        return this.jdbcTemplate.queryForObject(checkTagExistQuery, int.class, checkTagExistParams);
    }

    @Override
    public boolean checkTagsNotExist(int userIdx, List<Integer> tags) {
        //List<Integer> tags를 입력받아 하나씩 순회하면서 존재하고 있는지 확인한다.
        boolean TagsNotExist = false; //없다고 가정
        for(int i = 0; i < tags.size(); i++) {
            String checkTagNotExistQuery = "select exists(select tagIdx from tag where tagIdx =? AND userIdx= ?)";
            Object[] checkTagNotExistParams = new Object[]{tags.get(i), userIdx};
            TagsNotExist = this.jdbcTemplate.queryForObject(checkTagNotExistQuery,boolean.class, checkTagNotExistParams);
            System.out.println(TagsNotExist);
        }
        return TagsNotExist;
    }

    @Override
    public boolean checkTagNotExist(int userIdx, int tagIdx) {
        //List<Integer> tags를 입력받아 하나씩 순회하면서 존재하고 있는지 확인한다.
        boolean TagNotExist = false;
        String checkTagNotExistQuery = "select exists(select tagIdx from tag where tagIdx =? AND userIdx= ?)";
        Object[] checkTagNotExistParams = new Object[]{tagIdx, userIdx};
        TagNotExist = this.jdbcTemplate.queryForObject(checkTagNotExistQuery,boolean.class, checkTagNotExistParams);

        if(TagNotExist == true){
            return TagNotExist;
        }
        return false;
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
        System.out.println(this.jdbcTemplate.queryForObject(checkPostUserQuery, int.class, checkPostUserParams));

        return this.jdbcTemplate.queryForObject(checkPostUserQuery, int.class, checkPostUserParams);
    }

}





