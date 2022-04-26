package rmit.rmitsb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rmit.rmitsb.model.School;
import rmit.rmitsb.service.SchoolService;

import java.util.List;

@RestController
@CrossOrigin("*")
public class SchoolControl {

    @Autowired
    private SchoolService schoolServices;

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public List<School> getAllSchool(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "2") int pageSize){
        return schoolServices.getAllSchool(pageNo,pageSize);
    }

    @RequestMapping(path = "/school/addStudent", method = RequestMethod.GET)
    public boolean addStudentToSchool(@RequestParam long schoolId, @RequestParam long studentId) {
      return schoolServices.addStudent(schoolId,studentId);
    }

    @RequestMapping(path = "/school", method = RequestMethod.POST)
    public void addStudent(@RequestBody School school){
        schoolServices.saveSchool(school);
    }

    @RequestMapping(path = "/school", method = RequestMethod.PUT)
    public void updateSchool(@RequestBody School school){
        schoolServices.saveSchool(school);
    }

    @RequestMapping(path = "/school/{id}", method = RequestMethod.DELETE)
    public void deleteSchool(@PathVariable(value = "id") Long id){
        schoolServices.deleteSchool(id);
    }

    @RequestMapping(path = "/school/{id}", method = RequestMethod.GET)
    public School getSchool(@PathVariable(value = "id") Long id){
        return schoolServices.getSchool(id);
    }

}
