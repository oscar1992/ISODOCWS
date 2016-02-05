package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.proveedores.persistencia.TipoProveedorEntity;
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
public class TipoProveedorLogic {

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
     * Método que inserta un Tipo de Proveedor nuevo
     *
     * @param objetoTipoProveedor
     * @return
     */
    public TipoProveedorEntity insertarTipoProveedor(TipoProveedorEntity objetoTipoProveedor) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoTipoProveedor.setNumeroRespuesta(3);
                objetoTipoProveedor.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoTipoProveedor.setIdTipoProveedor(maxMetodo());
                sesion.save(objetoTipoProveedor);
                tx.commit();

                objetoTipoProveedor.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoTipoProveedor.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoTipoProveedor = new TipoProveedorEntity();
            objetoTipoProveedor.setNumeroRespuesta(0);
            objetoTipoProveedor.setTrazaRespuesta(e.getMessage());
        }
        return objetoTipoProveedor;
    }

    /**
     * Método que trae el siguiente ID de la tabla PRO_TTIPO
     *
     * @return
     */
    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idTipoProveedor) FROM TipoProveedorEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método que actualiza un Tipo de Proveedor
     *
     * @param objetoTipoProveedor
     * @return
     */
    public TipoProveedorEntity actualizarTipoProveedor(TipoProveedorEntity objetoTipoProveedor) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoTipoProveedor.setNumeroRespuesta(3);
                objetoTipoProveedor.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                System.out.println("JJ");
                sesion.update(objetoTipoProveedor);
                tx.commit();
                sesion.close();
                objetoTipoProveedor.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoTipoProveedor.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoTipoProveedor = new TipoProveedorEntity();
            objetoTipoProveedor.setNumeroRespuesta(0);
            objetoTipoProveedor.setTrazaRespuesta(e.getMessage());
        }
        return objetoTipoProveedor;
    }

    /**
     * Método Método para consultar la lista de Tipo de Proveedor
     */
    public ObjetoRetornaEntity listaTipoProveedor() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria = sesion.createCriteria(TipoProveedorEntity.class);
                criteria.add(Restrictions.ne("estadoTipoProveedor", "E"));
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Consulta tabla TipoProveedor exitosa");
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
}
