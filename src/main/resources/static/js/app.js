document.addEventListener('DOMContentLoaded', function(){
	// fetch and display pets
	fetch(`/api/pets`)
		.then(response => response.json())
			.then(data => {
				let petslist = document.getElementById('pets-list');
				data.forEach(pet => {
					let petitem = document.createElement('div');
					petitem.textContent = `${pet.name} - ${pet.description} - ${pet.age} - ${pet.price}`;
					petslist.appendChild(petitem);
			});
		});
	
	// handle the form submissions
	document.getElementById('pet-form').addEventListener('submit', function(event){
		event.preventDefault();
		
		let pet = {
			name: document.getElementById('name').value, 
			description: document.getElementById('description').value,
			age: document.getElementById('age').value,
			price: document.getElementById('price').value
		};
		
		fetch(`/api/pets`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(pet)
		})
		.then(response => response.json())
			.then(data => {
				let petslist = document.getElementById('pets-list');
				let petitem = document.createElement('div');
				petitem.textContent = `${data.name} - ${data.description} - ${data.age} - ${data.price}`;
				petslist.appendChild(petitem);
			})
		
		});
});