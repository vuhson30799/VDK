package packages.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import packages.model.Department;
import packages.model.Staff;
import packages.service.DepartmentService;
import packages.service.StaffService;

import java.util.List;

@Controller
public class StaffController {
    private final StaffService staffService;
    private final DepartmentService departmentService;

    @Autowired
    public StaffController (StaffService staffService, DepartmentService departmentService) {
        this.staffService = staffService;
        this.departmentService = departmentService;
    }

    @ModelAttribute("departments")
    public List<Department> departments(){return departmentService.findAll();}

    @GetMapping("/staffs")
    public ModelAndView findAll(){
        List<Staff> staff = staffService.findAll();

        return new ModelAndView("staff/list","staffs",staff);
    }

    @PostMapping("/create-staff")
    public String create(@ModelAttribute("staff") Staff staff){
        staffService.save(staff);
        return "redirect:/staffs";
    }

    @PostMapping("/update-staff")
    public String update(@ModelAttribute("staff")Staff staff){
        staffService.save(staff);
        return "redirect:/staffs";
    }

    @PostMapping("/delete-staff")
    public String delete(@ModelAttribute("staff")Staff staff){
        staffService.remove(staff);
        return "redirect:/staffs";
    }

    @GetMapping("view-staff/{id}")
    public String view(@PathVariable("id")Long id,Model model){
        model.addAttribute("staff",staffService.findById(id));
        return "staff/view";
    }
}
