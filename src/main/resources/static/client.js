//console.log("client.js");

window.onload = async function Options() {
    //API Request
    const request = await fetch("http://localhost:8080/locations", { method: "GET" });
    const response = await request.json();
    console.log(response);

    let locations = [];
    let routes = [];
    for (const key in response) {
        response[key].forEach(element => {for (const key in element) {
            if(key == "routeName"){
                let route =element[key] +" "+element.direction;
                if(!routes.includes(route)){
                    routes.push(route);
                }
            }
            if(key == "stopList"){
                element[key].forEach(elementArray => {for (const key in elementArray) {
                    
                    if(key == "stopName"){
                        if(!locations.includes(elementArray[key])){
                            locations.push(elementArray[key]);
                        }
                    }
                }});
            }
        }})
    }
    console.log(locations)
    console.log(routes)

    locations.forEach(element => {
        let elementValue = element.split(" ").join("-");
        document.getElementById("from").innerHTML = document.getElementById("from").innerHTML +'<option value ='+elementValue+'>'+element+'</option>'
        document.getElementById("to").innerHTML = document.getElementById("to").innerHTML +'<option value ='+elementValue+'>'+element+'</option>'

        document.getElementById("from1").innerHTML = document.getElementById("from1").innerHTML +'<option value ='+elementValue+'>'+element+'</option>'
        document.getElementById("from2").innerHTML = document.getElementById("from2").innerHTML +'<option value ='+elementValue+'>'+element+'</option>'    
        document.getElementById("from3").innerHTML = document.getElementById("from3").innerHTML +'<option value ='+elementValue+'>'+element+'</option>'
        
    })

    routes.forEach(element =>{
        let elementValue = element.split(" ").join("-");
        document.getElementById("route").innerHTML = document.getElementById("route").innerHTML + '<option value ='+elementValue+'>'+element+'</option>'
    })
}

async function SearchBuses() {

    const from = (document.getElementById("from").value).split("-").join(" ");
    const to = (document.getElementById("to").value).split("-").join(" ");

    const weekdays = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
    const day = new Date(document.getElementById("day").value);
    console.log(weekdays[day.getDay()]);

    const time = (document.getElementById("day").value).substring(11);
    console.log(time);


       if((from != to)&&(document.getElementById("day").value!="")){
         //API Request
         const request = await fetch(`http://localhost:8080/buses?from=${from}&to=${to}&day=${weekdays[day.getDay()]}&time=${time}`, { method: "GET" });
         //const request = await fetch(`http://localhost:8080/buses?from=dra`, { method: "GET" });
         const response = await request.json();
         console.log(response);
 
         DisplayBus(response);
 
         document.getElementById("searchResult").innerHTML=""
        document.getElementById("searchResultHeader").innerHTML=""
        document.getElementById("searchResultHeader").innerHTML ="<tr>"+
        '<th>From</th>'+
        "<th>Destination</th>"+
        "<th>Route</th>"+
        "<th>Direction</th>"+
        "<th>Day</th>"+
        "<th>Time</th>"+
     " </tr>"
 
     for (const key in response) {
         response[key].forEach(element => {
             document.getElementById("searchResult").innerHTML = document.getElementById("searchResult").innerHTML + '<tr><td>'+from+'<td>'+to+ '<td>'+element.split(" ")[0]+ '<td>'+element.split(" ")[1]+ '<td>'+weekdays[day.getDay()]+ '<td>'+time+'<tr/>';
         });
     }
 
         if (response.searchResult.length == 0) {
             document.getElementById("searchResult").innerHTML = document.getElementById("searchResult").innerHTML + "<h5 class='textcenter'>No Result</h5>"
         }
       }else if(document.getElementById("day").value==""){
        window.alert("Choose Date")
       }
       else{
        window.alert("Choose DIfferent Destination")
       }

}

//List all routes serving a given stop
async function BusesForaStop() {
    const from = (document.getElementById("from1").value).split("-").join(" ");
    const request = await fetch(`http://localhost:8080/buses?from=${from}`, { method: "GET" });
        const response = await request.json();
        console.log(response);

       // DisplayBus(response);
       document.getElementById("searchResult").innerHTML=""
       document.getElementById("searchResultHeader").innerHTML=""
       document.getElementById("searchResultHeader").innerHTML ="<tr>"+
       '<th>From</th>'+
       "<th>Route</th>"+
       "<th>Direction</th>"+
    " </tr>"

    for (const key in response) {
        response[key].forEach(element => {
            document.getElementById("searchResult").innerHTML = document.getElementById("searchResult").innerHTML + '<tr><td>'+from+ '<td>'+element.split(" ")[0]+ '<td>'+element.split(" ")[1]+'<tr/>';
        });
    }

    if (response.searchResult.length == 0) {
        document.getElementById("searchResult").innerHTML = document.getElementById("searchResult").innerHTML + "<h5 class='textcenter'>No Result</h5>"
    }

}

//List all times through the day a stop has service.
async function BusesOnDay() {
    const from = (document.getElementById("from2").value).split("-").join(" ");
    document.getElementById("searchResult").innerHTML = "";

    const weekdays = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
    const day = new Date(document.getElementById("day2").value);
    console.log(weekdays[day.getDay()]);

    const request = await fetch(`http://localhost:8080/buses?from=${from}&day=${day}`, { method: "GET" });
        const response = await request.json();
        console.log(response);
        
        //DisplayBus(response);
       document.getElementById("searchResult").innerHTML=""
       document.getElementById("searchResultHeader").innerHTML=""
       document.getElementById("searchResultHeader").innerHTML ="<tr>"+
       '<th>From</th>'+
       "<th>Route</th>"+
       "<th>Direction</th>"+
       "<th>Day</th>"+
       "<th>Time</th>"+
    " </tr>"

    for (const key in response) {
        response[key].forEach(element => {
            document.getElementById("searchResult").innerHTML = document.getElementById("searchResult").innerHTML + '<tr><td>'+from+ '<td>'+element.split(" ")[0]+ '<td>'+element.split(" ")[1]+ '<td>'+element.split(" ")[2]+ '<td>'+element.split(" ")[3]+'<tr/>';
        });
    }

    if (response.searchResult.length == 0) {
        document.getElementById("searchResult").innerHTML = document.getElementById("searchResult").innerHTML + "<h5 class='textcenter'>No Result</h5>"
    }

}

//List all routes serving a given stop at a certain time of day
async function BusesOnDayTime() {

    const from = (document.getElementById("from3").value).split("-").join(" ");
    

    const weekdays = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
    const day = new Date(document.getElementById("day3").value);
    console.log(weekdays[day.getDay()]);

    const time = (document.getElementById("day3").value).substring(11);
    console.log(time);

    //API Request
    const request = await fetch(`http://localhost:8080/buses?from=${from}&day=${weekdays[day.getDay()]}&time=${time}`, { method: "GET" });
    
    const response = await request.json();
    console.log(response);

    //DisplayBus(response);

    document.getElementById("searchResult").innerHTML=""
       document.getElementById("searchResultHeader").innerHTML=""
       document.getElementById("searchResultHeader").innerHTML ="<tr>"+
       '<th>From</th>'+
       "<th>Route</th>"+
       "<th>Direction</th>"+
       "<th>Day</th>"+
       "<th>Time</th>"+
    " </tr>"

    for (const key in response) {
        response[key].forEach(element => {
            document.getElementById("searchResult").innerHTML = document.getElementById("searchResult").innerHTML + '<tr><td>'+from+ '<td>'+element.split(" ")[0]+ '<td>'+element.split(" ")[1]+ '<td>'+weekdays[day.getDay()]+ '<td>'+time+'<tr/>';
        });
    }
    if (response.searchResult.length == 0) {
        document.getElementById("searchResult").innerHTML = document.getElementById("searchResult").innerHTML + "<h5 class='textcenter'>No Result</h5>"
    }
    
}

async function AddStop() {
   let routeName = (document.getElementById("route").value).split("-").join(" ");;
   let stop = document.getElementById("stopAdd").value;
   let stopLocation = document.getElementById("stopLocation").value;
   
   const weekdays = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
    const day = new Date(document.getElementById("addDay").value);
    console.log(weekdays[day.getDay()]);

    const time = (document.getElementById("addDay").value).substring(11);
    console.log(time);
    
    let timeTable = [{"Time" : weekdays[day.getDay()]+" "+time },]

    const data = {
        route: routeName,
        stopName: stop,
        stopLocation: stopLocation,
        timeTable:timeTable
      };
    
    
    try {
        const request = await fetch("http://localhost:8080/buses/addRoute", { method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify(data) });
        if(request.ok){
            window.alert("Sucess")
        } else {
            window.alert("Error Try Again")
        }
        //const response = await request.json();
        
    } catch (error) {
        console.error(error);
    }
}

function DisplayBus(response) {
    document.getElementById("searchResult").innerHTML ="";

    for (const key in response) {
        response[key].forEach(element => {
            document.getElementById("searchResult").innerHTML = document.getElementById("searchResult").innerHTML + '<tr>'+element+'<tr/>';
        });
    }
    
}