package ra.crudheroku.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ra.crudheroku.model.dto.request.StudentRequestDto;
import ra.crudheroku.model.entity.Student;
import ra.crudheroku.student.service.IStudentService;


@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class AdminController {
    private final IStudentService studentService;
    @RequestMapping
    public String student(Model model){
        model.addAttribute("students",studentService.findAll());
      return "student/student";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String doAddStudent(@ModelAttribute StudentRequestDto studentRequestDto){
        studentService.save(studentRequestDto);
        return "redirect:/students";
    }
    @RequestMapping("/edit")
    public String edit(@RequestParam Integer id,Model model){
        Student studentEdit   = studentService.findById(id);
        model.addAttribute("student",studentEdit);
        return "student/student-edit";
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update( @ModelAttribute StudentRequestDto studentRequestDto){
        studentService.save(studentRequestDto);
        return "redirect:/students";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Integer id){
        studentService.deleteById(id);
        return "redirect:/students";
    }
    @RequestMapping("/search")
    public String delete(@RequestParam String search , Model model){
        model.addAttribute("students",studentService.findByName(search));
        model.addAttribute("search",search);
        return "student/student";
    }


}
