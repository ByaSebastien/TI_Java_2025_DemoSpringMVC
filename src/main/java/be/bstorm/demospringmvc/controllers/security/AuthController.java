package be.bstorm.demospringmvc.controllers.security;

import be.bstorm.demospringmvc.models.auth.forms.CredentialForm;
import be.bstorm.demospringmvc.services.security.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String login() {

        return "pages/auth/login.html";
    }

    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("user", new CredentialForm());
        return "pages/auth/register.html";
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute CredentialForm user,
            Model model,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "pages/auth/register.html";
        }
        authService.register(user.toUser());
        return "redirect:/login?registered";
    }
}
