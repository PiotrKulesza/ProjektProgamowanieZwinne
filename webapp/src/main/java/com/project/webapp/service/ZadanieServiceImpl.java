package com.project.webapp.service;

import com.project.webapp.model.Zadanie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

@Service
public class ZadanieServiceImpl implements ZadanieService{
    private static final Logger logger = LoggerFactory.getLogger(ZadanieServiceImpl.class);

    @Value("${rest.server.url}")
    private String serverUrl;

    private final static String RESOURCE_PATH = "/api/zadania";

    private RestTemplate restTemplate;

    @Autowired
    public ZadanieServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public Optional<Zadanie> getZadanie(Integer zadanieId) {
        URI url = ServiceUtil.getUriComponent(serverUrl, getResourcePath(zadanieId))
                .build()
                .toUri();
        logger.info("REQUEST -> GET {}", url);
        return Optional.ofNullable(restTemplate.getForObject(url, Zadanie.class));
    }



    @Override
    public Page<Zadanie> getZadanieProjektu(Integer projektId, Pageable pageable) {
        URI url = ServiceUtil.getUriComponent(serverUrl, getResourcePath(),pageable)
                .queryParam("projektId",projektId)
                .build()
                .toUri();
        logger.info("REQUEST -> GET {}", url);
        return getPage(url, restTemplate);
    }

    @Override
    public Zadanie setZadanie(Zadanie zadanie, Integer projektId) {
        if(zadanie.getZadanieId() != null){
            String url = getUriStringComponent(zadanie.getZadanieId())+"?projektId="+projektId;
            logger.info("REQUEST -> PUT {}", url);
            restTemplate.put(url,zadanie);
            return zadanie;
        }else{
            HttpEntity<Zadanie> request = new HttpEntity<>(zadanie);
            String url = getUriStringComponent()+"?projektId="+projektId;
            String url2 = getUriStringComponent();
            logger.info("REQUEST -> POST {}", url);
            URI location = restTemplate.postForLocation(url, request);
            logger.info("REQUEST (location) -> GET {}", location);
            return restTemplate.getForObject(location,Zadanie.class);
        }
    }

    @Override
    public void deleteZadanie(Integer zadanieId, Integer projektId) {
        URI url = ServiceUtil.getUriComponent(serverUrl, getResourcePath(zadanieId))
                .queryParam("projektId",projektId)
                .build()
                .toUri();
        logger.info("REQUEST -> DELETE {}", url);
        restTemplate.delete(url);

    }

    private Page<Zadanie> getPage(URI uri, RestTemplate restTemplate) {
        return ServiceUtil.getPage(uri, restTemplate,
                new ParameterizedTypeReference<RestResponsePage<Zadanie>>() {});
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
