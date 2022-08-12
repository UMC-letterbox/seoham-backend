package seoham.seohamspring.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import seoham.seohamspring.post.domain.*;

import javax.sql.DataSource;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
public class PostRepositoryImpl implements PostRepository {

    private final JdbcTemplate jdbcTemplate;


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
        String saveTagQuery = "INSERT INTO tag(tagName, tagColor) VALUES (?,?)";
        Object [] saveTagParams = new Object[]{createTagRequest.getTagName(), createTagRequest.getTagColor()};
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
        return 0;
    }

    @Override
    public int checkTagExist(String tagName) {
        String checkTagExistQuery = "select exists(select tagIdx from tag where tagName =?)";
        String checkTagExistParams = tagName;
        return this.jdbcTemplate.queryForObject(checkTagExistQuery, int.class, checkTagExistParams);
    }



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
    public List<Sender> getSenderList() {
        return jdbcTemplate.query("select distinct `sender`" +
                "from post" +
                "where `ueserId` = $`userId`",
                 senderRowMapper()
        );
    }

    private RowMapper<Sender> senderRowMapper() {
        return(rs, rowNum)->{
            Sender sender = new Sender();
            sender.setSender(rs.getString("sender"));
            return sender;
        };
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
