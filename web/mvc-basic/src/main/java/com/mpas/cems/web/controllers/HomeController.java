
package com.mpas.cems.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("message", "Spring MVC JSP Example!!");
        return "home";
    }

    @GetMapping("/building/{buildingId}")
    public String matrix(@PathVariable String buildingId, @MatrixVariable int g, @MatrixVariable int u, Model model){
        List<String> dataList = new ArrayList<>();
        dataList.add("building number: ".concat(buildingId));
        dataList.add("ground floor flat number: "+ g);
        dataList.add("uppler floor flat number: " + u);
        model.addAttribute("dataList", dataList);
        return "sandbox";
    }
}