package com.bytebeats.mock.controller;

import com.bytebeats.mock.model.User;
import com.bytebeats.mock.service.DemoService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-28 22:51
 */
public class DemoControllerTest {

    @Mock
    private DemoService demoService;

    @InjectMocks
    private DemoController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testRegister() throws Exception {

        String name = "root";
        when(demoService.register(any(User.class))).thenReturn(15L);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.
                post("/demo/register")
                .param("name", name)
                .param("password", "12345")).andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        JsonObject jo = new JsonParser().parse(json).getAsJsonObject();

        Assert.assertEquals(jo.get("name").getAsString(), name);
    }
}
