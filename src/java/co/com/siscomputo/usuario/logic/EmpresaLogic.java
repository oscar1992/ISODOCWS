/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.EmpresaEntity;
import co.com.siscomputo.administracion.persistencia.RolesEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LENOVO
 */
public class EmpresaLogic {
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
     * Método para ingresar un empresa nueva
     * @param empresa
     * @return 
     */
    public EmpresaEntity ingresarEmpresa(EmpresaEntity empresa){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                empresa.setNumeroRespuesta(3);
                empresa.setTrazaRespuesta("Error de Conexión: "+validaConexion);
            } else {
                empresa.setIdEmpresa(maxEmpresa());
                sesion.save(empresa);
                tx.commit();
                sesion.close();
                empresa.setTrazaRespuesta("Inserción de Empresa exitosa");
                empresa.setNumeroRespuesta(32);
            }
        } catch (Exception e) {
            e.printStackTrace();
            empresa= new EmpresaEntity();
            empresa.setNumeroRespuesta(0);
            empresa.setTrazaRespuesta(e.getMessage());
        }
        return empresa;
    }
    /**
     * Método que trae el siguiente ID de la tabla de Empresas
     * @return 
     */
    public int maxEmpresa(){
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idEmpresa) FROM EmpresaEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }

        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    /**
     * Método para actualizar una empresa
     * @param empresa
     * @return 
     */
    public  EmpresaEntity actualizarEmpresa(EmpresaEntity empresa){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                empresa.setNumeroRespuesta(3);
                empresa.setTrazaRespuesta("Error de Conexión: "+validaConexion);
            } else {
                
                sesion.update(empresa);
                tx.commit();
                sesion.close();
                empresa.setTrazaRespuesta("Inserción de Empresa exitosa");
                empresa.setNumeroRespuesta(13);
            }
        } catch (Exception e) {
            e.printStackTrace();
            empresa= new EmpresaEntity();
            empresa.setNumeroRespuesta(0);
            empresa.setTrazaRespuesta(e.getMessage());
        }
        return empresa;
    }
    /**
     * Método que trae una lista de empresas
     * @return 
     */
    public ObjetoRetornaEntity listaEmpresa() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            initOperation();
            Query query = sesion.createQuery("FROM EmpresaEntity ");
            retorna.setRetorna((ArrayList<Object>) query.list());
            retorna.setTrazaRespuesta("Carga de Empresas exitosa");
            retorna.setNumeroRespuesta(34);
            sesion.close();
        } catch (Exception e) {
            retorna.setNumeroRespuesta(3);
            retorna.setTrazaRespuesta("ERROR: " + e);
            e.printStackTrace();
        }
        return retorna;
    }
}
