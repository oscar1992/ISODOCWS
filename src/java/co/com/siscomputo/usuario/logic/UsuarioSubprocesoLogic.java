/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioSubprocesoEntity;
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
     * Método que permite insertar una relación usuario-subproceso nueva
     *
     * @param ususub
     * @return
     */
    public ObjetoRetornaEntity ingresaUsuarioSubproceso(ArrayList<UsuarioSubprocesoEntity> ususub) {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        ArrayList<Object> listaretorna = new ArrayList<>();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                int siguiente = maxSub();
                for (UsuarioSubprocesoEntity usuarioSubp : ususub) {
                    UsuarioSubprocesoEntity usuarioSubprocesoEntity = usuarioSubp;
                    usuarioSubprocesoEntity.setIdUsuarioSubproceso(siguiente);
                    siguiente++;
                    sesion.save(usuarioSubprocesoEntity);
                    listaretorna.add(usuarioSubprocesoEntity);
                }
                tx.commit();
                sesion.close();
                retorna.setTrazaRespuesta("Inserción de Usuario-Subproceso Exitosa");
                retorna.setNumeroRespuesta(19);
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
     * Método que retorna el ID máximo de la tabla relacional
     * usuarios-subprocesos
     *
     * @return
     */
    private int maxSub() {
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
     * Método que permite actualizar una relación entre un Usuario y un
     * subproceso
     *
     * @param usuSubproceso
     * @return
     */
    public UsuarioSubprocesoEntity actualizarUsuarioSubproceso(UsuarioSubprocesoEntity usuSubproceso) {
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
            usuSubproceso = new UsuarioSubprocesoEntity();
            usuSubproceso.setNumeroRespuesta(0);
            usuSubproceso.setTrazaRespuesta(e.getMessage());
        }
        return usuSubproceso;
    }

    /**
     * Método que retorna una lista de relaciones entre usuarios y Subprocesos
     *
     * @return
     */
    public ObjetoRetornaEntity listaSubProcesos() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query = sesion.createQuery("FROM UsuarioSubprocesoEntity");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla usuario-Subproceso exitosa");
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
    /**
     * Método que retorna una lista de relaciones Usuarios-SubProcesos
     * @return 
     */
    public ObjetoRetornaEntity listaSubProcesosPorUsuario(int idUsuario){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("SELECT ump FROM UsuarioSubprocesoEntity ump, UsuarioEntity u WHERE ump.idUsuario=u AND u.idUsuario=:idUsuario");
                query.setParameter("idUsuario", idUsuario);
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla usuario-SubProceso exitosa");
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
     * Método que retorna una lista de relaciones Usuarios-MacroProcesos
     * @return 
     */
    public ObjetoRetornaEntity listaMacroProcesosPorUsuarioAccion(int idUsuario, int idAccion){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("SELECT ump FROM UsuarioSubprocesoEntity ump, UsuarioEntity u, AccionEntity a WHERE ump.idUsuario=u AND ump.idAccion=a AND u.idUsuario=:idUsuario AND a.idAccion=:idAccion");
                query.setParameter("idUsuario", idUsuario);
                query.setParameter("idAccion", idAccion);
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
    /**
     * Método que limpia las asiganaciones que tiene un usuario antes de que
     * ingresen las nuevas asignaciones de macroprocesos     *
     * @param idUsuario
     * @param idAccion
     */
    public void limpia(int idUsuario, int idAccion) {
        String validaConexion = initOperation();
        
        if (!"Ok".equalsIgnoreCase(validaConexion)) {
            System.out.println("error conexión: "+validaConexion);
        } else {
            Query query = sesion.createQuery("delete UsuarioSubprocesoEntity WHERE idUsuario.idUsuario=:idUsuario AND idAccion.idAccion=:idAccion");
            query.setParameter("idUsuario", idUsuario);
            query.setParameter("idAccion", idAccion);
            int result = query.executeUpdate();
            tx.commit();
            
            System.out.println("LIMPIA: " + result);
        }
    }
}
