package ifsa.solution.ifsa_backend.controller;

import ifsa.solution.ifsa_backend.entites.Client;
import ifsa.solution.ifsa_backend.entites.ClientDto;
import ifsa.solution.ifsa_backend.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(path="/client")
public class ClientController {

    String URL="localhost:8080/api/client";

    private ClientService clientService;
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public void creer(@RequestBody Client client){

        this.clientService.lireOuCreer(client);

    }
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<ClientDto> rechercher(){
        return this.clientService.rechercher();
    }

    @GetMapping(path="{id}",produces = APPLICATION_JSON_VALUE)
    public ClientDto lire(@PathVariable("id") int id){
        return this.clientService.lire(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(path="{id}", consumes =APPLICATION_JSON_VALUE)
    public void modifier(@PathVariable ("id") int id,  @RequestBody Client client){

        this.clientService.modifier(id,client);

    }
}
