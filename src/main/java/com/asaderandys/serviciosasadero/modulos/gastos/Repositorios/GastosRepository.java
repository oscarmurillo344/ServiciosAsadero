package com.asaderandys.serviciosasadero.modulos.gastos.Repositorios;

import com.asaderandys.serviciosasadero.modulos.gastos.Modelos.Gastos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public interface GastosRepository extends JpaRepository<Gastos, Long> {

    @Query(value = "SELECT * FROM gastos " +
            "where tipo= :tipo and CAST(fecha AS date) between CAST( :desde AS date) and CAST( :hasta AS date)",nativeQuery = true)
    List<Gastos> BuscarxFechaxTipo(@Param("desde") Calendar since,
                                   @Param("hasta")Calendar until,
                                   @Param("tipo")String type);

    @Query(value = "SELECT * FROM gastos " +
            "where tipo= :tipo and usuario= :user " +
            " and CAST(fecha AS date) between CAST( :desde AS date) and CAST( :hasta AS date)",nativeQuery = true)
    List<Gastos>BuscarxFechaxUserxTipo(@Param("desde") Calendar since,
                                       @Param("hasta")Calendar until,
                                       @Param("user")String usuario,
                                       @Param("tipo")String type);

    @Query(value = "SELECT * FROM gastos " +
            "where usuario= :user" +
            " and CAST(fecha AS date) between CAST( :desde AS date) and CAST( :hasta AS date)",nativeQuery = true)
    List<Gastos>BuscarxFechaxUser(@Param("desde") Calendar since,
                                  @Param("hasta")Calendar until,
                                  @Param("user")String usuario);

    @Query(value = "SELECT * FROM gastos " +
            "where CAST(fecha AS date) between CAST( :desde AS date) and CAST( :hasta AS date)",nativeQuery = true)
    List<Gastos>BuscarxFecha(@Param("desde") Calendar since,
                             @Param("hasta")Calendar until);




}

