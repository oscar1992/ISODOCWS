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
import co.com.siscomputo.administracion.persistencia.PaisEntity;
import co.com.siscomputo.administracion.persistencia.RolesEntity;
import co.com.siscomputo.administracion.persistencia.SedeEmpresaEntity;
import co.com.siscomputo.administracion.persistencia.SedeEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioEntity;
import co.com.siscomputo.usuario.logic.AreaLogic;
import co.com.siscomputo.usuario.logic.CiudadLogic;
import co.com.siscomputo.usuario.logic.DepartamentoLogic;
import co.com.siscomputo.usuario.logic.EmpresaLogic;
import co.com.siscomputo.usuario.logic.MenuLogic;
import co.com.siscomputo.usuario.logic.PaisLogic;
import co.com.siscomputo.usuario.logic.PermisosLogic;
import co.com.siscomputo.usuario.logic.RolesLogic;
import co.com.siscomputo.usuario.logic.SedeEmpresaLogic;
import co.com.siscomputo.usuario.logic.SedeLogic;
import co.com.siscomputo.usuario.logic.UsuarioLogic;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author LENOVO
 */
@WebService(serviceName = "Usuario")
public class Usuario {

    /**
     * Web service operation 
     * método de autenticación de usuario
     * @param nombre
     * @param pass
     * @return 
     */
    @WebMethod(operationName = "login")
    
    public ObjetoLogin login(@WebParam(name = "nombre") String nombre, @WebParam(name = "pass") String pass) {
        UsuarioLogic usuarioLogica = new UsuarioLogic();        
        return usuarioLogica.login(nombre, pass);
    }

    /**
     * Web service operation
     * Método de la lista de módulos filtrados por Usuario
     * @param id_usuario
     * @return 
     */
    @WebMethod(operationName = "listaModulos")
    public ObjetoRetornaEntity listaModulos(@WebParam(name = "id_usuario") int id_usuario) {
        UsuarioLogic usuarioLogica = new UsuarioLogic();
        return usuarioLogica.modulos(id_usuario);
    }

    /**
     * Web service operation
     * Método de la lista de permisos filtrados por Usuario
     * @param id_usuario
     * @return 
     */
    @WebMethod(operationName = "listaPermisos")
    public ObjetoRetornaEntity listaPermisos(@WebParam(name = "id_usuario") int id_usuario) {
        UsuarioLogic usuarioLogica= new UsuarioLogic();
        return usuarioLogica.permisos(id_usuario);
    }

    /**
     * Web service operation
     * Método de carga de lista de usuarios
     * @return 
     */
    @WebMethod(operationName = "listaUsuarios")
    public ObjetoRetornaEntity listaUsuarios() {
        UsuarioLogic usuarioLogic=new UsuarioLogic();
        try {
            return usuarioLogic.listaUsuarios(); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    /**
    * Web service operation
    * Método que permite actualizar un usario
    * @param usu
    * @return 
    */
    @WebMethod(operationName = "actualizarUsuario")
    public UsuarioEntity actualizarUsuario(@WebParam(name = "Usuario") UsuarioEntity usu) {
        UsuarioLogic usuarioLogic=new UsuarioLogic();
        return usuarioLogic.actualizarUsuario(usu);
    }
    
    /**
     * Método que permite ingresar un usuario nuevo
     * @param usu
     * @return 
     */
    @WebMethod(operationName = "ingresarUsuario")
    public UsuarioEntity ingresarUsuario(@WebParam(name = "Usuario") UsuarioEntity usu){
        UsuarioLogic usuarioLogic=new UsuarioLogic();
        return usuarioLogic.ingresarUsuario(usu);
    }

    /**
     * Web service operation
     * Método que carga una lista de permisos filtrados por Usuario
     * @param idUsuario
     * @return 
     */
    @WebMethod(operationName = "listaPermisosFiltrados")
    public ObjetoRetornaEntity listaPermisosFiltrados(@WebParam(name = "idUsuario") int idUsuario) {
        UsuarioLogic usuarioLogic=new UsuarioLogic();
        return usuarioLogic.permisosFiltrados(idUsuario);
    }
    
    /**
     * Método que trae las lista para crear los menus
     * @param idUsuario
     * @return 
     */
    @WebMethod(operationName = "menuDatosporUsuario")
    public ArrayList<MenuModuloEntity> menuDatosporUsuario(@WebParam(name = "idUsuario") int idUsuario){
        MenuLogic menuLogic=new MenuLogic();
        return menuLogic.datosMenu(idUsuario);
    }
    /**
     * Método que trae un único usuario filtrado por su ID
     * @param idUsuario
     * @return 
     */
    @WebMethod(operationName = "usuarioPorId")
    public UsuarioEntity usuarioPorId(@WebParam(name = "idUsuario")int idUsuario){
        UsuarioLogic usuarioLogic=new UsuarioLogic();
        return usuarioLogic.usuarioPorId(idUsuario);
    }
    
    
    /**
     * Método que trae la lista de roles disponibles
     * @return 
     */
    @WebMethod(operationName = "listaRoles")
    public ObjetoRetornaEntity listaRoles(){
        RolesLogic rolesLogic=new RolesLogic();
        return rolesLogic.listaRoles();
    }
       
    /**
     * Método que ingresa un Rol nuevo
     * @param rol
     * @return 
     */
    @WebMethod(operationName = "ingresarRol")
    public RolesEntity ingresarRol(@WebParam(name = "rol") RolesEntity rol){
        RolesLogic rolesLogic=new RolesLogic();
        return rolesLogic.ingresarRol(rol);
    }
    /**
     * Metodo que permite actualizar el Rol
     * @param rol
     * @return 
     */
    @WebMethod(operationName = "actualizarRol")
    public RolesEntity actualizarRol(@WebParam(name = "rol") RolesEntity rol){
        RolesLogic rolesLogic=new RolesLogic();
        return rolesLogic.actualizarRol(rol);
    }
    /**
     * Método que trae una lista de objetos para construir el árbol de los permisos
     * @return 
     */
    @WebMethod(operationName = "listaPermisosDisponibles")
    public ArrayList<ListaAsignaPermisosModulo> listaPermisosDisponibles(){
        PermisosLogic permisosLogic=new PermisosLogic();
        return permisosLogic.listaRolPermiso();
    }
    /**
     * Método que permite insertar un área nueva
     * @param area
     * @return 
     */
    @WebMethod(operationName = "insertarArea")
    public AreaEntity ingresarArea(@WebParam(name = "area") AreaEntity area){
        AreaLogic areaLogic=new AreaLogic();
        return areaLogic.ingresarArea(area);
    }
    /**
     * Método que permite actualizar un área 
     * @param area
     * @return 
     */
    @WebMethod(operationName = "actualizarArea")
    public AreaEntity actualizarArea(@WebParam(name = "area") AreaEntity area){
        AreaLogic areaLogic=new AreaLogic();
        return areaLogic.actualizarArea(area);
    }
    /**
     * Método que consulta la lista de Areas
     * @return 
     */
    @WebMethod(operationName = "listaAreas")
    public ObjetoRetornaEntity listaAreas(){
        AreaLogic areaLogic=new AreaLogic();
        return areaLogic.listaArea();
    }
    /**
     * Método que permite insertar un pais nuevo
     * @param pais
     * @return 
     */
    @WebMethod(operationName = "insertarPais")
    public PaisEntity ingresarPais(@WebParam(name = "pais")PaisEntity pais){
        PaisLogic paisLogic=new PaisLogic();
        return paisLogic.ingresaPais(pais);
    }
    /**
     * Método que permite actualizar un pais 
     * @param pais
     * @return 
     */
    @WebMethod(operationName = "actualizarPais")
    public PaisEntity actualizarPais(@WebParam(name = "pais") PaisEntity pais){
        PaisLogic paisLogic=new PaisLogic();
        return paisLogic.actualizaPais(pais);
    }
    /**
     * Método que consulta la lista de paises
     * @return 
     */
    @WebMethod(operationName = "listaPais")
    public ObjetoRetornaEntity listaPais(){
        PaisLogic paisLogic=new PaisLogic();
        return paisLogic.listaPais();
    }
    /**
     * Método que permite insertar un departamento nuevo
     * @param departamento
     * @return 
     */
    @WebMethod(operationName = "insertarDepartameno")
    public DepartamentoEntity ingresarDepartamento(@WebParam(name = "departamento")DepartamentoEntity departamento){
        DepartamentoLogic departamentoLogic=new DepartamentoLogic();
        return departamentoLogic.ingresaDepartamento(departamento);
    }
    /**
     * Método que permite actualizar un departamento nuevo
     * @param departamento 
     * @return 
     */
    @WebMethod(operationName = "actualizarDepartameno")
    public DepartamentoEntity actualizarDepartamento(@WebParam(name = "departamento")DepartamentoEntity departamento){
        DepartamentoLogic departamentoLogic=new DepartamentoLogic();
        return departamentoLogic.actualizaDepartamento(departamento);
    }
    /**
     * Método que consulta la lista de departamentos
     * @return 
     */
    @WebMethod(operationName = "listaDepartameno")
    public ObjetoRetornaEntity listaDepartamento(){
        DepartamentoLogic departamentoLogic=new DepartamentoLogic();
        return departamentoLogic.listaDepartamento();
    }
    /**
     * Método que permite insertar una ciudad nueva
     * @param ciudad
     * @return 
     */
    @WebMethod(operationName = "insertarCiudad")
    public CiudadEntity ingresarCiudad(@WebParam(name = "ciudad")CiudadEntity ciudad){
        CiudadLogic ciudadLogic=new CiudadLogic();
        return ciudadLogic.ingresaCiudad(ciudad);
    }
    /**
     * Método que permite actualizar una ciudad
     * @param ciudad
     * @return 
     */
    @WebMethod(operationName = "actualizarCiudad")
    public CiudadEntity actualizarCiudad(@WebParam(name = "ciudad")CiudadEntity ciudad){
        CiudadLogic ciudadLogic=new CiudadLogic();
        return ciudadLogic.actualizarCiudad(ciudad);
    }
    /**
     * Método que trae la lista de las ciudades disponibles
     * @return 
     */
    @WebMethod(operationName = "listaCiudades")
    public ObjetoRetornaEntity listaCiudad(){
        CiudadLogic ciudadLogic=new CiudadLogic();
        return ciudadLogic.listaCiudad();
    }
    /**
     * Método que permite insertar una sede nueva
     * @param sede
     * @return 
     */
    @WebMethod(operationName = "ingresaSede")
    public SedeEntity ingresarSede(@WebParam(name = "sede") SedeEntity sede){
        SedeLogic sedeLogic=new SedeLogic();
        return sedeLogic.ingresarSede(sede);
    }
    /**
     * Método que permite actualizar una sede
     * @param sede
     * @return 
     */
    @WebMethod(operationName = "actualizaSede")
    public SedeEntity actualizarSede(@WebParam(name = "sede") SedeEntity sede){
        SedeLogic sedeLogic=new SedeLogic();
        return sedeLogic.actualizarSede(sede);
    }
    /**
     * Método que qonculta una lista de sedes
     * @return 
     */
    @WebMethod(operationName = "listaSede")
    public ObjetoRetornaEntity listaSede(){
        SedeLogic sedeLogic=new SedeLogic();
        return sedeLogic.listaSedes();
    }
    
    @WebMethod(operationName = "sedePorId")
    public SedeEntity sedePorId(@WebParam(name = "sede") int idSede){
        SedeLogic sedeLogic=new SedeLogic();
        return sedeLogic.sedePorID(idSede);
    }
    /**
     * Método que inserta una empresa nueva
     * @param empresa
     * @return 
     */
    @WebMethod(operationName = "ingresarEmpresa")
    public EmpresaEntity ingresarEmpresa(@WebParam(name = "empresa") EmpresaEntity empresa){
        EmpresaLogic empresaLogic=new EmpresaLogic();
        return empresaLogic.ingresarEmpresa(empresa);
    }
    /**
     * Método que actualizauna empresa 
     * @param empresa
     * @return 
     */
    @WebMethod(operationName = "actualizarEmpresa")
    public EmpresaEntity actualizarEmpresa(@WebParam(name = "empresa") EmpresaEntity empresa){
        EmpresaLogic empresaLogic=new EmpresaLogic();
        return empresaLogic.actualizarEmpresa(empresa);
    }
    /**
     * Método que consulta la lista de empresas disponibles
     * @return 
     */
    @WebMethod(operationName = "listaEmpresa")
    public ObjetoRetornaEntity listaEmpresa(){
        EmpresaLogic empresaLogic=new EmpresaLogic();
        return empresaLogic.listaEmpresa();
    }
    /**
     * Método que permite ingrsar un registro nuevo a la tabla Sede_Empresa
     * @param sedeE
     * @return 
     */
    @WebMethod(operationName = "ingresaSedeEmpresa")
    public SedeEmpresaEntity ingresarSedeEmpresa(@WebParam(name = "sedee") SedeEmpresaEntity sedeE){
        SedeEmpresaLogic sedeEmpresaLogic=new SedeEmpresaLogic();
        return sedeEmpresaLogic.ingresarSedeEmpresa(sedeE);
    }
    /**
     * Método que permite actualizar un registro de la tabla Sede_Empresa
     * @param sedeE
     * @return 
     */
    @WebMethod(operationName = "actualizaSedeEmpresa")
    public SedeEmpresaEntity actualizaSedeEmpresa(@WebParam(name = "sedee") SedeEmpresaEntity sedeE){
        SedeEmpresaLogic sedeEmpresaLogic=new SedeEmpresaLogic();
        return sedeEmpresaLogic.actualizarsedeEmpresa(sedeE);
    }
    /**
     * Método que consulta una lista de relaciones Sede_Empresa
     * @return 
     */
    @WebMethod(operationName = "listaSedeEmpresa")
    public ObjetoRetornaEntity listaSedeEmpresa(){
        SedeEmpresaLogic sedeEmpresaLogic=new SedeEmpresaLogic();
        return sedeEmpresaLogic.listaRoles();
    }
}
