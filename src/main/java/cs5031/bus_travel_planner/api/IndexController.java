package cs5031.bus_travel_planner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

@Controller
public class IndexController {

    // Model model; //Object for model to call search function

    //Main page
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String welcome() {
        return "index.html";
    }

    //All the locations
    @RequestMapping(method = RequestMethod.GET, value = "/locations",produces = MediaType.APPLICATION_JSON_VALUE)
    public String locations() {
        return "locations.json";
    }

    //Search Start
    //To search
    @RequestMapping(method = RequestMethod.GET, value = "/buses/{from}/{to}/{day}")
    public String searchResults(@PathVariable("from") String from, @PathVariable("to") String to, @PathVariable("day") String day ) {
        
        // model.search(from,to,day);
        return "buses.json";
    }

    //List all routes serving a given stop
    @RequestMapping(method = RequestMethod.GET, value = "/buses/{from}")
    public String searchBusesForStop(@PathVariable("from") String from) {
        
        // model.search(from);
        return "buses.json";
    }

    //List all routes serving a given stop at a certain time of day
    @RequestMapping(method = RequestMethod.GET, value = "/buses/{from}/{time}")
    public String searchBusesForStop(@PathVariable("from") String from, @PathVariable("time") String time) {
        
        // model.searchWithTime(from,time);
        return "buses.json";
    }
    
    //List all times through the day a stop has service.
    @RequestMapping(method = RequestMethod.GET, value = "/buses/{from}")
    public String searchBusesForStopReturnTime(@PathVariable("from") String from) {
        
        // model.searchBusesForStopReturnTime(from);
        return "buses.json";
    }

    // Search End

    //To add a stop to Route
    @RequestMapping(method = RequestMethod.GET, value = "/buses/{stop}/{route}")
    public String searchResults(@PathVariable("stop") String stopName, @PathVariable("route") String route ) {
        
        // model.addStopToRoute(from,to,day);
        return "";
    }
}

	
