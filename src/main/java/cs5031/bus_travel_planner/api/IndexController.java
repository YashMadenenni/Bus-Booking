package cs5031.bus_travel_planner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.http.MediaType;

@Controller
public class IndexController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String welcome() {
        return "index.html";
    }
}

	
