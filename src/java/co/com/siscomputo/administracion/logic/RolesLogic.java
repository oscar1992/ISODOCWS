/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.RolesEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author LENOVO
 */
public class RolesLogic {

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
     * Método que crea la lista de roles del sistema
     *
     * @return
     */
    public ObjetoRetornaEntity listaRoles() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            initOperation();
            Query query = sesion.createQuery("FROM RolesEntity r WHERE r.estado_rol<> 'E'");
            retorna.setRetorna((ArrayList<Object>) query.list());
            retorna.setTrazaRespuesta("Carga de Roles exitosa");
            retorna.setNumeroRespuesta(12);
             
        } catch (Exception e) {
            retorna.setNumeroRespuesta(3);
            retorna.setTrazaRespuesta("ERROR: " + e);
            e.printStackTrace();
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return retorna;
    }

    /**
     * Método que ingresa un Rol nuevo
     *
     * @param rol
     * @return
     */
    public RolesEntity ingresarRol(RolesEntity rol) {

        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                rol.setNumeroRespuesta(3);
                rol.setTrazaRespuesta("Error de Conexión: "+validaConexion);
            } else {
                rol.setId_rol(maxRol());
                sesion.save(rol);
                tx.commit();
                 
                rol.setTrazaRespuesta("Inserción de Rol exitosa");
                rol.setNumeroRespuesta(13);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rol= new RolesEntity();
            rol.setNumeroRespuesta(0);
            rol.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return rol;
    }
    /**
     * Método que trae el ID siguiente para la inserción
     * @return 
     */
    public int maxRol() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(id_rol) FROM RolesEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }

        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    /**
     * Método que Actualiza el un Rol
     * @param rol
     * @return 
     */
    public RolesEntity actualizarRol(RolesEntity rol){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                rol.setNumeroRespuesta(3);
                rol.setTrazaRespuesta("Error de Conexión: "+validaConexion);
            } else {
                sesion.update(rol);
                tx.commit();
                 
                rol.setTrazaRespuesta("Rol Actualizado Correctamente");
                rol.setNumeroRespuesta(14);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rol= new RolesEntity();
            rol.setNumeroRespuesta(0);
            rol.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return rol;
    }
    /**
     * Método que crea la lista de roles del sistema
     *
     * @param idRol
     * @return
     */
    public RolesEntity rolPorId(int idRol) {
        RolesEntity retorna = new RolesEntity();
        try {
            initOperation();
            Criteria criteria=sesion.createCriteria(RolesEntity.class);
            criteria.add(Restrictions.ne("estado_rol", "E"));
            criteria.add(Restrictions.eq("id_rol", idRol));
            
            retorna=(RolesEntity) criteria.uniqueResult();
            retorna.setTrazaRespuesta("Carga de Roles exitosa");
            retorna.setNumeroRespuesta(12);
             
        } catch (Exception e) {
            retorna.setNumeroRespuesta(3);
            retorna.setTrazaRespuesta("ERROR: " + e);
            e.printStackTrace();
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return retorna;
    }
}
