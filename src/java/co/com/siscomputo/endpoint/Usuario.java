/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.endpoint;

import co.com.siscomputo.administracion.entites.ListaAsignaPermisosModulo;
import co.com.siscomputo.administracion.entites.MenuModuloEntity;
import co.com.siscomputo.administracion.entites.ObjetoLogin;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.AreaEntity;
import co.com.siscomputo.administracion.persistencia.CiudadEntity;
import co.com.siscomputo.administracion.persistencia.DepartamentoEntity;
import co.com.siscomputo.administracion.persistencia.EmpresaEntity;
import co.com.siscomputo.administracion.persistencia.FestivosEntity;
import co.com.siscomputo.administracion.persistencia.PaisEntity;
import co.com.siscomputo.administracion.persistencia.RolPermisoEntity;
import co.com.siscomputo.administracion.persistencia.RolesEntity;
import co.com.siscomputo.administracion.persistencia.SedeEmpresaEntity;
import co.com.siscomputo.administracion.persistencia.SedeEntity;
import co.com.siscomputo.administracion.persistencia.TiposDocumentalesEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioRolEntity;
import co.com.siscomputo.administracion.logic.AreaLogic;
import co.com.siscomputo.administracion.logic.CiudadLogic;
import co.com.siscomputo.administracion.logic.DepartamentoLogic;
import co.com.siscomputo.administracion.logic.EmpresaLogic;
import co.com.siscomputo.administracion.logic.FestivosLogic;
import co.com.siscomputo.administracion.logic.MenuLogic;
import co.com.siscomputo.administracion.logic.PaisLogic;
import co.com.siscomputo.administracion.logic.PermisosLogic;
import co.com.siscomputo.administracion.logic.RolesLogic;
import co.com.siscomputo.administracion.logic.SedeEmpresaLogic;
import co.com.siscomputo.administracion.logic.SedeLogic;
import co.com.siscomputo.administracion.logic.TiposDocumentalesLogic;
import co.com.siscomputo.administracion.logic.UsuarioLogic;
import co.com.siscomputo.administracion.logic.UsuarioRolLogic;
import co.com.siscomputo.administracion.persistencia.UsuarioEntity;
import co.com.siscomputo.utilidades.Valida;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author LENOVO
 */
@WebService(serviceName = "Usuario")
public class Usuario {

    /**
     * Web service operation método de autenticación de usuario
     *
     * @param nombre
     * @param pass
     * @return
     */
    @WebMethod(operationName = "login")
    public ObjetoLogin login(@WebParam(name = "nombre") @XmlElement(required = true) String nombre, @WebParam(name = "pass") String pass) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(nombre, "nombre"))) {
            ObjetoLogin ret = new ObjetoLogin();
            ret.setAcceso(false);
            ret.setTrazaRespuesta(valida.valida(nombre, "nombre"));
            return ret;
        }
        if (!"Ok".equalsIgnoreCase(valida.valida(pass, "contraseña"))) {
            ObjetoLogin ret = new ObjetoLogin();
            ret.setAcceso(false);
            ret.setTrazaRespuesta(valida.valida(pass, "contraseña"));
            return ret;
        } else {
            System.out.println("Inten");
            UsuarioLogic usuarioLogica = new UsuarioLogic();
            return usuarioLogica.login(nombre, pass);
        }

    }

    /**
     * Web service operation Método de la lista de módulos filtrados por Usuario
     * @return
     */
    @WebMethod(operationName = "listaModulos")
    public ObjetoRetornaEntity listaModulos(@WebParam(name = "id_usuario") int idUsuario) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idUsuario, "idUsuario"))) {
            ObjetoRetornaEntity ret = new ObjetoRetornaEntity();
            ret.setTrazaRespuesta(valida.valida(idUsuario, "idUsuario"));
            return ret;
        } else {
            UsuarioLogic usuarioLogica = new UsuarioLogic();
            return usuarioLogica.modulos(idUsuario);
        }

    }

    /**
     * Web service operation Método de la lista de permisos filtrados por
     * Usuario
     *
     * @param id_usuario
     * @return
     */
    @WebMethod(operationName = "listaPermisos")
    public ObjetoRetornaEntity listaPermisos(@WebParam(name = "id_usuario") int idUsuario) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idUsuario, "idUsuario"))) {
            ObjetoRetornaEntity ret = new ObjetoRetornaEntity();
            ret.setTrazaRespuesta(valida.valida(idUsuario, "idUsuario"));
            return ret;
        } else {
            UsuarioLogic usuarioLogica = new UsuarioLogic();
            return usuarioLogica.permisos(idUsuario);
        }
    }

    /**
     * Web service operation Método de carga de lista de usuarios
     *
     * @return
     */
    @WebMethod(operationName = "listaUsuarios")
    public ObjetoRetornaEntity listaUsuarios() {
        UsuarioLogic usuarioLogic = new UsuarioLogic();
        try {
            return usuarioLogic.listaUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    /**
     * Web service operation Método de carga de lista de usuarios
     *
     * @return
     */
    @WebMethod(operationName = "listaUsuariosPorAccion")
    public ObjetoRetornaEntity listaUsuariosPorAccion(@WebParam(name = "idAccion")int idAccion){
        UsuarioLogic usuarioLogic = new UsuarioLogic();
        try {
            return usuarioLogic.listaUsuariosPorAccion(idAccion);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Web service operation Método que permite actualizar un usario
     *
     * @param usu
     * @return
     */
    @WebMethod(operationName = "actualizarUsuario")
    public UsuarioEntity actualizarUsuario(@WebParam(name = "Usuario") UsuarioEntity usu) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(usu.getIdUsuario(), "idUsuario"))) {
            UsuarioEntity ret = new UsuarioEntity();
            ret.setTrazaRespuesta(valida.valida(usu.getIdUsuario(), "idUsuario"));
            return ret;
        } else {
            UsuarioLogic usuarioLogic = new UsuarioLogic();
            return usuarioLogic.actualizarUsuario(usu);
        }
    }

    /**
     * Método que permite ingresar un usuario nuevo
     *
     * @param usu
     * @return
     */
    @WebMethod(operationName = "ingresarUsuario")
    public UsuarioEntity ingresarUsuario(@WebParam(name = "Usuario") UsuarioEntity usu) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(usu.getNombre(), "idUsuario"))) {
            UsuarioEntity ret = new UsuarioEntity();
            ret.setTrazaRespuesta(valida.valida(usu.getNombre(), "idUsuario"));
            return ret;
        } else {
            UsuarioLogic usuarioLogic = new UsuarioLogic();
            return usuarioLogic.ingresarUsuario(usu);
        }
    }
    /**
     * Método que trae los usuarios de un grupo de usuarios
     * @param idGrupo
     * @return 
     */
    @WebMethod(operationName = "usuariosPorGrupo")
    public ObjetoRetornaEntity usuariosPorGrupo(@WebParam(name = "idGrupo")Integer idGrupo){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idGrupo, "idUsuario"))) {
            ObjetoRetornaEntity ret = new ObjetoRetornaEntity();
            ret.setTrazaRespuesta(valida.valida(idGrupo, "idUsuario"));
            return ret;
        } else {
            UsuarioLogic usuarioLogic = new UsuarioLogic();
            return usuarioLogic.listaUsuariosPorGrupo(idGrupo);
        }
    }
    
    /**
     * Web service operation Método que carga una lista de permisos filtrados
     * por Usuario
     *
     * @param idUsuario
     * @return
     */
    @WebMethod(operationName = "listaPermisosFiltrados")
    public ObjetoRetornaEntity listaPermisosFiltrados(@WebParam(name = "idUsuario") int idUsuario) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idUsuario, "idUsuario"))) {
            ObjetoRetornaEntity ret = new ObjetoRetornaEntity();
            ret.setTrazaRespuesta(valida.valida(idUsuario, "idUsuario"));
            return ret;
        } else {
            UsuarioLogic usuarioLogic = new UsuarioLogic();
            return usuarioLogic.permisosFiltrados(idUsuario);
        }
    }

    /**
     * Método que trae las lista para crear los menus
     *
     * @param idUsuario
     * @return
     */
    @WebMethod(operationName = "menuDatosporUsuario")
    public ArrayList<MenuModuloEntity> menuDatosporUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idUsuario, "idUsuario"))) {
            return null;
        } else {
            MenuLogic menuLogic = new MenuLogic();
            return menuLogic.datosMenu(idUsuario);
        }
    }

    /**
     * Método que trae un único usuario filtrado por su ID
     *
     * @param idUsuario
     * @return
     */
    @WebMethod(operationName = "usuarioPorId")
    public UsuarioEntity usuarioPorId(@WebParam(name = "idUsuario") int idUsuario) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idUsuario, "idUsuario"))) {
            UsuarioEntity ret = new UsuarioEntity();
            ret.setTrazaRespuesta(valida.valida(idUsuario, "idUsuario"));
            return ret;
        } else {
            UsuarioLogic usuarioLogic = new UsuarioLogic();
            return usuarioLogic.usuarioPorId(idUsuario);
        }
    }

    /**
     * Método que trae la lista de roles disponibles
     *
     * @return
     */
    @WebMethod(operationName = "listaRoles")
    public ObjetoRetornaEntity listaRoles() {
        RolesLogic rolesLogic = new RolesLogic();
        return rolesLogic.listaRoles();
    }

    /**
     * Método que ingresa un Rol nuevo
     *
     * @param rol
     * @return
     */
    @WebMethod(operationName = "ingresarRol")
    public RolesEntity ingresarRol(@WebParam(name = "rol") RolesEntity rol) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(rol.getNombre_rol(), "Rol"))) {
            RolesEntity ret = new RolesEntity();
            ret.setTrazaRespuesta(valida.valida(rol.getNombre_rol(), "Rol"));
            return ret;
        } else {
            RolesLogic rolesLogic = new RolesLogic();
            return rolesLogic.ingresarRol(rol);
        }
    }

    /**
     * Metodo que permite actualizar el Rol
     *
     * @param rol
     * @return
     */
    @WebMethod(operationName = "actualizarRol")
    public RolesEntity actualizarRol(@WebParam(name = "rol") RolesEntity rol) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(rol.getId_rol(), "Rol"))) {
            RolesEntity ret = new RolesEntity();
            ret.setTrazaRespuesta(valida.valida(rol.getId_rol(), "Rol"));
            return ret;
        } else {
            RolesLogic rolesLogic = new RolesLogic();
            return rolesLogic.actualizarRol(rol);
        }
    }
    /**
     * Metodo que busca un rol por su ID     
     * @param idRol
     * @return
     */
    @WebMethod(operationName = "rolPorId")
    public RolesEntity rolPorId(@WebParam(name = "rol") int idRol) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idRol, "Rol"))) {
            RolesEntity ret = new RolesEntity();
            ret.setTrazaRespuesta(valida.valida(idRol, "Rol"));
            return ret;
        } else {
            RolesLogic rolesLogic = new RolesLogic();
            return rolesLogic.rolPorId(idRol);
        }
    }

    /**
     * Método que trae una lista de objetos para construir el árbol de los
     * permisos
     *
     * @return
     */
    @WebMethod(operationName = "listaPermisosDisponibles")
    public ArrayList<ListaAsignaPermisosModulo> listaPermisosDisponibles() {
        PermisosLogic permisosLogic = new PermisosLogic();
        return permisosLogic.listaRolPermiso();
    }

    /**
     * Método que permite insertar un área nueva
     *
     * @param area
     * @return
     */
    @WebMethod(operationName = "insertarArea")
    public AreaEntity ingresarArea(@WebParam(name = "area") AreaEntity area) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(area.getNombreArea(), "Area"))) {
            AreaEntity ret = new AreaEntity();
            ret.setTrazaRespuesta(valida.valida(area.getNombreArea(), "Ares"));
            return ret;
        } else {
            AreaLogic areaLogic = new AreaLogic();
            return areaLogic.ingresarArea(area);
        }
    }

    /**
     * Método que permite actualizar un área
     *
     * @param area
     * @return
     */
    @WebMethod(operationName = "actualizarArea")
    public AreaEntity actualizarArea(@WebParam(name = "area") AreaEntity area) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(area.getIdArea(), "Ares"))) {
            AreaEntity ret = new AreaEntity();
            ret.setTrazaRespuesta(valida.valida(area.getIdArea(), "Area"));
            return ret;
        } else {
            AreaLogic areaLogic = new AreaLogic();
            return areaLogic.actualizarArea(area);
        }
    }

    /**
     * Método que consulta la lista de Areas
     *
     * @return
     */
    @WebMethod(operationName = "listaAreas")
    public ObjetoRetornaEntity listaAreas() {
        AreaLogic areaLogic = new AreaLogic();
        return areaLogic.listaArea();
    }

    /**
     * Método que permite insertar un pais nuevo
     *
     * @param pais
     * @return
     */
    @WebMethod(operationName = "insertarPais")
    public PaisEntity ingresarPais(@WebParam(name = "pais") PaisEntity pais) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(pais.getNombrePais(), "Pais"))) {
            PaisEntity ret = new PaisEntity();
            ret.setTrazaRespuesta(valida.valida(pais.getNombrePais(), "Pais"));
            return ret;
        } else {
            PaisLogic paisLogic = new PaisLogic();
            return paisLogic.ingresaPais(pais);
        }
    }

    /**
     * Método que permite actualizar un pais
     *
     * @param pais
     * @return
     */
    @WebMethod(operationName = "actualizarPais")
    public PaisEntity actualizarPais(@WebParam(name = "pais") PaisEntity pais) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(pais.getIdPais(), "Pais"))) {
            PaisEntity ret = new PaisEntity();
            ret.setTrazaRespuesta(valida.valida(pais.getIdPais(), "Pais"));
            return ret;
        } else {
            PaisLogic paisLogic = new PaisLogic();
            return paisLogic.actualizaPais(pais);
        }
    }

    /**
     * Método que consulta la lista de paises
     *
     * @return
     */
    @WebMethod(operationName = "listaPais")
    public ObjetoRetornaEntity listaPais() {
        PaisLogic paisLogic = new PaisLogic();
        return paisLogic.listaPais();
    }

    @WebMethod(operationName = "paisPorId")
    public PaisEntity paisPorId(@WebParam(name = "idPais") int idPais) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idPais, "Pais"))) {
            PaisEntity ret = new PaisEntity();
            ret.setTrazaRespuesta(valida.valida(idPais, "Pais"));
            return ret;
        } else {
            PaisLogic paisLogic = new PaisLogic();
            return paisLogic.paisPorID(idPais);
        }
    }

    /**
     * Método que permite insertar un departamento nuevo
     *
     * @param departamento
     * @return
     */
    @WebMethod(operationName = "insertarDepartameno")
    public DepartamentoEntity ingresarDepartamento(@WebParam(name = "departamento") DepartamentoEntity departamento) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(departamento.getNombreDepartamento(), "Depto"))) {
            DepartamentoEntity ret = new DepartamentoEntity();
            ret.setTrazaRespuesta(valida.valida(departamento.getNombreDepartamento(), "Depto"));
            return ret;
        } else {
            DepartamentoLogic departamentoLogic = new DepartamentoLogic();
            return departamentoLogic.ingresaDepartamento(departamento);
        }
    }

    /**
     * Método que permite actualizar un departamento nuevo
     *
     * @param departamento
     * @return
     */
    @WebMethod(operationName = "actualizarDepartameno")
    public DepartamentoEntity actualizarDepartamento(@WebParam(name = "departamento") DepartamentoEntity departamento) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(departamento.getIdDepartamento(), "Depto"))) {
            DepartamentoEntity ret = new DepartamentoEntity();
            ret.setTrazaRespuesta(valida.valida(departamento.getIdDepartamento(), "Depto"));
            return ret;
        } else {
            DepartamentoLogic departamentoLogic = new DepartamentoLogic();
            return departamentoLogic.actualizaDepartamento(departamento);
        }
    }

    /**
     * Método que consulta la lista de departamentos
     *
     * @return
     */
    @WebMethod(operationName = "listaDepartameno")
    public ObjetoRetornaEntity listaDepartamento() {
        DepartamentoLogic departamentoLogic = new DepartamentoLogic();
        return departamentoLogic.listaDepartamento();
    }

    /**
     * Método que trae un departamento por ID
     *
     * @param idDepto
     * @return
     */
    @WebMethod(operationName = "deptoPorId")
    public DepartamentoEntity departamentoPorId(@WebParam(name = "idDepto") int idDepto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idDepto, "Depto"))) {
            DepartamentoEntity ret = new DepartamentoEntity();
            ret.setTrazaRespuesta(valida.valida(idDepto, "Depto"));
            return ret;
        } else {
            DepartamentoLogic departamentoLogic = new DepartamentoLogic();
            return departamentoLogic.deptoPorID(idDepto);
        }
    }

    /**
     * Método que permite insertar una ciudad nueva
     *
     * @param ciudad
     * @return
     */
    @WebMethod(operationName = "insertarCiudad")

    public CiudadEntity ingresarCiudad(@WebParam(name = "ciudad") CiudadEntity ciudad) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(ciudad.getNombreCiudad(), "Ciudad"))) {
            CiudadEntity ret = new CiudadEntity();
            ret.setTrazaRespuesta(valida.valida(ciudad.getNombreCiudad(), "Ciudad"));
            return ret;
        } else {
            CiudadLogic ciudadLogic = new CiudadLogic();
            return ciudadLogic.ingresaCiudad(ciudad);
        }
    }

    /**
     * Método que permite actualizar una ciudad
     *
     * @param ciudad
     * @return
     */
    @WebMethod(operationName = "actualizarCiudad")
    public CiudadEntity actualizarCiudad(@WebParam(name = "ciudad") CiudadEntity ciudad) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(ciudad.getIdCiudad(), "Ciudad"))) {
            CiudadEntity ret = new CiudadEntity();
            ret.setTrazaRespuesta(valida.valida(ciudad.getIdCiudad(), "Ciudad"));
            return ret;
        } else {
            CiudadLogic ciudadLogic = new CiudadLogic();
            return ciudadLogic.actualizarCiudad(ciudad);
        }
    }

    /**
     * Método que trae la lista de las ciudades disponibles
     *
     * @return
     */
    @WebMethod(operationName = "listaCiudades")
    public ObjetoRetornaEntity listaCiudad() {
        CiudadLogic ciudadLogic = new CiudadLogic();
        return ciudadLogic.listaCiudad();
    }

    /**
     * Método que permite insertar una sede nueva
     *
     * @param sede
     * @return
     */
    @WebMethod(operationName = "ingresaSede")
    public SedeEntity ingresarSede(@WebParam(name = "sede") SedeEntity sede) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(sede.getNombreSede(), "Sede"))) {
            SedeEntity ret = new SedeEntity();
            ret.setTrazaRespuesta(valida.valida(sede.getNombreSede(), "Sede"));
            return ret;
        } else {
            SedeLogic sedeLogic = new SedeLogic();
            return sedeLogic.ingresarSede(sede);
        }
    }

    /**
     * Método que permite actualizar una sede
     *
     * @param sede
     * @return
     */
    @WebMethod(operationName = "actualizaSede")
    public SedeEntity actualizarSede(@WebParam(name = "sede") SedeEntity sede) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(sede.getIdSede(), "Sede"))) {
            SedeEntity ret = new SedeEntity();
            ret.setTrazaRespuesta(valida.valida(sede.getIdSede(), "Sede"));
            return ret;
        } else {
            SedeLogic sedeLogic = new SedeLogic();
            return sedeLogic.actualizarSede(sede);
        }
    }

    /**
     * Método que qonculta una lista de sedes
     *
     * @return
     */
    @WebMethod(operationName = "listaSede")
    public ObjetoRetornaEntity listaSede() {
        SedeLogic sedeLogic = new SedeLogic();
        return sedeLogic.listaSedes();
    }

    @WebMethod(operationName = "sedePorId")
    public SedeEntity sedePorId(@WebParam(name = "sede") int idSede) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idSede, "Sede"))) {
            SedeEntity ret = new SedeEntity();
            ret.setTrazaRespuesta(valida.valida(idSede, "Sede"));
            return ret;
        } else {
            SedeLogic sedeLogic = new SedeLogic();
            return sedeLogic.sedePorID(idSede);
        }
    }

    /**
     * Método que inserta una empresa nueva
     *
     * @param empresa
     * @return
     */
    @WebMethod(operationName = "ingresarEmpresa")
    public EmpresaEntity ingresarEmpresa(@WebParam(name = "empresa") EmpresaEntity empresa) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(empresa.getNombreEmpresa(), "empresa"))) {
            EmpresaEntity ret = new EmpresaEntity();
            ret.setTrazaRespuesta(valida.valida(empresa.getNombreEmpresa(), "empresa"));
            return ret;
        } else {
            EmpresaLogic empresaLogic = new EmpresaLogic();
            return empresaLogic.ingresarEmpresa(empresa);
        }
    }

    /**
     * Método que actualizauna empresa
     *
     * @param empresa
     * @return
     */
    @WebMethod(operationName = "actualizarEmpresa")
    public EmpresaEntity actualizarEmpresa(@WebParam(name = "empresa") EmpresaEntity empresa) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(empresa.getIdEmpresa(), "empresa"))) {
            EmpresaEntity ret = new EmpresaEntity();
            ret.setTrazaRespuesta(valida.valida(empresa.getIdEmpresa(), "empresa"));
            return ret;
        } else {
            EmpresaLogic empresaLogic = new EmpresaLogic();
            return empresaLogic.actualizarEmpresa(empresa);
        }
    }

    /**
     * Método que consulta la lista de empresas disponibles
     *
     * @return
     */
    @WebMethod(operationName = "listaEmpresa")
    public ObjetoRetornaEntity listaEmpresa() {
        EmpresaLogic empresaLogic = new EmpresaLogic();
        return empresaLogic.listaEmpresa();
    }

    /**
     * Método que permite ingrsar un registro nuevo a la tabla Sede_Empresa
     *
     * @param sedeE
     * @return
     */
    @WebMethod(operationName = "ingresaSedeEmpresa")
    public SedeEmpresaEntity ingresarSedeEmpresa(@WebParam(name = "sedee") SedeEmpresaEntity sedeE) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(sedeE.getIdSede(), "sedeE"))) {
            SedeEmpresaEntity ret = new SedeEmpresaEntity();
            ret.setTrazaRespuesta(valida.valida(sedeE.getIdSede(), "sedeE"));
            return ret;
        } else {
            SedeEmpresaLogic sedeEmpresaLogic = new SedeEmpresaLogic();
            return sedeEmpresaLogic.ingresarSedeEmpresa(sedeE);
        }
    }

    /**
     * Método que permite actualizar un registro de la tabla Sede_Empresa
     *
     * @param sedeE
     * @return
     */
    @WebMethod(operationName = "actualizaSedeEmpresa")
    public SedeEmpresaEntity actualizaSedeEmpresa(@WebParam(name = "sedee") SedeEmpresaEntity sedeE) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(sedeE.getIdSedeEmpresa(), "sedeE"))) {
            SedeEmpresaEntity ret = new SedeEmpresaEntity();
            ret.setTrazaRespuesta(valida.valida(sedeE.getIdSedeEmpresa(), "sedeE"));
            return ret;
        } else {
            SedeEmpresaLogic sedeEmpresaLogic = new SedeEmpresaLogic();
            return sedeEmpresaLogic.actualizarsedeEmpresa(sedeE);
        }
    }

    /**
     * Método que consulta una lista de relaciones Sede_Empresa
     *
     * @return
     */
    @WebMethod(operationName = "listaSedeEmpresa")
    public ObjetoRetornaEntity listaSedeEmpresa() {
        SedeEmpresaLogic sedeEmpresaLogic = new SedeEmpresaLogic();
        return sedeEmpresaLogic.listaRoles();
    }

    /**
     * Método qye permite ingresar un festivo nuevo
     *
     * @param festivo
     * @return
     */
    @WebMethod(operationName = "ingresaFestivo")
    public FestivosEntity ingresaFestivo(@WebParam(name = "festivo") FestivosEntity festivo) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(festivo.getNombreFestivo(), "festivo"))) {
            FestivosEntity ret = new FestivosEntity();
            ret.setTrazaRespuesta(valida.valida(festivo.getNombreFestivo(), "festivo"));
            return ret;
        } else {
            FestivosLogic festivosLogic = new FestivosLogic();
            return festivosLogic.ingresaFestivo(festivo);

        }
    }

    /**
     * Método qye permite actualizar un festivo
     *
     * @param festivo
     * @return
     */
    @WebMethod(operationName = "actualizarFestivo")
    public FestivosEntity actualizarFestivo(@WebParam(name = "festivo") FestivosEntity festivo) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(festivo.getIdFEstivo(), "festivo"))) {
            FestivosEntity ret = new FestivosEntity();
            ret.setTrazaRespuesta(valida.valida(festivo.getIdFEstivo(), "festivo"));
            return ret;
        } else {
            FestivosLogic festivosLogic = new FestivosLogic();
            return festivosLogic.actualizaFestivo(festivo);
        }
    }

    /**
     * Método que consulta los festivos disponibles
     *
     * @return
     */
    @WebMethod(operationName = "listafestivos")
    public ObjetoRetornaEntity listaFestivos() {
        FestivosLogic festivosLogic = new FestivosLogic();
        return festivosLogic.listaFestivos();
    }

    /**
     * Método que trae un único festivo filtrado por el ID
     *
     * @param idFestivo
     * @return
     */
    public FestivosEntity festtivoPorId(@WebParam(name = "idFestivo") int idFestivo) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idFestivo, "festivo"))) {
            FestivosEntity ret = new FestivosEntity();
            ret.setTrazaRespuesta(valida.valida(idFestivo, "festivo"));
            return ret;
        } else {
            FestivosLogic festivosLogic = new FestivosLogic();
            return festivosLogic.festivoPorId(idFestivo);
        }
    }

    /**
     * Método que trae una lista de las relaciones entre roles y permisos
     *
     * @param idRol
     * @return
     */
    @WebMethod(operationName = "listaRolPermiso")
    public ObjetoRetornaEntity listaRolPermiso(@WebParam(name = "idRol") int idRol) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idRol, "lista Rol-Permiso"))) {
            ObjetoRetornaEntity ret = new ObjetoRetornaEntity();
            ret.setTrazaRespuesta(valida.valida(idRol, "lista Rol-Permiso"));
            return ret;
        } else {
            PermisosLogic permisosLogic = new PermisosLogic();
            return permisosLogic.listaRolPermisoPorRol(idRol);
        }
    }

    /**
     * Método que permite insertar una relación entre Rol-Permiso
     *
     * @param rolPermiso
     * @return
     */
    @WebMethod(operationName = "ingresarRolPermiso")
    public RolPermisoEntity insertarRolPermiso(@WebParam(name = "RolPermiso") RolPermisoEntity rolPermiso) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(rolPermiso.getId_rol(), "lista Rol-Permiso"))) {
            RolPermisoEntity ret = new RolPermisoEntity();
            ret.setTrazaRespuesta(valida.valida(rolPermiso, "lista Rol-Permiso"));
            return ret;
        } else {
            PermisosLogic permisosLogic = new PermisosLogic();
            return permisosLogic.insertarRolPermiso(rolPermiso);
        }
    }

    /**
     * Método que permite inserttar una relación UsuarioRol nueva
     *
     * @param usuRol
     * @return
     */
    @WebMethod(operationName = "insertarUsuarioRol")
    public ObjetoRetornaEntity insertarUsuarioRol(@WebParam(name = "usuarioRol") ArrayList<UsuarioRolEntity> usuRol) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(usuRol, "lista Rol-Permiso"))) {
            System.out.println("ERROR");
            ObjetoRetornaEntity ret = new ObjetoRetornaEntity();
            ret.setTrazaRespuesta(valida.valida(usuRol, "lista Rol-Permiso"));
            return ret;
        } else {
            UsuarioRolLogic usuarioRolLogic = new UsuarioRolLogic();
            return usuarioRolLogic.insertarUsuarioRol(usuRol);
        }
    }

    /**
     * Método que permite actualizar una relación Usuario Rol
     *
     * @param usuRol
     * @return
     */
    @WebMethod(operationName = "actualizarUsuarioRol")
    public UsuarioRolEntity actualizarUsuarioRol(@WebParam(name = "usuarioRol") UsuarioRolEntity usuRol) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(usuRol.getUsuario(), "lista Rol-Permiso"))) {
            UsuarioRolEntity ret = new UsuarioRolEntity();
            ret.setTrazaRespuesta(valida.valida(usuRol, "lista Rol-Permiso"));
            return ret;
        } else {
            UsuarioRolLogic usuarioRolLogic = new UsuarioRolLogic();
            return usuarioRolLogic.actualizarUsuarioRol(usuRol);
        }
    }

    /**
     * Método que permite retorna una lista de registros Usuario-Rol
     *
     * @param idUsuario
     * @return
     */
    @WebMethod(operationName = "listaUsuarioRol")
    public ObjetoRetornaEntity listaUsuarioRol(@WebParam(name = "idUsuario") int idUsuario) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idUsuario, "lista Rol-Permiso"))) {
            ObjetoRetornaEntity ret = new ObjetoRetornaEntity();
            ret.setTrazaRespuesta(valida.valida(idUsuario, "lista Rol-Permiso"));
            return ret;
        } else {
            UsuarioRolLogic ususRolLogic = new UsuarioRolLogic();
            return ususRolLogic.listaRolPermisoPorRol(idUsuario);
        }
    }

    /**
     * Método que limpia los roles de un usuario antes de insertar los nuevos
     *
     * @param idUsuario
     */
    @WebMethod(operationName = "limpiaUsuarioRoles")
    public void limpiaUsuarioRoles(@WebParam(name = "idUsuario") int idUsuario, @WebParam(name = "idArea") int idArea) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idUsuario, "lista Rol-Permiso"))) {
            ObjetoRetornaEntity ret = new ObjetoRetornaEntity();
            ret.setTrazaRespuesta(valida.valida(idUsuario, "lista Rol-Permiso"));

        } else {
            UsuarioRolLogic ususRolLogic = new UsuarioRolLogic();
            ususRolLogic.limpia(idUsuario, idArea);
        }
    }

    /**
     * Método que consulta una lista de registros de usuarios por área
     *
     * @param idArea
     * @param idUsuario
     * @return
     */
    @WebMethod(operationName = "listaUsuarioRolporAreaUsuario")
    public ObjetoRetornaEntity listaUsuarioRolporAreaUsuario(@WebParam(name = "idArea") int idArea, @WebParam(name = "idUsuario") int idUsuario) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idUsuario, "lista Rol-Permiso"))) {
            ObjetoRetornaEntity ret = new ObjetoRetornaEntity();
            ret.setTrazaRespuesta(valida.valida(idUsuario, "lista Rol-Permiso"));
            return ret;
        } else {
            UsuarioRolLogic ususRolLogic = new UsuarioRolLogic();
            return ususRolLogic.listaRolPermisoPorArea(idArea, idUsuario);
        }
    }

    /**
     * Método que permite isngresar un tipo documental nuevo
     *
     * @param tiposd
     * @return
     */
    @WebMethod(operationName = "insertarTiposDocumetales")
    public TiposDocumentalesEntity ingresarTipoDocumental(@WebParam(name = "tiposd") TiposDocumentalesEntity tiposd) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(tiposd, "Tipo Documental"))) {
            System.out.println("ERROR");
            TiposDocumentalesEntity ret = new TiposDocumentalesEntity();
            ret.setTrazaRespuesta(valida.valida(tiposd, "Tipo Documental"));
            return ret;
        } else {
            TiposDocumentalesLogic tiposDocumentalesLogic = new TiposDocumentalesLogic();
            return tiposDocumentalesLogic.ingresaTipoDocuemtal(tiposd);
        }
    }

    /**
     * Método que permite actualizar una¿ tipo documental
     *
     * @param tiposd
     * @return
     */
    @WebMethod(operationName = "actualizarTipoDocumental")
    public TiposDocumentalesEntity actualizarTipoDocumental(@WebParam(name = "tiposd") TiposDocumentalesEntity tiposd) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(tiposd, "Tipo Documental"))) {
            System.out.println("ERROR");
            TiposDocumentalesEntity ret = new TiposDocumentalesEntity();
            ret.setTrazaRespuesta(valida.valida(tiposd, "Tipo Documental"));
            return ret;
        } else {
            TiposDocumentalesLogic tiposDocumentalesLogic = new TiposDocumentalesLogic();
            return tiposDocumentalesLogic.actualizaTipoDocuemtal(tiposd);
        }
    }

    /**
     * Método que devuelve una lista de registros de tipos documentales
     *
     * @return
     */
    public ObjetoRetornaEntity listaTipoDocuemtal() {
        TiposDocumentalesLogic tiposDocumentalesLogic = new TiposDocumentalesLogic();
        return tiposDocumentalesLogic.listaTipoDcouemntal();

    }
    /**
     * Método que permite isngresar un tipo documental nuevo
     *
     * @param tiposd
     * @return
     */
    @WebMethod(operationName = "tiposDocumetalesPorID")
    public TiposDocumentalesEntity tipoDocumentalPorID(@WebParam(name = "idipos") int idTipos) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idTipos, "Tipo Documental"))) {
            System.out.println("ERROR");
            TiposDocumentalesEntity ret = new TiposDocumentalesEntity();
            ret.setTrazaRespuesta(valida.valida(idTipos, "Tipo Documental"));
            return ret;
        } else {
            TiposDocumentalesLogic tiposDocumentalesLogic = new TiposDocumentalesLogic();
            return tiposDocumentalesLogic.tipoDocumentalPorID(idTipos);
        }
    }
}
