/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.proveedores.persistencia.TipoMonedaEntity;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Felipe
 */
public class TipoMonedaLogic {

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
                Query query = sesion.createQuery("SELECT MAX(idMoneda) FROM TipoMonedaEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Metodo para insertar un tipo de moneda
     *
     * @param objMoneda
     * @return
     */
    public TipoMonedaEntity InsertarTipoMoneda(TipoMonedaEntity objMoneda) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                objMoneda.setIdMoneda(maxDocumento());
                sesion.save(objMoneda);
                tx.commit();
                objMoneda.setNumeroRespuesta(23);
                objMoneda.setTrazaRespuesta("Insercion exitosa");
            } else {
                objMoneda.setNumeroRespuesta(3);
                objMoneda.setTrazaRespuesta("Error de conexion" + conexion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objMoneda.setNumeroRespuesta(0);
            objMoneda.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
                objMoneda.setNumeroRespuesta(0);
                objMoneda.setTrazaRespuesta(e.getMessage());
            }

        }
        return objMoneda;
    }

    /**
     * Metodo para actualizar un tipo de moneda
     *
     * @param objMoneda
     * @return
     */
    public TipoMonedaEntity actualizarTipoMoneda(TipoMonedaEntity objMoneda) {
        try {
            String conexion = initOperation();
            if (conexion.equalsIgnoreCase(conexion)) {
                sesion.update(objMoneda);
                tx.commit();
                objMoneda.setNumeroRespuesta(23);
                objMoneda.setTrazaRespuesta("Insercion exitosa");
            } else {
                objMoneda.setNumeroRespuesta(0);
                objMoneda.setTrazaRespuesta("Error de Conexion " + conexion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objMoneda.setNumeroRespuesta(0);
            objMoneda.setTrazaRespuesta(e.getMessage());

        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
                objMoneda.setNumeroRespuesta(0);
                objMoneda.setTrazaRespuesta(e.getMessage());
            }
        }
        return objMoneda;
    }

    public ObjetoRetornaEntity listaTipoMoneda() {
        ObjetoRetornaEntity retorno = new ObjetoRetornaEntity();
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                Query sentencia = sesion.createQuery("FROM TipoMonedaEntity WHERE estadoMoneda<>'E'");
                retorno.setRetorna((ArrayList<Object>) sentencia.list());
                retorno.setTrazaRespuesta("Consulta tabla lista de moneda exitosa");
                retorno.setNumeroRespuesta(1);
            } else {
                retorno.setNumeroRespuesta(0);
                retorno.setTrazaRespuesta("Error de conexion" + conexion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorno = new ObjetoRetornaEntity();
            retorno.setNumeroRespuesta(0);
            retorno.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
                retorno.setNumeroRespuesta(0);
                retorno.setTrazaRespuesta(e.getMessage());
            }
        }
        return retorno;
    }

}
