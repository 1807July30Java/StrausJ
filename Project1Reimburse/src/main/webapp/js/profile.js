function sendAjaxGet(url, func) {
    var xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            func(this);
        }
    };
    xhr.open("GET", url, true);
    xhr.send();
}

function populateEmployees(xhr) {
    if (xhr.responseText) {
        var res = JSON.parse(xhr.responseText);
        for (var i = 0; i < res.length; i++) {
            var table = document.getElementById("employeeTable");
            var row = document.createElement("tr");
            table.appendChild(row);
            var firstName = document.createElement("td");
            firstName.innerText = res[i].firstName;
            var lastName = document.createElement("td");
            lastName.innerText = res[i].lastName;
            var userName = document.createElement("td");
            userName.innerText = res[i].username;
            var email = document.createElement("td");
            email.innerText = res[i].email;
            table.append(firstName, lastName, userName, email);
        }
    }
}

function populateRequests(xhr) {
    if (xhr.responseText) {
        var res = JSON.parse(xhr.responseText);
        for (var i = 0; i < 5; i++) {
            if (res[i]) {
                var table = document.getElementById("requestTable");
                var row = document.createElement("tr");
                table.appendChild(row);
                var date = document.createElement("td");
                var tempDate = res[i].dateTime;
                var requestDate = new Date(tempDate.year, tempDate.monthValue, tempDate.dayOfMonth, tempDate.hour, tempDate.minute, tempDate.second);
                date.innerText = requestDate.toLocaleDateString() + " " + requestDate.toLocaleTimeString();
                var amount = document.createElement("td");
                amount.innerText = "$ " + res[i].amount;
                var status = document.createElement("td");
                status.innerText = res[i].status;
                var receipt = document.createElement("td");
                receipt.innerHTML = "<button class=\"btn btn-sm btn-primary btn-block text-uppercase\" onclick='sendAjaxGet(\"http://localhost:8084/data?entity=receipt&get=" + res[i].requestID + "\", makeModal)'>View Receipt</button>";
                table.append(date, amount, status, receipt);
            }
        }
    }
}

window.onload = function () {
    sendAjaxGet("http://localhost:8084/data?entity=employee&get=managed", populateEmployees);
    sendAjaxGet("http://localhost:8084/data?entity=request&get=open", populateRequests)
};