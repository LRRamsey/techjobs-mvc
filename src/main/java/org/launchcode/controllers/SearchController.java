package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.FormSubmitEvent;
import java.util.ArrayList;
import java.util.HashMap;

import static javax.swing.text.html.FormSubmitEvent.MethodType.POST;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {


        /*model.addAttribute("title", "Jobs With" + ListController.columnChoices.get(searchTerm) + "Values");
        model.addAttribute("jobs", jobs);*/

        if (searchType.equals("all") || searchTerm.equals("")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
            model.addAttribute("title", "All Jobs");
            model.addAttribute("jobs", jobs);
            return "search";
        } else {
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "All " + ListController.columnChoices.get(searchType) + " Values");
            model.addAttribute("searchTerm", searchTerm);
            model.addAttribute("jobs", jobs);
            return "search";
        }

    }

}
