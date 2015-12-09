package co.com.siscomputo.administracion.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "ADM_TGRUP")
public class GrupoUsuariosEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "GRUP_GRUP")
    private int idGrupoUsuarios;
    @Column(name = "GRUP_NOMB")
    private String nombreGrupoUsuarios;
    @Column(name = "GRUP_CREA")
    private int creaGrupoUsuarios;
    @Column(name = "GRUP_UPDA")
    private int actualizadorGrupoUsuarios;
    @Column(name = "GRUP_ESTA")
    private String estadoGrupoUsuarios;

    public int getIdGrupoUsuarios() {
        return idGrupoUsuarios;
    }

    public void setIdGrupoUsuarios(int idGrupoUsuarios) {
        this.idGrupoUsuarios = idGrupoUsuarios;
    }

    public String getNombreGrupoUsuarios() {
        return nombreGrupoUsuarios;
    }

    public void setNombreGrupoUsuarios(String nombreGrupoUsuarios) {
        this.nombreGrupoUsuarios = nombreGrupoUsuarios;
    }

    public int getCreaGrupoUsuarios() {
        return creaGrupoUsuarios;
    }

    public void setCreaGrupoUsuarios(int creaGrupoUsuarios) {
        this.creaGrupoUsuarios = creaGrupoUsuarios;
    }

    public int getActualizadorGrupoUsuarios() {
        return actualizadorGrupoUsuarios;
    }

    public void setActualizadorGrupoUsuarios(int actualizadorGrupoUsuarios) {
        this.actualizadorGrupoUsuarios = actualizadorGrupoUsuarios;
    }

    public String getEstadoGrupoUsuarios() {
        return estadoGrupoUsuarios;
    }

    public void setEstadoGrupoUsuarios(String estadoGrupoUsuarios) {
        this.estadoGrupoUsuarios = estadoGrupoUsuarios;
    }

}
