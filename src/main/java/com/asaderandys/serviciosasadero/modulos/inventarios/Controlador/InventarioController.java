package com.asaderandys.serviciosasadero.modulos.inventarios.Controlador;

import com.asaderandys.serviciosasadero.modulos.inventarios.Dto.actualizarPollo;
import com.asaderandys.serviciosasadero.modulos.inventarios.Dto.inventarioDto;
import com.asaderandys.serviciosasadero.modulos.inventarios.Modelos.Inventario;
import com.asaderandys.serviciosasadero.modulos.inventarios.Modelos.DiaPollo;
import com.asaderandys.serviciosasadero.modulos.inventarios.Servicios.DiaPolloService;
import com.asaderandys.serviciosasadero.modulos.inventarios.Servicios.InventarioService;
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
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired

    InventarioService inventarioservice;
    @Autowired
    DiaPolloService diaservice;


    @GetMapping("/listar")
    public ResponseEntity<List<Inventario>> listar(){
        try{
        List<Inventario> list = inventarioservice.listar();
        return new ResponseEntity(list, HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ingresar")
    public ResponseEntity<?> Ingresar(@RequestBody inventarioDto invenDto){
        try{
        if(inventarioservice.ExistePorId(invenDto.getId()))
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventarioo_07), HttpStatus.BAD_REQUEST);
        if(invenDto.getCantidad()<0)
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventario_01), HttpStatus.BAD_REQUEST);
        if(invenDto.getProducto().getNombre().isBlank())
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventario_05), HttpStatus.BAD_REQUEST);
        if(invenDto.getProducto().getPrecio().longValue() < 0 )
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventario_03), HttpStatus.BAD_REQUEST);

        inventarioservice.IngresarAndEditarInventario(invenDto);
        return new ResponseEntity(new Mensaje(MensajesUtils.Exitoso_01), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actualizar")
    public ResponseEntity<?> Editar(@RequestBody inventarioDto invenDto){
        try{
            if(invenDto.getCantidad()<0)
                return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventario_01), HttpStatus.BAD_REQUEST);
            if(invenDto.getProducto().getNombre().isBlank())
                return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventario_05), HttpStatus.BAD_REQUEST);
            if(invenDto.getProducto().getPrecio().longValue() < 0 )
                return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventario_03), HttpStatus.BAD_REQUEST);

            inventarioservice.IngresarAndEditarInventario(invenDto);
            return new ResponseEntity(new Mensaje(MensajesUtils.Exitoso_01), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/mercaderia/actualizar/{id}")
    public ResponseEntity<?> MercaderiaActualizar(@PathVariable("id")Long id, @RequestBody  actualizarPollo update){
        try{

        if(!inventarioservice.ExistePorId(id))
                return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventarioo_04), HttpStatus.BAD_REQUEST);
        if(update.getPollo() < 0)
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventario_01), HttpStatus.BAD_REQUEST);

        inventarioservice.ActulizarMercaderia(id,update);
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
        if(!inventarioservice.ExistePorId(id))
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventarioo_04), HttpStatus.NOT_FOUND);
        inventarioservice.EliminarInventario(id);
        return new ResponseEntity(new Mensaje(MensajesUtils.Exitoso_01), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/pollo")
    public ResponseEntity<?> PolloTable(@RequestBody actualizarPollo update)
    {
        if(update.getPollo() < 0)
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Inventario_06),HttpStatus.BAD_REQUEST);
        diaservice.ActualizarTablaPollo(update);
        return new ResponseEntity(new Mensaje(MensajesUtils.Exitoso_01),HttpStatus.OK);
    }

    @GetMapping("/pollo/lista")
    public ResponseEntity<actualizarPollo> lista()
    {
        List<DiaPollo> listaPollo=diaservice.Listar();
        if(listaPollo.size()>0){
            DiaPollo dia = listaPollo.get(0);
            return new ResponseEntity(new actualizarPollo(dia.getPollo(), dia.getPresa()),HttpStatus.OK);
        }else{
            return new ResponseEntity(new actualizarPollo(0, 0),HttpStatus.OK);
        }
    }
}
