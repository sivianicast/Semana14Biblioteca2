/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import java.sql.Date;

/**
 *
 * @author Jherom
 */
public class Condicion {
    public static enum Operadores{
        AND,
        OR
    }
    
    public String campo;
    
    private String valor;
    
    public void setValorEntero(int valor){
        this.valor = Integer.toString(valor);
    }
    
    public void setValorDouble(double valor){
        this.valor = Double.toString(valor);
    }
    
    public void setValorString(String valor){
        this.valor = "'" + valor + "'";
    }
    
    public void setValorBooleano(boolean valor){
        this.valor = valor? "true" : "false";
    }
    
    public void setValorFecha(Date valor){
        this.valor = "'"+valor+"'";
    }
}






















