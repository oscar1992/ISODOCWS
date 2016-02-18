/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.endpoint;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.mejoramientocontinuo.logic.AccionesRapidaLogic;
import co.com.siscomputo.mejoramientocontinuo.logic.AnexoAccionesRapidasLogic;
import co.com.siscomputo.mejoramientocontinuo.logic.AnexoAuditoriaLogic;
import co.com.siscomputo.mejoramientocontinuo.logic.AnexoPlanesAccionLogic;
import co.com.siscomputo.mejoramientocontinuo.logic.AuditoriaLogic;
import co.com.siscomputo.mejoramientocontinuo.logic.PlanesAccionLogic;
import co.com.siscomputo.mejoramientocontinuo.persistencia.AccionesRapidasEntity;
import co.com.siscomputo.mejoramientocontinuo.persistencia.AnexoAuditoriaEntity;
import co.com.siscomputo.mejoramientocontinuo.persistencia.AnexoPlanesAccionEntity;
import co.com.siscomputo.mejoramientocontinuo.persistencia.AnexosAccionesRapidasEntity;
import co.com.siscomputo.mejoramientocontinuo.persistencia.AuditoriaEntity;
import co.com.siscomputo.mejoramientocontinuo.persistencia.PlanesAccionEntity;
import co.com.siscomputo.utilidades.Valida;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Personal
 */
@WebService(serviceName = "MejoramientoContinuo")
public class MejoramientoContinuo {

    /**
     * Metodo para insertar una auditoria
     *
     * @param objAudi
     * @return
     */
    @WebMethod(operationName = "insertarAuditoria")
    public AuditoriaEntity insertarAuditoria(@WebParam(name = "auditoria") AuditoriaEntity objAudi) {
        try {
            Valida valida = new Valida();
            String respuesta = "";
            respuesta = valida.valida(objAudi.getEstadoAuditoria(), "estado");
            if ("OK".equalsIgnoreCase(respuesta)) {
                AuditoriaLogic logica = new AuditoriaLogic();
                objAudi = logica.insertarAuditoria(objAudi);
            } else {
                objAudi.setTrazaRespuesta(respuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objAudi;
    }

    /**
     * Metodo para actualizar una auditoria
     *
     * @param objAudi
     * @return
     */
    @WebMethod(operationName = "actualizarAuditoria")
    public AuditoriaEntity actualizarAuditoria(@WebParam(name = "auditoria") AuditoriaEntity objAudi) {
        try {
            Valida valida = new Valida();
            String respuesta = "";
            respuesta = valida.valida(objAudi.getIdAuditoria(), "Id");
            if ("OK".equalsIgnoreCase(respuesta)) {
                AuditoriaLogic logica = new AuditoriaLogic();
                objAudi = logica.actualizarAuditoria(objAudi);
            } else {
                objAudi.setTrazaRespuesta(respuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objAudi;
    }

    /**
     * Metodo para listar auditorias
     *
     * @return
     */
    @WebMethod(operationName = "listaAuditoria")
    public ObjetoRetornaEntity listarAuditoria() {
        ObjetoRetornaEntity retorno = new ObjetoRetornaEntity();
        try {
            AuditoriaLogic logic = new AuditoriaLogic();
            retorno = logic.listarAuditoria();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    /**
     * Método que permite insertar un AnexoAuditoria nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarAnexoAuditoria")
    public AnexoAuditoriaEntity insertarAnexoAuditoria(@WebParam(name = "objeto") AnexoAuditoriaEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getIdAnexo(), "id"))) {
            AnexoAuditoriaEntity ret = new AnexoAuditoriaEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getIdAnexo(), "id"));
            return ret;
        } else {
            AnexoAuditoriaLogic anexoAuditoriaLogic = new AnexoAuditoriaLogic();
            return anexoAuditoriaLogic.insertarAnexoAuditoria(objeto);
        }
    }

    /**
     * Método que permite actualizar un AnexoAuditoria
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarAnexoAuditoria")
    public AnexoAuditoriaEntity actualizarAnexoAuditoria(@WebParam(name = "objeto") AnexoAuditoriaEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getIdAnexo(), "Id"))) {
            AnexoAuditoriaEntity ret = new AnexoAuditoriaEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getIdAnexo(), "Id"));
            return ret;
        } else {
            AnexoAuditoriaLogic metodoRecuperacionLogic = new AnexoAuditoriaLogic();
            return metodoRecuperacionLogic.actualizarAnexoAuditoria(objeto);
        }
    }

    /**
     * Método que trae una lista de AnexoAuditoria
     *
     * @return
     */
    @WebMethod(operationName = "listaAnexoAuditoria")
    public ObjetoRetornaEntity listaAnexoAuditoria() {
        AnexoAuditoriaLogic anexoAuditoriaLogic = new AnexoAuditoriaLogic();
        return anexoAuditoriaLogic.listaAnexoAuditoria();
    }

    /**
     * Metodo para insertar una Accion rapida
     *
     * @param objAc
     * @return
     */
    @WebMethod(operationName = "insertarAccionRapida")
    public AccionesRapidasEntity insertarAccionR(@WebParam(name = "accionRapida") AccionesRapidasEntity objAc) {
        try {
            Valida valida = new Valida();
            String respuesta = "";
            respuesta = valida.valida(objAc.getEstadoAuditoria(), "estado");
            if ("OK".equalsIgnoreCase(respuesta)) {
                AccionesRapidaLogic logica = new AccionesRapidaLogic();
                objAc = logica.insertarAcciones(objAc);
            } else {
                objAc.setTrazaRespuesta(respuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objAc;
    }

    /**
     * Metodo para actualizar una accion rapida
     *
     * @param objAudi
     * @return
     */
    @WebMethod(operationName = "actualizarAccionRapida")
    public AccionesRapidasEntity actualizarAccionRapida(@WebParam(name = "auditoria") AccionesRapidasEntity objAudi) {
        try {
            Valida valida = new Valida();
            String respuesta = "";
            respuesta = valida.valida(objAudi.getIdAccionesR(), "Id");
            if ("OK".equalsIgnoreCase(respuesta)) {
                AccionesRapidaLogic logica = new AccionesRapidaLogic();
                objAudi = logica.actualizarAcciones(objAudi);
            } else {
                objAudi.setTrazaRespuesta(respuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objAudi;
    }

    /**
     * Metodo para listar auditorias
     *
     * @return
     */
    @WebMethod(operationName = "listaAccionRapida")
    public ObjetoRetornaEntity listarAccionRapida() {
        ObjetoRetornaEntity retorno = new ObjetoRetornaEntity();
        try {
            AccionesRapidaLogic logic = new AccionesRapidaLogic();
            retorno = logic.listarAcciones();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    /**
     * Metodo para insertar una anexo Accion rapida
     *
     * @param objAc
     * @return
     */
    @WebMethod(operationName = "insertarAnexoAccionRapida")
    public AnexosAccionesRapidasEntity insertarAnexoAccionRapida(@WebParam(name = "AnexoAccionRapida") AnexosAccionesRapidasEntity objAc) {
        try {
            Valida valida = new Valida();
            String respuesta = "";
            respuesta = valida.valida(objAc.getRutaAnexoAccion(), "ruta");
            if ("OK".equalsIgnoreCase(respuesta)) {
                AnexoAccionesRapidasLogic logica = new AnexoAccionesRapidasLogic();
                objAc = logica.insertarAnexoAcciones(objAc);
            } else {
                objAc.setTrazaRespuesta(respuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objAc;
    }

    /**
     * Metodo para actualizar una accion rapida
     *
     * @param objAudi
     * @return
     */
    @WebMethod(operationName = "actualizarAccionRapida")
    public AnexosAccionesRapidasEntity actualizarAccionRapida(@WebParam(name = "AnexoAccionRapida") AnexosAccionesRapidasEntity objAudi) {
        try {
            Valida valida = new Valida();
            String respuesta = "";
            respuesta = valida.valida(objAudi.getIdAnexoAcc(), "Id");
            if ("OK".equalsIgnoreCase(respuesta)) {
                AnexoAccionesRapidasLogic logica = new AnexoAccionesRapidasLogic();
                objAudi = logica.actualizarAcciones(objAudi);
            } else {
                objAudi.setTrazaRespuesta(respuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objAudi;
    }

    /**
     * Metodo para listar anexos de acciones rapidas
     *
     * @return
     */
    @WebMethod(operationName = "listaAnexoAccionRapida")
    public ObjetoRetornaEntity listaAnexoAccionRapida() {
        ObjetoRetornaEntity retorno = new ObjetoRetornaEntity();
        try {
            AccionesRapidaLogic logic = new AccionesRapidaLogic();
            retorno = logic.listarAcciones();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    /**
     * Metodo para insertar un plan de accion
     *
     * @param obj
     * @return
     */
    @WebMethod(operationName = "insertarPlanAccion")
    public PlanesAccionEntity insertarPlanAccion(@WebParam(name = "PlanAccion") PlanesAccionEntity obj) {
        try {
            Valida valida = new Valida();
            String respuesta = "";
            respuesta = valida.valida(obj.getEstadoPlanAccion(), "estado");
            if ("OK".equalsIgnoreCase(respuesta)) {
                PlanesAccionLogic logica = new PlanesAccionLogic();
                obj = logica.actualizarPlanesAccion(obj);
            } else {
                obj.setTrazaRespuesta(respuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * Metodo para actualizar un plan de accion
     *
     * @param obj
     * @return
     */
    @WebMethod(operationName = "actualizarPlanesAccion")
    public PlanesAccionEntity actualizarPlanesAccion(@WebParam(name = "PlanAccion") PlanesAccionEntity obj) {
        try {
            Valida valida = new Valida();
            String respuesta = "";
            respuesta = valida.valida(obj.getIdPlanesAccion(), "Id");
            if ("OK".equalsIgnoreCase(respuesta)) {
                PlanesAccionLogic logica = new PlanesAccionLogic();
                obj = logica.actualizarPlanesAccion(obj);
            } else {
                obj.setTrazaRespuesta(respuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * Metodo para listar anexos de acciones rapidas
     *
     * @return
     */
    @WebMethod(operationName = "listaPlanAccion")
    public ObjetoRetornaEntity listaPlanAccion() {
        ObjetoRetornaEntity retorno = new ObjetoRetornaEntity();
        try {
            PlanesAccionLogic logic = new PlanesAccionLogic();
            retorno = logic.listarPlanesAccion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }
    /**
     * Metodo para insertar un  anexo de plan de accion
     *
     * @param obj
     * @return
     */
    @WebMethod(operationName = "insertarAnexoPlanAccion")
    public AnexoPlanesAccionEntity insertarAnexoPlanAccion(@WebParam(name = "AnexoPlanAccion") AnexoPlanesAccionEntity obj) {
        try {
            Valida valida = new Valida();
            String respuesta = "";
            respuesta = valida.valida(obj.getUbicacionAnexo(), "ruta");
            if ("OK".equalsIgnoreCase(respuesta)) {
                AnexoPlanesAccionLogic logica = new AnexoPlanesAccionLogic();
                obj = logica.insertarAnexoAcciones(obj);
            } else {
                obj.setTrazaRespuesta(respuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * Metodo para actualizar un anexo de plan de accion
     *
     * @param obj
     * @return
     */
    @WebMethod(operationName = "actualizarPlanesAccion")
    public AnexoPlanesAccionEntity actualizarPlanesAccion(@WebParam(name = "AnexoPlanAccion") AnexoPlanesAccionEntity obj) {
        try {
            Valida valida = new Valida();
            String respuesta = "";
            respuesta = valida.valida(obj.getIdPlanesAccion(), "Id");
            if ("OK".equalsIgnoreCase(respuesta)) {
                AnexoPlanesAccionLogic logica = new AnexoPlanesAccionLogic();
                obj = logica.actualizarAnexoAcciones(obj);
            } else {
                obj.setTrazaRespuesta(respuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * Metodo para listar anexos de plan de accion
     * @return
     */
    @WebMethod(operationName = "listaAnexoPlanAccion")
    public ObjetoRetornaEntity listaAnexoPlanAccion() {
        ObjetoRetornaEntity retorno = new ObjetoRetornaEntity();
        try {
            AnexoPlanesAccionLogic logic = new AnexoPlanesAccionLogic();
            retorno = logic.listarAnexoAcciones();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

}
