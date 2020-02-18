package com.javaegitimleri.petclinic.web;

import com.javaegitimleri.petclinic.model.Owner;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by EMINCAKICI on Feb Sun 09,2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class PetClinicRestControllerTests {
    //private RestTemplate restTemplate;
    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = restTemplate.withBasicAuth("user2","secret");
        //restTemplate = new RestTemplate();
        //BasicAuthorizationInterceptor basicAuthorizationInterceptor = new BasicAuthorizationInterceptor("user", "secret");
        //restTemplate.setInterceptors(Arrays.asList(basicAuthorizationInterceptor));
    }

    @Test
    public void testDeleteOwner() {
        //restTemplate.delete("http://localhost:8080/rest/owner/1");
        ResponseEntity<Void> responseEntity = restTemplate.exchange("http://localhost:8080/rest/owner/1", HttpMethod.DELETE, null, Void.class);
        try {
            restTemplate.getForEntity("http://localhost:8080/rest/owner/1", Owner.class);
            Assert.fail("have not returned owner");
        } catch (HttpClientErrorException e) {
            MatcherAssert.assertThat(e.getStatusCode().value(), Matchers.equalTo(404));
        }

    }

    @Test
    public void testUpdateOwner() {
        Owner owner = restTemplate.getForObject("http://localhost:8080/rest/owner/2", Owner.class);
        MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Kenan"));
        owner.setFirstName("Salim Güray");
        restTemplate.put("http://localhost:8080/rest/owner/2", owner);
        owner = restTemplate.getForObject("http://localhost:8080/rest/owner/2", Owner.class);
        MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Salim Güray"));
    }

    @Test
    public void testCreateOwner() {
        Owner owner = new Owner();
        owner.setFirstName("Metehan");
        owner.setLastName("Yücel");

        URI location = restTemplate.postForLocation("http://localhost:8080/rest/owner", owner);
        Owner owner1 = restTemplate.getForObject(location, Owner.class);
        MatcherAssert.assertThat(owner1.getFirstName(), Matchers.equalTo(owner.getFirstName()));
        MatcherAssert.assertThat(owner1.getLastName(), Matchers.equalTo(owner.getLastName()));
    }

    @Test
    public void testGetOwnerById() {
        ResponseEntity<Owner> responseEntity = restTemplate.getForEntity("http://localhost:8080/rest/owner/1", Owner.class);
        MatcherAssert.assertThat(responseEntity.getStatusCodeValue(), Matchers.equalTo(200));
        //MatcherAssert.assertThat(responseEntity.getBody().getFirstName(), Matchers.equalTo("Emin"));
    }

    @Test
    public void testGetOwnersByLastName() {
        ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/owner?ln=Cakici", List.class);
        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
        List<Map<String, String>> body = response.getBody();
        List<String> firstNames = body.stream().map(e -> e.get("firstName")).collect(Collectors.toList());

        MatcherAssert.assertThat(firstNames, Matchers.containsInAnyOrder("Emin"));
    }

    @Test
    public void testGetOwners() {
        ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:4444/rest/owners", List.class);
        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
        List<Map<String, String>> body = response.getBody();
        List<String> firstNames = body.stream().map(e -> e.get("firstName")).collect(Collectors.toList());

        MatcherAssert.assertThat(firstNames, Matchers.containsInAnyOrder("Emin", "Kenan"));

    }

}
