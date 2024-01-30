package ra.crudheroku.student.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.crudheroku.model.dto.request.StudentRequestDto;
import ra.crudheroku.model.entity.Student;
import ra.crudheroku.student.UploadService;
import ra.crudheroku.student.repository.StudentRepository;

import java.util.List;


@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy id"));
    }

    @Override
    public List<Student> findByName(String name) {
        return findByName(name);
    }

    @Override
    public void save(StudentRequestDto student) {
        String imageUrl = studentRepository.findById(student.getStudentId()).orElse(new Student()).getImageUrl();
        if (student.getFile().getSize()!=0){
            imageUrl= uploadService.uploadFileToServer(student.getFile());
        }
        // chuyển đổi tù dto -> model
        Student newStudent = modelMapper.map(student,Student.class);
        newStudent.setImageUrl(imageUrl);
        studentRepository.save(newStudent);
    }

    @Override
    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }



}
