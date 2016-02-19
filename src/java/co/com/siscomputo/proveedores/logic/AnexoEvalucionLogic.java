
package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.proveedores.persistencia.AnexoEvalucionEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author LENOVO
 */
public class AnexoEvalucionLogic implements AutoCloseable{
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
     * Método que inserta un anexos nuevo
     * @param objetoAnexoEvalucion
     * @return 
     */    public AnexoEvalucionEntity insertarAnexoEvalucion(AnexoEvalucionEntity objetoAnexoEvalucion){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {                
                objetoAnexoEvalucion.setNumeroRespuesta(3);
                objetoAnexoEvalucion.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoAnexoEvalucion.setIdAnexoEvalucion(maxMetodo());
                sesion.save(objetoAnexoEvalucion);
                tx.commit();

                objetoAnexoEvalucion.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoAnexoEvalucion.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoAnexoEvalucion = new AnexoEvalucionEntity();
            objetoAnexoEvalucion.setNumeroRespuesta(0);
            objetoAnexoEvalucion.setTrazaRespuesta(e.getMessage());
        }
        return objetoAnexoEvalucion;
    }

     /**
     * Método que trae el siguiente ID de la tabla PRO_ANEX
     * @return 
     */    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(IdAnexoEvalucion) FROM AnexoEvalucionEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    
     /**
     * Método que actualiza un anexo
     * @param objetoAnexoEvalucion
     * @return 
     */ public AnexoEvalucionEntity actualizarAnexoEvalucion(AnexoEvalucionEntity objetoAnexoEvalucion){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoAnexoEvalucion.setNumeroRespuesta(3);
                objetoAnexoEvalucion.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                System.out.println("JJ");
                sesion.update(objetoAnexoEvalucion);
                tx.commit();
                sesion.close();
                objetoAnexoEvalucion.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoAnexoEvalucion.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoAnexoEvalucion = new AnexoEvalucionEntity();
            objetoAnexoEvalucion.setNumeroRespuesta(0);
            objetoAnexoEvalucion.setTrazaRespuesta(e.getMessage());
        }
        return objetoAnexoEvalucion;
    }
     /**
     *  Método para consultar la lista de anexos
     * @return 
     */public ObjetoRetornaEntity listaAnexoEvalucion(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria=sesion.createCriteria(AnexoEvalucionEntity.class);
                criteria.add(Restrictions.ne("rutaAnexoEvalucion", "E"));      
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Consulta tabla AnexoEvalucion exitosa");
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
     
     @Override
    public void close() throws Exception {
        try {
            if (tx != null) {
                tx.commit();
            }
            if (sesion != null) {
                sesion.close();
                sesion = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}