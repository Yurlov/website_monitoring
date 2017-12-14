package website.tool.monitoring.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import website.tool.monitoring.domain.Result;
import website.tool.monitoring.domain.Status;
import website.tool.monitoring.domain.Url;
import website.tool.monitoring.service.UrlSchedulerService;
import website.tool.monitoring.service.UrlService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = MainController.class)
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlService urlService;
    @MockBean
    private UrlSchedulerService urlSchedulerService;
    private final SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Before
    public void before(){
        Url url = null;
        try {
            url = new Url("http://bingo.com/monitor", sp.parse("2018-11-22 14:00"),
                    "111/222/333",
                    "200","33","2222","records", new Result(50L,"200","100","records", Status.OK));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        url.setId(1L);
        when(urlService.findAll()).thenReturn(Arrays.asList(url));

    }
    @Test
    public void index() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(model().attribute("urls",hasSize(1)))
                .andExpect(model().attribute("urls",hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("nameUrl",is("http://bingo.com/monitor")),
                                hasProperty("minSize", is("33")),
                                hasProperty("timeToResponseFromServer",is("111/222/333")),
                                hasProperty("maxSize", is("2222")),
                                hasProperty("extra", is("records"))
                        )
                )));
        verify(urlService,times(1)).findAll();
        verifyNoMoreInteractions(urlService);
    }


}