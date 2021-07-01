package br.com.plannic.repository;

import br.com.plannic.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
    Material findById(int id);

    @Query("SELECT u FROM Material u WHERE u.idUsuario = ?1")
        List<Material> findMyMaterial(Integer id);

    @Query("SELECT u FROM Material u WHERE u.idUsuario = ?1 AND u.idMateriaBase = ?2")
        List<Material> findMyMaterialByIdMateriaBase(Integer id, Integer idMateriaBase);

    @Query("SELECT u FROM Material u WHERE u.idUsuario <> ?1 AND u.idMateriaBase = ?2 AND u.publico = true")
        List<Material> findMaterialByIdMateriaBase(Integer id, Integer idMateriaBase);
}