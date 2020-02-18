package com.javaegitimleri.petclinic;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by EMINCAKICI on Feb Tue 18,2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("dev")
@AutoConfigureMockMvc
@WithMockUser(username = "user", password = "secret", authorities = "USER")
public class OwnersWebMvc {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testOwners() throws Exception {
        //owners uri na erisim saglayacak requestBuilder objesi tanimladik.
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners");
        ResultActions resultActions = mockMvc.perform(requestBuilder);
        MvcResult mvcResult = resultActions.andReturn();
        // "/ownsers" a map edilmis controller metodunun dondugu ModelAndView nesnesi olusuturuyor
        ModelAndView maw = mvcResult.getModelAndView();
        MatcherAssert.assertThat(maw.getViewName(), Matchers.equalTo("owners"));
        MatcherAssert.assertThat(maw.getModel().containsKey("owners"), Matchers.is(true));
    }
}
