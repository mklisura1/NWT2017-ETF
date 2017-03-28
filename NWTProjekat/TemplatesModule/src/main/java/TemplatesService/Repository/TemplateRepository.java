package TemplatesService.Repository;
 
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import TemplatesService.Model.Template;

@Component
public interface TemplateRepository extends CrudRepository<Template, Integer>{
}