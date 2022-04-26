package rmit.rmitsb.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="school")
@NoArgsConstructor
@Data
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="school_id")
    private long id;

    @Column(name="name")
    private String name;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, mappedBy = "school")
    @JsonManagedReference
    private List<Student> students;

    //Helper Methode
    public void addStudent(Student student) {
        if(students == null) {
            this.students =  new ArrayList<>();
        }
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
        student.setSchool(null);
    }
}
