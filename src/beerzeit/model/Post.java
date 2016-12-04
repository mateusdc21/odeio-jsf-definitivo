package beerzeit.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by pedro on 27/11/16.
 */
public class Post {
    private int id;
    private Usuario usuario;
    private String message;
    private List<Usuario> likes;
    private Date createdat;
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private String latitude;
    private String longitude;

    public Post(Usuario usuario, String message, List<Usuario> likes, Date createdat, String latitude, String longitude) {
        this.usuario = usuario;
        this.message = message;
        this.likes = likes;
        this.createdat = createdat;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Post(int id, Usuario usuario, String message, List<Usuario> likes, Date createdat, String latitude, String longitude) {
        this.id = id;
        this.usuario = usuario;
        this.message = message;
        this.likes = likes;
        this.createdat = createdat;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Usuario> getLikes() {
        return likes;
    }

    public void setLikes(List<Usuario> likes) {
        this.likes = likes;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public String getHourcreatedat() {
        return timeFormat.format(createdat);
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
