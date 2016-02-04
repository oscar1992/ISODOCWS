
package co.com.siscomputo.administracion.logic;

import co.com.siscomputo.administracion.persistencia.TipoAlmacenamientoEntity;
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
public class TipoAlmacenamientoLogic {
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
     * Método que inserta un Tipo de Almacenamiento nuevo
     * @param objetoTipoAlmacenamiento
     * @return 
     */    public TipoAlmacenamientoEntity insertarTipoAlmacenamiento(TipoAlmacenamientoEntity objetoTipoAlmacenamiento){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {                
                objetoTipoAlmacenamiento.setNumeroRespuesta(3);
                objetoTipoAlmacenamiento.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoTipoAlmacenamiento.setIdTipoAlmacenamiento(maxMetodo());
                sesion.save(objetoTipoAlmacenamiento);
                tx.commit();

                objetoTipoAlmacenamiento.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoTipoAlmacenamiento.setNumeroRespuesta(18);
                 
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoTipoAlmacenamiento = new TipoAlmacenamientoEntity();
            objetoTipoAlmacenamiento.setNumeroRespuesta(0);
            objetoTipoAlmacenamiento.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return objetoTipoAlmacenamiento;
    }

     /**
     * Método que trae el siguiente ID de la tabla ADM_TALMA
     * @return 
     */    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idTipoAlmacenamiento) FROM TipoAlmacenamientoEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    
     /**
     * Método que actualiza un Tipo de Almacenamiento
     * @param objetoTipoAlmacenamiento
     * @return 
     */ public TipoAlmacenamientoEntity actualizarTipoAlmacenamiento(TipoAlmacenamientoEntity objetoTipoAlmacenamiento){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoTipoAlmacenamiento.setNumeroRespuesta(3);
                objetoTipoAlmacenamiento.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                System.out.println("JJ");
                sesion.update(objetoTipoAlmacenamiento);
                tx.commit();
                 
                objetoTipoAlmacenamiento.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoTipoAlmacenamiento.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoTipoAlmacenamiento = new TipoAlmacenamientoEntity();
            objetoTipoAlmacenamiento.setNumeroRespuesta(0);
            objetoTipoAlmacenamiento.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return objetoTipoAlmacenamiento;
    }
     /**
     * Método que permite actualizar un Tipo de Almacenamiento
     * @return 
     */public ObjetoRetornaEntity listaTipoAlmacenamiento(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM TipoAlmacenamientoEntity d WHERE d.estadoTipoAlmacenamiento<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla TipoAlmacenamiento exitosa");
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