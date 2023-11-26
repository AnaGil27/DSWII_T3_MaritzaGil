package cibertec.edu.pe.DSWII_T3_MaritzaGil.repository;

import cibertec.edu.pe.DSWII_T3_MaritzaGil.model.bd.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Rol findByNomrol(String nombrerol);
}
