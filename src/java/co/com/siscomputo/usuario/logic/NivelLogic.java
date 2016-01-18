package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.persistencia.NivelEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author LENOVO
 */
public class NivelLogic {

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
     * Método que inserta un Nivel de Proceso nuevo
     *
     * @param objetoNivel
     * @return
     */
    public NivelEntity insertarNivel(NivelEntity objetoNivel) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoNivel.setNumeroRespuesta(3);
                objetoNivel.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoNivel.setIdNivel(maxMetodo());
                sesion.save(objetoNivel);
                tx.commit();

                objetoNivel.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoNivel.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoNivel = new NivelEntity();
            objetoNivel.setNumeroRespuesta(0);
            objetoNivel.setTrazaRespuesta(e.getMessage());
        }
        return objetoNivel;
    }

    /**
     * Método que trae el siguiente ID de la tabla ADM_TNIVE
     *
     * @return
     */
    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idNivel) FROM NivelEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método que actualiza un Nivel de Proceso
     *
     * @param objetoNivel
     * @return
     */
    public NivelEntity actualizarNivel(NivelEntity objetoNivel) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoNivel.setNumeroRespuesta(3);
                objetoNivel.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                System.out.println("JJ");
                sesion.update(objetoNivel);
                tx.commit();
                sesion.close();
                objetoNivel.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoNivel.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoNivel = new NivelEntity();
            objetoNivel.setNumeroRespuesta(0);
            objetoNivel.setTrazaRespuesta(e.getMessage());
        }
        return objetoNivel;
    }

    /**
     * Método Método para consultar la lista de Nivel de Proceso
     */
    public ObjetoRetornaEntity listaNivel() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria = sesion.createCriteria(NivelEntity.class);
                criteria.add(Restrictions.ne("estadoNivel", "E"));
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Consulta tabla Nivel exitosa");
                retorna.setNumeroRespuesta(22);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna = new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }
        return retorna;
    }
    /**
     * Método que trae un nivel por su ID
     * @param idNivel
     * @return 
     */
    public NivelEntity nivelPorId(int idNivel){
        NivelEntity retorna = new NivelEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria = sesion.createCriteria(NivelEntity.class);
                criteria.add(Restrictions.ne("idNivel", idNivel));
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Consulta tabla Nivel exitosa");
                retorna.setNumeroRespuesta(22);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna = new NivelEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }
        return retorna;
    }
}
