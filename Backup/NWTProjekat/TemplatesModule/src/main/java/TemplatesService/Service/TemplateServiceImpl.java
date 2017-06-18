package TemplatesService.Service;

import java.util.ArrayList;
import java.util.List;

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
    
    public List<Template> getTemplatesByUserId(Integer user_id)
    {
    	
    	List<Template> userTemplates = new ArrayList<Template>();
    	
    	List<Template> templates = (List<Template>) templateRepository.findAll();
    	
    	for(Template item : templates)
    	{
    		
    		if(item.getUser_id().equals(user_id))
    		{
    			
    			userTemplates.add(item);
    			
    		}
    		
    	}
    	
    	return userTemplates;
    	
    }
    
    public List<Template> getTemplatesBySenderName(String sender_name)
    {
    	
    	List<Template> senderTemplates = new ArrayList<Template>();
    	
    	List<Template> templates = (List<Template>) templateRepository.findAll();
    	
    	for(Template item : templates)
    	{
    		
    		if(item.getSender_name().equals(sender_name)){
    			
    			senderTemplates.add(item);
    			
    		}
    		
    	}
    	
    	return senderTemplates;
    	
    }
    
    public List<Template> getTemplatesByReceiverName(String receiver_name)
    {
    	
    	List<Template> receiverTemplates = new ArrayList<Template>();
    	
    	List<Template> templates = (List<Template>) templateRepository.findAll();
    	
    	for(Template item : templates)
    	{
    		
    		if(item.getReceiver_name().equals(receiver_name))
    		{
    			
    			receiverTemplates.add(item);
    			
    		}
    		
    	}
    	
    	return receiverTemplates;
    	
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