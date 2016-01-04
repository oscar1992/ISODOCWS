package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.persistencia.AccionEntity;
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
public class AccionLogic {

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
     * Método que inserta un Acción nuevo
     *
     * @param objetoAccion
     * @return
     */
    public AccionEntity insertarAccion(AccionEntity objetoAccion) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoAccion.setNumeroRespuesta(3);
                objetoAccion.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoAccion.setIdAccion(maxMetodo());
                sesion.save(objetoAccion);
                tx.commit();

                objetoAccion.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoAccion.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoAccion = new AccionEntity();
            objetoAccion.setNumeroRespuesta(0);
            objetoAccion.setTrazaRespuesta(e.getMessage());
        }
        return objetoAccion;
    }

    /**
     * Método que trae el siguiente ID de la tabla ADM_TACCI
     *
     * @return
     */
    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idAccion) FROM AccionEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método que actualiza un Acción
     *
     * @param objetoAccion
     * @return
     */
    public AccionEntity actualizarAccion(AccionEntity objetoAccion) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoAccion.setNumeroRespuesta(3);
                objetoAccion.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                System.out.println("JJ");
                sesion.update(objetoAccion);
                tx.commit();
                sesion.close();
                objetoAccion.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoAccion.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoAccion = new AccionEntity();
            objetoAccion.setNumeroRespuesta(0);
            objetoAccion.setTrazaRespuesta(e.getMessage());
        }
        return objetoAccion;
    }

    /**
     * Método Método para consultar la lista de Acción
     */
    public ObjetoRetornaEntity listaAccion() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query = sesion.createQuery("FROM AccionEntity d WHERE d.estadoAccion<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla Accion exitosa");
                retorna.setNumeroRespuesta(22);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna = new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }
        return retorna;
    }
    
    public AccionEntity AccionPorId(Integer idAccion){
        AccionEntity accionEntity=new AccionEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                accionEntity.setNumeroRespuesta(3);
                accionEntity.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria=sesion.createCriteria(AccionEntity.class);
                criteria.add(Restrictions.eq("idAccion", idAccion));
                accionEntity=(AccionEntity) criteria.uniqueResult();
                accionEntity.setNumeroRespuesta(22);
                accionEntity.setTrazaRespuesta("Consulta Accion por ID exitosa");
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            accionEntity = new AccionEntity();
            accionEntity.setNumeroRespuesta(0);
            accionEntity.setTrazaRespuesta(e.getMessage());
        }
        return accionEntity;
    }
}
