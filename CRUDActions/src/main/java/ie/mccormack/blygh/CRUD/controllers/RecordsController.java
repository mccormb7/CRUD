package ie.mccormack.blygh.CRUD.controllers;

import ie.mccormack.blygh.CRUD.domain.Record;
import ie.mccormack.blygh.CRUD.domain.RecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String listRecords(Model model) {
        model.addAttribute("records", repository.findAll());
        return "records/list";
    }
    
    

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable long id) {
        repository.delete(id);
        return new ModelAndView("redirect:/records");
    }

    @RequestMapping(value="/newRecord", method = RequestMethod.GET)
    public String newProject() {
        return "records/newRecord";
    }

    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@RequestParam("name") String name, @RequestParam("pps") String pps, 
    		@RequestParam("dob") String dob, @RequestParam("number") Double number) {
    	Record record = new Record();
    	record.setName(name);
    	record.setPps(pps);
    	record.setDob(dob);
    	record.setNumber(number);
    	repository.save(record);
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
        return new ModelAndView("redirect:/records");
    }

    @RequestMapping(value = "/{id}/editRecord", method = RequestMethod.GET)
    public String edit(@PathVariable long id,
                       Model model) {
        Record record = repository.findOne(id);
        model.addAttribute("record", record);
        return "records/editRecord";
    }


}
