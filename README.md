# Car Management System

This is a basic web app for managing car data. Users can add, view, update, and delete car information.

## Features

- Upload a JSON file to add multiple car details in bulk.
- Add a new car with various details such as VIN, year, make, model, price, etc.
- View a list of all cars in the database.
- Sort cars by different criteria.
- Paginate through the list of cars.
- Update existing car information.
- Delete cars from the database.

## Technologies Used

- **Frontend**: HTML, CSS, JavaScript
- **Backend**: Java, Spring Boot
- **Database**: MySQL, H2 (for testing)
- **Other Tools**: Maven, Git

## Installation

1. Clone the repository:
   `
   git clonehttps://github.com/Murli0399/IntentBI_Assignment.git
   `

2. Navigate to the project directory:

3. Build the project using Maven:

4. Run the Spring Boot application:

5. Access the application in your web browser at [http://localhost:8080](http://localhost:8080).

## Usage

- Upon accessing the application, you'll see a list of cars if any exist in the database.
- You can add a new car by clicking on the "Add Car" button and filling out the form.
- Existing cars can be updated or deleted by clicking on the corresponding buttons next to each car entry.
- You can also sort the list of cars by clicking on the column headers.

## Backend Folder Structure
```
    Backend
        └── src
            └── main
                └── java
                    └── com
                        └── murli
                            ├── controller
                            │   └──CarController.java
                            ├── entity
                            ├── exceptions
                            ├── repository
                            ├── service
                            │   └── impl
                            └── JsonToWebApplication.java

```

## Frontend Folder Structure
```
F:
└── IntentBI_Assignment
    └── Frontend
        ├── css
        │   ├── index.css
        │   ├── register.js
        │   ├── car.js
        │   └── modal.js
        ├── js
        │   ├── car.js
        │   ├── register.js
        │   └── index.js
        ├── index.html
        ├── car.html
        └── register.html

```

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/my-feature`)
3. Make your changes
4. Commit your changes (`git commit -am 'Add new feature'`)
5. Push to the branch (`git push origin feature/my-feature`)
6. Create a new Pull Request


## 🔗 Contact Me

[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://murli0399.github.io/)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/murli-khaire/)


## Authors

- [Murli Khaire](https://github.com/Murli0399)


## Acknowledgements

- [Masai School](https://www.masaischool.com/)

