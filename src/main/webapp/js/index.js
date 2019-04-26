window.onload = () => {
	populateTodosTable();
}

const populateTodosTable = () => {
	const xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = () => {
		if (xhr.status === 200 && xhr.readyState === 4) {
			let todos = JSON.parse(xhr.responseText);
			addTodosToTable(todos);
		}
	}
	
	xhr.open("GET", "http://localhost:8080/DevopsExample/api/todos");
	xhr.send();
}

function addTodosToTable(todos) {
	for (let todo of todos) {
		let tdId = document.createElement("td");
		let tdTitle = document.createElement("td");
		let tdDescription = document.createElement("td");
		
		console.log(todo);
		
		tdId.textContent = todo.id;
		tdTitle.textContent = todo.title;
		tdDescription.textContent = todo.description;
		
		let row = document.createElement("tr");
		
		row.appendChild(tdId);
		row.appendChild(tdTitle);
		row.appendChild(tdDescription);
		
		document.getElementById("todoTable").appendChild(row);
	}
}