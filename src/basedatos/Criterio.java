/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

/**
 *
 * @author Jherom
 */
public class Criterio {
    private final String WHERE = " where ";
    public String getWhere(){
        return WHERE + this.getConditions();
    }

    private String getConditions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
