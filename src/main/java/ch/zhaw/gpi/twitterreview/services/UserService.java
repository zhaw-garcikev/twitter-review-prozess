package ch.zhaw.gpi.twitterreview.services;

import ch.zhaw.gpi.twitterreview.resources.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author kevingarcia
 */
@Component
public class UserService {

    // Als Finaler parameter, damit dieser nicht immer angepasst werden kann
    public final RestTemplate restTemplate;

    //userendpoint muss ausgelesen werden, da value angeben
    @Value(value = "${userservice.endpoint}")
    private String userServiceEndpoint;
    
    
    //Konstruktor um dies zu inizialisieren
    public UserService() {
        restTemplate = new RestTemplate();
    }

    //wegen Bean (Component) Zugang zu einem Resttemplate (getUser Methode. Ziel Vorname und Email Adresse vom Benutzer zurückerhalten wenn UserName übergeben wird
    public User getUser(String userName) {

        // Wenn es gut läuft, dann User returnen
        try {
            User user = restTemplate.getForObject("http://localhost:8070/userapi/v1/users/{userName}", User.class, userName);
            return user;

            // Wenn Fehler vorhanden ist, dann überprüfen....
        } catch (HttpClientErrorException httpClientErrorException) {

            //... handelt es sich um den Fehler 404 --> Not found, dann kein Fehler auswerfen sondern nur null
            if (httpClientErrorException.getStatusCode() == HttpStatus.NOT_FOUND) {

                return null;

                //.. handelt es sich um einen anderen Fehler, dann Fehlermeldung auswerfen
            } else {

                throw httpClientErrorException;
            }

        }

    }

}
