package com.asaderandys.serviciosasadero.modulos.inventarios.Servicios;


import com.asaderandys.serviciosasadero.modulos.inventarios.Dto.ProductoDto;
import com.asaderandys.serviciosasadero.modulos.inventarios.Modelos.Producto;
import com.asaderandys.serviciosasadero.modulos.inventarios.Repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public List<Producto> ListarTodo(){
        return productoRepository.findAll();
    }

    public Optional<Producto> ObtenerUno(Long id){
        return productoRepository.findById(id);
    }

    public Optional<Producto> ObtenerPorNombre(String nombre){
        return productoRepository.findByNombre(nombre);
    }

    public Producto  NuevoProducto(ProductoDto productoDto){
        Producto producto;
        if(productoDto.getId() == 0){
            producto = new Producto(productoDto.getNombre(),productoDto.getTipo()
                    ,productoDto.getPrecio(),productoDto.getPresa());
        }else{
            producto = this.ObtenerUno(productoDto.getId()).get();
            producto.setNombre(productoDto.getNombre());
            producto.setTipo(productoDto.getTipo());
            producto.setPrecio(productoDto.getPrecio());
            producto.setPresa(productoDto.getPresa());
        }
        return productoRepository.save(producto);
    }

    public void EliminarProducto(Long id){ productoRepository.deleteById(id); }

    public boolean ExistePorId(Long id){
        return productoRepository.existsById(id);
    }

    public boolean ExistePorNombre(String nombre){
        return productoRepository.existsByNombre(nombre);
    }
}
