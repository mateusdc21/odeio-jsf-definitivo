package beerzeit.beans;

import beerzeit.control.UserManagement;
import beerzeit.model.Usuario;
import beerzeit.utils.AvatarStorage;
import beerzeit.utils.SessionUtils;
import beerzeit.utils.exception.InvalidUserException;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.OutputUtil;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.io.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by pedro on 27/11/16.
 */
@ManagedBean(name="user")
@SessionScoped
public class UserBean {
    private String email;
    private String password;
    private UserManagement um = new UserManagement();
    private Usuario usuario;

    private String fullname;
    private String username;
    private String confirmpassword;
    private String dateofbirth;

    private UploadedFile avatar;


    public String login() {
        try {
            this.usuario = um.login(this.email, this.password);
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("userid", usuario.getId());
            return "index";
        } catch (SQLException | ClassNotFoundException | InvalidUserException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email e/ou senha errado.", ""));
            return "login";
        }
    }

    public String logout() {
        SessionUtils.getSession().invalidate();
        return "login";
    }


    public String signup() {
        try {
            if (avatar != null) {
                String avatarurl = AvatarStorage.save(avatar, this.username);
                Usuario u = new Usuario(
                        fullname,
                        dateofbirth,
                        password,
                        email,
                        username,
                        avatarurl
                );
                um.create(u);
                return "index";
            }
            return "login";
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return "login";
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public UploadedFile getAvatar() {
        return avatar;
    }

    public void setAvatar(UploadedFile avatar) {
        this.avatar = avatar;
    }
}
