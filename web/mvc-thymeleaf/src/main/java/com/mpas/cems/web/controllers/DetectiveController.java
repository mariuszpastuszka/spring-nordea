
package com.mpas.cems.web.controllers;

import com.mpas.cems.dao.Detective;
import com.mpas.cems.dj.services.DetectiveService;
import com.mpas.cems.dj.services.wrappers.DetectiveWrapper;
import com.mpas.cems.web.problem.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static com.mpas.cems.util.Functions.COMPARATOR_BY_ID;


@Controller
@RequestMapping("/detectives")
public class DetectiveController {

    private Logger logger = LoggerFactory.getLogger(DetectiveController.class);

    private DetectiveService detectiveService;

    public DetectiveController(DetectiveService detectiveService) {
        this.detectiveService = detectiveService;
    }

    /**
     * Handles requests to list all detectives.
     */
    @GetMapping(value = "/list")
    public String list(Model model) {
        logger.info("Populating model with list...");
        List<Detective> detectives =  detectiveService.findAll();
        detectives.sort(COMPARATOR_BY_ID);

        model.addAttribute("detectives", detectives);
        return "detectives/list";
    }

    /**
     * Handles requests to show detail about one detective.
     */
    @RequestMapping(value = "/{id:[\\d]*}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model) throws NotFoundException {
        DetectiveWrapper detective = detectiveService.findById(id);
        if(!detective.isEmpty()) {
            model.addAttribute("detective", detective);
        } else {
            throw new NotFoundException(Detective.class, id);
        }
        return "detectives/show";
    }
}
