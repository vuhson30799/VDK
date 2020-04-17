package packages.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import packages.DTO.UserDTO;
import packages.constant.BusinessConstant;
import packages.model.Staff;
import packages.model.User;
import packages.service.DepartmentService;
import packages.service.StaffService;
import packages.service.UserService;

import javax.validation.Valid;

@Controller
public class AuthenticationController {
    private final UserService userService;
    private final DepartmentService departmentService;
    private final StaffService staffService;

    @Autowired
    public AuthenticationController(UserService userService,
                                    DepartmentService departmentService,
                                    StaffService staffService) {
        this.userService = userService;
        this.departmentService = departmentService;
        this.staffService = staffService;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        UserDTO userDTO = new UserDTO();
        modelAndView.addObject("userDTO", userDTO);
        modelAndView.addObject("departments", departmentService.findAll());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView createNewUser(@Valid UserDTO userDTO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findByUserName(userDTO.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the user name provided");
        }
        for (String email :
                BusinessConstant.ADMIN_EMAILS) {
            if (userDTO.getEmail().equals(email)) {
                userDTO.setRole("ROLE_ADMIN");
            }else {
                userDTO.setRole("ROLE_USER");
            }
        }
        if (!bindingResult.hasErrors()) {
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setEmail(userDTO.getEmail());
            user.setRole(userDTO.getRole());
            Staff staff = new Staff();
            staff.setFirstName(userDTO.getFirstName());
            staff.setLastName(userDTO.getLastName());
            staff.setEmail(userDTO.getEmail());
            staff.setSalaryPerHour(BusinessConstant.SALARY_PER_HOUR); //temporary
            staff.setDepartment(departmentService.findByName(userDTO.getDepartment()));

            staffService.save(staff);
            userService.save(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("userDTO", new UserDTO());
        }
        modelAndView.setViewName("registration");
        return modelAndView;
    }
}
