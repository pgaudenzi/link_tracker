package com.spring.clase3.link_tracker.services;

import com.spring.clase3.link_tracker.dtos.LinkDto;
import com.spring.clase3.link_tracker.exceptions.InvalidIdException;
import com.spring.clase3.link_tracker.exceptions.InvalidUrlException;
import com.spring.clase3.link_tracker.repositories.LinkTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LinkTrackerServiceImpl implements LinkTrackerService {

    private final AtomicLong id = new AtomicLong();

    @Autowired
    private LinkTrackerRepository repository;

    @Override
    public LinkDto generateLink(String url) throws InvalidUrlException {
        if (validateUrl(url)) {
            LinkDto link = new LinkDto(id.incrementAndGet(), url, 0);
            repository.saveLink(link.getId(), link);
            return link;
        } else {
            throw new InvalidUrlException("Invalid URL: " + url);
        }
    }

    @Override
    public String searchLink(Long id) throws InvalidIdException {
        LinkDto link = repository.findLinkById(id);
        link.setStatistics(link.getStatistics() + 1);
        return link.getUrl();
    }

    @Override
    public Integer getStatistics(Long id) throws InvalidIdException {
        return repository.findLinkById(id).getStatistics();
    }

    @Override
    public void invalidateUrl(Long id) throws InvalidIdException {
        repository.deleteLinkById(id);
    }

    private boolean validateUrl(String url) {
        final String urlRegex =
                "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))" +
                        "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)" +
                        "([).!';/?:,][[:blank:]])?$";

        final Pattern urlPattern = Pattern.compile(urlRegex);
        if (url == null) {
            return false;
        }
        Matcher matcher = urlPattern.matcher(url);
        return matcher.matches();

    }
}
