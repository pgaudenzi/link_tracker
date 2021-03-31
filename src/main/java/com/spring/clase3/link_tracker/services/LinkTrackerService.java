package com.spring.clase3.link_tracker.services;

import com.spring.clase3.link_tracker.dtos.LinkDto;
import com.spring.clase3.link_tracker.exceptions.InvalidIdException;
import com.spring.clase3.link_tracker.exceptions.InvalidUrlException;

public interface LinkTrackerService {

    LinkDto generateLink(String url) throws InvalidUrlException;

    String searchLink(Long id) throws InvalidIdException;
}
