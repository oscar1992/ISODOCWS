/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.entites;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class ObjetoRetornaEntity extends ObjetoTraza implements Serializable{
    private ArrayList<Object> retorna;

    public ArrayList<Object> getRetorna() {
        return retorna;
    }

    public void setRetorna(ArrayList<Object> retorna) {
        this.retorna = retorna;
    }
    

    
}
