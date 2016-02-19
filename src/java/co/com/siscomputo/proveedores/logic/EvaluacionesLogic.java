
package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.proveedores.persistencia.EvaluacionesEntity;
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
public class EvaluacionesLogic implements AutoCloseable{
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
     * Método que inserta un Evaluacion nuevo
     * @param objetoEvaluaciones
     * @return 
     */    public EvaluacionesEntity insertarEvaluaciones(EvaluacionesEntity objetoEvaluaciones){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {                
                objetoEvaluaciones.setNumeroRespuesta(3);
                objetoEvaluaciones.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoEvaluaciones.setIDEvaluaciones(maxMetodo());
                sesion.save(objetoEvaluaciones);
                tx.commit();

                objetoEvaluaciones.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoEvaluaciones.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoEvaluaciones = new EvaluacionesEntity();
            objetoEvaluaciones.setNumeroRespuesta(0);
            objetoEvaluaciones.setTrazaRespuesta(e.getMessage());
        }
        return objetoEvaluaciones;
    }

     /**
     * Método que trae el siguiente ID de la tabla PRO_EVA
     * @return 
     */    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(IDEvaluaciones) FROM EvaluacionesEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    
     /**
     * Método que actualiza un Evaluacion
     * @param objetoEvaluaciones
     * @return 
     */ public EvaluacionesEntity actualizarEvaluaciones(EvaluacionesEntity objetoEvaluaciones){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoEvaluaciones.setNumeroRespuesta(3);
                objetoEvaluaciones.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                System.out.println("JJ");
                sesion.update(objetoEvaluaciones);
                tx.commit();
                sesion.close();
                objetoEvaluaciones.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoEvaluaciones.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoEvaluaciones = new EvaluacionesEntity();
            objetoEvaluaciones.setNumeroRespuesta(0);
            objetoEvaluaciones.setTrazaRespuesta(e.getMessage());
        }
        return objetoEvaluaciones;
    }
     /**
     * Método Método para consultar la lista de Evaluacion
     * @return 
     */public ObjetoRetornaEntity listaEvaluaciones(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria=sesion.createCriteria(EvaluacionesEntity.class);
                criteria.add(Restrictions.ne("estadoEvaluaciones", "E"));      
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Consulta tabla Evaluaciones exitosa");
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