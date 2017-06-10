package TemplatesService.Service;

import TemplatesService.Model.Template;
import TemplatesService.Repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Service
public class TemplateServiceImpl implements TemplateService 
{
	@Autowired
	private TemplateRepository templateRepository;

    @PostConstruct
    @Transactional
    public void populate() {
//        for (Integer i=0;i<10;i++){
//            Template t = new Template();
//            t.setTemplate_name("Template broj: " + i);
//            t.setPayment_purpose("Racun za elektricnu energiju");
//            t.setPayment_type_id(1);
//            t.setAmount(100*new Random().nextDouble());
//            t.setPayment_type((i%2==0) ? "InternalPayment" : (i%3==0) ? "ForeignPayment": "DometicPayment");
//            t.setReceiver_bank_acc_number("123123123");
//            t.setReceiver_name("Elektrodistribucija BIH");
//            t.setSender_name("Hamo Hamic");
//            t.setSender_bank_acc_number("321321321");
//            t.setUserId(1);
//            templateRepository.save(t);
//        }
    }

    @Override
    public Iterable<Template> listAllTemplates() 
    {
        return templateRepository.findAll();
    }

    @Override
    public Iterable<Template> listAllTemplatesForUser(Integer userId) { return templateRepository.findByUserId(userId);}
    public boolean isTemplateExist(Template template) 
    {
    	return templateRepository.exists(template.getTemplate_id());
    }

    @Override
    public Template getTemplateById(Integer id) 
    {
        return templateRepository.findOne(id);
    }
    
    public List<Template> getTemplatesByUserId(Integer UserId)
    {
    	
    	List<Template> userTemplates = new ArrayList<Template>();
    	
    	List<Template> templates = (List<Template>) templateRepository.findAll();
    	
    	for(Template item : templates)
    	{
    		
    		if(item.getUserId().equals(UserId))
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