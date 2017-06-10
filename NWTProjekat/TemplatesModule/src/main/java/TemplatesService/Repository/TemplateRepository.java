package TemplatesService.Repository;

import TemplatesService.Model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TemplateRepository extends JpaRepository<Template, Integer> {

    Iterable<Template> findByUserId(Integer userId);

}