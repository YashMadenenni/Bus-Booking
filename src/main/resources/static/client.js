//console.log("client.js");



// function Display() {
//     if (document.getElementById("oneWay").checked) {
//         console.log("oneway");
//         console.log(document.getElementById("fromDate").value);
//     } else if (document.getElementById("twoWay").checked) {
//         console.log("twoWay");
//         console.log(document.getElementById("fromDate").value);
//     }
// }

// function EnableReturnDate() {
//     if (document.getElementById("returnDate").hasAttribute("disabled")) {
//         document.getElementById("returnDate").removeAttribute("disabled")
//     }
// }

// function DisableReturnDate() {
//     if (!document.getElementById("returnDate").hasAttribute("disabled")) {
//         document.getElementById("returnDate").setAttribute("disabled", "disabled");
//     }
// }

async function SearchBuses() {

    const from = document.getElementById("from").value;
    const to = document.getElementById("to").value;

    const weekdays = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
    const day = new Date(document.getElementById("day").value);
    console.log(weekdays[day.getDay()]);

    const time = (document.getElementById("day").value).substring(11);
    console.log(time);

    // if (document.getElementById("oneWay").checked) {
    //     console.log("oneway");

        //API Request
        const request = await fetch(`http://localhost:8080/buses?from=${from}&to=${to}&day=${day}&time=${time}`, { method: "GET" });
        const response = await request.json();
        console.log(response);
    // } else if (document.getElementById("twoWay").checked) {
    //     let toDate = document.getElementById("returnDate").value;
    //     console.log("twoWay");
    //     //API Request
    //     const request = await fetch(`http://localhost:5000/buses?from=${from}&to=${to}&fromDate=${fromDate}&toDate=${toDate}`, { method: "GET" });

    // }

}