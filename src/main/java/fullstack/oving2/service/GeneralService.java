package fullstack.oving2.service;

import org.springframework.stereotype.Service;

@Service
public class GeneralService {

    public String getHomepage() {
        return "Welcome to the homepage of the author & book database!";
    }

    public String getHomepage(String input) {
        return "Hello "+ input +"!\nWelcome to the homepage of the author & book database!";
    }
}
