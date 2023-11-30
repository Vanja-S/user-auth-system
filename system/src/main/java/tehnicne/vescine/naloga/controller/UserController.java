package tehnicne.vescine.naloga.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tehnicne.vescine.naloga.entity.User;
import tehnicne.vescine.naloga.exception.UserNotFoundException;
import tehnicne.vescine.naloga.exception.UsernameAlreadyInUseException;
import tehnicne.vescine.naloga.service.UserService;
import tehnicne.vescine.naloga.user.WebUser;

import java.util.logging.Logger;

/**
 * NEED TO UPGRADE TO USE NEW AUTH SYSTEM
 */
@Controller
@RequestMapping("/users")
public class UserController {
    private Logger logger = Logger.getLogger(getClass().getName());
    private UserService userService;

    @Autowired
    public UserController(UserService theUserService) {
        userService = theUserService;
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        theModel.addAttribute("user", new WebUser());
        logger.info("Processing request to /users/showFormForAdd for new user creation.");
        return "users/user-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(
            @RequestParam("username") String username,
            Model theModel) {
        User user;
        logger.info("Processing request to /users/showFormForUpdate for user: " + username);
        try {
            user = userService.findByUserName(username);
        } catch (UserNotFoundException e) {
            logger.warning("User not found while processing update form: " + username);
            return "users/user-not-found";
        }
        theModel.addAttribute("user", user.toWebUser());
        theModel.addAttribute("usernameField", user.getUserName());
        return "users/user-form";
    }

    @PostMapping("/save")
    public String saveUser(
            @RequestParam("username") String username,
            @Valid @ModelAttribute("member") WebUser webUser,
            BindingResult theBindingResult,
            Model theModel) throws UserNotFoundException {
        logger.info("Processing request to /users/save for user: " + webUser.getUserName());
        try {
            if (webUser.getPassword() == null || webUser.getPassword().isBlank() || webUser.getPassword().isEmpty()) {
                userService.update(webUser, username);
            } else {
                userService.save(webUser);
            }
        } catch (UsernameAlreadyInUseException e) {
            theModel.addAttribute("user", webUser);
            theModel.addAttribute("usernameField", username);
            theModel.addAttribute("mergeError", "User name already exists.");
            return "users/user-form";
        } catch (Exception e) {
            return "users/action-failure";
        }
        theModel.addAttribute("webUser", webUser);
        return "users/action-confirmation";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("username") String username) throws UserNotFoundException {
        userService.deleteByUserName(username);
        return "redirect:/";
    }
}
