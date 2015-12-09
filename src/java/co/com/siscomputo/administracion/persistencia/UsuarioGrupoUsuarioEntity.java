package co.com.siscomputo.administracion.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "ADM_TUSGR")
public class UsuarioGrupoUsuarioEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "USGR_USGR")
    private int idUsuarioGrupoUsuario;
    @JoinColumn(name = "USGR_USUA")
    @ManyToOne
    private UsuarioEntity usuario;
    @JoinColumn(name = "USGR_GRUP")
    @ManyToOne
    private GrupoUsuariosEntity grupoUsuario;

    public int getIdUsuarioGrupoUsuario() {
        return idUsuarioGrupoUsuario;
    }

    public void setIdUsuarioGrupoUsuario(int idUsuarioGrupoUsuario) {
        this.idUsuarioGrupoUsuario = idUsuarioGrupoUsuario;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public GrupoUsuariosEntity getGrupoUsuario() {
        return grupoUsuario;
    }

    public void setGrupoUsuario(GrupoUsuariosEntity grupoUsuario) {
        this.grupoUsuario = grupoUsuario;
    }

}
