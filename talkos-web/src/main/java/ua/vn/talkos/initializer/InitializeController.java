package ua.vn.talkos.initializer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author oleg.sukhov
 */
@Controller
public class InitializeController {
    @RequestMapping("/")
    public ModelAndView initialize() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
