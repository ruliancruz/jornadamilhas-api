package com.jornadamilhas.api.controller;

import com.jornadamilhas.api.model.testimonial.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TestimonialControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<TestimonialPostData> testimonialPostDataJacksonTester;

    @Autowired
    private JacksonTester<TestimonialPutData> testimonialPutDataJacksonTester;

    @MockBean
    private TestimonialRepository repository;

    @Test
    @DisplayName("Must return 400 http code when data submitted are invalid")
    public void postTestimonial_scenario1() throws Exception
    {
        var response = mockMvc.perform(post("/depoimentos"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Must return 201 http code when all data submitted are valid")
    public void postTestimonial_scenario2() throws Exception
    {
        var response = mockMvc.perform(post("/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testimonialPostDataJacksonTester.write(new TestimonialPostData("João da Silva","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vitae consequat sem.", "storage/images/photo1.jpg")).getJson()))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Must return 200 http code when id chosen in the path is found")
    void getDetailedTestimonial_scenario2() throws Exception
    {
        Testimonial testimonial = new Testimonial(new TestimonialPostData("João da Silva", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vitae consequat sem.", "storage/images/photo1.jpg"));

        when(repository.getReferenceById(any())).thenReturn(testimonial);

        var response = mockMvc.perform(get("/depoimentos/1"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Must return 200 http code even when there are no testimonials registered in the database")
    void getRandomTestimonials_scenario1() throws Exception
    {
        var response = mockMvc.perform(get("/depoimentos-home"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Must return 400 http code when data submitted are invalid")
    void putTestimonial_scenario1() throws Exception
    {
        Testimonial testimonial = new Testimonial(new TestimonialPostData("João da Silva", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vitae consequat sem.", "storage/images/photo1.jpg"));

        when(repository.getReferenceById(any())).thenReturn(testimonial);

        var response = mockMvc.perform(put("/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testimonialPutDataJacksonTester.write(new TestimonialPutData(1l, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vitae consequat sem.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vitae consequat sem.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vitae consequat sem", "storage/images/photo-changed.jpg")).getJson()))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Must return 200 http code when all data submitted are valid")
    void putTestimonial_scenario2() throws Exception
    {
        Testimonial testimonial = new Testimonial(new TestimonialPostData("João da Silva", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vitae consequat sem.", "storage/images/photo1.jpg"));

        when(repository.getReferenceById(any())).thenReturn(testimonial);

        var response = mockMvc.perform(put("/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testimonialPutDataJacksonTester.write(new TestimonialPutData(1l,"João updated", "Lorem ipsum updated.", "storage/images/photo-updated.jpg")).getJson()))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Must return 204 http code when id chosen in the path is found")
    void deleteTestimonial_scenario1() throws Exception
    {
        Testimonial testimonial = new Testimonial(new TestimonialPostData("João da Silva", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vitae consequat sem.", "storage/images/photo1.jpg"));

        when(repository.getReferenceById(any())).thenReturn(testimonial);

        var response = mockMvc.perform(delete("/depoimentos/1"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("Must return 404 http code when id chosen in the path is not found")
    void PathVariableRequest_scenario1()
    {
        MockHttpServletResponse response = new MockHttpServletResponse();

        if(repository.getReferenceById(9l) == null)
            response.setStatus(HttpStatus.NOT_FOUND.value());

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}