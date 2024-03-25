const fileUpload = document.getElementById('file-upload');
const uploadButton = document.getElementById('upload-button');
const uploadJson = document.getElementById('upload-json');
const myModal = document.getElementById('myModal');
const addNewCar = document.getElementById('add-new');
const homeBtn = document.getElementById('index');
const message = document.getElementById('message');
const cont = document.getElementById('tbody');
const baseURL = "http://localhost:8080/api/cars";

let user = localStorage.getItem("intentBi");
if (user != "admin@gmail.com") {
    document.getElementById('loginModal').style.display = "block";
}

document.getElementById("logout").addEventListener("click", () => {
    localStorage.removeItem("intentBi");
    location.href = 'index.html';
})

uploadJson.addEventListener('click', () => {
    myModal.style.display = 'block';
})

addNewCar.addEventListener('click', () => {
    location.href = 'register.html';
})


document.getElementById('login').addEventListener('click', () => {
    const uname = document.getElementById('uname').value;
    const pwd = document.getElementById('pwd').value;
    if (uname == "admin@gmail.com") {
        if (pwd == "123456") {
            localStorage.setItem("intentBi", uname);
            document.getElementById('loginModal').style.display = "none";
        }
        else {
            alert("Password Incorrect");
        }
    }
    else {
        alert("Username Incorrect");
    }

})


function loadPage() {
    fetch(baseURL + '/all')
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Failed to fetch cars: ' + response.statusText);
            }
        })
        .then(data => {
            // Array to store cars
            const carsArray = [];

            // Iterate through the data and push each car to the array
            data.forEach(car => {
                const carObj = {
                    id: car.id,
                    vin: car.vin,
                    year: car.year,
                    vehicleType: car.vehicleType,
                    make: car.make,
                    model: car.model,
                    price: car.price,
                    sellerType: car.sellerType,
                    source: car.source,
                    interiorColor: car.interiorColor,
                    exteriorColor: car.exteriorColor,
                    drivetrain: car.drivetrain,
                    cylinders: car.cylinders,
                    bodySubtype: car.bodySubtype,
                    doors: car.doors,
                    madeIn: car.madeIn,
                    trim: car.trim,
                    engine: car.engine,
                    engineSize: car.engineSize,
                    fuelType: car.fuelType,
                    trimR: car.trimR
                };

                carsArray.push(carObj);
            });

            console.log('Cars Array:', carsArray);
            displayData(carsArray);
            // Now you have an array of cars
        })
        .catch(error => {
            console.error('Error occurred while fetching cars:', error);
            // Handle network errors or other exceptions
        });
}

loadPage();

function displayData(arr) {
    cont.innerHTML = null;
    arr.forEach((el, ind) => {
        let tr = document.createElement('tr');
        let td1 = document.createElement('td');
        td1.innerText = el.id;
        let td2 = document.createElement('td');
        td2.innerText = el.vehicleType;
        let td3 = document.createElement('td');
        td3.innerText = el.price;
        let td4 = document.createElement('td');
        td4.innerText = el.year;
        let td5 = document.createElement('td');
        td5.innerText = el.fuelType;
        let td6 = document.createElement('td');
        td6.innerText = el.madeIn;
        let td7 = document.createElement('td');
        td7.innerText = "View Details";
        td7.style.cursor = 'pointer';
        td7.style.color = 'blue';
        td7.addEventListener('click', () => {
            localStorage.setItem('carId', el.id);
            location.href = "car.html";
        })
        tr.append(td1, td2, td3, td4, td5, td6, td7);
        cont.append(tr);
    })
}


homeBtn.addEventListener('click', () => {
    location.href = 'index.html';
})

uploadButton.addEventListener('click', function () {
    const selectedFile = fileUpload.files[0];

    if (!selectedFile) {
        message.textContent = 'Please select a text file to upload.';
        return;
    }
    if (selectedFile.type !== 'application/json') {
        message.textContent = 'Please select a JSON file.';
        return;
    }
    // if (selectedFile.type !== 'text/plain') {
    //     message.textContent = 'Please select a text file (.txt).';
    //     return;
    // }

    const reader = new FileReader();
    reader.onload = function (e) {
        const content = e.target.result;

        // Prepare the data to send to the upload API
        const formData = new FormData();
        formData.append('file', selectedFile);

        // Replace 'YOUR_UPLOAD_API_URL' with the actual URL of your upload API
        fetch(baseURL + "/upload", {
            method: 'POST',
            body: formData
            // mode: 'no-cors'
        })
            .then(response => {
                if (response.ok) {
                    return response.text(); // Extract text from response
                } else {
                    console.log("Else Block " + response.message);
                    throw new Error('Error uploading file.');
                }
            })
            .then(data => {
                alert(data);
                message.textContent = data; // Display response message
            })
            .catch(error => {
                console.log("catch Block " + error.message);
                message.textContent = 'An error occurred: ' + error.message;
            });
    };
    reader.readAsText(selectedFile);
    myModal.style.display = 'none';
    loadPage();
});
