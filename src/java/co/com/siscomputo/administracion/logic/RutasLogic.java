/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.RutasEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author LENOVO
 */
public class RutasLogic {

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
     * Método que retorna la lista de rutas posibles en el sistema
     *
     * @return
     */
    public ObjetoRetornaEntity listaRutas() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión: " + validaConexion);
            } else {
                Criteria criteria = sesion.createCriteria(RutasEntity.class);
                if(criteria.list().isEmpty()){
                    System.out.println("Vacio__");
                }else{
                    retorna.setRetorna((ArrayList<Object>) criteria.list());
                }
                retorna.setTrazaRespuesta("Carga de Rutas exitosa");
                retorna.setNumeroRespuesta(0440);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna.setNumeroRespuesta(3);
            retorna.setTrazaRespuesta("ERROR: " + e);
        } finally {
            try {

                sesion.close();
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return retorna;
    }
    /**
     * Método que actualiza una ruta del sistema 
     * @param ruta
     * @return 
     */
    public RutasEntity acualizarRutas(RutasEntity ruta) {
        RutasEntity retorna = new RutasEntity();
        try {
            retorna = ruta;
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión: " + validaConexion);
            } else {
                sesion.update(retorna);
                tx.commit();
                retorna.setTrazaRespuesta("Ruta Actualizada Correctamente");
                retorna.setNumeroRespuesta(00);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna.setTrazaRespuesta(e.getMessage());
            retorna.setNumeroRespuesta(0);
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
     * Método que permite ingresar un aruta de carpeta nueva
     * @param ruta
     * @return 
     */
    public RutasEntity ingresarRuta(RutasEntity ruta){
        RutasEntity retorna=new RutasEntity();
        try {
            retorna=ruta;
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión: "+validaConexion);
            }else{
                retorna.setIdRutas(maxmetodo());
                sesion.save(retorna);
                tx.commit();
                retorna.setTrazaRespuesta("Inserción de Ruta exitosa");
                retorna.setNumeroRespuesta(00);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    
    public int maxmetodo(){
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idRutas) FROM RutasEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }

        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    
    public RutasEntity rutaPorTipo(String tipo){
        RutasEntity rutas=new RutasEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                rutas.setNumeroRespuesta(3);
                rutas.setTrazaRespuesta("Error de Conexión: "+validaConexion);
            } else {
                Criteria criteria=sesion.createCriteria(RutasEntity.class);
                criteria.add(Restrictions.eq("tipoRutas", tipo));
                rutas=(RutasEntity) criteria.uniqueResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
            rutas.setNumeroRespuesta(0);
            rutas.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return rutas;
    }
}
