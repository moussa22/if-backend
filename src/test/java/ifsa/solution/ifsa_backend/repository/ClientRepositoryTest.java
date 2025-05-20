package ifsa.solution.ifsa_backend.repository;

import ifsa.solution.ifsa_backend.IfsaBackendApplication;
import ifsa.solution.ifsa_backend.entites.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
/*@SpringBootTest(classes = IfsaBackendApplication.class)*/
@ActiveProfiles(value = "test")

class ClientRepositoryTest {
    @Autowired
    ClientRepository clientRepository;
    @Test
    void shouldReturnClientByClient() {
        //Arrange
       Client clientOne=Client.builder().telephone("5148589632").email("1koutiala@yahoo.com").build();
        Client clienttWO=Client.builder().telephone("4502139632").email("2tata@yahoo.com").build();
        this.clientRepository.saveAll(List.of(clientOne,clienttWO));

        //Act

         Client client= this.clientRepository.findByEmail("1koutiala@yahoo.com");

         //Assert

        Assertions.assertEquals("1koutiala@yahoo.com",client.getEmail());


    }
}