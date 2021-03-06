/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.AreaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LENOVO
 */
public class AreaLogic {

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
 * Método que permite ingresar un área nueva
 * @param area
 * @return 
 */
    public AreaEntity ingresarArea(AreaEntity area) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                area.setNumeroRespuesta(3);
                area.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                area.setIdArea(maxArea());
                sesion.save(area);
                tx.commit();
                
                area.setTrazaRespuesta("Inserción de Area exitoso");
                area.setNumeroRespuesta(18);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            area = new AreaEntity();
            area.setNumeroRespuesta(0);
            area.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return area;
    }
    /**
     * Método que trae el siguiente ID
     * @return 
     */
    public int maxArea() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idArea) FROM AreaEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    /**
     * Método que permite actiulizar un área
     * @param area
     * @return 
     */
    public AreaEntity actualizarArea(AreaEntity area) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                area.setNumeroRespuesta(3);
                area.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                
                sesion.update(area);
                tx.commit();
                
                area.setTrazaRespuesta("Actualización de Area exitoso");
                area.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            area = new AreaEntity();
            area.setNumeroRespuesta(0);
            area.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return area;
    }
    /**
     * método que devuelve una lista de áreas
     * @return 
     */
    public ObjetoRetornaEntity listaArea() {
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM AreaEntity a WHERE a.estadoArea<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla Areas exitosa");
                retorna.setNumeroRespuesta(22);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna=new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return retorna ;
    }
}
