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

function hideAdmin(xhr) {
    if (xhr.responseText) {
        var res = JSON.parse(xhr.responseText);
        if (!res.manager) {
            var elements = document.getElementsByClassName("admin-only");
            for (var i = 0; i < elements.length; i++) {
                elements.item(i).style.display = "none";
            }
        }
    }
}

sendAjaxGet("http://ec2-184-72-109-231.compute-1.amazonaws.com:8080/Project1/data?entity=employee&get=current", hideAdmin);