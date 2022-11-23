package com.asaderandys.serviciosasadero.modulos.facturar.Controlador;

import com.asaderandys.serviciosasadero.modulos.facturar.Dto.BetweenFechas;
import com.asaderandys.serviciosasadero.modulos.facturar.Dto.FacturaDto;
import com.asaderandys.serviciosasadero.modulos.facturar.Dto.VentasDay;
import com.asaderandys.serviciosasadero.modulos.facturar.Modelos.Factura;
import com.asaderandys.serviciosasadero.modulos.facturar.Servicios.FacturaService;
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
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    FacturaService facturaservice;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista/{numero}")
    public ResponseEntity<List<Factura>> Listar(@PathVariable("numero") Long numero){
        try{
            if (!facturaservice.existsByNumero(numero))
                return new ResponseEntity(new Mensaje(MensajesUtils.Error_Facturar_03), HttpStatus.NOT_FOUND);
            List<Factura> list = facturaservice.listaNumero(numero);
            return new ResponseEntity(list, HttpStatus.OK);
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
            if(!facturaservice.existsByNumero(id))
                return new ResponseEntity(new Mensaje(MensajesUtils.Error_Facturar_03), HttpStatus.NOT_FOUND);
            facturaservice.EliminarFactura(id);
            return new ResponseEntity(new Mensaje(MensajesUtils.Exitoso_01), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/facturar")
    public ResponseEntity<?> Ingresar(@RequestBody FacturaDto factDto){
    try{
       if(factDto.getFacturaItem().size() < 0 )
            return new ResponseEntity(new Mensaje(MensajesUtils.Error_Facturar_04), HttpStatus.BAD_REQUEST);
        facturaservice.IngresarFactura(factDto);
        return new ResponseEntity(new Mensaje(MensajesUtils.Exitoso_01), HttpStatus.OK);
    }catch (DataAccessException ex){
        return new ResponseEntity(new Mensaje
                ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }


    @GetMapping("/numero")
    public ResponseEntity<?> Numerofactura(){
        try{
        Long NumeroFactura=facturaservice.MaximoValor();
        return new ResponseEntity(NumeroFactura, HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/totaldia/{usu}")
    public ResponseEntity<?> TotalDia(@PathVariable("usu") String usu){
        try{
        List<VentasDay> l=facturaservice.TotalDia(usu);
        return new ResponseEntity(l, HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/totalfecha")
    public ResponseEntity<?> totalFecha(@RequestBody BetweenFechas fec)
    {
        try{
        if(fec.getFechaFirst() == null )
            return new ResponseEntity(new Mensaje("No existe fecha"),HttpStatus.BAD_REQUEST);

        List<VentasDay> listar=facturaservice.TotalFechas(fec.getFechaFirst(),fec.getFechaSecond());
        return new ResponseEntity(listar,HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/totalfechaUser")
    public ResponseEntity<?> totalFechaUser(@RequestBody BetweenFechas fec){
        try{
        if(fec.getFechaFirst() == null )
            return new ResponseEntity(new Mensaje("No existe fecha"),HttpStatus.BAD_REQUEST);
        if(fec.getUsuario().isEmpty())
            return new ResponseEntity(new Mensaje("No existe usuario"),HttpStatus.BAD_REQUEST);

        List<VentasDay> lis=facturaservice.TotalFechasUser
                (fec.getUsuario(),fec.getFechaFirst(),fec.getFechaSecond());
        return new ResponseEntity(lis,HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/totalfechauserdia")
    public ResponseEntity<?> totalFechaUserDia(@RequestBody BetweenFechas fec){
        try{
        if(fec.getFechaFirst() == null )
            return new ResponseEntity(new Mensaje("No existe fecha"),HttpStatus.BAD_REQUEST);
        if(fec.getUsuario().isEmpty())
            return new ResponseEntity(new Mensaje("No existe usuario"),HttpStatus.BAD_REQUEST);
        List<VentasDay> listar=facturaservice.TotalFechasUserDia
        (fec.getUsuario(),fec.getFechaFirst(),fec.getFechaSecond(),fec.getDia());
        return new ResponseEntity(listar,HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping("/totalfechadia")
    public ResponseEntity<?> totalFechaDia(@RequestBody BetweenFechas fec){
        try{
        if(fec.getFechaFirst() == null )
            return new ResponseEntity(new Mensaje("No existe fecha"),HttpStatus.BAD_REQUEST);
        if(fec.getUsuario().isEmpty())
            return new ResponseEntity(new Mensaje("No existe usuario"),HttpStatus.BAD_REQUEST);
        List<VentasDay> listar=facturaservice.TotalFechasDia
                (fec.getFechaFirst(),fec.getFechaSecond(),fec.getDia());
        return new ResponseEntity(listar,HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/totalfechasComp")
    public ResponseEntity<?> totalFechaComp(@RequestBody BetweenFechas fec){
        try{
            if(fec.getFechaFirst() == null || fec.getFechaSecond() == null )
                return new ResponseEntity(new Mensaje("No existe fecha"),HttpStatus.BAD_REQUEST);
            List<VentasDay> listar=facturaservice.TotalFechasComplete
                    (fec.getFechaFirst(),fec.getFechaSecond());
            return new ResponseEntity(listar,HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
