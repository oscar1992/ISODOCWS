package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.proveedores.persistencia.TipoTributarioEntity;
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
public class TipoTributarioLogic implements AutoCloseable {

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
     * Método que inserta un Tipo Tributario nuevo
     *
     * @param objetoTipoTributario
     * @return
     */
    public TipoTributarioEntity insertarTipoTributario(TipoTributarioEntity objetoTipoTributario) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoTipoTributario.setNumeroRespuesta(3);
                objetoTipoTributario.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoTipoTributario.setIdTipoTributario(maxMetodo());
                sesion.save(objetoTipoTributario);
                tx.commit();

                objetoTipoTributario.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoTipoTributario.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoTipoTributario = new TipoTributarioEntity();
            objetoTipoTributario.setNumeroRespuesta(0);
            objetoTipoTributario.setTrazaRespuesta(e.getMessage());
        }
        return objetoTipoTributario;
    }

    /**
     * Método que trae el siguiente ID de la tabla PRO_TTIRB
     *
     * @return
     */
    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idTipoTributario) FROM TipoTributarioEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método que actualiza un Tipo Tributario
     *
     * @param objetoTipoTributario
     * @return
     */
    public TipoTributarioEntity actualizarTipoTributario(TipoTributarioEntity objetoTipoTributario) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoTipoTributario.setNumeroRespuesta(3);
                objetoTipoTributario.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                System.out.println("JJ");
                sesion.update(objetoTipoTributario);
                tx.commit();
                sesion.close();
                objetoTipoTributario.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoTipoTributario.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoTipoTributario = new TipoTributarioEntity();
            objetoTipoTributario.setNumeroRespuesta(0);
            objetoTipoTributario.setTrazaRespuesta(e.getMessage());
        }
        return objetoTipoTributario;
    }

    /**
     * Método Método para consultar la lista de Tipo Tributario
     */
    public ObjetoRetornaEntity listaTipoTributario() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria = sesion.createCriteria(TipoTributarioEntity.class);
                criteria.add(Restrictions.ne("estadoTipoTributario", "E"));
                if (criteria.list().isEmpty()) {

                } else {
                    retorna.setRetorna((ArrayList<Object>) criteria.list());
                }
                retorna.setTrazaRespuesta("Consulta tabla TipoTributario exitosa");
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
