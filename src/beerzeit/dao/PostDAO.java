package beerzeit.dao;

import beerzeit.model.Post;
import beerzeit.model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 * Created by pedro on 27/11/16.
 */
public class PostDAO extends DAO{
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    public void insertLike(int userid, int postid) throws SQLException, ClassNotFoundException {
        this.open();
        PreparedStatement stmt = this.conn.prepareStatement(
                "INSERT INTO public.likes(usuario, post) VALUES (?, ?);"
        );
        stmt.setInt(1, userid);
        stmt.setInt(2, postid);
        stmt.execute();
        this.close();
    }

    private List<Usuario> getLikes(int postId) throws SQLException, ClassNotFoundException {
        this.open();
        PreparedStatement stmt = this.conn.prepareStatement(
                "SELECT u.* " +
                "FROM likes l " +
                "INNER JOIN usuario u ON l.usuario = u.id " +
                "WHERE l.post = ?;"
        );

        stmt.setInt(1, postId);
        ResultSet rs = stmt.executeQuery();

        List<Usuario> usersWhoLiked = new ArrayList<>();
        while (rs.next()) {
            usersWhoLiked.add(
                new Usuario(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("dateofbirth"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("avatar")
                )
            );
        }
        this.close();
        return usersWhoLiked;
    }

    public List<Post> list(int page) throws SQLException, ClassNotFoundException {
        this.open();
        List<Post> posts = new ArrayList<>();
        PreparedStatement stmt = this.conn.prepareStatement(
                "SELECT * FROM posts ORDER BY id DESC LIMIT 15 OFFSET " + (page * 15) + ";"
        );
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int postId = rs.getInt("id");
            posts.add(
                    new Post(
                        postId,
                        usuarioDAO.getUserById(rs.getInt("usuario")),
                        rs.getString("message"),
                        this.getLikes(postId),
                        new Date(new Timestamp(new Long(rs.getString("createdat"))).getTime()),
                        rs.getString("latitude"),
                        rs.getString("longitude")
                    )
            );

        }
        this.close();
        return posts;
    }
    public void insertPost(int authorid, String message, String lat, String lon) throws SQLException, ClassNotFoundException {
        this.open();
        PreparedStatement stmt = this.conn.prepareStatement(
                "INSERT INTO public.posts(usuario, message, createdat, latitude, longitude) VALUES (?, ?, ?, ?, ?);"
        );
        stmt.setInt(1, authorid);
        stmt.setString(2, message);
        stmt.setString(3, "" + System.currentTimeMillis());
        stmt.setString(4, lat);
        stmt.setString(5, lon);
        stmt.execute();

        this.close();
    }
}
