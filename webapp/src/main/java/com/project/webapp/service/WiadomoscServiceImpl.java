package com.project.webapp.service;

import com.project.webapp.model.Student;
import com.project.webapp.model.Wiadomosc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.print.DocFlavor;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class WiadomoscServiceImpl implements WiadomoscService{
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Value("${rest.server.url}")
    private String serverUrl;

    private final static String RESOURCE_PATH = "/api/studenci";

    private RestTemplate restTemplate;

    @Autowired
    public WiadomoscServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Wiadomosc> getWiadomosci(Integer nadStudentId, Integer adStudentId) {
        URI url = ServiceUtil.getUriComponent(serverUrl,getResourcePath()+"/wiadomosci")
                .queryParam("nadStudentId",nadStudentId)
                .queryParam("adStudentId",adStudentId)
                .build()
                .toUri();
        logger.info("REQUEST -> GET {}", url);
        Wiadomosc [] wiadomoscs = restTemplate.getForObject(url, Wiadomosc[].class);
        List<Wiadomosc> wiadomoscList = Arrays.asList(wiadomoscs);
        return wiadomoscList;
    }


    @Override
    public Wiadomosc saveWiadomosc(Wiadomosc wiadomosc,Integer nadStudentId, Integer adStudentId) {
        HttpEntity<Wiadomosc> request = new HttpEntity<>(wiadomosc);
        URI url = ServiceUtil.getUriComponent(serverUrl,getResourcePath()+"/wiadomosci")
                .queryParam("nadStudentId",nadStudentId)
                .queryParam("adStudentId",adStudentId)
                .build()
                .toUri();
        logger.info("REQUEST -> POST {}", url);
        URI location = restTemplate.postForLocation(url, request);
        return null;
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
