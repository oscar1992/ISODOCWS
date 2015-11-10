/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.utilidades;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class Valida {
    
    public String valida(Object valor, String nombre) {
        String retorno = "";
        if (valor == null) {
            retorno = "" + nombre + ": nulo";
        } else {
            System.out.println("TIPO: " + valor + "-" + valor.getClass().getSimpleName());
            switch (valor.getClass().getSimpleName()) {
                case "String":
                    if (((String)valor).trim().isEmpty()||((String)valor).equalsIgnoreCase("?")) {
                        retorno = "" + nombre + ": vacio";
                    } else {
                        retorno="Ok";
                    }
                    break;
                case "Integer":
                    System.out.println("ENTERO???: "+valor);
                    Integer compara=(Integer) valor;
                    System.out.println("ENTERO???: "+compara);
                    if(compara!=0){
                        retorno="Ok";
                    }else{
                        retorno = "" + nombre + ": llego cero";
                    }
                    break;
                case "Long":
                    System.out.println("Largo??");
                    break;
                case "Boolean":
                    System.out.println("Booleanoooo");
                    break;
                case "Method":
                    System.out.println("METODOOO");
                    break;
                    
                default:
                    System.out.println("Objeto");
                    if(valor!=null){
                        retorno="Ok";
                    }else{
                        retorno = "" + nombre + ": vacio";
                    }
                    break;
            }
        }
        
        return retorno;
    }
}
