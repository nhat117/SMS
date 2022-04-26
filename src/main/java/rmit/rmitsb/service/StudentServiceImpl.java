package rmit.rmitsb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rmit.rmitsb.model.Student;
import rmit.rmitsb.repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void saveStudent(Student student){
        this.studentRepository.save(student);
    }

    public List<Student> getAllStudents(int pageNo, int pageSize){
        Pageable firstPageWithTwoElement = PageRequest.of(pageNo,pageSize);

        Page<Student> pagedResult = studentRepository.findAll(firstPageWithTwoElement);

        //If the page have content

        if(pagedResult.hasContent()){
            return pagedResult.getContent();
        } else {
            return new ArrayList<Student>();
        }
    }

    public Student getStudent(Long id){
        Student student = null;
        try {
            student = this.studentRepository.findById(id)
                    .orElseThrow(() -> new Exception("Student not found for this id :: " + id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public void deleteStudent(Long id){

        Student student = getStudent(id);
        this.studentRepository.delete(student);
    }

}
