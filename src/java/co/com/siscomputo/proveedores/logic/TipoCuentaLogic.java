/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.proveedores.persistencia.TipoCuentaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Felipe
 */
public class TipoCuentaLogic {

    private Session sesion;//Variable de la sesión y conexión de la base de datos
    private Transaction tx;//Variable que almacena las consultas y las transacciones de la base de datos

    /**
     * Metodo para establecer una conexion con la base de datos
     *
     * @return
     */
    private String initOperation() {
        String respuesta;
        try {
            if (sesion == null) {
                sesion = HibernateUtil.getSessionFactory().openSession();
                tx = sesion.beginTransaction();
            }
            respuesta = "Ok";

        } catch (Exception e) {
            respuesta = "Error Conexión Hibernate " + e;
        }

        return respuesta;
    }

    /**
     * Método que trae el siguiente ID de una tabla
     *
     * @return
     */
    private int maxDocumento() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idTipoCuenta) FROM TipoCuentaEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Logica para insertar un tipo de cuenta
     *
     * @param ObjtipoCuenta
     * @return
     */
    public TipoCuentaEntity insertarTipoCuenta(TipoCuentaEntity ObjtipoCuenta) {
        try {
            String validaConex = initOperation();
            if ("OK".equalsIgnoreCase(validaConex)) {
                ObjtipoCuenta.setIdTipoCuenta(maxDocumento());
                sesion.save(ObjtipoCuenta);
                tx.commit();
                ObjtipoCuenta.setNumeroRespuesta(23);
                ObjtipoCuenta.setTrazaRespuesta("Tipo de cuenta insertada correctamente");
            } else {
                ObjtipoCuenta.setNumeroRespuesta(3);
                ObjtipoCuenta.setTrazaRespuesta("Error de Conexión " + validaConex);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ObjtipoCuenta = new TipoCuentaEntity();
            ObjtipoCuenta.setNumeroRespuesta(0);
            ObjtipoCuenta.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return ObjtipoCuenta;
    }

    /**
     * Metodo para actualizar el tipo de cuenta de proveedores
     *
     * @param ObjtipoCuenta
     * @return
     */
    public TipoCuentaEntity actualizarTipoCuenta(TipoCuentaEntity ObjtipoCuenta) {

        try {
            String conex = initOperation();
            if ("OK".equalsIgnoreCase(conex)) {
                sesion.update(ObjtipoCuenta);
                tx.commit();
                ObjtipoCuenta.setNumeroRespuesta(0);
                ObjtipoCuenta.setTrazaRespuesta("TipoCuentaActualizado correctamente");
            } else {
                ObjtipoCuenta.setTrazaRespuesta("Error de Conexión" + conex);
                ObjtipoCuenta.setNumeroRespuesta(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ObjtipoCuenta;
    }

    /**
     * Metodo que trae todos los tipos de cuenta
     *
     * @return
     */
    public ObjetoRetornaEntity listaTipoCuenta() {
        ObjetoRetornaEntity retorno = new ObjetoRetornaEntity();
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                Query sentencia = sesion.createQuery("FROM TipoCuentaEntity t WHERE t.estadoTipoCuenta<>'E'");
                retorno.setRetorna((ArrayList<Object>) sentencia.list());
                retorno.setTrazaRespuesta("Consulta tabla TipoCuenta exitosa");
                retorno.setNumeroRespuesta(1);
            } else {
                retorno.setNumeroRespuesta(0);
                retorno.setTrazaRespuesta("Error de conexion" + conexion);
            }
        } catch (Exception e) {
            retorno = new ObjetoRetornaEntity();
            retorno.setNumeroRespuesta(0);
            retorno.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                retorno = new ObjetoRetornaEntity();
                retorno.setNumeroRespuesta(0);
                retorno.setTrazaRespuesta(e.getMessage());
            }
        }

        return retorno;
    }

}
