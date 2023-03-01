//console.log("client.js");



function Display() {
    if (document.getElementById("oneWay").checked) {
        console.log("oneway");
        console.log(document.getElementById("fromDate").value);
    } else if (document.getElementById("twoWay").checked) {
        console.log("twoWay");
        console.log(document.getElementById("fromDate").value);
    }
}

function EnableReturnDate() {
    if (document.getElementById("returnDate").hasAttribute("disabled")) {
        document.getElementById("returnDate").removeAttribute("disabled")
    }
}

function DisableReturnDate() {
    if (!document.getElementById("returnDate").hasAttribute("disabled")) {
        document.getElementById("returnDate").setAttribute("disabled", "disabled");
    }
}

async function SearchBuses() {

    const from = document.getElementById("from").value;
    const to = document.getElementById("to").value;
    const fromDate = document.getElementById("fromDate").value;

    if (document.getElementById("oneWay").checked) {
        console.log("oneway");
        //API Request
        const request = await fetch(`http://localhost:5000/buses?route=oneWay&from=${from}&to=${to}&fromDate=${fromDate}`, { method: "GET" });

    } else if (document.getElementById("twoWay").checked) {
        let toDate = document.getElementById("returnDate").value;
        console.log("twoWay");
        //API Request
        const request = await fetch(`http://localhost:5000/buses?route=twoWay&from=${from}&to=${to}&fromDate=${fromDate}&toDate=${toDate}`, { method: "GET" });

    }

}