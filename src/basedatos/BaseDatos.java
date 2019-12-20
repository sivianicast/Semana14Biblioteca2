/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import entidades.Categoria;
import entidades.Libro;
import entidades.Entidad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jherom Chacon
 */
public class BaseDatos<Entidad> {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String PATH = "jdbc:mysql://localhost:3306/";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    private static final String DATA_BASE_NAME = "biblioteca";
    
    private String message = "";
    
    private entidades.Entidad dato;
    
    private String query="";
    
    private String TABLA="";
    
    public void setDato(entidades.Entidad dato){
        this.dato = dato;
    }
    
    public entidades.Entidad getDato(){
        return this.dato;
    }
    
    public void encontrarUno()throws Exception{
        try{
            if(this.dato instanceof Categoria)
                this.consultar((Categoria)this.dato);
            else if(this.dato instanceof Libro)
                this.consultar((Libro)this.dato);
        }catch(Exception ex){
            throw ex;
        }
    }
    
    public void encontrarTodos()throws Exception{
        try{
            if(this.dato instanceof Categoria)
                this.consultar((Categoria)this.dato);
            else if(this.dato instanceof Libro)
                this.consultar((Libro)this.dato);
        }catch(Exception ex){
            throw ex;
        }
    }
    
    public void encontrarAlgunos() throws Exception{
        try{
            if(this.dato instanceof Categoria)
                this.consultar((Categoria)this.dato);
            else if(this.dato instanceof Libro)
                this.consultar((Libro)this.dato);
        }catch(Exception ex){
            throw ex;
        }
    }
    
    public void salvar() throws Exception{
        try{
            if(this.dato instanceof Categoria){
                this.salvar((Categoria)this.dato);
            }else if(this.dato instanceof Libro){
                this.salvar((Libro)this.dato);
            }
        }catch(Exception ex){
            throw ex;
        }
    }
    
    public void eliminar() throws Exception{
        try{
            if(this.dato instanceof Categoria){
                this.eliminar((Categoria)this.dato);
            }else if(this.dato instanceof Libro){
                this.eliminar((Libro)this.dato);
            }
        }catch(Exception ex){
            throw ex;
        }
    }
    
    private void salvar(Categoria categoria) throws Exception{
        if(!this.exists(categoria))
            this.query = "Insert into biblioteca.categoria"
                    + "(id,nombre,fecha_creacion,activo)"
                + "values("+categoria.getId()+","
                + "'"+categoria.getNombre()+"',"
                + "'" + categoria.getFechaCreacion()+"',"
                + categoria.getEstado()+")";
        else
            this.query = "Update biblioteca.categoria "
                    + "set nombre = '"+categoria.getNombre()+"',"
                    + "fecha_creacion = '"+categoria.getFechaCreacion()+"',"
                    + "activo = "+categoria.getEstado()
                    + " where id = " + categoria.getId();
        try(Connection con = this.getConnection()){
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            this.message = "Entidad salvada!";
        }catch(SQLException sqlEx){throw sqlEx;
        }catch(Exception ex){throw ex;}
    }
    
    private void salvar(Libro libro) throws Exception{
        this.query = "Insert into biblioteca.libro"
                + "(id,categoria,nombre,fecha_llegada,prestado)"
                + "values("+libro.getId()+","
                + ""+libro.getCategoria()+","
                + "'"+libro.getNombre()+"',"
                + "'" + libro.getFechaCreacion()+"',"
                + libro.getEstado()+")";
        try(Connection con = this.getConnection()){
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            this.message = "Entidad salvada!";
        }catch(SQLException sqlEx){throw sqlEx;
        }catch(Exception ex){throw ex;}
    }
    
    private void eliminar(Categoria categoria) throws Exception{
        this.query = "Delete from biblioteca.categoria "
                + "where id = "+categoria.getId();
        try(Connection con = this.getConnection()){
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            this.message = "Entidad eliminada!";
        }catch(SQLException sqlEx){throw sqlEx;
        }catch(Exception ex){throw ex;}
    }
    
    private void eliminar(Libro libro) throws Exception{}
    
    private void consultar(Categoria categoria) throws Exception{
        query = "Select nombre, fecha_creacion, estado "
            + "from biblioteca.categoria "
            + "where id = " + categoria.getId();
        try(Connection con = this.getConnection()){
            PreparedStatement preparedStatement = 
                    con.prepareStatement(query);
            ResultSet resultSet = 
                    preparedStatement.executeQuery();
            if(resultSet.next()){
                this.dato = 
                    new Categoria(categoria.getId());
                ((Categoria)this.dato)
                    .setNombre(resultSet.getString("nombre"));
                ((Categoria)this.dato)
                    .setFechaCreacion(resultSet
                        .getDate("fecha_creacion"));
                ((Categoria)this.dato)
                    .setEstado(resultSet
                        .getBoolean("activo"));
            }
        }catch(SQLException ex){throw ex;
        }catch(Exception ex){throw ex;}
    }
    
    private void consultar(Libro libro) throws Exception{
        query = "Select nombre, fecha_llegada, prestado "
            + "from biblioteca.libro "
            + "where id = " + libro.getId()
            + " and categoria = " + libro.getCategoria();
        try(Connection con = this.getConnection()){
            PreparedStatement preparedStatement = 
                    con.prepareStatement(query);
            ResultSet resultSet = 
                    preparedStatement.executeQuery();
            if(resultSet.next()){
                this.dato = 
                    new Libro(libro.getId(),libro.getCategoria());
                ((Libro)this.dato)
                    .setNombre(resultSet.getString("nombre"));
                ((Libro)this.dato)
                    .setFechaCreacion(resultSet
                        .getDate("fecha_llegada"));
                ((Libro)this.dato)
                    .setEstado(resultSet
                        .getBoolean("preatado"));
            }
        }catch(SQLException ex){throw ex;
        }catch(Exception ex){throw ex;}
    }
    private boolean exists(Categoria categoria) throws Exception{
        return this.exists("categoria");
    }
    private boolean exists(Libro libro) throws Exception{
        return this.exists("libro");
    }
    private boolean exists(String tabla)throws Exception{
        String query = "Select nombre "
                + "from biblioteca." + tabla
                + " where id = " 
                + ((entidades.Entidad)this.dato).getId();
        try(Connection con = this.getConnection()){
            PreparedStatement preparedStatement = 
                    con.prepareStatement(query);
            final ResultSet resultSet = 
                    preparedStatement.executeQuery();
            if(resultSet.next())
                return true;
        }catch(SQLException ex){throw ex;
        }catch(Exception ex){throw ex;}
        return false;
    }
    private Connection getConnection() throws Exception{
        java.sql.Connection connection = null;
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(
                    PATH + DATA_BASE_NAME, USER_NAME, PASSWORD);
        }catch(Exception ex){
            throw ex;
        }finally{
            return connection;
        }
    }

    public String getMessage() {
        return this.message;
    }
}
