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


        //API Request
        const request = await fetch(`http://localhost:8080/buses?from=${from}&to=${to}&day=${weekdays[day.getDay()]}&time=${time}`, { method: "GET" });
        //const request = await fetch(`http://localhost:8080/buses?from=dra`, { method: "GET" });
        const response = await request.json();
        console.log(response);

        DisplayBus(response);

}

//List all routes serving a given stop
async function BusesForaStop() {
    const from = (document.getElementById("from").value).split("-").join(" ");
    const request = await fetch(`http://localhost:8080/buses?from=${from}`, { method: "GET" });
        const response = await request.json();
        console.log(response);

        DisplayBus(response);

}

//List all times through the day a stop has service.
async function BusesOnDay() {
    const from = (document.getElementById("from").value).split("-").join(" ");
    document.getElementById("searchResult").innerHTML = "";

    const weekdays = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
    const day = new Date(document.getElementById("day").value);
    console.log(weekdays[day.getDay()]);

    const request = await fetch(`http://localhost:8080/buses?from=${from}&day=${weekdays[day.getDay()]}`, { method: "GET" });
        const response = await request.json();
        console.log(response);
        
        DisplayBus(response);

}

//List all routes serving a given stop at a certain time of day
async function BusesOnDayTime() {

    const from = (document.getElementById("from").value).split("-").join(" ");
    

    const weekdays = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
    const day = new Date(document.getElementById("day").value);
    console.log(weekdays[day.getDay()]);

    const time = (document.getElementById("day").value).substring(11);
    console.log(time);

    //API Request
    const request = await fetch(`http://localhost:8080/buses?from=${from}&day=${weekdays[day.getDay()]}&time=${time}`, { method: "GET" });
    
    const response = await request.json();
    console.log(response);

    DisplayBus(response);
    
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
            document.getElementById("searchResult").innerHTML = document.getElementById("searchResult").innerHTML + element+'<br/>';
        });
    }
    
}