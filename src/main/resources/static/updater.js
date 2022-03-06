document.getElementById("add").addEventListener("submit", function (e) {
   e.preventDefault();
   let xhr = new XMLHttpRequest();
   xhr.open("POST", "/questions/add", true);
   xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

   let question = document.getElementById("question").value;
   let answer = document.getElementById("answer").value;
   let data = "question=" + question + "&answer=" + answer;

   if (data != null) {
      xhr.send(data);
   }

   setTimeout(reload, 500);
});

document.getElementById("del").addEventListener("submit", function (e) {
   e.preventDefault();
   let xhr = new XMLHttpRequest();
   xhr.open("POST", "/questions/del", true);
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