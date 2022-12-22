package com.asaderandys.serviciosasadero.modulos.facturar.Servicios;

import com.asaderandys.serviciosasadero.modulos.facturar.Dto.FacturaDto;
import com.asaderandys.serviciosasadero.modulos.facturar.Dto.VentasDay;
import com.asaderandys.serviciosasadero.modulos.facturar.Modelos.Factura;
import com.asaderandys.serviciosasadero.modulos.facturar.Modelos.FacturaItems;
import com.asaderandys.serviciosasadero.modulos.facturar.Repositorios.FacturaRepository;
import com.asaderandys.serviciosasadero.modulos.inventarios.Modelos.DiaPollo;
import com.asaderandys.serviciosasadero.modulos.inventarios.Modelos.Inventario;
import com.asaderandys.serviciosasadero.modulos.inventarios.Servicios.DiaPolloService;
import com.asaderandys.serviciosasadero.modulos.inventarios.Servicios.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FacturaService {

    @Autowired
    FacturaRepository facturarepository;
    @Autowired
    InventarioService inventarioservice;
    @Autowired
    DiaPolloService diaservice;

    public void IngresarFactura(FacturaDto factDto){
        int count=0,count2=0;
        Factura fact = new Factura(
                factDto.getUsuario(),
                factDto.getFormaPago(),
                factDto.getFechaIngreso(),
                factDto.getFechaIngreso(),
                factDto.getDiaIngreso(),
                factDto.getFacturaItem().stream().map( m -> new FacturaItems(m.getCantidad(),m.getExtras(),m.getProducto(),m.getDescuento(),m.getMontoPago())).toList()
        );
        for(FacturaItems items : fact.getFacturaItem()){
            Inventario inventory = inventarioservice.BuscarProducto(items.getProducto());
            count=inventory.getCantidadExiste()- items.getCantidad();
            inventory.setCantidadExiste(count);
            inventarioservice.GuardarUnico(inventory);

            if(items.getExtras() != ""){
                String[] list=items.getExtras().split(",");
                for (int i=0;i < list.length ;i++){
                    Inventario invent=inventarioservice.ObtenerInventarioPorId(Long.parseLong(list[i])).get();
                    count2=invent.getCantidadExiste()- items.getCantidad();
                    invent.setCantidadExiste(count2);
                    inventarioservice.GuardarUnico(invent);
                }
            }
        }
        facturarepository.save(fact);
    }

    public boolean existsByNumeroFatura(Long numeroFactura){
        return facturarepository.existsById(numeroFactura);
    }

    public List<Factura> list(){
        return facturarepository.findAll();
    }


    public Long  MaximoValor(){ return facturarepository.FacturaMaxima() + 1L; }

    public List<VentasDay> TotalDia(){ return facturarepository.TotalDay();}

    public List<VentasDay> TotalDia(String usuario){ return facturarepository.TotalDay(usuario);}

    public List<VentasDay> TotalFechasUser(String usua, Date dateF, Date dateS)
    { return facturarepository.TotalUserFechas(usua,dateF,dateS);}

    public List<VentasDay> TotalFechas(Date dateF, Date dateS)
    { return facturarepository.TotalFechas(dateF,dateS);}

    public List<VentasDay> TotalFechasUserDia(String usua,Date dateF, Date dateS,String dia)
    { return facturarepository.TotalUserFechasdia(usua,dateF,dateS,dia);}

    public List<VentasDay> TotalFechasComplete(Date dateF, Date dateS)
    { return facturarepository.TotalFechasComplete(dateF,dateS);}

    public List<VentasDay> TotalFechasDia(Date dateF, Date dateS,String dia)
    { return facturarepository.TotalFechasdia(dateF,dateS,dia);}

    public Optional<Factura> listaNumero(Long numeroFactura){return facturarepository.findById(numeroFactura);}

    public void EliminarFactura(Long numero){
        int presa=0,cantidad=0,Totalpresa=0;
        Optional<Factura> listaFactura = facturarepository.findById(numero);
        for(FacturaItems factura : listaFactura.get().getFacturaItem()){
            Inventario p = inventarioservice.BuscarProducto(factura.getProducto());
            p.setCantidadExiste(p.getCantidadExiste() + factura.getCantidad());
            presa += factura.getProducto().getPresa();
            cantidad = factura.getCantidad();
            inventarioservice.GuardarUnico(p);
        }
        Totalpresa=presa*cantidad;
        List<DiaPollo> lista= diaservice.Listar();
        if(lista.size() > 0){
            DiaPollo listaPollo = lista.get(0);
            if(Totalpresa>=8){
                while(Totalpresa>=8){
                    Totalpresa-=8;
                    listaPollo.setPollo(listaPollo.getPollo()+1);
                }
                listaPollo.setPresa(listaPollo.getPresa()+Totalpresa);
            }else listaPollo.setPresa(listaPollo.getPresa()+Totalpresa);
            diaservice.Ingresar(listaPollo);
        }
        facturarepository.deleteById(numero);
    }

}
