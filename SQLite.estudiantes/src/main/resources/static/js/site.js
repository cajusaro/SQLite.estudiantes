const uri = "http://localhost:8080/readestudiantes";
const uriadd = "http://localhost:8080/createestudiante";
const uriupd = "http://localhost:8080/updateestudiante";
const uridel = "http://localhost:8080/deleteestudiante";
const uribycurso = "http://localhost:8080/readbycurso";

let est = [];

function getBookItems() {
  fetch(uri)
    .then(response => response.json())
    .then(data => _displayItems(data))
    .catch(error => console.error("Imposible leer Estudiantes", error));
}
function getCursoItem() {
  const itemCurso = document.getElementById("get-curso").value.trim();
  fetch(`${uribycurso}/${itemCurso}`, {
    method: "GET"
  })
  .then(response => response.json())
  .then(data => _displayItems(data))
  .catch(error => console.error("Imposible leer Curso", error));
}
function addBookItem() {
  const idInputText = document.getElementById("add-id");
  const nameInputText = document.getElementById("add-name");
  const emailInputText = document.getElementById("add-email");
  const cursoInputText = document.getElementById("add-curso");

  const item = {
    id: parseInt(idInputText.value.trim()),
    name: nameInputText.value.trim(),
    email: emailInputText.value.trim(),
    curso: cursoInputText.value.trim()
  };
  console.log(JSON.stringify(item));
  fetch(uriadd, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json"
    },
    body: JSON.stringify(item)
  })
    .then(response => response.json())
    .then(() => {
      getBookItems();
      idInputText.value = "";
      nameInputText.value = "";
      emailInputText.value = "";
      cursoInputText.value = "";
    })
    .catch(error => console.error("Imposible Adicionar Estudiante.", error));
}
function deleteBookItem() {
  const itemId = document.getElementById("delete-id").value.trim();
  fetch(`${uridel}/${itemId}`, {
    method: "DELETE"
  })
  .then(() => getBookItems())
  .catch(error => console.error(".", error));
}
function displayDeleteForm(id) {
  const item = est.find(item => item.id === id);
  document.getElementById("delete-id").value = item.id;
}

function displayEditForm(id) {

  const item = est.find(item => item.id === id);

  console.log(item.id);

  document.getElementById("edit-id").value = item.id;
  document.getElementById("edit-name").value = item.name;
  document.getElementById("edit-email").value = item.email;
  document.getElementById("edit-curso").value = item.curso;
}

function updateBookItem() {
  const itemId = document.getElementById("edit-id").value.trim();

  const item = {
    id: parseInt(itemId, 10),
    name: document.getElementById("edit-name").value.trim(),
    email: document.getElementById("edit-email").value.trim(),
    curso: document.getElementById("edit-curso").value.trim()
  };

  console.log(item);


  fetch(uriupd, {
    method: "PUT",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json"
    },
    body: JSON.stringify(item)
  })
  .then(() => getBookItems())
  .catch(error => console.error("No fue posible actualizar Estudiante.", error));

  return false;
}

function _displayCount(itemCount) {
  const name = itemCount === 1 ? "entry" : "registros";
  document.getElementById(
    "counter"
  ).innerHTML = `Desplegados <b>${itemCount}</b> ${name}`;
}

function _displayItems(data) {
  const tBody = document.getElementById("est");
  tBody.innerHTML = "";
  _displayCount(data.length);
  const button = document.createElement("button");

  data.forEach(item => {
    let editButton = document.createElement("a");
    editButton.href = "#editBookModal";
    editButton.className = "edit";
    editButton.setAttribute("onclick", `displayEditForm(${item.id})`);
    editButton.setAttribute("data-toggle", "modal");
    editButton.innerHTML =
      "<i class='material-icons' data-toggle='tooltip' title='Edit'>&#xE254;</i>";

    let deleteButton = document.createElement("a");
    deleteButton.href = "#deleteBookModal";
    deleteButton.className = "delete";
    deleteButton.setAttribute("onclick", `displayDeleteForm(${item.id})`);
    deleteButton.setAttribute("data-toggle", "modal");
    deleteButton.innerHTML =
      "<i class='material-icons' data-toggle='tooltip' title='Delete'>&#xE872;</i>";

    let tr = tBody.insertRow();

    let td1 = tr.insertCell(0);
    let textId = document.createTextNode(item.id);
    td1.appendChild(textId);

    let td2 = tr.insertCell(1);
    let textName = document.createTextNode(item.name);
    td2.appendChild(textName);

    let td3 = tr.insertCell(2);
    let textEmail = document.createTextNode(item.email);
    td3.appendChild(textEmail);

    let td4 = tr.insertCell(3);
    let textCurso = document.createTextNode(item.curso);
    td4.appendChild(textCurso);

    let td5 = tr.insertCell(4);
    td5.appendChild(editButton);
    td5.appendChild(deleteButton);
  });
  est = data;
}
