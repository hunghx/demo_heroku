package ra.crudheroku.student.service;

import ra.crudheroku.model.dto.request.StudentRequestDto;
import ra.crudheroku.model.entity.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    Student findById(Integer id);
    List<Student> findByName(String name);
    void save(StudentRequestDto student);
    void deleteById(Integer id);
}
