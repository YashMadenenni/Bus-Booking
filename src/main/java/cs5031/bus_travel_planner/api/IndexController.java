package cs5031.bus_travel_planner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.IOException;

@Controller
public class IndexController {

    BusModel model = new BusModel("src/main/resources/static/initialState.json"); //Object for model to call search function

    //Main page
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String welcome() {
        return "index.html";
    }

    //All the locations
    @RequestMapping(method = RequestMethod.GET, value = "/locations",produces = MediaType.APPLICATION_JSON_VALUE)
    public String locations() {
        return "initialState.json";
    }

    //Search Start
    //To search
    @RequestMapping(method = RequestMethod.GET, value = "/buses", 
    params = {"from","to","day","time"},produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchResults(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("day") String day) {
        // 
        // model.search(from,to,day);
        return "buses.json";
    }

    //List all routes serving a given stop
    @RequestMapping(method = RequestMethod.GET, value = "/buses", params = "from",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    // @CrossOrigin(origins = "http://localhost:8080/buses")
    public String searchBusesForStop(@RequestParam("from") String from) {
         
        //JsonIO.convertStringToJson(model.getRoutesFromStop(from));
        //return "buses.json";
        return model.getRoutesFromStop(from);
    }

    //List all routes serving a given stop at a certain time of day
    @RequestMapping(method = RequestMethod.GET, value = "/buses", params = {"from","day","time"})
    @ResponseBody
    public String searchBusesForStop(@RequestParam("from") String from,  @RequestParam("day") String day, @RequestParam("time") String time) {
        
        //JsonIO.convertStringToJson(model.getRoutesFromStop(from, day, time));
        //return "buses.json";
        return model.getRoutesFromStop(from, day, time);
    }
    
    //List all times through the day a stop has service.
    @RequestMapping(method = RequestMethod.GET, value = "/buses", params = {"from","day"})
    @ResponseBody
    public String searchBusesForStopReturnTime(@RequestParam("from") String from, @RequestParam("day") String day) {
        
        //JsonIO.convertStringToJson(model.getRoutesFromStop(from, day));
        //return "buses.json";
        return model.getRoutesFromStop(from, day);
    }

    // Search End

    //To add a stop to Route
    @PostMapping (value = "/buses/addRoute") 
    @ResponseBody //sends actual content in double quotes
    public String searchResults(@RequestBody String requestBody ) {
        System.out.println(requestBody);
        JSONObject obj = JsonIO.convertStringToJson(requestBody);
        model.addStopToRoute(obj);

        JSONObject initialObj = null;
        try {
        initialObj = model.loadInitialState(JsonIO.initialFilePath);
        }
        catch (IOException | JSONException e){
        }
        JsonIO.addStopJson(obj, initialObj);
        

        //String route = requestBody.getRoute();
        //String stop = requestBody.getStop();
        
        // model.addStopToRoute();
        return "Adding";
    }

    public static class RouteStop {
        private String route;
        private String stop;
        private String stopLocation;
        private JSONObject timeTable[]; 
        public String getStop() {
            return stop;
        }
        public void setStop(String stop) {
            this.stop = stop;
        }
        public String getRoute() {
            return route;
        }
        public void setRoute(String route) {
            this.route = route;
        }
        public String getStopLocation() {
            return stopLocation;
        }
        public void setStopLocation(String stopLocation) {
            this.stopLocation = stopLocation;
        }
        public JSONObject[] getTimeTable() {
            return timeTable;
        }
        public void setTimeTable(JSONObject[] timeTable) {
            this.timeTable = timeTable;
        }
    }
}
	
