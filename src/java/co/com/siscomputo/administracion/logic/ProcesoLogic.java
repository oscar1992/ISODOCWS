package co.com.siscomputo.administracion.logic;

import co.com.siscomputo.administracion.persistencia.ProcesoEntity;
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
public class ProcesoLogic {

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
     * Método que inserta un Procesos nuevo
     *
     * @param objetoProceso
     * @return
     */
    public ProcesoEntity insertarProceso(ProcesoEntity objetoProceso) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoProceso.setNumeroRespuesta(3);
                objetoProceso.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoProceso.setIdProceso(maxMetodo());
           
                
                sesion.save(objetoProceso);
                tx.commit();

                objetoProceso.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoProceso.setNumeroRespuesta(18);
                 
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoProceso = new ProcesoEntity();
            objetoProceso.setNumeroRespuesta(0);
            objetoProceso.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return objetoProceso;
    }

    /**
     * Método que trae el siguiente ID de la tabla ADM_TPROC2
     *
     * @return
     */
    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idProceso) FROM ProcesoEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método que actualiza un Procesos
     *
     * @param objetoProceso
     * @return
     */
    public ProcesoEntity actualizarProceso(ProcesoEntity objetoProceso) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoProceso.setNumeroRespuesta(3);
                objetoProceso.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                System.out.println("JJ");
                sesion.update(objetoProceso);
                tx.commit();
                 
                objetoProceso.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoProceso.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoProceso = new ProcesoEntity();
            objetoProceso.setNumeroRespuesta(0);
            objetoProceso.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return objetoProceso;
    }

    /**
     * Método Método para consultar la lista de Procesos
     *
     * @return
     */
    public ObjetoRetornaEntity listaProceso() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query = sesion.createQuery("FROM ProcesoEntity d WHERE d.estadoProceso<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla Proceso exitosa");
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
/**
 * Método que retorna un proceso po su ID
 * @param idProceso
 * @return 
 */
    public ProcesoEntity procesoPorId(int idProceso) {
        ProcesoEntity pe = new ProcesoEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                pe.setNumeroRespuesta(3);
                pe.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria = sesion.createCriteria(ProcesoEntity.class);
                criteria.add(Restrictions.eq("idProceso", idProceso));                
                pe= (ProcesoEntity) criteria.uniqueResult();
                System.out.println("PE: "+pe.getNombreProceso());
                pe.setTrazaRespuesta("Consulta tabla Proceso exitosa");
                pe.setNumeroRespuesta(22);
                 
            }
        } catch (Exception e) {
            e.printStackTrace();
            pe = new ProcesoEntity();
            pe.setNumeroRespuesta(0);
            pe.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return pe;
    }
}
