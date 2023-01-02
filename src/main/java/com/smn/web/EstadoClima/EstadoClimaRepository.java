package com.smn.web.EstadoClima;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoClimaRepository extends JpaRepository<EstadoClima, Long> {

}
