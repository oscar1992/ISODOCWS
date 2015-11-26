
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.persistencia.TiposAccesoEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class TiposAccesoLogic {
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
     * Método que inserta un Tipo de Acceso nuevo
     * @param objetoTiposAcceso
     * @return 
     */    public TiposAccesoEntity insertarTiposAcceso(TiposAccesoEntity objetoTiposAcceso){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {                
                objetoTiposAcceso.setNumeroRespuesta(3);
                objetoTiposAcceso.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoTiposAcceso.setIdTiposAcceso(maxMetodo());
                sesion.save(objetoTiposAcceso);
                tx.commit();

                objetoTiposAcceso.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoTiposAcceso.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoTiposAcceso = new TiposAccesoEntity();
            objetoTiposAcceso.setNumeroRespuesta(0);
            objetoTiposAcceso.setTrazaRespuesta(e.getMessage());
        }
        return objetoTiposAcceso;
    }

     /**
     * Método que trae el siguiente ID de la tabla ADM_TACCE
     * @return 
     */    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idTiposAcceso) FROM TiposAccesoEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    
     /**
     * Método que actualiza un Tipo de Acceso
     * @param objetoTiposAcceso
     * @return 
     */ public TiposAccesoEntity actualizarTiposAcceso(TiposAccesoEntity objetoTiposAcceso){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoTiposAcceso.setNumeroRespuesta(3);
                objetoTiposAcceso.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                System.out.println("JJ");
                sesion.update(objetoTiposAcceso);
                tx.commit();
                sesion.close();
                objetoTiposAcceso.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoTiposAcceso.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoTiposAcceso = new TiposAccesoEntity();
            objetoTiposAcceso.setNumeroRespuesta(0);
            objetoTiposAcceso.setTrazaRespuesta(e.getMessage());
        }
        return objetoTiposAcceso;
    }
     /**
     * Método que permite actualizar un Tipo de Acceso
     * @return 
     */public ObjetoRetornaEntity listaTiposAcceso(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM TiposAccesoEntity d WHERE d.estadoTiposAcceso<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla TiposAcceso exitosa");
                retorna.setNumeroRespuesta(22);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna=new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }
        return retorna ;
    }
}