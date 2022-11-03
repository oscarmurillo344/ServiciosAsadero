package com.asaderandys.serviciosasadero.modulos.clientes.Controlador;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/factura")
@CrossOrigin(origins = {"https://asaderoweb.herokuapp.com","http://192.168.100.115:4200","*"})
public class ClienteController {


}
