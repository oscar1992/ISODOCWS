/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.TiposDocumentalesEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LENOVO
 */
public class TiposDocumentalesLogic {
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
     * Método para ingresar un TipoDocumental
     * @param tiposd
     * @return 
     */
    public TiposDocumentalesEntity ingresaTipoDocuemtal(TiposDocumentalesEntity tiposd){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                tiposd.setNumeroRespuesta(3);
                tiposd.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                tiposd.setIdTipoDocumental(maxTiposDocuemntales());
                sesion.save(tiposd);
                tx.commit();
                sesion.close();
                tiposd.setTrazaRespuesta("Inserción de Tipos Documentales Exitosa");
                tiposd.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            tiposd=new TiposDocumentalesEntity();
            tiposd.setNumeroRespuesta(0);
            tiposd.setTrazaRespuesta(e.getMessage());            
        }
        return tiposd;
    }
    /**
     * Método que retorna el siguiente ID de la tabla Tipos Documentales
     * @return 
     */
    private int maxTiposDocuemntales() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idTipoDocumental) FROM TiposDocumentalesEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    /**
     * Método para actualizar un Tipo Docuemtal
     * @param tipod
     * @return 
     */
    public TiposDocumentalesEntity actualizaTipoDocuemtal(TiposDocumentalesEntity tipod){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                tipod.setNumeroRespuesta(3);
                tipod.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                sesion.update(tipod);
                tx.commit();
                sesion.close();
                tipod.setTrazaRespuesta("Actualización de Tipo Documental Exitosa");
                tipod.setNumeroRespuesta(20);
            }
        } catch (Exception e) {
            e.printStackTrace();
            tipod=new TiposDocumentalesEntity();
            tipod.setNumeroRespuesta(0);
            tipod.setTrazaRespuesta(e.getMessage());            
        }
        return tipod;
    }
    /**
     * Método que retorna la lista de tipos documentales disponibles
     * @return 
     */
    public ObjetoRetornaEntity listaTipoDcouemntal(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM TiposDocumentalesEntity p WHERE p.estadoTipoDocuemtal<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla Tipos Documentales exitosa");
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
