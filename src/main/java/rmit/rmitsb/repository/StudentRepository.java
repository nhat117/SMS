package rmit.rmitsb.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import rmit.rmitsb.model.Student;


//To use pagination
//First you have to create an interface
//extends Paging and Sorting Repository
//In the implement getall methode, pass the methode pagable
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
}
