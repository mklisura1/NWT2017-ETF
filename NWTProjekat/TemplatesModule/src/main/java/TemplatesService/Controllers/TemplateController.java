package TemplatesService.Controllers;

import TemplatesService.Model.Template;
import TemplatesService.Service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping(value="/api")
public class TemplateController 
{
	@Autowired
	private TemplateService templateService;
	
	//-------------------Retrieve All Templates--------------------------------------------------------
    
    @RequestMapping(value = "/template", method = RequestMethod.GET)
    public ResponseEntity<List<Template>> listAllTemplates(@RequestParam(required = false) Integer userId) {

        List<Template> templates = null;
        if(userId != null){
            templates = (List<Template>) templateService.listAllTemplatesForUser(userId);
        }else
            templates = (List<Template>) templateService.listAllTemplates();

        if(templates.isEmpty()){
            return new ResponseEntity<List<Template>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Template>>(templates, HttpStatus.OK);
    }
    
	//-------------------Retrieve Templates By User Id-------------------------------------------
    
    @RequestMapping(value = "/user/{id}/template", method = RequestMethod.GET)
    public ResponseEntity<List<Template>> getTemplatesByUserId(@PathVariable("id") Integer id) {
        List<Template> templates = (List<Template>) templateService.getTemplatesByUserId(id);
        if(templates.isEmpty()){
            return new ResponseEntity<List<Template>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        
        return new ResponseEntity<List<Template>>(templates, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single Template--------------------------------------------------------
     
    @RequestMapping(value = "/template/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Template> getTemplate(@PathVariable("id") Integer id) {
        System.out.println("Fetching Template with id " + id);
        Template template = templateService.getTemplateById(id);
        if (template == null) {
            System.out.println("Template with id " + id + " not found");
            return new ResponseEntity<Template>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Template>(template, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a Template--------------------------------------------------------
     
    @RequestMapping(value = "/template", method = RequestMethod.POST)
    public ResponseEntity<Template> createTemplate(@RequestBody Template template, UriComponentsBuilder ucBuilder) {
 
//        if (templateService.isTemplateExist(template)) {
//            System.out.println("A Template with name " + template.getTemplate_id() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }


 
        Template t = templateService.saveTemplate(template);
 
        HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(ucBuilder.path("/template/{id}").buildAndExpand(template.getTemplate_id()).toUri());
        return new ResponseEntity<Template>(t, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a Template --------------------------------------------------------
     
    @RequestMapping(value = "/template/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Template> updateTemplate(@PathVariable("id") Integer id, @RequestBody Template template) {
        System.out.println("Updating Template " + id);
         
        Template currentTemplate = templateService.getTemplateById(id);
         
        if (currentTemplate==null) {
            System.out.println("Template with id " + id + " not found");
            return new ResponseEntity<Template>(HttpStatus.NOT_FOUND);
        }
 
        currentTemplate.setSender_name(template.getSender_name());
        currentTemplate.setReceiver_name(template.getReceiver_name());
         
        templateService.updateTemplate(currentTemplate);;
        return new ResponseEntity<Template>(currentTemplate, HttpStatus.OK);
    }
 
    //------------------- Delete a Template --------------------------------------------------------
     
    @RequestMapping(value = "/template/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Template> deleteTemplate(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting Template with id " + id);
 
        Template template = templateService.getTemplateById(id);
        if (template == null) {
            System.out.println("Unable to delete. Template with id " + id + " not found");
            return new ResponseEntity<Template>(HttpStatus.NOT_FOUND);
        }

        templateService.deleteTemplate(id);
        return new ResponseEntity<Template>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All Templates --------------------------------------------------------
    
    @RequestMapping(value = "/template/", method = RequestMethod.DELETE)
    public ResponseEntity<Template> deleteAllTemplates() {
        System.out.println("Deleting All Templates");
 
        for(Template item: templateService.listAllTemplates())
        {
        	templateService.deleteTemplate(item.getTemplate_id());
        }
        return new ResponseEntity<Template>(HttpStatus.NO_CONTENT);
    }
}
