package fullstack.oving2.web;

import fullstack.oving2.model.Author;
import fullstack.oving2.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

    @Autowired
    private MyService service;

    Logger logger = LoggerFactory.getLogger(Controller.class);

    @RequestMapping("/")
    //    @ResponseBody() er unødvendig når vi bruker @RestController, mapping til JSON skjer automagisk
    public Author veryArchitecturalMessage() {

        logger.trace("ermahgerd trers");
        logger.debug("ermahgerd derberg");
        logger.info("ermahgerd ernferh");
        logger.warn("ermahgerd wern");
        logger.error("ermahgerd ehrrerr");

        return this.service.message();
    }

}
