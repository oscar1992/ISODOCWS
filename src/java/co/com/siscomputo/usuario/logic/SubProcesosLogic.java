/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.SubprocesoEntity;
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
public class SubProcesosLogic {
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
     * Ingresa
     * @param subpro
     * @return 
     */
    public SubprocesoEntity ingresaSubProceso(SubprocesoEntity subpro){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                subpro.setNumeroRespuesta(3);
                subpro.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                subpro.setIdSubproceso(maxSubproceso());
                sesion.save(subpro);
                tx.commit();
                sesion.close();
                subpro.setTrazaRespuesta("Inserción de Subproceso Exitosa");
                subpro.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            subpro=new SubprocesoEntity();
            subpro.setNumeroRespuesta(0);
            subpro.setTrazaRespuesta(e.getMessage());            
        }
        return subpro;
    }
    /**
     * maxID
     * @return 
     */
    private int maxSubproceso() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idSubproceso) FROM SubprocesoEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    /**
     * Actualizar
     * @param subpro
     * @return 
     */
    public SubprocesoEntity actualizarSubprocesos(SubprocesoEntity subpro){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                subpro.setNumeroRespuesta(3);
                subpro.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                sesion.update(subpro);
                tx.commit();
                sesion.close();
                subpro.setTrazaRespuesta("Actualización de SubProceso Exitosa");
                subpro.setNumeroRespuesta(20);
            }
        } catch (Exception e) {
            e.printStackTrace();
            subpro=new SubprocesoEntity();
            subpro.setNumeroRespuesta(0);
            subpro.setTrazaRespuesta(e.getMessage());            
        }
        return subpro;
    }
    /**
     * Consultar
     * @return 
     */
    public ObjetoRetornaEntity listaSubproceso(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM SubprocesoEntity p WHERE p.estadoSubproceso<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla SubProcesos exitosa");
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
     * porID
     * @param idSubPro
     * @return 
     */
    public SubprocesoEntity subProcesoProID(int idSubPro){
        SubprocesoEntity subpro = new SubprocesoEntity();
        String validaConexion = initOperation();
        try {
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                subpro.setNumeroRespuesta(3);
                subpro.setTrazaRespuesta("Error de Conexión: " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM SubprocesoEntity p WHERE p.idSubproceso=:idS");
                query.setParameter("idS", idSubPro);
                subpro=(SubprocesoEntity) query.uniqueResult();
                subpro.setTrazaRespuesta("Consulta de subpro exitosa");
                subpro.setNumeroRespuesta(35);
            }
        } catch (Exception e) {
            e.printStackTrace();
            subpro = new SubprocesoEntity();
            subpro.setNumeroRespuesta(0);
            subpro.setTrazaRespuesta(e.getMessage());
        }
        return subpro;
    }
}
