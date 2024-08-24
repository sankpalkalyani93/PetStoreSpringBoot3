document.addEventListener('DOMContentLoaded', function(){
	// fetch and display products
	fetch(`/api/products`)
		.then(response => response.json())
			.then(data => {
				let productslist = document.getElementById('products-list');
				data.forEach(product => {
					let item = document.createElement('div');
					item.textContent = `${product.name} - ${product.description} - ${product.price}`;
					productslist.appendChild(item);
			});
		});
	
	// handle the form submissions
	document.getElementById('product-form').addEventListener('submit', function(event){
		event.preventDefault();
		
		let product = {
			name: document.getElementById('name').value, 
			description: document.getElementById('description').value,
			price: document.getElementById('price').value
		};
		
		fetch(`/api/products`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(product)
		})
		.then(response => response.json())
			.then(data => {
				let productslist = document.getElementById('products-list');
				let item = document.createElement('div');
				item.textContent = `${data.name} - ${data.description} - ${data.price}`;
				productslist.appendChild(item);
			})
		
		});
	
});