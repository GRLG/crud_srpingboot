/*** Esta clase define la entidad cliente de la base de datos sb_clientes.
 * @author: Glenda López
 * @version: 18/03/2022 */
package com.springboot.clienteapp.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "El nombre es requeridos")
    private String nombres;
    @NotEmpty(message = "El apellido es requerido")
    private String apellidos;
    @NotEmpty(message = "El teléfono es requerido")
    @Pattern(regexp ="[0-9]{2}-[0-9]{10}" ,message = "El formato del télefono no es valido." +
            "Formato requerido: 57-1239998887")
    private String telefono;
    @NotEmpty(message = "El email es requerido")
    @Email(message = "El formato del email es incorrecto")
    private String email;
    @ManyToOne
    @JoinColumn(name="ciudades_id")
    private Ciudad ciudad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", ciudad=" + ciudad +
                '}';
    }
}
