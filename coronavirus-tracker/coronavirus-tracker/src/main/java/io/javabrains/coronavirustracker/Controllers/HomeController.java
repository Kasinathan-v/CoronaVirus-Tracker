package io.javabrains.coronavirustracker.Controllers;

import io.javabrains.coronavirustracker.services.coronavirusdataservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    coronavirusdataservice coronaVirusdataservice;
    @GetMapping("/")
    public String Home(Model model)
    {

        model.addAttribute("locationstats",coronaVirusdataservice.getAllist());
        model.addAttribute("sum",coronaVirusdataservice.getSum());
        model.addAttribute("newsum",coronaVirusdataservice.getNewsum());

        return "home";

    }
}
