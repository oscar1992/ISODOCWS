/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.AccionEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.proveedores.persistencia.CertificadoCalidadEntity;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Felipe
 */
public class CertificadoLogic {

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
                Query query = sesion.createQuery("SELECT MAX(idCertificado) FROM CertificadoCalidadEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Metodo para insertar un certificado de calidad
     *
     * @param certificado
     * @return
     */
    public CertificadoCalidadEntity insertarCertificadoCalidad(CertificadoCalidadEntity certificado) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                certificado.setIdCertificado(maxDocumento());
                sesion.save(certificado);
                tx.commit();
                certificado.setNumeroRespuesta(23);
                certificado.setTrazaRespuesta("Insercion correcta");
            } else {
                certificado = new CertificadoCalidadEntity();
                certificado.setNumeroRespuesta(0);
                certificado.setTrazaRespuesta("Error de conexión: " + conexion);
            }

        } catch (Exception e) {
            e.printStackTrace();
            certificado = new CertificadoCalidadEntity();
            certificado.setNumeroRespuesta(0);
            certificado.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
                certificado = new CertificadoCalidadEntity();
                certificado.setNumeroRespuesta(0);
                certificado.setTrazaRespuesta(e.getMessage());
            }
        }
        return certificado;
    }

    /**
     * Metodo para actualizar certificados
     *
     * @param certificado
     * @return
     */
    public CertificadoCalidadEntity actualizarCertificados(CertificadoCalidadEntity certificado) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                sesion.update(certificado);
                tx.commit();
                certificado.setNumeroRespuesta(23);
                certificado.setTrazaRespuesta("Actualización correcta");
            } else {
                certificado.setNumeroRespuesta(0);
                certificado.setTrazaRespuesta("Actualización correcta");
            }
        } catch (Exception e) {
            e.printStackTrace();
            certificado = new CertificadoCalidadEntity();
            certificado.setNumeroRespuesta(0);
            certificado.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
                certificado = new CertificadoCalidadEntity();
                certificado.setNumeroRespuesta(0);
                certificado.setTrazaRespuesta(e.getMessage());
            }
        }
        return certificado;
    }

    /**
     *Metodo para traer todos los certificados de calidad
     * @return
     */
    public ObjetoRetornaEntity listaCertificados() {
        ObjetoRetornaEntity retorno = new ObjetoRetornaEntity();
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                Query sentencia = sesion.createQuery("From CertificadoCalidadEntity where estadoCertificado<>'E'");
                retorno.setRetorna((ArrayList<Object>) sentencia.list());
                retorno.setTrazaRespuesta("Consulta tabla certificado exitosa");
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
                retorno = new ObjetoRetornaEntity();
                retorno.setNumeroRespuesta(0);
                retorno.setTrazaRespuesta(e.getMessage());
            }
        }
        return retorno;
    }

}
