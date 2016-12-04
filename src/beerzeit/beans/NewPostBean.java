package beerzeit.beans;

import beerzeit.control.PostsControl;
import beerzeit.utils.SessionUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.SQLException;

/**
 * Created by pedro on 28/11/16.
 */
@ManagedBean(name = "newpost")
@SessionScoped
public class NewPostBean {
    private String message;
    private PostsControl postsControl = new PostsControl();
    private String lat;
    private String lon;

    public String sendPost() throws SQLException, ClassNotFoundException {
        postsControl.sendPost(new Integer(SessionUtils.getUserId()), getMessage(), lat, lon);
        this.message = null;
        return "index";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
