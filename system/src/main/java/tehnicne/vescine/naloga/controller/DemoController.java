package tehnicne.vescine.naloga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tehnicne.vescine.naloga.entity.User;
import tehnicne.vescine.naloga.service.UserService;

import java.util.List;

@Controller
public class DemoController {
    private UserService userService;

    @Autowired
    public DemoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showHome(Model theModel) {
        List<User> users = userService.findAll();
        theModel.addAttribute("users", users);
        return "home";
    }

    // add a request mapping for /leaders

    @GetMapping("/leaders")
    public String showLeaders() {

        return "leaders";
    }

    // add request mapping for /systems

    @GetMapping("/systems")
    public String showSystems() {

        return "systems";
    }
}
