package com.practice.coronavirustracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.practice.coronavirustracker.entity.LocationStats;
import com.practice.coronavirustracker.services.CoronaVirusDataService;

@Controller
public class HomeController {

  @Autowired CoronaVirusDataService coronaVirusDataService;

  @GetMapping("/")
  public String home(Model model) {

    List<LocationStats> stats = coronaVirusDataService.getAllStats();
    int totalCases = stats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
    int totalNewCases = stats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
    model.addAttribute("locationStats", stats);
    model.addAttribute("totalReportedCases", totalCases);
    model.addAttribute("totalNewCases", totalNewCases);
    return "home";
  }
}
