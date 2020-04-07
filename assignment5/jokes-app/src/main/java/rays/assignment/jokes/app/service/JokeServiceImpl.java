package rays.assignment.jokes.app.service;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.stereotype.Service;

@Service
public class JokeServiceImpl implements JokesService {
    @Override
    public String getRandomQuote() {
        return new ChuckNorrisQuotes().getRandomQuote();
    }
}
