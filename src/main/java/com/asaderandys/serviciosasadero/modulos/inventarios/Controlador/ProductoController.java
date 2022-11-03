package com.asaderandys.serviciosasadero.modulos.inventarios.Controlador;

import com.asaderandys.serviciosasadero.modulos.inventarios.Dto.ProductoDto;
import com.asaderandys.serviciosasadero.modulos.inventarios.Modelos.Producto;
import com.asaderandys.serviciosasadero.modulos.inventarios.Servicios.ProductoService;
import com.asaderandys.serviciosasadero.modulos.usuarios.Dto.Mensaje;
import com.asaderandys.serviciosasadero.utilitarios.MensajesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = {"https://asaderoweb.herokuapp.com","http://192.168.100.115:4200"})
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> list(){
        try{
        List<Producto> list = productoService.ListarTodo();
        return new ResponseEntity(list, HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Producto> ObtenerPorId(@PathVariable("id") Long id){
        try{
        if(!productoService.ExistePorId(id))
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventarioo_04), HttpStatus.NOT_FOUND);
        Producto producto = productoService.ObtenerUno(id).get();
        return new ResponseEntity(producto, HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ingresar")
    public ResponseEntity<?> Ingresar(@RequestBody ProductoDto productoDto){
        try{
        if(productoDto.getNombre().isBlank())
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventario_05), HttpStatus.BAD_REQUEST);
        if(productoDto.getPrecio().longValue() < 0 )
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventario_03), HttpStatus.BAD_REQUEST);
        if(productoService.ExistePorNombre(productoDto.getNombre()))
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventario_02), HttpStatus.BAD_REQUEST);

       Producto producto= productoService.NuevoProducto(productoDto);
        return new ResponseEntity(new Mensaje(MensajesUtils.Exitoso_01,producto), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar")
    public ResponseEntity<?> Actualizar(@RequestBody ProductoDto productoDto){
        try{
        if(!productoService.ExistePorId(productoDto.getId()))
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventarioo_04), HttpStatus.NOT_FOUND);
        if(productoService.ExistePorNombre(productoDto.getNombre()) && productoService.ObtenerPorNombre(productoDto.getNombre()).get().getId() != productoDto.getId())
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventario_02), HttpStatus.BAD_REQUEST);
        if(productoDto.getNombre().isBlank())
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventario_05), HttpStatus.BAD_REQUEST);
        if(productoDto.getPrecio().longValue() < 0 )
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventario_03), HttpStatus.BAD_REQUEST);

        productoService.NuevoProducto(productoDto);
        return new ResponseEntity(new Mensaje(MensajesUtils.Exitoso_01), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> Eliminar(@PathVariable("id")Long id){
        try{
        if(!productoService.ExistePorId(id))
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventarioo_04), HttpStatus.NOT_FOUND);
        productoService.EliminarProducto(id);
        return new ResponseEntity(new Mensaje(MensajesUtils.Exitoso_01), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
