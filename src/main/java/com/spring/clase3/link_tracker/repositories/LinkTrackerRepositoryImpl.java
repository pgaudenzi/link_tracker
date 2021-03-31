package com.spring.clase3.link_tracker.repositories;

import com.spring.clase3.link_tracker.dtos.LinkDto;
import com.spring.clase3.link_tracker.exceptions.InvalidIdException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LinkTrackerRepositoryImpl implements LinkTrackerRepository {

    private final Map<Long, LinkDto> links = new HashMap<>();

    @Override
    public void saveLink(Long id, LinkDto link) {
       links.put(id, link);
    }

    @Override
    public LinkDto findLinkById(Long id) throws InvalidIdException {
        if (links.containsKey(id)) {
            return links.get(id);
        } else {
            throw new InvalidIdException("Invalid id: " + id);
        }
    }
}
