/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.AreaEntity;
import co.com.siscomputo.administracion.persistencia.DisposicionesEntity;
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
public class DisposicionesLogic {

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
     * Método que permite insertar una disposicion nueva
     * @param disposicion
     * @return 
     */
    public DisposicionesEntity ingresarDisposición(DisposicionesEntity disposicion) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                disposicion.setNumeroRespuesta(3);
                disposicion.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                disposicion.setIdDisposirciones(maxDisposicion());
                sesion.save(disposicion);
                tx.commit();

                disposicion.setTrazaRespuesta("Inserción de Disposicion exitoso");
                disposicion.setNumeroRespuesta(18);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            disposicion = new DisposicionesEntity();
            disposicion.setNumeroRespuesta(0);
            disposicion.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return disposicion;
    }
    /**
     * Método que trae el siguiente ID de la tabla Disposiciones
     * @return 
     */
    private int maxDisposicion() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idDisposirciones) FROM DisposicionesEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    /**
     * Método que permite actualizar una disposiciónn
     * @param disposicion
     * @return 
     */
    public DisposicionesEntity actualizarDisposicion(DisposicionesEntity disposicion){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                disposicion.setNumeroRespuesta(3);
                disposicion.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                System.out.println("JJ");
                sesion.update(disposicion);
                tx.commit();
                
                disposicion.setTrazaRespuesta("Actualización de Disposicion exitoso");
                disposicion.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            disposicion = new DisposicionesEntity();
            disposicion.setNumeroRespuesta(0);
            disposicion.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return disposicion;
    }
    /**
     * Método que trae una lista de Disposiciones
     * @return 
     */
    public ObjetoRetornaEntity listaDisposicion(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM DisposicionesEntity d WHERE d.estadoDisposiciones<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla Disposiciones exitosa");
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

