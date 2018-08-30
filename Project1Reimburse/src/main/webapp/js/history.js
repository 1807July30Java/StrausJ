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

function sendAjaxUserGet(url, func, element) {
    var xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            func(this, element);
        }
    };
    xhr.open("GET", url, true);
    xhr.send();
}

function sendAjaxManagerGet(url, func, element1, element2) {
    var xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            func(this, element1, element2);
        }
    };
    xhr.open("GET", url, true);
    xhr.send();
}

function populateManager(xhr, element) {
    if (xhr.responseText) {
        var res = JSON.parse(xhr.responseText);
        if (res != null) {
            element.innerText = res.firstName + " " + res.lastName;
        } else {
            element.innerText = "No Manager";
        }

    }
}

function populateUser(xhr, element1, element2) {
    if (xhr.responseText) {
        var res = JSON.parse(xhr.responseText);
        element1.innerText = res.firstName + " " + res.lastName;
        sendAjaxUserGet("http://localhost:8084/data?entity=employee&get=" + res.managedBy, populateManager, element2);
    }

}

function makeModal(id) {
    document.getElementById("modal-image").setAttribute("src", "http://localhost:8084/image?id=" + id);
}

function populateDenied(xhr) {
    if (xhr.responseText) {
        var res = JSON.parse(xhr.responseText);
        for (var i = 0; i < res.length; i++) {
            if (res[i]) {
                var table = document.getElementById("deniedTable");
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
                var user = document.createElement("td");
                var manager = document.createElement("td");
                sendAjaxManagerGet('http://localhost:8084/data?entity=employee&get=' + res[i].employeeId, populateUser, user, manager);
                var receipt = document.createElement("td");
                receipt.innerHTML = "<button class=\"btn btn-sm btn-primary btn-block text-uppercase\" onclick='makeModal(" + res[i].requestId + ")' data-toggle=\"modal\" data-target=\"#exampleModal\">View Receipt</button>";
                row.append(date, amount, desc, user, manager, receipt);
            }
        }
    }
}

function populateApproved(xhr) {
    if (xhr.responseText) {
        var res = JSON.parse(xhr.responseText);
        for (var i = 0; i < res.length; i++) {
            if (res[i]) {
                var table = document.getElementById("approvedTable");
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
                var user = document.createElement("td");
                var manager = document.createElement("td");
                sendAjaxManagerGet('http://localhost:8084/data?entity=employee&get=' + res[i].employeeId, populateUser, user, manager);
                var receipt = document.createElement("td");
                receipt.innerHTML = "<button class=\"btn btn-sm btn-primary btn-block text-uppercase\" onclick='makeModal(" + res[i].requestId + ")' data-toggle=\"modal\" data-target=\"#exampleModal\">View Receipt</button>";
                row.append(date, amount, desc, user, manager, receipt);
            }
        }
    }
}

window.onload = function () {
    sendAjaxGet("http://localhost:8084/data?entity=request&get=allApproved", populateApproved);
    sendAjaxGet("http://localhost:8084/data?entity=request&get=allDenied", populateDenied);
};