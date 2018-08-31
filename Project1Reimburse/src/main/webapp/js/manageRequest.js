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

function populateUser(xhr, element) {
    if (xhr.responseText) {
        var res = JSON.parse(xhr.responseText);
        element.innerText = res.firstName + " " + res.lastName;
    }
}

function sendAjaxPost(url, func, id, newCode) {
    var xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            func(id);
        }
    };
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send("id=" + id + "&newCode=" + newCode);
}

function removeRow(id) {
    var element = document.getElementById(id);
    element.parentElement.removeChild(element);
}

function makeModal(id) {
    document.getElementById("modal-image").setAttribute("src", "http://localhost:8084/image?id=" + id);
}

function populateRequests(xhr) {
    if (xhr.responseText) {
        var res = JSON.parse(xhr.responseText);
        for (var i = 0; i < 5; i++) {
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
                var user = document.createElement("td");
                sendAjaxUserGet('http://localhost:8084/data?entity=employee&get=' + res[i].employeeId, populateUser, user);
                var approve = document.createElement("td");
                var id = res[i].requestId;
                approve.innerHTML = "<button class=\"btn btn-sm btn-deny btn-block btn-primary text-uppercase\" onclick='sendAjaxPost(\"http://localhost:8084/data?entity=request&get=update\", removeRow(" + id + "), " + id + ", 2)'>Approve</button>";
                var deny = document.createElement("td");
                deny.innerHTML = "<button class='btn btn-sm btn-deny btn-block btn-primary text-uppercase' onclick='sendAjaxPost(\"http://localhost:8084/data?entity=request&get=update\", removeRow(" + id + "), " + id + ", 0)'>Deny</button>";
                var receipt = document.createElement("td");
                receipt.innerHTML = "<button class=\"btn btn-sm btn-primary btn-block text-uppercase\" onclick='makeModal(" + res[i].requestId + ")' data-toggle=\"modal\" data-target=\"#exampleModal\">View Receipt</button>";
                row.append(date, amount, desc, user, receipt, approve, deny);
            }
        }
    }
}

window.onload = function () {
    sendAjaxGet("http://localhost:8084/data?entity=request&get=managed", populateRequests);
};