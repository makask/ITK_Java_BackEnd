package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exweb.Post;
import util.DataSourceProvider;

public class PostDao {

    public List<Post> getPosts() {

        List<Post> posts = new ArrayList<>();

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = conn.createStatement()) {

            try (ResultSet r = stmt.executeQuery("select * from post")) {
                while (r.next()) {
                    posts.add(new Post(r.getLong(1), r.getString(2), r.getString(3)));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return posts;
    }

    public void insertPost(Post post){

        String query = "INSERT INTO post (id, title, text) " +
                "VALUES (NEXT VALUE FOR seq1, ?, ?)";


        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, post.getTitle());
            ps.setString(2, post.getText());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deletePost(Long id){

        String query = "delete from post where id = ?";


        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setLong(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
