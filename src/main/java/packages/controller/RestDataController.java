package packages.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import packages.model.Staff;
import packages.model.User;
import packages.service.StaffService;
import packages.service.UserService;

@RestController
public class RestDataController {
    private final UserService userService;
    private final StaffService staffService;

    @Autowired
    public RestDataController(UserService userService, StaffService staffService) {
        this.userService = userService;
        this.staffService = staffService;
    }

    @GetMapping(value = "/header/get-username", produces = "application/json")
    public ResponseEntity<String> getUsername(@RequestParam(value = "username") String username) {
        User user = userService.findByUserName(username);
        if (user != null) {
            Staff staff = staffService.findByEmail(user.getEmail());
            if (staff != null) {
                return new ResponseEntity<>(staff.getLastName(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }
}
