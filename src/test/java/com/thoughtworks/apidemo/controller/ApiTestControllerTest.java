package com.thoughtworks.apidemo.controller;

import com.thoughtworks.apidemo.dto.AddResponse;
import com.thoughtworks.apidemo.service.ApiTestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTestControllerTest {

    public static final String GET_ADD_RESULT_API = "/api/increase";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @MockBean
    private ApiTestService apiTestService;

    @Before
    public void setUp() {
        this.mockMvc =
                MockMvcBuilders.
                        webAppContextSetup(this.wac).
                        build();
        when(apiTestService.getCheckNumberResult(any())).thenReturn(new AddResponse(1, "data"));
    }

    @Test
    public void should_throw_bad_request_when_request_param_cannot_convert_to_number() throws Exception {
        mockMvc.perform(get(GET_ADD_RESULT_API)
                .param("number", "asfewf"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_get_response_when_request_param_is_number() throws Exception {
        mockMvc.perform(get(GET_ADD_RESULT_API)
                .param("number", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode", is(1)))
                .andExpect(jsonPath("$.data", is("data")));
    }


    @Test
    public void should_get_response_when_request_param_can_be_formatted_to_number() throws Exception {
        mockMvc.perform(get(GET_ADD_RESULT_API)
                .param("number", "   0   "))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode", is(1)))
                .andExpect(jsonPath("$.data", is("data")));
    }
}