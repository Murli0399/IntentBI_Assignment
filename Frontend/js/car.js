const homeBtn = document.getElementById('index');
let carId = localStorage.getItem('carId');
const baseURL = "http://localhost:8080/api/cars/";

let user = localStorage.getItem("intentBi");
if (user != "admin@gmail.com") {
    location.href = "inde.html";
}

document.getElementById("logout").addEventListener("click", () => {
    localStorage.removeItem("intentBi");
    location.href = 'index.html';
})

homeBtn.addEventListener('click', () => {
    location.href = 'index.html';
})

// deleting the car details
document.getElementById('deleteButton').addEventListener('click', () => {
    const requestOptions = {
        method: 'DELETE',
    };

    fetch(baseURL + `delete/${carId}`, requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to delete car by ID');
            }
            alert('Car deleted successfully');
            location.href = "index.html";
        })
        .catch(error => {
            alert('Error deleting car by ID:', error.message);
        });
})


// Updating car details
const form = document.getElementById('carForm');

form.addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData(form);

    const carData = {
        id: parseInt(formData.get('id')),
        vin: formData.get('vin'),
        year: parseInt(formData.get('year')),
        vehicleType: formData.get('vehicleType'),
        make: formData.get('make'),
        model: formData.get('model'),
        price: parseFloat(formData.get('price')),
        sellerType: formData.get('sellerType'),
        source: formData.get('source'),
        interiorColor: formData.get('interiorColor'),
        exteriorColor: formData.get('exteriorColor'),
        drivetrain: formData.get('drivetrain'),
        cylinders: parseInt(formData.get('cylinders')),
        bodySubtype: formData.get('bodySubtype'),
        doors: parseInt(formData.get('doors')),
        madeIn: formData.get('madeIn'),
        trim: formData.get('trim'),
        engine: formData.get('engine'),
        engineSize: parseFloat(formData.get('engineSize')),
        fuelType: formData.get('fuelType'),
        trimR: formData.get('trimR')
    };

    const requestOptions = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(carData)
    };

    fetch(baseURL + "update", requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to update car');
            }
            return response.json();
        })
        .then(updatedCar => {
            console.log('Updated car:', updatedCar);
            alert("Car updated successful");
        })
        .catch(error => {
            console.error('Error updating car:', error.message);
        });

})


// Featching Car details by id
function fetchCarById(id) {

    fetch(baseURL + id)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch car by ID');
            }
            return response.json();
        })
        .then(car => {
            console.log('Retrieved car:', car);
            displayData(car);
        })
        .catch(error => {
            console.error('Error fetching car by ID:', error.message);
        });
}

fetchCarById(carId);


// Display data on web
function displayData(car) {
    document.getElementById('id').value = car.id,
        document.getElementById('vin').value = car.vin,
        document.getElementById('year').value = car.year,
        document.getElementById('vehicleType').value = car.vehicleType,
        document.getElementById('make').value = car.make,
        document.getElementById('model').value = car.model,
        document.getElementById('price').value = car.price,
        document.getElementById('sellerType').value = car.sellerType,
        document.getElementById('source').value = car.source,
        document.getElementById('interiorColor').value = car.interiorColor,
        document.getElementById('exteriorColor').value = car.exteriorColor,
        document.getElementById('drivetrain').value = car.drivetrain,
        document.getElementById('cylinders').value = car.cylinders,
        document.getElementById('bodySubtype').value = car.bodySubtype,
        document.getElementById('doors').value = car.doors,
        document.getElementById('madeIn').value = car.madeIn,
        document.getElementById('trim').value = car.trim,
        document.getElementById('engine').value = car.engine,
        document.getElementById('engineSize').value = car.engineSize,
        document.getElementById('fuelType').value = car.fuelType,
        document.getElementById('trimR').value = car.trimR
}