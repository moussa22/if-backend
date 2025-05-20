package ifsa.solution.ifsa_backend.controller;

import ifsa.solution.ifsa_backend.entites.Client;
import ifsa.solution.ifsa_backend.entites.ClientDto;
import ifsa.solution.ifsa_backend.service.ClientService;
import ifsa.solution.ifsa_backend.service.SentimentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ClientController.class)
class ClientControllerTest {
    String URL="localhost:8080/api/client";
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ClientService clientService;
    @MockitoBean
    SentimentService sentimentService;
    @Test
    void shouldcreateClient() throws Exception {
        Client clientOne=new Client(1,"testcontroleOne@yahoo.com","5148587896");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(clientOne)))
                        .andExpect(status().isCreated())
                        .andExpect(content().string("User Ramesh created successfully!"));
    }

    @Test
    void shouldReturnListOfClient() throws Exception {

        ClientDto clientOne=new ClientDto(1,"testcontroleOne@yahoo.com","5148587896");

        ClientDto clientTwo=new ClientDto(2,"testcontroleTwo@yahoo.com","5148587800");

        Mockito.when(this.clientService.rechercher()).thenReturn(List.of(clientOne,clientTwo));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/client"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("testcontroleOne@yahoo")));
    }

    @Test
    void shouldReturnOneClient() throws Exception {

        ClientDto clientOne=new ClientDto(1,"testcontroleOne@yahoo.com","5148587896");



        Mockito.when(this.clientService.lire(anyInt())).thenReturn(clientOne);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/client/1"))
                .andExpect(jsonPath("$.email").value(clientOne.email()))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void shoulddmodifierClient() throws Exception {

        ClientDto clientOne=new ClientDto(1,"testcontroleOne@yahoo.com","5148587896");



        Mockito.when(this.clientService.lire(anyInt())).thenReturn(clientOne);

        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/client/1"))
                .andExpect(jsonPath("$.email").value(clientOne.email()))
                .andExpect(status().isOk())
                .andDo(print());
    }
}