//console.log("client.js");

async function SearchBuses() {

    const from = document.getElementById("from").value;
    const to = document.getElementById("to").value;

    const weekdays = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
    const day = new Date(document.getElementById("day").value);
    console.log(weekdays[day.getDay()]);

    const time = (document.getElementById("day").value).substring(11);
    console.log(time);


        //API Request
        const request = await fetch(`http://localhost:8080/buses?from=${from}&to=${to}&day=${day}&time=${time}`, { method: "GET" });
        //const request = await fetch(`http://localhost:8080/buses?from=dra`, { method: "GET" });
        const response = await request.json();
        console.log(response);

        DisplayBus(response);

}

//List all routes serving a given stop
async function BusesForaStop() {
    const from = document.getElementById("from").value;
    const request = await fetch(`http://localhost:8080/buses?from=${from}`, { method: "GET" });
        const response = await request.json();
        console.log(response);

        DisplayBus(response);

}

//List all times through the day a stop has service.
async function BusesOnDay() {
    const from = document.getElementById("from").value;

    const weekdays = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
    const day = new Date(document.getElementById("day").value);
    console.log(weekdays[day.getDay()]);

    const request = await fetch(`http://localhost:8080/buses?from=${from}&day=${day}`, { method: "GET" });
        const response = await request.json();
        console.log(response);

        DisplayBus(response);

}

//List all routes serving a given stop at a certain time of day
async function BusesOnDayTime() {

    const from = document.getElementById("from").value;

    const weekdays = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
    const day = new Date(document.getElementById("day").value);
    console.log(weekdays[day.getDay()]);

    const time = (document.getElementById("day").value).substring(11);
    console.log(time);

    //API Request
    const request = await fetch(`http://localhost:8080/buses?from=${from}&day=${day}&time=${time}`, { method: "GET" });
    
    const response = await request.json();
    console.log(response);

    DisplayBus(response);
    
}

async function AddStop() {
   let routeName = document.getElementById("route").value;
   let stop = document.getElementById("stopAdd").value;
    const data = {
        route: routeName,
        stopName: stop
      };
    
    
    try {
        const request = await fetch("http://localhost:8080/buses/addRoute", { method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify(data) });
        if(request.ok){
            window.alert("Sucess")
        } else {
            window.alert("Error Try Again")
        }
        const response = await request.json();
        
    } catch (error) {
        console.error(error);
    }
}

function DisplayBus(response) {
    document.getElementById("searchResult").innerHTML = "";
    for(const key in response) {
        console.log(response[key].forEach(element => console.log(element)));
    }
    document.getElementById("searchResult").innerHTML = document.getElementById("searchResult").innerHTML + JSON.stringify(response);
    console.log(typeof(response));
}
