package rmit.rmitsb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rmit.rmitsb.model.School;
import rmit.rmitsb.model.Student;
import rmit.rmitsb.repository.SchoolRepository;
import rmit.rmitsb.repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Transactional
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveSchool(School input) {
        this.schoolRepository.save(input);
    }

    @Override
    public List<School> getAllSchool(int pageNo, int pageSize) {
        Pageable firstPageWithTwoElement = PageRequest.of(pageNo, pageSize);

        Page<School> pagedResult = schoolRepository.findAll(firstPageWithTwoElement);

        //If the page have content

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<School>();
        }
    }

    @Override
    public School getSchool(Long id) {
        Optional<School> schoolOptional = this.schoolRepository.findById(id);
        return schoolOptional.orElse(null);
    }

    @Override
    @Transactional
    public School deleteSchool(Long id) {
        Optional<School> optionalSchool = schoolRepository.findById(id);
        if (optionalSchool.isPresent()) {
            School tmp = optionalSchool.get();
            if (!tmp.getStudents().isEmpty()) {
                String hsql = "update Student s set s.school = null where s.school=: school";
                Query query = entityManager.createQuery(hsql);
                query.setParameter("school", tmp);
                entityManager.flush();
                query.executeUpdate();
                entityManager.clear();
            }
            schoolRepository.delete(tmp);
            return tmp;
        }
        return null;
    }

    @Override
    public void deleteStudent(Long id) {

    }

    @Override
    public List<Student> getAllStudent(Long school_id) {
        Optional<School> schoolOptional = schoolRepository.findById(school_id);

        return schoolOptional.map(School::getStudents).orElse(null);
    }

    @Override
    @Transactional
    public boolean addStudent(Long schoolId, Long studentId) {
        Optional<School> schoolOptional = schoolRepository.findById(schoolId);
//        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Query query;
        if (schoolOptional.isPresent()) {
            String hsql = "update Student s set s.school=:school where s.id=:sId";
            query = entityManager.createQuery(hsql);
            query.setParameter("school", schoolOptional.get());
            query.setParameter("sId", studentId);
            entityManager.flush();
            query.executeUpdate();
            entityManager.clear();
            return true;
        }
        return false;
    }
}
