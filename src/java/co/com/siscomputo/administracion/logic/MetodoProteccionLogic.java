
package co.com.siscomputo.administracion.logic;

import co.com.siscomputo.administracion.persistencia.MetodoProteccionEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import java.util.ArrayList;
import org.hibernate.HibernateException;

/**
 *
 * @author LENOVO
 */
public class MetodoProteccionLogic {
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
     * Método que inserta un Métodos de Protección nuevo
     * @param objetoMetodoProteccion
     * @return 
     */    public MetodoProteccionEntity insertarMetodoProteccion(MetodoProteccionEntity objetoMetodoProteccion){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {                
                objetoMetodoProteccion.setNumeroRespuesta(3);
                objetoMetodoProteccion.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoMetodoProteccion.setIdMetodoProteccion(maxMetodo());
                sesion.save(objetoMetodoProteccion);
                tx.commit();

                objetoMetodoProteccion.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoMetodoProteccion.setNumeroRespuesta(18);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoMetodoProteccion = new MetodoProteccionEntity();
            objetoMetodoProteccion.setNumeroRespuesta(0);
            objetoMetodoProteccion.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return objetoMetodoProteccion;
    }

     /**
     * Método que trae el siguiente ID de la tabla ADM_PROT
     * @return 
     */    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idMetodoProteccion) FROM MetodoProteccionEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    
     /**
     * Método que actualiza un Métodos de Protección
     * @param objetoMetodoProteccion
     * @return 
     */ public MetodoProteccionEntity actualizarMetodoProteccion(MetodoProteccionEntity objetoMetodoProteccion){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoMetodoProteccion.setNumeroRespuesta(3);
                objetoMetodoProteccion.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                System.out.println("JJ");
                sesion.update(objetoMetodoProteccion);
                tx.commit();
                
                objetoMetodoProteccion.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoMetodoProteccion.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoMetodoProteccion = new MetodoProteccionEntity();
            objetoMetodoProteccion.setNumeroRespuesta(0);
            objetoMetodoProteccion.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return objetoMetodoProteccion;
    }
     /**
     * Método que permite actualizar un Métodos de Protección
     * @return 
     */public ObjetoRetornaEntity listaMetodoProteccion(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM MetodoProteccionEntity d WHERE d.estadoMetodoProteccion<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla MetodoProteccion exitosa");
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