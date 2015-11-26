
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.persistencia.TipoControlDistribucionEntity;
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
public class TipoControlDistribucionLogic {
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
     * Método que inserta un Tipo de Control de distribución nuevo
     * @param objetoTipoControlDistribucion
     * @return 
     */    public TipoControlDistribucionEntity insertarTipoControlDistribucion(TipoControlDistribucionEntity objetoTipoControlDistribucion){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {                
                objetoTipoControlDistribucion.setNumeroRespuesta(3);
                objetoTipoControlDistribucion.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoTipoControlDistribucion.setIdTipoControlDistribucion(maxMetodo());
                sesion.save(objetoTipoControlDistribucion);
                tx.commit();

                objetoTipoControlDistribucion.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoTipoControlDistribucion.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoTipoControlDistribucion = new TipoControlDistribucionEntity();
            objetoTipoControlDistribucion.setNumeroRespuesta(0);
            objetoTipoControlDistribucion.setTrazaRespuesta(e.getMessage());
        }
        return objetoTipoControlDistribucion;
    }

     /**
     * Método que trae el siguiente ID de la tabla ADM_TOCNT
     * @return 
     */    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idTipoControlDistribucion) FROM TipoControlDistribucionEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    
     /**
     * Método que actualiza un Tipo de Control de distribución
     * @param objetoTipoControlDistribucion
     * @return 
     */ public TipoControlDistribucionEntity actualizarTipoControlDistribucion(TipoControlDistribucionEntity objetoTipoControlDistribucion){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoTipoControlDistribucion.setNumeroRespuesta(3);
                objetoTipoControlDistribucion.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                System.out.println("JJ");
                sesion.update(objetoTipoControlDistribucion);
                tx.commit();
                sesion.close();
                objetoTipoControlDistribucion.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoTipoControlDistribucion.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoTipoControlDistribucion = new TipoControlDistribucionEntity();
            objetoTipoControlDistribucion.setNumeroRespuesta(0);
            objetoTipoControlDistribucion.setTrazaRespuesta(e.getMessage());
        }
        return objetoTipoControlDistribucion;
    }
     /**
     * Método que permite actualizar un Tipo de Control de distribución
     * @return 
     */public ObjetoRetornaEntity listaTipoControlDistribucion(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM TipoControlDistribucionEntity d WHERE d.estadoTipoControlDistribucion<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla TipoControlDistribucion exitosa");
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