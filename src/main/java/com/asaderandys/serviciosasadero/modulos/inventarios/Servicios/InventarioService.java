package com.asaderandys.serviciosasadero.modulos.inventarios.Servicios;

import com.asaderandys.serviciosasadero.modulos.inventarios.Dto.actualizarPollo;
import com.asaderandys.serviciosasadero.modulos.inventarios.Dto.inventarioDto;
import com.asaderandys.serviciosasadero.modulos.inventarios.Modelos.Inventario;
import com.asaderandys.serviciosasadero.modulos.inventarios.Modelos.Producto;
import com.asaderandys.serviciosasadero.modulos.inventarios.Repositorios.inventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventarioService {

    @Autowired
    inventarioRepository inventariorepository;

    public List<Inventario> listar(){ return inventariorepository.findAll(Sort.by(Sort.Direction.ASC, "producto.nombre")); }

    public Optional<Inventario> ObtenerInventarioPorId(Long id){ return inventariorepository.findById(id); }

    public void IngresarAndEditarInventario(inventarioDto inven) throws DataAccessException {
        Inventario inventario;
        if(inven.getId() == 0){
            inventario =new Inventario(
                    Calendar.getInstance(),inven.getProducto(),inven.getExtras(),inven.getCantidad(),0);
        }else{
            inventario=this.ObtenerInventarioPorId(inven.getId()).get();
            inventario.setCantidadTotal(inven.getCantidad());
            inventario.setFecha(Calendar.getInstance());
            inventario.setCantidadExiste(inven.getCantidad()+inventario.getCantidadExiste());
            inventario.setExtras(inven.getExtras());
        }
        inventariorepository.save(inventario);
    }

    public void ActulizarMercaderia(Long id, actualizarPollo update)throws DataAccessException{
        int pollo = 0,presa = 0;
        int valorPollo = 8;
        Inventario inventario = this.ObtenerInventarioPorId(id).get();

        pollo = inventario.getCantidadExiste() - update.getPollo();
        if(update.getPresa()>0){
            pollo--;
            presa = valorPollo + inventario.getProducto().getPresa();
            presa  -= update.getPresa();
            inventario.getProducto().setPresa(presa);
        }
        inventario.setCantidadExiste(pollo);
        inventariorepository.save(inventario);
    }
    public void GuardarUnico(Inventario in){ inventariorepository.save(in);}

    public void EliminarInventario(Long id){
        inventariorepository.deleteById(id);
    }

    public boolean ExistePorId(Long id){
        return inventariorepository.existsById(id);
    }

    public Inventario ActulizarProduct(Producto pid){ return inventariorepository.findByProductoId(pid);}





}
