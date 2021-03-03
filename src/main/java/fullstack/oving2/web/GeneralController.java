package fullstack.oving2.web;

import fullstack.oving2.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {
    private final GeneralService service;

    @Autowired
    public GeneralController(GeneralService service) {
        this.service = service;
    }

    @GetMapping()
    public String getHomepage() {
        return service.getHomepage();
    }

    @GetMapping("/{input}")
    public String getHomepage(@PathVariable String input) {
        return service.getHomepage(input);
    }
}
