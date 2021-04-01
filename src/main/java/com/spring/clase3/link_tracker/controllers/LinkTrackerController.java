package com.spring.clase3.link_tracker.controllers;

import com.spring.clase3.link_tracker.dtos.LinkDto;
import com.spring.clase3.link_tracker.exceptions.InvalidIdException;
import com.spring.clase3.link_tracker.exceptions.InvalidUrlException;
import com.spring.clase3.link_tracker.services.LinkTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class LinkTrackerController {

    @Autowired
    private LinkTrackerService service;

    @PostMapping("/tracker")
    public ResponseEntity<LinkDto> generate(@RequestBody String url) throws InvalidUrlException {
        return new ResponseEntity<>(service.generateLink(url), HttpStatus.OK);
    }

    @GetMapping("/redirect/{id}")
    public RedirectView redirect(@PathVariable Long id) throws InvalidIdException {
        return new RedirectView(service.searchLink(id));
    }

    @GetMapping("/statistics/{id}")
    public ResponseEntity<String> statistics(@PathVariable Long id) throws InvalidIdException {
        return new ResponseEntity<>(
                "The number of redirections is: " + service.getStatistics(id),
                HttpStatus.OK);
    }

    @PostMapping("/invalidate")
    public void invalidate(@RequestBody Long id) throws InvalidIdException {
        service.invalidateUrl(id);
    }

}
