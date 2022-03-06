document.getElementById("add").addEventListener("submit", function (e) {
   e.preventDefault();
   let xhr = new XMLHttpRequest();
   xhr.open("POST", "/buddies/add", true);
   xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

   let name = document.getElementById("name").value;
   let address = document.getElementById("address").value;
   let phoneNumber = document.getElementById("phoneNumber").value;
   let data = "name=" + name + "&address=" + address + "&phoneNumber=" + phoneNumber;

   if (data != null) {
      xhr.send(data);
   }

   setTimeout(reload, 500);
});

document.getElementById("del").addEventListener("submit", function (e) {
   e.preventDefault();
   let xhr = new XMLHttpRequest();
   xhr.open("POST", "/buddies/del", true);
   xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   let data = "id=" + document.getElementById("id").value;
   if (data != null) {
      xhr.send(data);
   }
   setTimeout(reload, 500);
});

function reload() {
   location.reload();
}