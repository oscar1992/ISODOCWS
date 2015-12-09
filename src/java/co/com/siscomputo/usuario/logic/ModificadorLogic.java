
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.persistencia.ModificadorEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class ModificadorLogic {
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
     * Método que inserta un Aprobador Modificador nuevo
     * @param objetoModificador
     * @return 
     */    public ModificadorEntity insertarModificador(ModificadorEntity objetoModificador){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {                
                objetoModificador.setNumeroRespuesta(3);
                objetoModificador.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoModificador.setIdModificador(maxMetodo());
                sesion.save(objetoModificador);
                tx.commit();

                objetoModificador.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoModificador.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoModificador = new ModificadorEntity();
            objetoModificador.setNumeroRespuesta(0);
            objetoModificador.setTrazaRespuesta(e.getMessage());
        }
        return objetoModificador;
    }

     /**
     * Método que trae el siguiente ID de la tabla ADM_TMODI
     * @return 
     */    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idModificador) FROM ModificadorEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    
     /**
     * Método que actualiza un Aprobador Modificador
     * @param objetoModificador
     * @return 
     */ public ModificadorEntity actualizarModificador(ModificadorEntity objetoModificador){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoModificador.setNumeroRespuesta(3);
                objetoModificador.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                System.out.println("JJ");
                sesion.update(objetoModificador);
                tx.commit();
                sesion.close();
                objetoModificador.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoModificador.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoModificador = new ModificadorEntity();
            objetoModificador.setNumeroRespuesta(0);
            objetoModificador.setTrazaRespuesta(e.getMessage());
        }
        return objetoModificador;
    }
     /**
     * Método Método para consultar la lista de Aprobador Modificador
     */public ObjetoRetornaEntity listaModificador(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM ModificadorEntity d WHERE d.estadoModificador<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla Modificador exitosa");
                retorna.setNumeroRespuesta(22);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna=new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }
        return retorna ;
    }
/**
     * Método que devuelve un Aprobador Modificador filtrado por su Id
     * @param modificador
     * @return 
     */
    public ModificadorEntity ModificadorPorID(int modificador) {
        ModificadorEntity depto = new ModificadorEntity();
        String validaConexion = initOperation();
        try {
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                depto.setNumeroRespuesta(3);
                depto.setTrazaRespuesta("Error de Conexión: " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM ModificadorEntity p WHERE p.idModificador=:idS");
                query.setParameter("idS", modificador);
                depto=(ModificadorEntity) query.uniqueResult();
                depto.setTrazaRespuesta("Consulta de modificador exitosa");
                depto.setNumeroRespuesta(35);
            }
        } catch (Exception e) {
            e.printStackTrace();
            depto = new ModificadorEntity();
            depto.setNumeroRespuesta(0);
            depto.setTrazaRespuesta(e.getMessage());
        }
        return depto;
    }
}