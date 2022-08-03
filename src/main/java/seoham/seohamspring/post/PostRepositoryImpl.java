package seoham.seohamspring.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    /*
    태그로 post 찾기
     */
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
        return null;
    }

    @Override
    public Optional<Post> findByPostId(long postIdx) {
        List<Post> result = jdbcTemplate.query("select * from post where postIdx = ?", postRowMapper(), postIdx);
        return result.stream().findAny();
    }

    private RowMapper<Post> postRowMapper(){
        return(rs, rowNum)->{
            Post post = new Post();
            post.setPostIdx(rs.getLong("postIdx"));
            post.setSender(rs.getString("sender"));
            post.setDate(rs.getInt("date"));
            post.setTagIdx(rs.getInt("tagIdx"));
            post.setContent(rs.getString("content"));
            post.setLetterIdx(rs.getInt("letterIdx"));
            return post;
        };
    }
}
