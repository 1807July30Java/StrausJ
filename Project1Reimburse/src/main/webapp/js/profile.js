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
            row.append(firstName, lastName, userName, email);
        }
    }
}

function makeModal(id) {
    document.getElementById("modal-image").setAttribute("src", "http://ec2-184-72-109-231.compute-1.amazonaws.com:8080/Project1/image?id=" + id);
}

function populateRequests(xhr) {
    if (xhr.responseText) {
        var res = JSON.parse(xhr.responseText);
        for (var i = 0; i < res.length; i++) {
            if (res[i]) {
                var table = document.getElementById("requestTable");
                var row = document.createElement("tr");
                row.setAttribute("id", res[i].requestId);
                table.appendChild(row);
                var date = document.createElement("td");
                var tempDate = res[i].dateTime;
                var requestDate = new Date(tempDate.year, tempDate.monthValue - 1, tempDate.dayOfMonth, tempDate.hour, tempDate.minute, tempDate.second);
                date.innerText = requestDate.toLocaleDateString() + " " + requestDate.toLocaleTimeString();
                var amount = document.createElement("td");
                amount.innerText = "$ " + res[i].amount;
                var desc = document.createElement("td");
                if (res[i].description) {
                    desc.innerText = res[i].description;
                } else {
                    desc.innerText = "No Description";
                }
                var status = document.createElement("td");
                if (res[i].status === 0) {
                    status.innerText = "Rejected";
                } else if (res[i].status === 1) {
                    status.innerText = "In Progress"
                } else {
                    status.innerText = "Accepted"
                }
                var receipt = document.createElement("td");
                receipt.innerHTML = "<button class=\"btn btn-sm btn-primary btn-block text-uppercase\" onclick='makeModal(" + res[i].requestId + ")' data-toggle=\"modal\" data-target=\"#exampleModal\">View Receipt</button>";
                row.append(date, amount, desc, status, receipt);
            }
        }
    }
}

window.onload = function () {
    sendAjaxGet("http://ec2-184-72-109-231.compute-1.amazonaws.com:8080/Project1/data?entity=employee&get=managed", populateEmployees);
    sendAjaxGet("http://ec2-184-72-109-231.compute-1.amazonaws.com:8080/Project1/data?entity=request&get=all", populateRequests)
};