package co.com.siscomputo.mejoramientocontinuo.logic;

import co.com.siscomputo.conexion.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.mejoramientocontinuo.persistencia.AnexoAuditoriaEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author LENOVO
 */
public class AnexoAuditoriaLogic implements AutoCloseable {

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
     * Método que inserta un AnexoAuditoria nuevo
     *
     * @param objetoAnexoAuditoria
     * @return
     */
    public AnexoAuditoriaEntity insertarAnexoAuditoria(AnexoAuditoriaEntity objetoAnexoAuditoria) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoAnexoAuditoria.setNumeroRespuesta(3);
                objetoAnexoAuditoria.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoAnexoAuditoria.setIdAuditoria(maxMetodo());
                sesion.save(objetoAnexoAuditoria);
                tx.commit();

                objetoAnexoAuditoria.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoAnexoAuditoria.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoAnexoAuditoria = new AnexoAuditoriaEntity();
            objetoAnexoAuditoria.setNumeroRespuesta(0);
            objetoAnexoAuditoria.setTrazaRespuesta(e.getMessage());
        }
        return objetoAnexoAuditoria;
    }

    /**
     * Método que trae el siguiente ID de la tabla MJ_TANEX
     *
     * @return
     */
    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(IdAnexo) FROM AnexoAuditoriaEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método que actualiza un AnexoAuditoria
     *
     * @param objetoAnexoAuditoria
     * @return
     */
    public AnexoAuditoriaEntity actualizarAnexoAuditoria(AnexoAuditoriaEntity objetoAnexoAuditoria) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoAnexoAuditoria.setNumeroRespuesta(3);
                objetoAnexoAuditoria.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                System.out.println("JJ");
                sesion.update(objetoAnexoAuditoria);
                tx.commit();
                sesion.close();
                objetoAnexoAuditoria.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoAnexoAuditoria.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoAnexoAuditoria = new AnexoAuditoriaEntity();
            objetoAnexoAuditoria.setNumeroRespuesta(0);
            objetoAnexoAuditoria.setTrazaRespuesta(e.getMessage());
        }
        return objetoAnexoAuditoria;
    }

    /**
     * Método Método para consultar la lista de AnexoAuditoria
     *
     * @return
     */
    public ObjetoRetornaEntity listaAnexoAuditoria() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria = sesion.createCriteria(AnexoAuditoriaEntity.class);
                criteria.add(Restrictions.ne("rutaAnexoAuditoria", "E"));
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Consulta tabla AnexoAuditoria exitosa");
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
