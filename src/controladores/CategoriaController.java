/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import basedatos.BaseDatos;
import entidades.Categoria;
import entidades.Entidad;
import enumeradores.EstadosTransaccionales;
import excepciones.BibliotecaException;
import interfaces.Transaccionable;
import java.util.List;

/**
 *
 * @author Jherom
 */
public class CategoriaController implements Transaccionable{
    private BaseDatos baseDatos;
    
    private Categoria categoria;
    
    public CategoriaController(){
        baseDatos = new BaseDatos();
    }
    
    public void setEntidad(Categoria categoria){
        this.categoria = categoria;
        baseDatos.setDato(this.categoria);
    }

    @Override
    public EstadosTransaccionales salvar() throws BibliotecaException {
        try{
            baseDatos.salvar();
            return EstadosTransaccionales.Exitoso;
        }catch(Exception ex){
            throw new BibliotecaException(ex.getMessage());
        }
    }

    @Override
    public EstadosTransaccionales eliminar() throws BibliotecaException {
        try{
            baseDatos.eliminar();
            return EstadosTransaccionales.Exitoso;
        }catch(Exception ex){
            throw new BibliotecaException(ex.getMessage());
        }
    }

    @Override
    public Entidad encontrarUno() throws BibliotecaException {
        try{
            return null;
        }catch(Exception ex){
            throw new BibliotecaException(ex.getMessage());
        }
    }

    @Override
    public List<Entidad> encontrarEntidades() throws BibliotecaException {
        try{
            return null;
        }catch(Exception ex){
            throw new BibliotecaException(ex.getMessage());
        }
    }
}
