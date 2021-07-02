package br.com.plannic.service;

import br.com.plannic.model.Material;
import br.com.plannic.repository.MaterialRepository;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MaterialService {

    private final MaterialRepository repository;

    private static Logger logger = Logger.getLogger(MaterialService.class);

    public MaterialService(MaterialRepository repository) {
        this.repository = repository;
    }

    public void save(Material material) {
        ModelMapper mapper = new ModelMapper();
        var materialSalvo = repository.save(mapper.map(material, Material.class));
        MDC.put("idMaterial", materialSalvo.getMaterial());
        logger.info("Material salvo");
    }

    public boolean update(Material material) {
        Optional<Material> materiais = Optional.ofNullable(this.repository.findById(material.getIdMaterial()));

        if (materiais.isPresent()) {
            logger.info("Material atualizado");
            ModelMapper mapper = new ModelMapper();
            repository.save(mapper.map(material, Material.class));
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        Optional<Material> materiais = Optional.ofNullable(this.repository.findById(id));

        if (materiais.isPresent()) {
            logger.info("Material deletada");
            this.repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Material> getMyMaterial(int id) {
        ModelMapper mapper = new ModelMapper();
        List<Material> materiais = this.repository.findMyMaterial(id);

        if (!materiais.isEmpty()) {
            logger.info("Materiais recuperados");
            return  materiais
                    .stream()
                    .map(material -> mapper.map(material, Material.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public List<Material> getMyMaterialByMateria(int id, int idMateria) {
        ModelMapper mapper = new ModelMapper();
        List<Material> materiais = this.repository.findMyMaterialByIdMateriaBase(id, idMateria);

        if (!materiais.isEmpty()) {
            logger.info("Materiais recuperados");
            return  materiais
                    .stream()
                    .map(material -> mapper.map(material, Material.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public List<Material> getMaterialByMateria(int id, int idMateria) {
        ModelMapper mapper = new ModelMapper();
        List<Material> materiais = this.repository.findMaterialByIdMateriaBase(id, idMateria);

        if (!materiais.isEmpty()) {
            logger.info("Materiais recuperados");
            return  materiais
                    .stream()
                    .map(material -> mapper.map(material, Material.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public Material getMaterialById(int idMaterial) {
        Material material = this.repository.findById(idMaterial);

        return material;
    }
}
