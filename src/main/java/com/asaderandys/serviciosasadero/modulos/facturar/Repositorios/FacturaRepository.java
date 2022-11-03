package com.asaderandys.serviciosasadero.modulos.facturar.Repositorios;


import com.asaderandys.serviciosasadero.modulos.facturar.Dto.VentasDay;
import com.asaderandys.serviciosasadero.modulos.facturar.Modelos.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    @Query(value = "SELECT CASE" +
            "     WHEN MAX(f.numeroFactura) IS NULL THEN 0" +
            "     ELSE MAX(f.numeroFactura)" +
            "      END AS NumeroFactura" +
            "     FROM Factura f")
    Long FacturaMaxima();

    @Query(value = "SELECT f.usuarioId AS usuarioId, " +
                          "pr.nombre AS nombre," +
                          "pf.MontoPago AS montoPago," +
                          "sum(pf.cantidad) as cantidad " +
            "FROM Factura f INNER JOIN f.facturaItem pf INNER JOIN pf.producto pr " +
            " where f.FechaIngreso = CURRENT_DATE " +
            " group by f.usuarioId,pr.nombre,pf.MontoPago " +
            " order by pr.nombre")
    List<VentasDay> TotalDay(@Param("user") String usuario);

    @Query(value = "SELECT f.usuarioId AS usuarioId, " +
                          "pr.nombre AS nombre," +
                          "pf.MontoPago AS montoPago," +
                          "sum(pf.cantidad) as cantidad " +
            "FROM Factura f INNER JOIN f.facturaItem pf INNER JOIN pf.producto pr " +
            " where f.usuarioId = :user and " +
            "f.FechaIngreso between :dateFirst and :dateSecond " +
            " group by f.usuarioId,pr.nombre,pf.MontoPago " +
            " order by pr.nombre")
    List<VentasDay> TotalUserFechas(@Param("user") String usuario,
                                @Param("dateFirst") Date dateF,
                                @Param("dateSecond") Date dateS);

    @Query(value = "SELECT   pr.nombre AS nombre," +
                            "pf.MontoPago AS montoPago," +
                            "sum(pf.cantidad) as cantidad " +
            "FROM Factura f INNER JOIN f.facturaItem pf INNER JOIN pf.producto pr " +
            "WHERE f.FechaIngreso between :dateFirst and  :dateSecond " +
            " group by pr.nombre,pf.MontoPago " +
            " order by pr.nombre")
    List<VentasDay> TotalFechas(@Param("dateFirst") Date dateF,
                                @Param("dateSecond") Date dateS);

    @Query(value = "SELECT   f.usuarioId AS usuarioId, " +
                            "pr.nombre AS nombre," +
                            "pf.MontoPago AS montoPago," +
                            "sum(pf.cantidad) as cantidad " +
            "FROM Factura f INNER JOIN f.facturaItem pf INNER JOIN pf.producto pr " +
            " WHERE f.usuarioId= :user and f.DiaIngreso=:dia and " +
            "f.FechaIngreso between :dateFirst and :dateSecond " +
            " group by f.usuarioId,pr.nombre,pf.MontoPago " +
            " order by pr.nombre")
    List<VentasDay> TotalUserFechasdia(@Param("user") String usuario,
                                    @Param("dateFirst") Date dateF,
                                    @Param("dateSecond") Date dateS,
                                   @Param("dia") String dia);

    @Query(value = "SELECT   pr.nombre AS nombre," +
                            "pf.MontoPago AS montoPago," +
                            "sum(pf.cantidad) as cantidad " +
            "FROM Factura f INNER JOIN f.facturaItem pf INNER JOIN pf.producto pr " +
            " WHERE f.DiaIngreso=:dia and " +
            "f.FechaIngreso between :dateFirst and :dateSecond " +
            " group by pr.nombre,pf.MontoPago " +
            " order by pr.nombre")
    List<VentasDay> TotalFechasdia(@Param("dateFirst") Date dateF,
                                       @Param("dateSecond") Date dateS,
                                       @Param("dia") String dia);

    @Query(value = "SELECT f.usuarioId AS usuarioId, " +
                            "pr.nombre AS nombre, " +
                            "pf.MontoPago AS montoPago, " +
                            "sum(pf.cantidad) as cantidad , " +
                            "f.FechaIngreso AS FechaIngreso , " +
                            "f.DiaIngreso AS diaIngreso "+
            "FROM Factura f INNER JOIN f.facturaItem pf INNER JOIN pf.producto pr " +
            " where f.FechaIngreso between :dateFirst and :dateSecond " +
            " group by f.usuarioId,pr.nombre,pr.precio " +
            " order by pr.nombre")
    List<VentasDay> TotalFechasComplete(@Param("dateFirst") Date dateF,
                                         @Param("dateSecond") Date dateS);

    List<Factura> findByNumeroFactura(Long id);

    boolean existsByNumeroFactura(Long id);

    Factura deleteByNumeroFactura(Long id);

}
