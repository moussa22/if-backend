package ifsa.solution.ifsa_backend.service;

import ifsa.solution.ifsa_backend.entites.Client;
import ifsa.solution.ifsa_backend.entites.Sentiment;
import ifsa.solution.ifsa_backend.enums.TypeSentiment;
import ifsa.solution.ifsa_backend.repository.SentimentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class SentimentService {

private SentimentRepository sentimentRepository;

private ClientService clientService;

    public SentimentService(SentimentRepository sentimentRepository, ClientService clientService) {
        this.sentimentRepository = sentimentRepository;
        this.clientService = clientService;
    }


    public void creer(Sentiment sentiment){

        Client client= clientService.lireOuCreer(sentiment.getClient());
        sentiment.setClient(client);
        if(sentiment.getText().contains("pas")){

            sentiment.setType(TypeSentiment.NEGATIF);
        }else {
            sentiment.setType(TypeSentiment.POSITIF);
        }
        this.sentimentRepository.save(sentiment);

    }

    public List<Sentiment> rechercher(TypeSentiment typeSentiment) {
        if(typeSentiment==null){
            return this.sentimentRepository.findAll();
        }else {
            return  this.sentimentRepository.findByType(typeSentiment);
        }

    }

    public void supprimer(int id) {
        this.sentimentRepository.deleteById(id);
    }
}
