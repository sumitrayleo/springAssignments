package rays.assignment.jokes.app.service;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class JokeServiceImplTest {

    @Test
    void getRandomQuote() {
        String randomQuote = new ChuckNorrisQuotes().getRandomQuote();
        System.out.println("Random Chuck Norris Quote = " + randomQuote);
        Assert.notNull(randomQuote, "The random message is = " + randomQuote);
    }
}