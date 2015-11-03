/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioMacroprocesoEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioSubprocesoEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LENOVO
 */
public class UsuarioSubprocesoLogic {
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
     * Método que permite ingresar una relaciín entre un usuario y un subproceso nuevo
     * @param ususubproceso
     * @return 
     */
    public UsuarioSubprocesoEntity ingresaUsuarioSubproceso(UsuarioSubprocesoEntity ususubproceso){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                ususubproceso.setNumeroRespuesta(3);
                ususubproceso.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                ususubproceso.setIdUsuarioSubproceso(maxMacro());
                sesion.save(ususubproceso);
                tx.commit();
                sesion.close();
                ususubproceso.setTrazaRespuesta("Inserción de Usuario-Subproceso Exitosa");
                ususubproceso.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ususubproceso=new UsuarioSubprocesoEntity();
            ususubproceso.setNumeroRespuesta(0);
            ususubproceso.setTrazaRespuesta(e.getMessage());            
        }
        return ususubproceso;
    }
    /**
     * Método que retorna el ID máximo de la tabla relacional usuarios-subprocesos
     * @return 
     */
    private int maxMacro() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idUsuarioSubproceso) FROM UsuarioSubprocesoEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    /**
     * Método que permite actualizar una relación entre un Usuario y un subproceso
     * @param usuSubproceso
     * @return 
     */
    public UsuarioSubprocesoEntity actualizarUsuarioSubproceso(UsuarioSubprocesoEntity usuSubproceso){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                usuSubproceso.setNumeroRespuesta(3);
                usuSubproceso.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                sesion.update(usuSubproceso);
                tx.commit();
                sesion.close();
                usuSubproceso.setTrazaRespuesta("Actualización de Usuario-Subproceso Exitosa");
                usuSubproceso.setNumeroRespuesta(20);
            }
        } catch (Exception e) {
            e.printStackTrace();
            usuSubproceso=new UsuarioSubprocesoEntity();
            usuSubproceso.setNumeroRespuesta(0);
            usuSubproceso.setTrazaRespuesta(e.getMessage());            
        }
        return usuSubproceso;
    }
    /**
     * Método que retorna una lista de relaciones entre usuarios y Subprocesos
     * @return 
     */
    public ObjetoRetornaEntity listaSubProcesos(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM UsuarioSubprocesoEntity");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla usuario-Subproceso exitosa");
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
