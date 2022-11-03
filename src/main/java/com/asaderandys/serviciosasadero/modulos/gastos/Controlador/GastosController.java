package com.asaderandys.serviciosasadero.modulos.gastos.Controlador;


import com.asaderandys.serviciosasadero.modulos.gastos.Dto.GastoDto;
import com.asaderandys.serviciosasadero.modulos.gastos.Dto.GastosRequest;
import com.asaderandys.serviciosasadero.modulos.gastos.Modelos.Gastos;
import com.asaderandys.serviciosasadero.modulos.gastos.Servicios.GastosService;
import com.asaderandys.serviciosasadero.modulos.usuarios.Dto.Mensaje;
import com.asaderandys.serviciosasadero.utilitarios.MensajesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/gastos")
@CrossOrigin(origins = {"https://asaderoweb.herokuapp.com","http://192.168.100.115:4200"})
public class GastosController {

        @Autowired
        GastosService gastosSer;

        @PreAuthorize("hasRole('ADMIN')")
        @GetMapping("/lista")
        public ResponseEntity<List<Gastos>> Listar(){
            try{
            List<Gastos> list = gastosSer.Listar();
            return new ResponseEntity(list, HttpStatus.OK);
            }catch (DataAccessException ex){
                return new ResponseEntity(new Mensaje
                        ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage()),null),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PostMapping("/ingresar")
        public ResponseEntity<?> Ingresar(@RequestBody GastoDto gastos){
            try{
            if(gastos.getDescripcion().isBlank())
                return new ResponseEntity<>(new Mensaje(MensajesUtils.Error_Gasto_02,null),HttpStatus.BAD_REQUEST);
            if (gastos.getValor().longValue()<0)
                return new ResponseEntity<>(new Mensaje(MensajesUtils.Error_Gasto_01,null),HttpStatus.BAD_REQUEST);

            Gastos g =new Gastos(gastos.getTipo(),gastos.getUsuario(),Calendar.getInstance(),
                        gastos.getValor(),gastos.getDescripcion());
            gastosSer.Guardar(g);
            return new ResponseEntity<>(new Mensaje(MensajesUtils.Exitoso_01,null),HttpStatus.OK);
            }catch (DataAccessException ex){
                return new ResponseEntity(new Mensaje
                        ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage()),null),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PreAuthorize("hasRole('ADMIN')")
        @DeleteMapping("/eliminar/{id}")
        public ResponseEntity<?> Eliminar(@PathVariable("id")Long id){
            try{
            if(!gastosSer.existsById(id))
                return new ResponseEntity(new Mensaje(MensajesUtils.Error_Gasto_03,null), HttpStatus.NOT_FOUND);
            gastosSer.eliminar(id);
            return new ResponseEntity(new Mensaje(MensajesUtils.Exitoso_01,null), HttpStatus.OK);
            }catch (DataAccessException ex){
                return new ResponseEntity(new Mensaje
                        ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage()),null),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping("/listaTipo/")
        public ResponseEntity<List<Gastos>> ListarTipo(@RequestBody GastosRequest gasto){
            try{
            List<Gastos> list = gastosSer.ListarTipo(gasto.getInicial(),gasto.getFin(),gasto.getTipo());
            return new ResponseEntity(list, HttpStatus.OK);
            }catch (DataAccessException ex){
                return new ResponseEntity(new Mensaje
                        ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage()),null),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping("/listaTipoUserFecha/")
        public ResponseEntity<List<Gastos>> ListarTipoUserFecha(@RequestBody GastosRequest gasto){
            try{
            List<Gastos> list = gastosSer.ListarxFechaxUserxTipo
                    (gasto.getInicial(),gasto.getFin(),gasto.getUsuario(),gasto.getTipo());
            return new ResponseEntity(list, HttpStatus.OK);
            }catch (DataAccessException ex){
                return new ResponseEntity(new Mensaje
                        ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage()),null),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping("/listaUserFecha/")
        public ResponseEntity<List<Gastos>> ListarUserFecha(@RequestBody GastosRequest gasto){
            try{
            List<Gastos> list = gastosSer.ListarxFechaxUser
                    (gasto.getInicial(),gasto.getFin(),gasto.getUsuario());
            return new ResponseEntity(list, HttpStatus.OK);
            }catch (DataAccessException ex){
                return new ResponseEntity(new Mensaje
                        ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage()),null),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping("/listaFecha/")
        public ResponseEntity<List<Gastos>> ListarFecha(@RequestBody GastosRequest gasto){
            try{
            List<Gastos> list = gastosSer.ListarxFecha
                    (gasto.getInicial(),gasto.getFin());
            return new ResponseEntity(list, HttpStatus.OK);
            }catch (DataAccessException ex){
                return new ResponseEntity(new Mensaje
                        ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage()),null),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }


}
