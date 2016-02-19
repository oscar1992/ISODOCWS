package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.proveedores.persistencia.AnexoProveedorEntity;
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
public class AnexoProveedorLogic implements AutoCloseable {

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
     * Método que inserta un Anexos del proveedor nuevo
     *
     * @param objetoAnexoProveedor
     * @return
     */
    public AnexoProveedorEntity insertarAnexoProveedor(AnexoProveedorEntity objetoAnexoProveedor) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoAnexoProveedor.setNumeroRespuesta(3);
                objetoAnexoProveedor.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoAnexoProveedor.setIdAnexoProveedor(maxMetodo());
                sesion.save(objetoAnexoProveedor);
                tx.commit();

                objetoAnexoProveedor.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoAnexoProveedor.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoAnexoProveedor = new AnexoProveedorEntity();
            objetoAnexoProveedor.setNumeroRespuesta(0);
            objetoAnexoProveedor.setTrazaRespuesta(e.getMessage());
        }
        return objetoAnexoProveedor;
    }

    /**
     * Método que trae el siguiente ID de la tabla PRO_TANPR
     *
     * @return
     */
    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idAnexoProveedor) FROM AnexoProveedorEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método que actualiza un Anexos del proveedor
     *
     * @param objetoAnexoProveedor
     * @return
     */
    public AnexoProveedorEntity actualizarAnexoProveedor(AnexoProveedorEntity objetoAnexoProveedor) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoAnexoProveedor.setNumeroRespuesta(3);
                objetoAnexoProveedor.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                System.out.println("JJ");
                sesion.update(objetoAnexoProveedor);
                tx.commit();
                sesion.close();
                objetoAnexoProveedor.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoAnexoProveedor.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoAnexoProveedor = new AnexoProveedorEntity();
            objetoAnexoProveedor.setNumeroRespuesta(0);
            objetoAnexoProveedor.setTrazaRespuesta(e.getMessage());
        }
        return objetoAnexoProveedor;
    }

    /**
     * Método Método para consultar la lista de Anexos del proveedor
     *
     * @return
     */
    public ObjetoRetornaEntity listaAnexoProveedor() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria = sesion.createCriteria(AnexoProveedorEntity.class);
                criteria.add(Restrictions.ne("estadoAnexoProveedor", "E"));
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Consulta tabla AnexoProveedor exitosa");
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
