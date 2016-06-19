package ie.mccormack.blygh.CRUD.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import ie.mccormack.blygh.CRUD.domain.Record;
import ie.mccormack.blygh.CRUD.domain.RecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/records")
public class RecordsController {

    @Autowired
    private RecordRepository repository;

    @RequestMapping(value="", method=RequestMethod.GET)
    public String home(Model model) {
       
        return "records/home";
    }
    
    @RequestMapping(value="/home", method=RequestMethod.GET)
    public String homelanding(Model model) {
       
        return "records/home";
    }
    
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public String listRecords(Model model) {
        model.addAttribute("records", repository.findAll());
        return "records/list";
    }
    
    

    

    @RequestMapping(value="/newRecord", method = RequestMethod.GET)
    public String newProject(Record record) {
        return "records/newRecord";
    }
    
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String checkPersonInfo(@Valid Record record, BindingResult bindingResult) {

    	//repository.exists(record.getPps())
    	if(repository.exists(record.getPps())){
    		System.out.println(" username is in use : " + record.getPps());
    		//bindingResult.rejectValue("pps", "ppsn number already registered");
    		bindingResult.rejectValue("pps", "error.record", "This PPS Number is already registered.");

    		return "records/newRecord";
    	}
    	
    	String prefix = "08";
    	String inputNumber = record.getNumber().substring(1);
    	if(prefix!=inputNumber){
    		bindingResult.rejectValue("number", "error.record", "Number must start with 08.");
    		return "records/newRecord";
    	}
    	
    	
    	
        if (bindingResult.hasErrors()) {
            return "records/newRecord";
        }
     //   DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date current= new Date();
        String saved = new SimpleDateFormat("dd/MM/yyyy").format(current);
        record.setCreated(saved);
       
        try{
        	repository.save(record);
    	}catch(DuplicateKeyException e){
    		//bindingResult.rejectValue("pps", "In use");
    		bindingResult.rejectValue("pps", "error.record", "An account already exists for this email.");
    		return "records/newRecord";
    	}
        
        
        return "redirect:/records/list";
    }

    
//    @RequestMapping(value = "/create", method = RequestMethod.GET)
//    public ModelAndView create(@RequestParam("name") String name,  @RequestParam("pps") String pps, 
//    		@RequestParam("dob") String dob, @RequestParam("number") Double number) {
//    	
//
//    	Record record = new Record();
//    	record.setName(name);
//    	record.setPps(pps);
//    	record.setDob(dob);
//    	record.setNumber(number);
//    	repository.save(record);
//        return new ModelAndView("redirect:/records/list");
//    }

 /*   @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable long id) {
        repository.delete(id);
        return new ModelAndView("redirect:/records");
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView update(@RequestParam("record_id") long id,
    		@RequestParam("name") String name, @RequestParam("pps") String pps, 
    	    		@RequestParam("dob") String dob, @RequestParam("number") Double number){

    	Record record = repository.findOne(id);
    	record.setName(name);
    	record.setPps(pps);
    	record.setDob(dob);
    	record.setNumber(number);
    	repository.save(record);
        return new ModelAndView("redirect:/records/list");
    }

    @RequestMapping(value = "/{id}/editRecord", method = RequestMethod.GET)
    public String edit(@PathVariable long id,
                       Model model) {
        Record record = repository.findOne(id);
        model.addAttribute("record", record);
        return "records/editRecord";
    }
*/

}
