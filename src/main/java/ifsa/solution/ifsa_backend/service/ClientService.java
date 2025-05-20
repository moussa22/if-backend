package ifsa.solution.ifsa_backend.service;

import ifsa.solution.ifsa_backend.entites.Client;
import ifsa.solution.ifsa_backend.entites.ClientDto;
import ifsa.solution.ifsa_backend.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private ClientRepository clientRepository;


    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }




/*    public Client creer(Client client){

        Optional<Client>  clientDanalaBaseDeDonnee= Optional.ofNullable(this.clientRepository.findByEmail(client.getEmail()));

        if(clientDanalaBaseDeDonnee.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.FOUND, "client existe");
           //
        }
        return this.clientRepository.save(client);
    }*/
public void  creer(Client client){
    Client clientDanalaBaseDeDonnee= this.clientRepository.findByEmail(client.getEmail());

    if (clientDanalaBaseDeDonnee==null){
        this.clientRepository.save(client);
    }


}

    public List<ClientDto> rechercher(){

        return this.clientRepository.findAll().stream().map(client->new ClientDto(client.getId(),client.getEmail(),client.getTelephone())).collect(Collectors.toList());

    }

    public ClientDto lire(int id) {
        Client client=this.clientRepository.findById(id).orElseThrow(()->new IllegalArgumentException("No Client for id"+id));

        return new ClientDto(client.getId(),client.getEmail(),client.getTelephone());
    }

    public Client lireOuCreer(Client clientAcreer) {

        Client clientDanalaBaseDeDonnee= this.clientRepository.findByEmail(clientAcreer.getEmail());

        if(clientDanalaBaseDeDonnee ==null){
            this.clientRepository.save(clientAcreer);
        }
         return clientDanalaBaseDeDonnee;
    }

    public void modifier(int id, Client client) {
        ClientDto clientDanaLaBaseDedonnee= this.lire(id);

        if(clientDanaLaBaseDedonnee.id()==client.getId()){
            Client client1= new Client();
            client1.setTelephone(client.getTelephone());
            client1.setEmail(client.getEmail());
          //  client1.setId(client.getId());
            this.clientRepository.save(client1);
        }


    }
}
