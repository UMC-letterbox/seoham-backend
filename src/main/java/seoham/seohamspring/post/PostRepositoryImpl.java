package seoham.seohamspring.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import seoham.seohamspring.post.domain.*;

import javax.sql.DataSource;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final JdbcTemplate jdbcTemplate;
    //private final GetLetterCountResponse getLetterCountResponse;


    @Autowired
    public PostRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /*
     * 게시물 저장
     */
    @Override
    public int savePost(CreatePostRequest createPostRequest) {
        String savePostQuery = "INSERT INTO post(userIdx, sender, date, tagIdx, content, letterIdx) VALUES (?,?,?,?,?,?)";
        Object [] savePostParams = new Object[]{createPostRequest.getUserIdx(), createPostRequest.getSender(), createPostRequest.getDate(),
        createPostRequest.getTagIdx(), createPostRequest.getContent(), createPostRequest.getLetterIdx()};
        this.jdbcTemplate.update(savePostQuery, savePostParams);

        String lastSavePostIdxQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastSavePostIdxQuery, int.class);

    }

    @Override
    public int updatePost(int postIdx, PatchPostRequest patchPostRequest) {
        String updatePostQuery = "UPDATE post SET userIdx=?, sender=?, date=?, tagIdx=?, content=?, letterIdx=? WHERE postIdx = ?";
        Object [] updatePostParams = new Object[]{patchPostRequest.getUserIdx(), patchPostRequest.getSender(), patchPostRequest.getDate(), patchPostRequest.getTagIdx(),
                patchPostRequest.getContent(), patchPostRequest.getLetterIdx(), postIdx};

        return this.jdbcTemplate.update(updatePostQuery, updatePostParams);
    }

    @Override
    public int checkPostExist(int postIdx) {
        String checkPostExistQuery = "select exists(select postIdx from post where postIdx =?)";
        int checkPostExistParams = postIdx;
        return this.jdbcTemplate.queryForObject(checkPostExistQuery, int.class, checkPostExistParams);
    }



    @Override
    public int deletePost(int postIdx) {
        String deletePostQuery = "DELETE FROM post WHERE postIdx = ?";
        Object [] deletePostParams = new Object[]{postIdx};

        return this.jdbcTemplate.update(deletePostQuery, deletePostParams);
    }

    @Override
    public int saveTag(CreateTagRequest createTagRequest) {
        String saveTagQuery = "INSERT INTO tag(tagName, tagColor,userIdx) VALUES (?,?,?)";
        Object [] saveTagParams = new Object[]{createTagRequest.getTagName(), createTagRequest.getTagColor(), createTagRequest.getUserIdx()};
        this.jdbcTemplate.update(saveTagQuery, saveTagParams);

        String lastSaveTagIdxQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastSaveTagIdxQuery, int.class);
    }

    @Override
    public int updateTag(int tagIdx, PatchTagRequest patchTagRequest) {
        String updateTagQuery = "UPDATE tag SET tagName = ?, tagColor = ? where tagIdx = ?";
        Object [] updateTagParams = new Object[]{patchTagRequest.getTagName(), patchTagRequest.getTagColor(), tagIdx};

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

    @Override
    public int checkTagExist(int userIdx, String tagName) {
        String checkTagExistQuery = "select exists(select tagIdx from tag where tagName =? AND userIdx = ?)";
        Object [] checkTagExistParams = new Object[]{tagName, userIdx};


        return this.jdbcTemplate.queryForObject(checkTagExistQuery, int.class, checkTagExistParams);
    }

    @Override
    public int checkTagNotExist(int tagIdx) {
        String checkTagNotExistQuery = "select exists(select tagIdx from tag where tagIdx =?)";
        int checkTagNotExistParams = tagIdx;


        return this.jdbcTemplate.queryForObject(checkTagNotExistQuery, int.class, checkTagNotExistParams);
    }

    @Override
    public int updateSender(String originalSender, PatchSenderRequest patchSenderRequest) {
        String updateSenderQuery = "UPDATE post SET sender = ? where userIdx = ? AND sender =?";
        Object [] updateSenderParams = new Object[]{patchSenderRequest.getChangedSender(), patchSenderRequest.getUserIdx(), originalSender};

        return this.jdbcTemplate.update(updateSenderQuery, updateSenderParams);

    }

    @Override
    public int checkSenderExist(int userIdx, String sender) {
        String checkSenderExistQuery = "select exists(select postIdx from post where userIdx = ? AND sender = ?)";
        Object [] checkSenderExistParams = new Object[]{userIdx, sender};

        return this.jdbcTemplate.queryForObject(checkSenderExistQuery, int.class, checkSenderExistParams);
    }

    @Override
    public int deleteSender(String sender, DeleteSenderRequest deleteSenderRequest) {
        //기존 post 테이블에서 tagIdx = 0으로 update
        String changeSenderQuery = "UPDATE post SET sender= \"someone\" where userIdx= ? AND sender = ?";
        Object [] changeSenderParmas = new Object[]{deleteSenderRequest.getUserIdx(), sender};
        return this.jdbcTemplate.update(changeSenderQuery, changeSenderParmas);
    }

    @Override
    public List<GetTagListResponse> selectTagList(int userIdx) {
        String selectTagListQuery = "SELECT * FROM tag WHERE userIdx =?";

        return this.jdbcTemplate.query(selectTagListQuery,
                (rs,rowNum) -> new GetTagListResponse(
                        rs.getInt("tagIdx"),
                        rs.getString("tagName"),
                        rs.getString("tagColor")
                ), userIdx);
    }

    @Override
    public List<GetPostResponse> selectPostByTag(int tagIdx) {
        String selectPostByTagQuery = "select a.postIdx, a.sender, a.date, a.tagIdx, b.tagName, b.tagColor, a.letterIdx\n" +
                "from (select *\n" +
                "      from post\n" +
                "      where tagIdx=?) as a\n" +
                "left join tag as b\n" +
                "on a.tagIdx = b.tagIdx";
        return this.jdbcTemplate.query(selectPostByTagQuery,
                (rs,rowNum) -> new GetPostResponse(
                        rs.getInt("postIdx"),
                        rs.getString("sender"),
                        rs.getTimestamp("date"),
                        rs.getInt("tagIdx"),
                        rs.getString("tagName"),
                        rs.getString("tagColor"),
                        rs.getInt("letterIdx")
                ), tagIdx);
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
                (rs,rowNum) -> new GetPostResponse(
                        rs.getInt("postIdx"),
                        rs.getString("sender"),
                        rs.getTimestamp("date"),
                        rs.getInt("tagIdx"),
                        rs.getString("tagName"),
                        rs.getString("tagColor"),
                        rs.getInt("letterIdx")
                ), userIdx);
    }

    /*
    @Override
    public List<GetSenderListResponse> selectSenderList(int userIdx) {
        String selectSenderListQuery = "SELECT distinct sender FROM post WHERE userIdx =?";
        int countOfLetter = this.jdbcTemplate.queryForInt("select count(*) from post where sender = ?", "Joe");


        return this.jdbcTemplate.query(selectSenderListQuery,
                (rs,rowNum) -> new GetSenderListResponse(
                        rs.getString("sender");
                ), userIdx);
    }

    @Override
    public List<GetPostResponse> selectPostBySender(int userIdx, String sender) {
        return null;
    }

     */








    /*
    @Override
    public List<Tag> getTagList() {
        return jdbcTemplate.query("select * " +
                "from (select distinct `tagId` " +
                "from post " +
                "where `ueserId` = $`userId`) AS a" +
                "left join tag AS b" +
                "on a.`tagId` = b.`tagId`", tagRowMapper()
        );
    }


    private RowMapper<Tag> tagRowMapper() {
        return(rs, rowNum)->{

            return tag;
        };
    }


    @Override
    public Optional<Post> findByTag(int tagIdx) {
        List<Post> result = jdbcTemplate.query("select * from post where tagIdx = ?", postRowMapper(), tagIdx);
        return result.stream().findAny();
    }

    @Override
    public Optional<Post> findByDate(int date) {
        List<Post> result = jdbcTemplate.query("select * from post where date = ?", postRowMapper(), date);
        return result.stream().findAny();
    }



    @Override
    public Optional<Post> findBySender(String sender) {
        List<Post> result = jdbcTemplate.query("select * from post where sender = ?", postRowMapper(), sender);
        return result.stream().findAny();
    }

    @Override
    public Optional<Post> findByPostId(long postIdx) {
        List<Post> result = jdbcTemplate.query("select * from post where postIdx = ?", postRowMapper(), postIdx);
        return result.stream().findAny();
    }

    private RowMapper<Post> postRowMapper(){
        return(rs, rowNum)->{
            Post post = new Post();
            post.setPostIdx(rs.getInt("postIdx"));
            post.setSender(rs.getString("sender"));
            post.setDate(rs.getInt("date"));
            post.setTagIdx(rs.getInt("tagIdx"));
            post.setContent(rs.getString("content"));
            post.setLetterIdx(rs.getInt("letterIdx"));
            return post;
        };
    }

     */

}
