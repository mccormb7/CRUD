package ie.mccormack.blygh.CRUD.controllers;

import ie.mccormack.blygh.CRUD.domain.Record;
import ie.mccormack.blygh.CRUD.domain.RecordRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/records")
public class RecordsController {

	@Autowired
	private RecordRepository repository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Model model) {

		return "records/home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homelanding(Model model) {

		return "records/home";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listRecords(Model model) {
		// orders the list by date created
		List<Record> inOrder = (List<Record>) repository.findAll();
		Collections.reverse(inOrder);

		model.addAttribute("records", inOrder);
		return "records/list";
	}

	@RequestMapping(value = "/newRecord", method = RequestMethod.GET)
	public String newProject(Record record) {
		return "records/newRecord";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String checkPersonInfo(@Valid Record record,
			BindingResult bindingResult) {

		Date dob = null;
		Date dateNow = null;
		int over16inDays = 5840;
		int userAge = 0;
		String formatedDOB = null;

		if (bindingResult.hasErrors()) {
			return "records/newRecord";
		}

		// Check if PPSN is unique
		if (repository.exists(record.getPps())) {
			bindingResult.rejectValue("pps", "error.record",
					"This PPS Number is already registered.");

			return "records/newRecord";
		}

		// Check if prefix is 08
		if (!record.getNumber().startsWith("08")) {
			bindingResult.rejectValue("number", "error.record",
					"Number must start with 08.");
			return "records/newRecord";
		}

		// gets current date, and formats
		Date current = new Date();
		String savedDisplayCurrent = new SimpleDateFormat("dd/MM/yyyy")
				.format(current);

		// temp variable for DOB
		String tempDOB = new SimpleDateFormat("yyyy-MM-dd").format(current);
		SimpleDateFormat formatCalculation = new SimpleDateFormat("yyyy-MM-dd");

		// calculates users age
		try {
			dob = formatCalculation.parse(tempDOB);
			dateNow = formatCalculation.parse(record.getDob());

			DateTime dob1 = new DateTime(dob);
			DateTime dateNow1 = new DateTime(dateNow);

			// calculates age
			userAge = Math.abs(Days.daysBetween(dob1, dateNow1).getDays());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// userinput < 5840 (16 years)
		if (userAge < over16inDays) {

			bindingResult.rejectValue("dob", "error.record",
					"User must be over 16");
			return "records/newRecord";
		}

		// converts date to correct format
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			Date tempDate = simpleDateFormat.parse(record.getDob());
			SimpleDateFormat outputDateFormat = new SimpleDateFormat(
					"dd/MM/YYYY");
			formatedDOB = outputDateFormat.format(tempDate);
		} catch (ParseException ex) {
			System.out.println("Parse Exception");
		}

		// saves dateCreated(in-case it will be required to be displayed in the future)
		record.setCreated(savedDisplayCurrent);
		// sets formatted DOB
		record.setDob(formatedDOB);
		repository.save(record);

		return "redirect:/records/list";
	}

	// for deleting record entry
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable String id) {
		repository.delete(id);
		return "redirect:/records/list";
	}

}
