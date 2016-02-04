/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.SedeEmpresaEntity;
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
public class SedeEmpresaLogic {
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
     * Método que permite ingresar una Sede_Empresa nueva
     * @param sedeE
     * @return 
     */
    public SedeEmpresaEntity ingresarSedeEmpresa(SedeEmpresaEntity sedeE){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                sedeE.setNumeroRespuesta(3);
                sedeE.setTrazaRespuesta("Error de Conexión: "+validaConexion);
            } else {
                sedeE.setIdSedeEmpresa(maxSedeEmpresa());
                sesion.save(sedeE);
                tx.commit();
                 
                sedeE.setTrazaRespuesta("Inserción de SedeEmpresa exitosa");
                sedeE.setNumeroRespuesta(13);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sedeE= new SedeEmpresaEntity();
            sedeE.setNumeroRespuesta(0);
            sedeE.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return sedeE;
    }
    /**
     * Método que consulta el siguente ID de la tabla Sede-Empresa
     * @return 
     */
    public int maxSedeEmpresa(){
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idSedeEmpresa) FROM SedeEmpresaEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }

        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    /**
     * Método que actualiza un registro de la tabla Sede-Empresa
     * @param sedeE
     * @return 
     */
    public SedeEmpresaEntity actualizarsedeEmpresa(SedeEmpresaEntity sedeE){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                sedeE.setNumeroRespuesta(3);
                sedeE.setTrazaRespuesta("Error de Conexión: "+validaConexion);
            } else {                
                sesion.update(sedeE);
                tx.commit();
                 
                sedeE.setTrazaRespuesta("Inserción de SedeEmpresa exitosa");
                sedeE.setNumeroRespuesta(13);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sedeE= new SedeEmpresaEntity();
            sedeE.setNumeroRespuesta(0);
            sedeE.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return sedeE;
    }
    /**
     * Método que construe una lista de registros de la tabla Sede-Empresa
     * @return 
     */
    public ObjetoRetornaEntity listaRoles() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            initOperation();
            Query query = sesion.createQuery("FROM SedeEmpresaEntity");
            retorna.setRetorna((ArrayList<Object>) query.list());
            retorna.setTrazaRespuesta("Carga de Sedes-Empresa exitosa");
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
