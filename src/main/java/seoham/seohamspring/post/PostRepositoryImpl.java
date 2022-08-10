package seoham.seohamspring.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
    public Post save(Post post) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("post").usingGeneratedKeyColumns("postIdx");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("sender", post.getSender());
        parameters.put("date", post.getDate());
        parameters.put("tagIdx", post.getTagIdx());
        parameters.put("content", post.getContent());
        parameters.put("letterIdx", post.getLetterIdx());

        return post;
    }

    @Override
    public void delete(int postIdx) {
    }

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
            Tag tag = new Tag();
            tag.setTagIdx(rs.getInt("tagIdx"));
            tag.setTagName(rs.getString("tagName"));
            tag.setTagColor(rs.getString("tagColor"));
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

}
