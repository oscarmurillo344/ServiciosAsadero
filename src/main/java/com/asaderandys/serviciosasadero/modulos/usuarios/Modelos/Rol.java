package com.asaderandys.serviciosasadero.modulos.usuarios.Modelos;
import com.asaderandys.serviciosasadero.modulos.usuarios.Dto.RolNombre;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Rol  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RolNombre rolNombre;


    public Rol() {
    }

    public Rol(@NotNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RolNombre getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
}
