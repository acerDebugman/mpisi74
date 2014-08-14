package rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.IMP1001Service;
import entity.MP1001;

@Controller
@RequestMapping(value="/employees")
public class EmployeeController {
	@Autowired
	IMP1001Service serviceMP1001;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<MP1001> findAll(){
		return serviceMP1001.findAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public MP1001 findById(@PathVariable("id") String empCode){
		return serviceMP1001.findById(empCode);
	}
}
