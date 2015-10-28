/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.PaisEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LENOVO
 */
public class PaisLogic {
    private Session sesion;//Variable de la sesión y conexión de la base de datos

    private Transaction tx;//Variable que almacena las consultas y las transacciones de la base de datos

    /**
     * Metodo que establece la conexión a la base de datos, previa validación de
     * que la sesión no exista o este nula
     *
     * @throws HibernateException
     */
    private String initOperation() {
        String retorno;
        try {
            if (sesion == null) {
                sesion = HibernateUtil.getSessionFactory().openSession();
                tx = sesion.beginTransaction();
            }
            retorno = "Ok";
        } catch (Error e) {
            retorno = "Error Conexión Hibernate " + e;
        }
        return retorno;
    }
    /**
     * Método para ingresar un país
     * @param pais
     * @return 
     */
    public PaisEntity ingresaPais(PaisEntity pais){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                pais.setNumeroRespuesta(3);
                pais.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                pais.setIdPais(maxPais());
                sesion.save(pais);
                tx.commit();
                sesion.close();
                pais.setTrazaRespuesta("Inserción de Permiso Exitosa");
                pais.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            pais=new PaisEntity();
            pais.setNumeroRespuesta(0);
            pais.setTrazaRespuesta(e.getMessage());            
        }
        return pais;
    }
    
    public int maxPais() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idPais) FROM PaisEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    /**
     * Método para actualizar un país
     * @param pais
     * @return 
     */
    public PaisEntity actualizaPais(PaisEntity pais){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                pais.setNumeroRespuesta(3);
                pais.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                sesion.update(pais);
                tx.commit();
                sesion.close();
                pais.setTrazaRespuesta("Actualización de Permiso Exitosa");
                pais.setNumeroRespuesta(20);
            }
        } catch (Exception e) {
            e.printStackTrace();
            pais=new PaisEntity();
            pais.setNumeroRespuesta(0);
            pais.setTrazaRespuesta(e.getMessage());            
        }
        return pais;
    }
    /**
     * Método que retorna la lista de países disponibles
     * @return 
     */
    public ObjetoRetornaEntity listaPais(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM PaisEntity");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla Países exitosa");
                retorna.setNumeroRespuesta(21);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna=new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }
        return retorna;
    }
}
