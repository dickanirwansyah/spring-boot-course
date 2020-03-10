package com.dicka.springcoronatracking.controllers;

import com.dicka.springcoronatracking.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private CoronaVirusDataService coronaVirusDataService;

    @RequestMapping(value = "/")
    public String home(Model model){
        String titlePage = "Welcome";

        int calcTotalReportCases = coronaVirusDataService.getAllStats()
                .stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();

        int calcTotalNewCases = coronaVirusDataService.getAllStats()
                .stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();

        model.addAttribute("calcTotalReportCases", calcTotalReportCases);
        model.addAttribute("calcTotalNewCases", calcTotalNewCases);

        model.addAttribute("titlePage", titlePage);
        model.addAttribute("locationStates", coronaVirusDataService.getAllStats());
        return "home";
    }
}
