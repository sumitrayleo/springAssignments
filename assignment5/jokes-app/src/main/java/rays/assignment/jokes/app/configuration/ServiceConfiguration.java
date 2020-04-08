package rays.assignment.jokes.app.configuration;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    /*
        Always a good design to convert any external/OS class into Spring Bean using @Bean
     */
    @Bean
    public ChuckNorrisQuotes getChuckNorrisQuotes(){
        return new ChuckNorrisQuotes();
    }

}
