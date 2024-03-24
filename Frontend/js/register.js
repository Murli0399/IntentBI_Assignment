const homeBtn = document.getElementById('index');
const baseURL = "http://localhost:8080/api/cars";

homeBtn.addEventListener('click', () => {
    location.href = 'index.html';
})

const form = document.getElementById('carForm');


form.addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData(form);

    const carData = {
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

    console.log(carData);

    fetch(baseURL + '/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(carData)
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Failed to add car: ' + response.statusText);
            }
        })
        .then(data => {
            alert('Car added successfully:', data);
            location.href = "index.html";
        })
        .catch(error => {
            alert('Error occurred while adding car:', error);
        });
})