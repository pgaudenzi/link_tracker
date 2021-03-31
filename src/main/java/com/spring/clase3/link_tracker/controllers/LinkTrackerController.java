package com.spring.clase3.link_tracker.controllers;

import com.spring.clase3.link_tracker.dtos.LinkDto;
import com.spring.clase3.link_tracker.exceptions.InvalidIdException;
import com.spring.clase3.link_tracker.exceptions.InvalidUrlException;
import com.spring.clase3.link_tracker.services.LinkTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class LinkTrackerController {

    @Autowired
    private LinkTrackerService service;

    @PostMapping("/tracker")
    public LinkDto generate(@RequestBody String url) throws InvalidUrlException {
        return service.generateLink(url);
    }

    @GetMapping("/redirect/{id}")
    public RedirectView redirect(@PathVariable Long id)
            throws InvalidIdException {
        return new RedirectView(service.searchLink(id));
    }

}
