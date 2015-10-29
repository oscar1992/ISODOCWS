/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.DepartamentoEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LENOVO
 */
public class DepartamentoLogic {
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
     * Método para insertar un departamento
     * @param departamento
     * @return 
     */
    public DepartamentoEntity ingresaDepartamento(DepartamentoEntity departamento){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                departamento.setNumeroRespuesta(3);
                departamento.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                departamento.setIdDepartamento(maxDepartamento());
                sesion.save(departamento);
                tx.commit();
                sesion.close();
                departamento.setTrazaRespuesta("Inserción de departamento Exitosa");
                departamento.setNumeroRespuesta(23);
            }
        } catch (Exception e) {
            e.printStackTrace();
            departamento=new DepartamentoEntity();
            departamento.setNumeroRespuesta(0);
        }
        return departamento;
    }
    
    public int maxDepartamento() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idDepartamento) FROM DepartamentoEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    /**
     * Método para actualizar un departamento
     * @param departamento
     * @return 
     */
    public DepartamentoEntity actualizaDepartamento(DepartamentoEntity departamento){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                departamento.setNumeroRespuesta(3);
                departamento.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                
                sesion.update(departamento);
                tx.commit();
                sesion.close();
                departamento.setTrazaRespuesta("Actualización de departamento Exitosa");
                departamento.setNumeroRespuesta(24);
            }
        } catch (Exception e) {
            e.printStackTrace();
            departamento=new DepartamentoEntity();
            departamento.setNumeroRespuesta(0);
        }
        return departamento;
    }
    /**
     * Método para consultar la lista de departamentos
     * @return 
     */
    public ObjetoRetornaEntity listaDepartamento(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM DepartamentoEntity");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla Departamentos exitosa");
                retorna.setNumeroRespuesta(25);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna=new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }
        return retorna;
    }
}
