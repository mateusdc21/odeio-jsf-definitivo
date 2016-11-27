package beerzeit.dao;

import beerzeit.model.Usuario;
import beerzeit.utils.exception.InvalidUserException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO extends DAO{
    public void inserir(Usuario u) throws ClassNotFoundException, SQLException {
        this.open();
        PreparedStatement stmt = this.conn.prepareStatement(
                "INSERT INTO public.usuario(name, email, password, dateofbirth) VALUES (?, ?, ?, ?);"
        );

        stmt.setString(1, u.getName());
        stmt.setString(2, u.getEmail());
        stmt.setString(3, u.getPassword());
        stmt.setString(4, u.getDateOfBirth());
        stmt.execute();
        this.close();
    }

    public Usuario getUser(String email) throws SQLException, ClassNotFoundException {
        this.open();
        PreparedStatement stmt = this.conn.prepareStatement(
                "SELECT email, password, name, dateofbirth FROM public.usuario WHERE email like ?"
        );
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return new Usuario(
                rs.getString("name"),
                rs.getString("dateofbirth"),
                rs.getString("password"),
                rs.getString("email")
        );
    }
}
