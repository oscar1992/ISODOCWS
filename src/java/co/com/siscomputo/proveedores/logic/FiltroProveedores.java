/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.proveedores.persistencia.ProveedoresEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author LENOVO
 */
public class FiltroProveedores implements AutoCloseable{

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
     *
     * @param idTipoEstado
     * @param idCiudad
     * @param idLinea
     * @param idEmpresa
     * @param idResponsable
     * @param idTipoProveedor
     * @param idTibutaria
     * @param idTipoCuenta
     * @param idFormaPago
     * @return
     */
    public ObjetoRetornaEntity filtrarProvedores(Integer idTipoEstado, Integer idCiudad, Integer idLinea, Integer idEmpresa, Integer idResponsable, Integer idTipoProveedor, Integer idTibutaria, Integer idTipoCuenta, Integer idFormaPago) {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if ("Ok".equalsIgnoreCase(validaConexion)) {
                Criteria criteria = sesion.createCriteria(ProveedoresEntity.class);
                if (idTipoEstado != 0) {
                    criteria.add(Restrictions.eq("estadoProveedor.idEstadoProveedor", idTipoEstado));
                }
                if (idCiudad != 0) {
                    criteria.add(Restrictions.eq("ciudadProveedor", idCiudad));
                }
                if (idLinea != 0) {
                    criteria.add(Restrictions.eq("lineaProveedores.idLinea", idLinea));
                }
                if (idEmpresa != 0) {
                    criteria.add(Restrictions.eq("empresaProveedor.idEmpresa", idEmpresa));
                }
                if (idResponsable != 0) {
                    criteria.add(Restrictions.eq("usuarioResponsable.idUsuario", idResponsable));
                }
                if (idTipoProveedor != 0) {
                    criteria.add(Restrictions.eq("idTipoProveedor.idTipoProveedor", idTipoProveedor));
                }
                if (idTibutaria != 0) {
                    criteria.add(Restrictions.eq("idTipoTributario.idTipoTributario", idTibutaria));
                }
                if (idTipoCuenta != 0) {
                    criteria.add(Restrictions.eq("idTipocuenta.idTipoCuenta", idTipoCuenta));
                }
                if (idFormaPago != 0) {
                    criteria.add(Restrictions.eq("idFormaPago.idFormasPagos", idFormaPago));
                }

                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Carga exitosa de proveedores filtrados");
                retorna.setNumeroRespuesta(99);
            } else {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
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
