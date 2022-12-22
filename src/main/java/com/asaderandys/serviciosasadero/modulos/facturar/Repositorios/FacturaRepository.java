package com.asaderandys.serviciosasadero.modulos.facturar.Repositorios;


import com.asaderandys.serviciosasadero.modulos.facturar.Dto.VentasDay;
import com.asaderandys.serviciosasadero.modulos.facturar.Modelos.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    @Query(value = "SELECT CASE" +
            "     WHEN MAX(f.id) IS NULL THEN 0" +
            "     ELSE MAX(f.id)" +
            "      END AS NumeroFactura" +
            "     FROM Factura f")
    Long FacturaMaxima();

    @Query(value = "SELECT pr.nombre AS nombre," +
                          "pf.montoPago AS precio," +
                          "sum(pf.cantidad) as cantidad " +
            "FROM Factura f INNER JOIN f.facturaItem pf INNER JOIN pf.producto pr " +
            " where f.fechaIngreso = CURRENT_DATE " +
            " group by pr.nombre,pf.montoPago " +
            " order by pr.nombre")
    List<VentasDay> TotalDay();

    @Query(value = "SELECT pr.nombre AS nombre," +
            "pf.montoPago AS precio," +
            "sum(pf.cantidad) as cantidad " +
            "FROM Factura f INNER JOIN f.facturaItem pf INNER JOIN pf.producto pr " +
            " where f.fechaIngreso = CURRENT_DATE and f.usuario = :user " +
            " group by pr.nombre,pf.montoPago " +
            " order by pr.nombre")
    List<VentasDay> TotalDay(@Param("user") String usuario);

    @Query(value = "SELECT pr.nombre AS nombre," +
                          "pf.montoPago AS precio," +
                          "sum(pf.cantidad) as cantidad " +
            "FROM Factura f INNER JOIN f.facturaItem pf INNER JOIN pf.producto pr " +
            " where f.usuario = :user and " +
            "f.fechaIngreso between :dateFirst and :dateSecond " +
            " group by pr.nombre,pf.montoPago " +
            " order by pr.nombre")
    List<VentasDay> TotalUserFechas(@Param("user") String usuario,
                                @Param("dateFirst") Date dateF,
                                @Param("dateSecond") Date dateS);

    @Query(value = "SELECT   pr.nombre AS nombre," +
                            "pf.montoPago AS precio," +
                            "sum(pf.cantidad) as cantidad " +
            "FROM Factura f INNER JOIN f.facturaItem pf INNER JOIN pf.producto pr " +
            "WHERE f.fechaIngreso between :dateFirst and  :dateSecond " +
            " group by pr.nombre,pf.montoPago " +
            " order by pr.nombre")
    List<VentasDay> TotalFechas(@Param("dateFirst") Date dateF,
                                @Param("dateSecond") Date dateS);

    @Query(value = "SELECT  pr.nombre AS nombre," +
                            "pf.montoPago AS precio," +
                            "sum(pf.cantidad) as cantidad " +
            "FROM Factura f INNER JOIN f.facturaItem pf INNER JOIN pf.producto pr " +
            " WHERE f.usuario = :user and f.diaIngreso=:dia and " +
            "f.fechaIngreso between :dateFirst and :dateSecond " +
            " group by f.usuario,pr.nombre,pf.montoPago " +
            " order by pr.nombre")
    List<VentasDay> TotalUserFechasdia(@Param("user") String usuario,
                                    @Param("dateFirst") Date dateF,
                                    @Param("dateSecond") Date dateS,
                                   @Param("dia") String dia);

    @Query(value = "SELECT   pr.nombre AS nombre," +
                            "pf.montoPago AS precio," +
                            "sum(pf.cantidad) as cantidad " +
            "FROM Factura f INNER JOIN f.facturaItem pf INNER JOIN pf.producto pr " +
            " WHERE f.diaIngreso=:dia and " +
            "f.fechaIngreso between :dateFirst and :dateSecond " +
            " group by pr.nombre,pf.montoPago " +
            " order by pr.nombre")
    List<VentasDay> TotalFechasdia(@Param("dateFirst") Date dateF,
                                       @Param("dateSecond") Date dateS,
                                       @Param("dia") String dia);

    @Query(value = "SELECT pr.nombre AS nombre, " +
                            "pf.montoPago AS precio, " +
                            "sum(pf.cantidad) as cantidad , " +
                            "f.fechaIngreso AS FechaIngreso , " +
                            "f.diaIngreso AS diaIngreso "+
            "FROM Factura f INNER JOIN f.facturaItem pf INNER JOIN pf.producto pr " +
            " where f.fechaIngreso between :dateFirst and :dateSecond " +
            " group by pr.nombre,pr.precio " +
            " order by pr.nombre")
    List<VentasDay> TotalFechasComplete(@Param("dateFirst") Date dateF,
                                         @Param("dateSecond") Date dateS);


}
