package rmit.rmitsb.service;

import rmit.rmitsb.model.Student;

import java.util.List;

public interface StudentService {


    public void saveStudent(Student student);

    public List<Student> getAllStudents(int pageNo, int pageSize);
    public Student getStudent(Long id);

    public void deleteStudent(Long id);


}
