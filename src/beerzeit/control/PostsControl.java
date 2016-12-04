package beerzeit.control;

import beerzeit.dao.PostDAO;
import beerzeit.model.Post;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by pedro on 27/11/16.
 */
public class PostsControl {
    PostDAO postDAO = new PostDAO();
    public List<Post> listPage(int pageNumber) throws SQLException, ClassNotFoundException {
        return postDAO.list(pageNumber);
    }
    public void insertLike(int userid, int postid) throws SQLException, ClassNotFoundException {
        postDAO.insertLike(userid, postid);
    }

    public void sendPost(int userid, String message, String lat, String lon) throws SQLException, ClassNotFoundException {
        postDAO.insertPost(userid, message, lat, lon);
    }

    public List<Post> listByUser(int userid) throws SQLException, ClassNotFoundException {
        return postDAO.listByUser(userid);
    }
}
