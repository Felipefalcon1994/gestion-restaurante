package com.example.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioDTO {

    @NotBlank(message = "El nombre del usuario es obligatorio")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ingresar un formato de correo electronico valido")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
    
    @NotNull(message = "Debe asignar un ID de rol al usuario")
    private Long idRol;
}
