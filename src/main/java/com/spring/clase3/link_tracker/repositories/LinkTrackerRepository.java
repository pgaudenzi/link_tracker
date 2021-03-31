package com.spring.clase3.link_tracker.repositories;

import com.spring.clase3.link_tracker.dtos.LinkDto;
import com.spring.clase3.link_tracker.exceptions.InvalidIdException;

public interface LinkTrackerRepository {

    void saveLink(Long id, LinkDto link);
    LinkDto findLinkById(Long id) throws InvalidIdException;

}
