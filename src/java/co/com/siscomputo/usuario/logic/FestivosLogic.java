/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.FestivosEntity;
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
public class FestivosLogic {
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
     * Método para ingresar un Festivo
     * @param festivo 
     * @return 
     */
    public FestivosEntity ingresaFestivo(FestivosEntity festivo){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                festivo.setNumeroRespuesta(3);
                festivo.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                festivo.setIdFEstivo(maxFestivo());
                sesion.save(festivo);
                tx.commit();
                sesion.close();
                festivo.setTrazaRespuesta("Inserción de Festivo Exitosa");
                festivo.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            festivo=new FestivosEntity();
            festivo.setNumeroRespuesta(0);
            festivo.setTrazaRespuesta(e.getMessage());            
        }
        return festivo;
    }
    /**
     * Método que retorna el siguiente ID de la tabla festivos
     * @return 
     */
    public int maxFestivo(){
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idFEstivo) FROM FestivosEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    /**
     * Método para actualizar un festivo
     * @param festivo
     * @return 
     */
    public FestivosEntity actualizaFestivo(FestivosEntity festivo){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                festivo.setNumeroRespuesta(3);
                festivo.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                sesion.update(festivo);
                tx.commit();
                sesion.close();
                festivo.setTrazaRespuesta("Actualización de Festivo Exitosa");
                festivo.setNumeroRespuesta(20);
            }
        } catch (Exception e) {
            e.printStackTrace();
            festivo=new FestivosEntity();
            festivo.setNumeroRespuesta(0);
            festivo.setTrazaRespuesta(e.getMessage());            
        }
        return festivo;
    }
    /**
     * Método que retorna la lista de festivos disponibles
     * @return 
     */
    public ObjetoRetornaEntity listaFestivos(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM FestivosEntity");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla Festivos exitosa");
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
    /**
     * Método que trae un Festivo por ID
     * @param idFestivo
     * @return 
     */
    public FestivosEntity festivoPorId(int idFestivo){
        FestivosEntity festivo=new FestivosEntity();
        String validaConexion = initOperation();
        try {
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                festivo.setNumeroRespuesta(3);
                festivo.setTrazaRespuesta("Error de Conexión: " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM FestivosEntity f WHERE f.idFEstivo=:idS");
                query.setParameter("idS", idFestivo);
                festivo=(FestivosEntity) query.uniqueResult();
                festivo.setTrazaRespuesta("Consulta de festivo exitosa");
                festivo.setNumeroRespuesta(35);
            }
        } catch (Exception e) {
            e.printStackTrace();
            festivo = new FestivosEntity();
            festivo.setNumeroRespuesta(0);
            festivo.setTrazaRespuesta(e.getMessage());
        }
        return festivo;
    }
}
