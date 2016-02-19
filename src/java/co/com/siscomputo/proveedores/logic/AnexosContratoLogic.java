/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.proveedores.persistencia.AnexoContratoEntity;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Felipe
 */
public class AnexosContratoLogic  implements AutoCloseable{

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
                Query query = sesion.createQuery("SELECT MAX(idAnexo) FROM AnexoContratoEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Metodo para insertar un anexo
     *
     * @param Objanexo
     * @return
     */
    public AnexoContratoEntity InsertarAnexo(AnexoContratoEntity Objanexo) {
        try {

            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                Objanexo.setIdAnexo(maxDocumento());
                sesion.save(Objanexo);
                Objanexo.setTrazaRespuesta("Insercion correcta");
                Objanexo.setNumeroRespuesta(23);
            } else {
                Objanexo.setTrazaRespuesta("Error de conexion :" + conexion);
                Objanexo.setNumeroRespuesta(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Objanexo.setIdAnexo(0);
            Objanexo.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return Objanexo;
    }

    /**
     * Metodo para actualizar un anexo de contrato
     *
     * @param ObjContratoAne
     * @return
     */
    public AnexoContratoEntity actualizarAnexoContrato(AnexoContratoEntity ObjContratoAne) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                sesion.update(ObjContratoAne);
                tx.commit();
                ObjContratoAne.setTrazaRespuesta("Actualizacion correcta");
                ObjContratoAne.setNumeroRespuesta(23);
            } else {
                ObjContratoAne.setTrazaRespuesta("Error de conexion :" + conexion);
                ObjContratoAne.setNumeroRespuesta(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ObjContratoAne.setTrazaRespuesta(e.getMessage());
            ObjContratoAne.setNumeroRespuesta(0);
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return ObjContratoAne;

    }

    /**
     * Metodo para listar todos los anexos de los contratos
     *
     * @return
     */
    public ObjetoRetornaEntity listarAnexos() {
        ObjetoRetornaEntity retorno = new ObjetoRetornaEntity();
        String conex = initOperation();
        try {

            if ("OK".equalsIgnoreCase(conex)) {
                Query sentencia = sesion.createQuery("From AnexoContratoEntity where ubicacionAnexo<>''");
                retorno.setRetorna((ArrayList<Object>) sentencia.list());
                retorno.setTrazaRespuesta("Consulta tabla  de anexos exitosa");
                retorno.setNumeroRespuesta(1);
            } else {
                retorno.setNumeroRespuesta(0);
                retorno.setTrazaRespuesta("Error de conexion" + conex);
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
