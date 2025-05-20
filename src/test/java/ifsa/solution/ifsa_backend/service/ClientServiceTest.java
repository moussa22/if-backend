package ifsa.solution.ifsa_backend.service;

import ifsa.solution.ifsa_backend.IfsaBackendApplication;
import ifsa.solution.ifsa_backend.entites.Client;
import ifsa.solution.ifsa_backend.entites.ClientDto;
import ifsa.solution.ifsa_backend.repository.ClientRepository;
import org.hibernate.mapping.Array;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
//@SpringBootTest(classes = IfsaBackendApplication.class)
@DataJpaTest
@ActiveProfiles(value = "test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ClientServiceTest {

  @Autowired
    ClientRepository clientRepository;

  //@InjectMocks
    ClientService clientService;

    @Test
    void shouldCreateClient() {

       // clientService=new ClientService(clientRepository);

        Client client=Client.builder()
                .email("sekou@yahoo.com")
                .telephone("6148587896").build();


        this.clientService.creer(client);

        Assertions.assertEquals("sekou@yahoo.com",client.getEmail());
        Assertions.assertEquals("6148587896",client.getTelephone());

    }

    @Test
    void shouldReturnListOfClient() {
       clientService=new ClientService(clientRepository);
        List<ClientDto> clientListRetourne= clientService.rechercher();

        Assertions.assertEquals(5,clientListRetourne.size());
    }

    @Test
    void shouldReadClient() {
      //  ClientRepository clientRepository1=Mock(ClientRepository.class);

      //  Client clientOne=Client.builder().id(1).telephone("5148587896").build();
      //  when(clientRepository.findById(1)).thenReturn(Optional.of(clientOne));
        clientService=new ClientService(clientRepository);
        ClientDto clientObtenu= clientService.lire(1);
        Assertions.assertEquals(clientObtenu.email(),"451kolokani22@yahoo.fr");



    }

    @Test
    void ShouldReadOrCreateClient() {
        clientService=new ClientService(clientRepository);
        Client client=new Client();
        client.setTelephone("5144523695");
        client.setEmail("451kolokani22@yahoo.fr");

        clientService.lireOuCreer(client);

        Assertions.assertEquals("5144523695",client.getTelephone());

    }

    @Test
    void ShouldModifyClient() {
        clientService=new ClientService(clientRepository);
        Client client=new Client();
        client.setId(2);
        client.setTelephone("4508587855");
        client.setEmail("tata22@yahoo.com");

        clientService.modifier(2,client);

        Assertions.assertEquals("tata22@yahoo.com",client.getEmail());
    }

    @Test




    void ShouldReturnThrowException() {
        clientService=new ClientService(clientRepository);
      //  ClientRepository clientRepository1=Mockito.mock(ClientRepository.class);

        when(clientRepository.findById(anyInt())).thenReturn(Optional.empty());


      final IllegalAccessException exception=  Assertions.assertThrows(IllegalAccessException.class,()-> this.clientService.lire(1));
      Assertions.assertEquals("No Client for id",exception.getMessage());

    }
}