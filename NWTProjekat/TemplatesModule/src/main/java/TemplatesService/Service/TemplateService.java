package TemplatesService.Service;

import java.util.List;

import TemplatesService.Model.Template;

public interface TemplateService 
{
    Iterable<Template> listAllTemplates();
    
    boolean isTemplateExist(Template template);

    Template getTemplateById(Integer id);
    
    List<Template> getTemplatesByUserId(Integer user_id);
    
    List<Template> getTemplatesBySenderName(String sender_name);
    
    List<Template> getTemplatesByReceiverName(String receiver_name);

    Template saveTemplate(Template template);
    
    void updateTemplate(Template template);

    void deleteTemplate(Integer id);
}