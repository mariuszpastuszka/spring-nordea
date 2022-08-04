
package com.mpas.cems;

import com.mpas.cems.person.services.PersonAudit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {

    private PersonAudit audit;

    public HomeController(PersonAudit audit) {
        this.audit = audit;
    }

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        StringBuilder sb = new StringBuilder();
        audit.getAuditMessages().forEach((k,v) -> sb.append(k).append(": ").append(v).append("\n"));
        model.addAttribute("logs", sb.toString());
        return "home";
    }
}