package rmit.rmitsb.service;

import rmit.rmitsb.model.School;
import rmit.rmitsb.model.Student;

import java.util.List;

public interface SchoolService {
    public void saveSchool(School input);


    public List<School> getAllSchool(int pageNo, int pageSize);

    public School getSchool(Long id);

    public School deleteSchool(Long id);

    public void deleteStudent(Long id);

    public List<Student> getAllStudent(Long school_id);

    public boolean addStudent(Long schoolId, Long studentId);
}
