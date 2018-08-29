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

function populateOptions(xhr) {
    if (xhr.responseText) {
        var res = JSON.parse(xhr.responseText);
        var blank = document.createElement("option");
        blank.value = null;
        document.getElementById("managedBy").appendChild(blank);
        for (var i = 0; i < res.length; i++) {
            var option = document.createElement("option");
            option.value = res[i].employeeId;
            option.innerText = res[i].firstName + " " + res[i].lastName;
            document.getElementById("managedBy").appendChild(option);
        }
    }
}

window.onload = function () {
    sendAjaxGet("http://localhost:8084/data?entity=employee&get=managers", populateOptions)
}