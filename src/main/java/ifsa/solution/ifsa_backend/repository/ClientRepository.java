package ifsa.solution.ifsa_backend.repository;

import ifsa.solution.ifsa_backend.entites.Client;
import ifsa.solution.ifsa_backend.entites.ClientDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    Client findByEmail(String email);
}
