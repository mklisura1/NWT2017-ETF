package TemplatesService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TemplatesService.Model.Template;
import TemplatesService.Repository.TemplateRepository;


@Service
public class TemplateServiceImpl implements TemplateService 
{
	@Autowired
	private TemplateRepository templateRepository;

    @Override
    public Iterable<Template> listAllTemplates() 
    {
        return templateRepository.findAll();
    }
    
    public boolean isTemplateExist(Template template) 
    {
    	return templateRepository.exists(template.getTemplate_id());
    }

    @Override
    public Template getTemplateById(Integer id) 
    {
        return templateRepository.findOne(id);
    }

    public Template saveTemplate(Template Template) 
    {
        return templateRepository.save(Template);
    }
    
    public void updateTemplate(Template template)
    {     
        templateRepository.save(template);
    }

    @Override
    public void deleteTemplate(Integer id) 
    {
        templateRepository.delete(id);
    }
}