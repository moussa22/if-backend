package ifsa.solution.ifsa_backend.controller;

import ifsa.solution.ifsa_backend.entites.Client;
import ifsa.solution.ifsa_backend.entites.Sentiment;
import ifsa.solution.ifsa_backend.enums.TypeSentiment;
import ifsa.solution.ifsa_backend.service.SentimentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "sentiment", produces = APPLICATION_JSON_VALUE)
public class SentimentController {

    private SentimentService sentimentService;

    public SentimentController(SentimentService sentimentService) {
        this.sentimentService = sentimentService;
    }
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void creer(@RequestBody Sentiment sentiment){

        this.sentimentService.creer(sentiment);

    }

    @GetMapping
    @ResponseBody
    public  List<Sentiment>rechercher(@RequestParam(name = "typeSentiment",required = false) TypeSentiment typeSentiment){
        return this.sentimentService.rechercher(typeSentiment);

    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(path="{id}")
    public void supprimer(@PathVariable("id") int id){

        this.sentimentService.supprimer(id);
    }
}
