/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioRolEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LENOVO
 */
public class UsuarioRolLogic {

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
            }else{
                sesion=null;
                sesion=HibernateUtil.getSessionFactory().openSession();
                tx=sesion.beginTransaction();
            }
            retorno = "Ok";
        } catch (Error e) {
            retorno = "Error Conexión Hibernate " + e;
        }
        return retorno;
    }

    /**
     * Método que inserta una relación usuario Rol nueva
     *
     * @param usurol
     * @return
     */
    public ObjetoRetornaEntity insertarUsuarioRol(ArrayList<UsuarioRolEntity> usurol) {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        ArrayList<Object> listaRetorna = new ArrayList<>();
        System.out.println("Ingresa");
        if (usurol.size() > 0) {
            //limpia(usurol.get(0).getId_usuario_rol());
        }
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {

                int siguiente = maxUsuarioRol();
                for (UsuarioRolEntity usrol : usurol) {
                    System.out.println("SIGUIENTE: " + siguiente);
                    UsuarioRolEntity usuarioRolEntity = usrol;
                    usuarioRolEntity.setId_usuario_rol(siguiente);
                    siguiente++;
                    sesion.save(usuarioRolEntity);
                    listaRetorna.add(usuarioRolEntity);
                }
                tx.commit();
                sesion.close();
                retorna.setRetorna(listaRetorna);
                retorna.setTrazaRespuesta("Inserción de UsuarioRol Exitosa");
                retorna.setNumeroRespuesta(15);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna = new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }
        return retorna;
    }

    /**
     * Método que limpia las asiganaciones que tiene un usuario antes de que
     * ingresen las nuevas asignaciones de roles
     *
     * @param idUsuario
     */
    public void limpia(int idUsuario) {
        String validaConexion = initOperation();
        
        if (!"Ok".equalsIgnoreCase(validaConexion)) {
            System.out.println("error conexión: "+validaConexion);
        } else {
            Query query = sesion.createQuery("delete UsuarioRolEntity WHERE usuario.idUsuario=:idUsuario");
            query.setParameter("idUsuario", idUsuario);
            int result = query.executeUpdate();
            tx.commit();
            
            System.out.println("LIMPIA: " + result);
        }
    }

    /**
     * Método que trar el siguiente ID de la tabla Usuario-Rol
     *
     * @return
     */
    private int maxUsuarioRol() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(id_usuario_rol) FROM UsuarioRolEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * étodo que permite actualizar una relación usuario-rol
     *
     * @param usurol
     */
    public UsuarioRolEntity actualizarUsuarioRol(UsuarioRolEntity usurol) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                usurol.setNumeroRespuesta(3);
                usurol.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {

                sesion.update(usurol);
                tx.commit();
                sesion.close();
                usurol.setTrazaRespuesta("Actualización de RolPermiso Exitosa");
                usurol.setNumeroRespuesta(16);
            }
        } catch (Exception e) {
            e.printStackTrace();
            usurol = new UsuarioRolEntity();
            usurol.setNumeroRespuesta(0);
            usurol.setTrazaRespuesta(e.getMessage());
        }
        return usurol;
    }

    /**
     * Método que consulta la lista de relaciones entre un Usuarios y Roles
     *
     * @param idUsuario
     * @return
     */
    public ObjetoRetornaEntity listaRolPermisoPorRol(int idUsuario) {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query = sesion.createQuery("SELECT urp FROM UsuarioRolEntity urp, UsuarioEntity u WHERE urp.usuario=u AND u.idUsuario=:idUsuario");
                query.setParameter("idUsuario", idUsuario);
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla Usuario-Rol exitosa");
                retorna.setNumeroRespuesta(21);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna = new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }
        return retorna;
    }

}
