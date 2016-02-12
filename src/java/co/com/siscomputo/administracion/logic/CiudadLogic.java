/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.CiudadEntity;
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
public class CiudadLogic {

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
     * Método que permite ingresar una ciudad nueva
     * @param ciudad
     * @return 
     */
    public CiudadEntity ingresaCiudad(CiudadEntity ciudad) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                ciudad.setNumeroRespuesta(3);
                ciudad.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                ciudad.setIdCiudad(maxCiudad());
                sesion.save(ciudad);
                tx.commit();
                
                ciudad.setTrazaRespuesta("Inserción de Ciudad Exitosa");
                ciudad.setNumeroRespuesta(26);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ciudad = new CiudadEntity();
            ciudad.setNumeroRespuesta(0);
            ciudad.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return ciudad;
    }
    /**
     * Método que trae el siguiente id de la tabla ciudades
     * @return 
     */
    public int maxCiudad() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idCiudad) FROM CiudadEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    /**
     * Método que permite actualizar una sciudad
     * @return 
     */
    public CiudadEntity actualizarCiudad(CiudadEntity ciudad){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                ciudad.setNumeroRespuesta(3);
                ciudad.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                sesion.update(ciudad);
                tx.commit();
                
                ciudad.setTrazaRespuesta("Actualización de Ciudad Exitosa");
                ciudad.setNumeroRespuesta(27);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ciudad = new CiudadEntity();
            ciudad.setNumeroRespuesta(0);
            ciudad.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return ciudad;
    }
    /**
     * Método que consulta la lista de ciudades disponibles en el sistema     
     * @return 
     */
    public ObjetoRetornaEntity listaCiudad(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM CiudadEntity C WHERE C.estadoCiudad<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla Ciudades exitosa");
                retorna.setNumeroRespuesta(28);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna=new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return retorna;
    }
    /**
     * Método que trae una lista de ciudades por País
     * @param idPais
     * @return 
     */
    public ObjetoRetornaEntity ciudadesPorPais(int idPais){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM CiudadEntity C, DepartamentoEntity D, PaisEntity P WHERE C.estadoCiudad<>'E' AND D.idDepartamento=C.ciudadDepartamento.idDepartamento AND  P.idPais=D.idPais.idPais AND P.idPais=:idP");
                query.setParameter("idP", idPais);
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla Ciudades exitosa");
                retorna.setNumeroRespuesta(28);                
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna=new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();                  
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return retorna;
    }
}

