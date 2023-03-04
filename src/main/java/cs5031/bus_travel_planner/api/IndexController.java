package cs5031.bus_travel_planner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.http.MediaType;

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
    @RequestMapping(method = RequestMethod.GET, value = "/buses", 
    params = {"from","to","day","time"})
    public String searchResults(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("day") String day) {
        // 
        // model.search(from,to,day);
        return "buses.json";
    }

    //List all routes serving a given stop
    @RequestMapping(method = RequestMethod.GET, value = "/buses", params = "from")
    public String searchBusesForStop(@RequestParam("from") String from) {
         
        // model.search(from);
        return "buses.json";
    }

    //List all routes serving a given stop at a certain time of day
    @RequestMapping(method = RequestMethod.GET, value = "/buses", params = {"from","time"})
    public String searchBusesForStop(@RequestParam("from") String from, @RequestParam("time") String time) {
        
        // model.searchWithTime(from,time);
        return "buses.json";
    }
    
    //List all times through the day a stop has service.
    @RequestMapping(method = RequestMethod.GET, value = "/buses", params = {"from","day"})
    public String searchBusesForStopReturnTime(@RequestParam("from") String from, @RequestParam("day") String day) {
        
        // model.searchBusesForStopReturnTime(from,day);
        return "buses.json";
    }

    // Search End

    //To add a stop to Route
    @RequestMapping(method = RequestMethod.GET, value = "/buses", params = {"stop","route"})
    @ResponseBody //sends actual content in double quotes
    public String searchResults(@RequestParam("stop") String stopName, @RequestParam("route") String route ) {
        
        // model.addStopToRoute(from,to,day);
        return "Adding";
    }
}

	
