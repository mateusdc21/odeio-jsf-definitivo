package beerzeit.beans;

import beerzeit.control.PostsControl;
import beerzeit.model.Post;
import beerzeit.model.Usuario;
import org.primefaces.model.StreamedContent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pedro on 27/11/16.
 */
@ManagedBean(name="posts")
@ViewScoped
public class PostBean {
    private PostsControl postsControl = new PostsControl();
    private Map<Integer, StreamedContent> pictures = new HashMap<>();

    public List<Post> listAll() throws SQLException, ClassNotFoundException {
        List<Post> posts = postsControl.listPage(0);
        for (Post p: posts) {
            pictures.put(p.getUsuario().getId(), p.getUsuario().getAvatarToShow());
        }
        return posts;

    }

    public String containsLikeUser(Post post, int id) {
        for (Usuario u: post.getLikes()) {
            if (u.getId() == id) {
                return "col-lg-12 like-button liked";
            }
        }
        return "col-lg-12 like-button";
    }

    public void sendLike(int usuarioid, int postid) throws ClassNotFoundException, SQLException {
        try {
            postsControl.insertLike(usuarioid, postid);
        } catch (SQLException e) {
            if (e.getMessage().indexOf("ERROR: duplicate key value violates unique constraint \"likes_usuario_post_key\"") >= 0) {
                e.printStackTrace();
            } else {
                throw e;
            }
        }
    }

    public List<Post> listByUser(int userid) throws SQLException, ClassNotFoundException {
        return postsControl.listByUser(userid);
    }

    public Map<Integer, StreamedContent> getPictures() {
        return pictures;
    }

    public void setPictures(Map<Integer, StreamedContent> pictures) {
        this.pictures = pictures;
    }
}
