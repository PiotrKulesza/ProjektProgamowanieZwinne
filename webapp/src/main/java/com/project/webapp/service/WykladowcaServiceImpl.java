package com.project.webapp.service;

import com.project.webapp.model.Student;
import com.project.webapp.model.Wiadomosc;
import com.project.webapp.model.Wykladowca;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

@Service
public class WykladowcaServiceImpl implements WykladowcaService{

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Value("${rest.server.url}")
    private String serverUrl;

    private final static String RESOURCE_PATH = "/api/wykladowca";

    private RestTemplate restTemplate;

    @Autowired
    public WykladowcaServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Wykladowca> getByLogin(String email, String haslo) {
        URI url = ServiceUtil.getUriComponent(serverUrl,getResourcePath()+"/login")
                .queryParam("email",email)
                .queryParam("haslo",haslo)
                .build()
                .toUri();
        logger.info("REQUEST -> GET {}", url);
        return Optional.ofNullable(restTemplate.getForObject(url, Wykladowca.class));
    }

    private RestResponsePage<Wiadomosc> getPage(URI uri, RestTemplate restTemplate) {
        return ServiceUtil.getPage(uri, restTemplate,
                new ParameterizedTypeReference<RestResponsePage<Wiadomosc>>() {});
    }

    private String getResourcePath() {
        return RESOURCE_PATH;
    }
    private String getResourcePath(Integer id) {
        return RESOURCE_PATH + "/" + id;
    }
    private String getUriStringComponent() {
        return serverUrl + getResourcePath();
    }
    private String getUriStringComponent(Integer id) {
        return serverUrl + getResourcePath(id);
    }
}
