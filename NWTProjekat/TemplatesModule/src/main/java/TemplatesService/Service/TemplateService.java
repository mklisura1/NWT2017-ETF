package TemplatesService.Service;

import TemplatesService.Model.Template;

import java.util.List;

public interface TemplateService 
{
    Iterable<Template> listAllTemplates();
    Iterable<Template> listAllTemplatesForUser(Integer userId);

    boolean isTemplateExist(Template template);

    Template getTemplateById(Integer id);
    
    List<Template> getTemplatesByUserId(Integer user_id);
    
    List<Template> getTemplatesBySenderName(String sender_name);
    
    List<Template> getTemplatesByReceiverName(String receiver_name);

    Template saveTemplate(Template template);
    
    void updateTemplate(Template template);

    void deleteTemplate(Integer id);
}