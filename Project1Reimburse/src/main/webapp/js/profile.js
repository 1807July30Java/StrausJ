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

window.onload = function () {
    sendAjaxGet("http://localhost:8084/data?entity=employee&get=managed", populateEmployees);
};