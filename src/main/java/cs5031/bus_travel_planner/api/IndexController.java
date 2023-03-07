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
    @ResponseBody
    public String locations() {
        return model.getIndex();
        //.return "initialState.json";
    }

    //Search Start
    //To search
    @RequestMapping(method = RequestMethod.GET, value = "/buses", 
    params = {"from","to","day","time"},produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String searchResults(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("day") String day) {
        // 
        return model.getRoutesFromToStop(from,to);
        //return "buses.json";
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
    @RequestMapping(method = RequestMethod.GET, value = "/buses", params = {"from","day","time"},produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String searchBusesForStop(@RequestParam("from") String from,  @RequestParam("day") String day, @RequestParam("time") String time) {
        
        //JsonIO.convertStringToJson(model.getRoutesFromStop(from, day, time));
        //return "buses.json";
        return model.getRoutesFromStop(from, day, time);
    }
    
    //List all times through the day a stop has service.
    @RequestMapping(method = RequestMethod.GET, value = "/buses", params = {"from","day"},produces = MediaType.APPLICATION_JSON_VALUE)
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
    public String addRoute(@RequestBody String requestBody ) {
        //System.out.println(requestBody);
        JSONObject obj = JsonIO.convertStringToJson(requestBody);

        JSONObject initialObj = null;
        try {
        initialObj = model.loadInitialState(JsonIO.initialFilePath);
        }
        catch (IOException | JSONException e){
        }
        JsonIO.addStopJson(obj, initialObj, requestBody);
        
        model.addStopToRoute(obj);

        return "success";
    }
}
	
