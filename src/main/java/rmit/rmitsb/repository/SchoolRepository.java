package rmit.rmitsb.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import rmit.rmitsb.model.School;

public interface SchoolRepository extends PagingAndSortingRepository<School, Long> {
}
