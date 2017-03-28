package TemplatesService.Service;

import TemplatesService.Model.Template;

public interface TemplateService 
{
    Iterable<Template> listAllTemplates();
    
    boolean isTemplateExist(Template template);

    Template getTemplateById(Integer id);

    Template saveTemplate(Template template);
    
    void updateTemplate(Template template);

    void deleteTemplate(Integer id);
}