package com.asaderandys.serviciosasadero.modulos.usuarios.Controlador;

import com.asaderandys.serviciosasadero.modulos.usuarios.Dto.*;

import com.asaderandys.serviciosasadero.modulos.usuarios.Modelos.Rol;
import com.asaderandys.serviciosasadero.modulos.usuarios.Modelos.Usuario;
import com.asaderandys.serviciosasadero.modulos.usuarios.Servicios.RolService;
import com.asaderandys.serviciosasadero.modulos.usuarios.Servicios.UsuarioService;
import com.asaderandys.serviciosasadero.utilitarios.MensajesUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = {"*"})
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/lista")
    public ResponseEntity<?> ListarUsuario(){
        try{
            List<ListaUsuario> list = usuarioService.ListarUsuario();
            return new ResponseEntity(list, HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/ingresar")
    public ResponseEntity<?> Ingresar(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        try{
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Usuario_01), HttpStatus.BAD_REQUEST);
        if (usuarioService.ExistePorNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Usuario_02), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(0L,nuevoUsuario.getNombre(),nuevoUsuario.getApellido() ,nuevoUsuario.getNombreUsuario(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));

                usuarioService.NuevoUsuario(usuario,nuevoUsuario.getRoles());
        return new ResponseEntity(new Mensaje(MensajesUtils.Exitoso_01), HttpStatus.CREATED);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> Eliminar(@PathVariable("id") Long id) {
        try{
        if (!usuarioService.ExisteUsuario(id))
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Usuario_03), HttpStatus.NOT_FOUND);
        usuarioService.EliminarUsuario(id);
        return new ResponseEntity(new Mensaje(MensajesUtils.Exitoso_01), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/rol/eliminar/{codigo}")
    public ResponseEntity<?> RolEliminar(@PathVariable("codigo") int codigo) {
        try{
        if (!rolService.ExisteRol(codigo))
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Usuario_04), HttpStatus.NOT_FOUND);
        rolService.EliminarRol(codigo);
        return new ResponseEntity(new Mensaje("Rol eliminado"), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/rol/ingresar")
    public ResponseEntity<?> RolIngresar(){
        try{
             Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
             Rol rolUser = new Rol(RolNombre.ROLE_USER);
             if(rolService.ObtenerRol(rolAdmin.getRolNombre()) != null &&
                     rolService.ObtenerRol(rolAdmin.getRolNombre()) != null) {
                rolService.NuevoRol(rolAdmin);
                rolService.NuevoRol(rolUser);
            }
            return new ResponseEntity(new Mensaje(MensajesUtils.Exitoso_01),HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
