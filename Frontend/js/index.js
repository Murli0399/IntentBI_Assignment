const fileUpload = document.getElementById('file-upload');
const uploadButton = document.getElementById('upload-button');
const uploadJson = document.getElementById('upload-json');
const myModal = document.getElementById('myModal');
const addNewCar = document.getElementById('add-new');
const homeBtn = document.getElementById('index');
const message = document.getElementById('message');
const baseURL = "http://localhost:8080/api/cars";

uploadJson.addEventListener('click', () => {
    myModal.style.display = 'block';
})

addNewCar.addEventListener('click', () => {
    location.href = 'register.html';
})


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
                    console.log("Else Block" + response);
                    throw new Error('Error uploading file.');
                }
            })
            .then(data => {
                console.log("then Data Block" + data);
                message.textContent = data; // Display response message
            })
            .catch(error => {
                console.log("catch Block" + error);
                message.textContent = 'An error occurred: ' + error.message;
            });
    };
    reader.readAsText(selectedFile);
    myModal.style.display = 'none';
});
