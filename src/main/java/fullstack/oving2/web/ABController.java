package fullstack.oving2.web;

import fullstack.oving2.model.AuthorBook;
import fullstack.oving2.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ABController {
    Logger logger = LoggerFactory.getLogger(ABController.class);

    @Autowired
    private MyService service;

    @GetMapping("/")
    public String getHomepage() {
        logMessage("Someone entered the homepage.");
        return "Welcome to the author & book database!";
    }

    @GetMapping("/search/{search}")
    public ArrayList<String> searchAll(@PathVariable String search) {
        logMessage("General search for: '"+ search +"'");
        return service.searchAll(search);
    }

    @GetMapping("/con")
    public ArrayList<AuthorBook> getABconns() {
        logMessage("Returning all author/book connections.");
        return service.getAbConns();
    }

    @PutMapping("/con/{authorID}+{bookID}")
    public AuthorBook addConnection(@PathVariable int authorID, @PathVariable int bookID) {
        logMessage("Connected author with ID: "+ authorID +" to book with ID: "+ bookID);
        return service.addConnection(authorID, bookID);
    }

    public void logMessage(String log) {
        logger.info("Info: "+ log);
    }
}
