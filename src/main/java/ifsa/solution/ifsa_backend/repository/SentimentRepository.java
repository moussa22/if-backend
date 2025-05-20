package ifsa.solution.ifsa_backend.repository;

import ifsa.solution.ifsa_backend.entites.Sentiment;
import ifsa.solution.ifsa_backend.enums.TypeSentiment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SentimentRepository extends JpaRepository<Sentiment,Integer> {

    List<Sentiment>findByType(TypeSentiment typeSentiment);
}
