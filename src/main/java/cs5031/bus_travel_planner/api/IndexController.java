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

/**
This class serves as a controller for the index page and various endpoints of the spring-boot application.
*/
@Controller
public class IndexController {

    BusModel model = new BusModel("src/main/resources/static/initialState.json"); //Object for model to call search function

    /**
      Returns the main page.
      @return the main page as a string
     */
    @RequestMapping(method = RequestMethod.GET, value = "/")
        public String welcome() {
            return "index.html";
        }

    /**
      Returns all locations in the model.
      @return a string representation of all locations in the model as a JSON object
     */
    @RequestMapping(method = RequestMethod.GET, value = "/locations",produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public String locations() {
            return model.getIndex();
        }

    //Search Start

    /**
      Searches for bus routes between two stops.
      @param from the starting stop for the search
      @param to the ending stop for the search
      @param day the day of the search
      @return a string representation of the search results as a JSON object
     */
    @RequestMapping(method = RequestMethod.GET, value = "/buses", 
            params = {"from","to","day","time"},produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public String searchResults(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("day") String day) {

            return model.getRoutesFromToStop(from,to);
        }

    /**
      Lists all bus routes serving a given stop.
      @param from the stop to search for
      @return a string representation of the search results as a JSON object
     */
    @RequestMapping(method = RequestMethod.GET, value = "/buses", params = "from",produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        // @CrossOrigin(origins = "http://localhost:8080/buses")
        public String searchBusesForStop(@RequestParam("from") String from) {

            return model.getRoutesFromStop(from);
        }


    /**
      Lists all bus routes serving a given stop at a certain time of day.
      @param from the stop to search for
      @param day the day of the search
      @param time the time of the search
      @return a string representation of the search results as a JSON object
     */
    @RequestMapping(method = RequestMethod.GET, value = "/buses", params = {"from","day","time"},produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public String searchBusesForStop(@RequestParam("from") String from,  @RequestParam("day") String day, @RequestParam("time") String time) {

            return model.getRoutesFromStop(from, day, time);
        }

    /**
      Lists all times through the day a stop has service.
      @param from the stop to search for
      @param day the day of the search
      @return a string representation of the search results as a JSON object
     */
    @RequestMapping(method = RequestMethod.GET, value = "/buses", params = {"from","day"},produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public String searchBusesForStopReturnTime(@RequestParam("from") String from, @RequestParam("day") String day) {

            return model.getRoutesFromStop(from, day);
        }

    // Search End

    /**
      This method handles HTTP POST requests to add a new stop to a bus route.
      @param requestBody The request body in JSON format containing information about the new stop.
      @return A string indicating success or failure of the operation.
     */
    @PostMapping (value = "/buses/addRoute") 
        @ResponseBody 
        public String addRoute(@RequestBody String requestBody ) {

            JSONObject initialObj = null;
            JSONObject obj = JsonIO.convertStringToJson(requestBody);

            if(null == obj) {
                return "failure";
            }

            try {
                initialObj = model.loadInitialState(JsonIO.initialFilePath);
            }
            catch (IOException | JSONException e){
                return "failure";
            }

            if(!JsonIO.addStopJson(obj, initialObj, requestBody)) {
                return "failure";
            }

            if(!model.addStopToRoute(obj)) {
                return "failure";
            }

            return "success";
        }
}
	
