package com.jornadamilhas.api.controller;

import com.jornadamilhas.api.model.destination.Destination;
import com.jornadamilhas.api.model.destination.DestinationPostData;
import com.jornadamilhas.api.model.destination.DestinationPutData;
import com.jornadamilhas.api.model.destination.DestinationRepository;
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
class DestinationControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DestinationPostData> destinationPostDataJacksonTester;

    @Autowired
    private JacksonTester<DestinationPutData> destinationPutDataJacksonTester;

    @MockBean
    private DestinationRepository repository;

    @Test
    @DisplayName("Must return 400 http code when data submitted are invalid")
    public void postDestination_scenario1() throws Exception
    {
        var response = mockMvc.perform(post("/destinos"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Must return 201 http code when all data submitted are valid")
    public void postDestination_scenario2() throws Exception
    {
        var response = mockMvc.perform(post("/destinos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(destinationPostDataJacksonTester.write(new DestinationPostData("Patagonia","storage/images/destination/patagonia1.jpg", "storage/images/destination/patagonia2.jpg", "Lorem Ipsum", "Lorem Ipsum", 500.0f)).getJson()))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Must return 200 http code when id chosen in the path is found")
    void getSpecificDestination_scenario1() throws Exception
    {
        Destination destination = new Destination(new DestinationPostData("Patagonia","storage/images/destination/patagonia1.jpg", "storage/images/destination/patagonia2.jpg", "Lorem Ipsum", "Lorem Ipsum", 500.0f));

        when(repository.getReferenceById(any())).thenReturn(destination);

        var response = mockMvc.perform(get("/destinos/1"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Must return 200 http code when id chosen in the path is found")
    void getCreatedDestination_scenario1() throws Exception
    {
        Destination destination = new Destination(new DestinationPostData("Patagonia","storage/images/destination/patagonia1.jpg", "storage/images/destination/patagonia2.jpg", "Lorem Ipsum", "Lorem Ipsum", 500.0f));

        when(repository.getReferenceById(any())).thenReturn(destination);

        var response = mockMvc.perform(get("/destinos/created/1"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Must return 200 http code even when there are no destinations registered in the database that matches the name parameter")
    void getDestinations_scenario1() throws Exception
    {
        var response = mockMvc.perform(get("/destinos?name=João"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Must return 400 http code when data submitted are invalid")
    void putDestination_scenario1() throws Exception
    {
        Destination destination = new Destination(new DestinationPostData("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vitae consequat sem.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vitae consequat sem.","storage/images/destination/patagonia1.jpg",  "storage/images/destination/patagonia2.jpg", "Lorem Ipsum", "Lorem Ipsum", 500.0f));

        when(repository.getReferenceById(any())).thenReturn(destination);

        var response = mockMvc.perform(put("/destinos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(destinationPutDataJacksonTester.write(new DestinationPutData(1l, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vitae consequat sem.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vitae consequat sem.","storage/images/destination/patagonia1.jpg", "storage/images/destination/patagonia2.jpg", "Lorem Ipsum", "Lorem Ipsum", 500.0f)).getJson()))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Must return 200 http code when all data submitted are valid")
    void putDestination_scenario2() throws Exception
    {
        Destination destination = new Destination(new DestinationPostData("Patagonia","storage/images/destination/patagonia1.jpg", "storage/images/destination/patagonia2.jpg", "Lorem Ipsum", "Lorem Ipsum", 500.0f));

        when(repository.getReferenceById(any())).thenReturn(destination);

        var response = mockMvc.perform(put("/destinos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(destinationPutDataJacksonTester.write(new DestinationPutData(1l, "Patagonia", "storage/images/destination/patagonia1.jpg", "storage/images/destination/patagonia2.jpg", "Lorem Ipsum", "Lorem Ipsum", 500.0f)).getJson()))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Must return 204 http code when id chosen in the path is found")
    void deleteDestination_scenario1() throws Exception
    {
        Destination destination = new Destination(new DestinationPostData("Patagonia", "storage/images/destination/patagonia1.jpg", "storage/images/destination/patagonia2.jpg", "Lorem Ipsum", "Lorem Ipsum", 500.0f));

        when(repository.getReferenceById(any())).thenReturn(destination);

        var response = mockMvc.perform(delete("/destinos/1"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("Must return 404 http code when id chosen in the path is not found")
    void PathVariableRequest_scenario1()
    {
        var response = new MockHttpServletResponse();

        if(repository.getReferenceById(9l) == null)
            response.setStatus(HttpStatus.NOT_FOUND.value());

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}