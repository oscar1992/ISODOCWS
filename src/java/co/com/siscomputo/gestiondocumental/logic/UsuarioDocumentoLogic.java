/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.gestiondocumental.logic;

import co.com.siscomputo.gestiondocumental.persistencia.UsuarioDocumentoEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author LENOVO
 */
public class UsuarioDocumentoLogic {

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
     * Método que inserta un Relación Usuario Docuemento nuevo
     *
     * @param objetoUsuarioDocumento
     * @return
     */
    public UsuarioDocumentoEntity insertarUsuarioDocumento(UsuarioDocumentoEntity objetoUsuarioDocumento) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoUsuarioDocumento.setNumeroRespuesta(3);
                objetoUsuarioDocumento.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoUsuarioDocumento.setIdUsuarioDocumento(maxMetodo());
                sesion.save(objetoUsuarioDocumento);
                tx.commit();

                objetoUsuarioDocumento.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoUsuarioDocumento.setNumeroRespuesta(18);

            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoUsuarioDocumento = new UsuarioDocumentoEntity();
            objetoUsuarioDocumento.setNumeroRespuesta(0);
            objetoUsuarioDocumento.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();  

            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return objetoUsuarioDocumento;
    }

    /**
     * Método que trae el siguiente ID de la tabla GDO_TUSDO
     *
     * @return
     */
    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idUsuarioDocumento) FROM UsuarioDocumentoEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método que actualiza un Relación Usuario Docuemento
     *
     * @param objetoUsuarioDocumento
     * @return
     */
    public UsuarioDocumentoEntity actualizarUsuarioDocumento(UsuarioDocumentoEntity objetoUsuarioDocumento) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoUsuarioDocumento.setNumeroRespuesta(3);
                objetoUsuarioDocumento.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                System.out.println("JJ");
                sesion.update(objetoUsuarioDocumento);
                tx.commit();

                objetoUsuarioDocumento.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoUsuarioDocumento.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoUsuarioDocumento = new UsuarioDocumentoEntity();
            objetoUsuarioDocumento.setNumeroRespuesta(0);
            objetoUsuarioDocumento.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return objetoUsuarioDocumento;
    }

    /**
     * Método Método para consultar la lista de Relación Usuario Docuemento
     */
    public ObjetoRetornaEntity listaUsuarioDocumento() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria = sesion.createCriteria(UsuarioDocumentoEntity.class);
                criteria.add(Restrictions.eq("estadoUsuarioDocumento", "E"));
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Consulta tabla UsuarioDocumento exitosa");
                retorna.setNumeroRespuesta(22);

            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna = new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
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
