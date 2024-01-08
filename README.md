![Pizza](http://xn--80aawgpnx4aa.xn--p1ai/assets/1.ico#right) 
# pizzatime! - Pizza Delivery Service
  
Welcome to pizzatime! - a Java (Spring Boot) + Vue + Freemarker + Postgres based pizza delivery service. This service allows users to select items, add them to their cart, receive an email receipt, and complete the order process. The service supports complete navigation, product cards, cart functionality, and enables the user to provide their contact number. The orders are stored in a PostgreSQL database, allowing the operator to access customer details such as address and contact information.  
  
## Features  
  
- Select and customize your favorite pizza items.  
- Add items to your cart and review your order.  
- Receive an order confirmation and receipt via email.  
- Seamless navigation through the website.  
- Accessible product cards with detailed information.  
- Provide a contact number for order-related communication.  
- Store order details in a PostgreSQL database.  
- Operators can access customer information and address.  
  
## Technologies Used  
  
- Java (Spring Boot) for the backend development.  
- Vue.js for the frontend development.  
- Freemarker for templating.  
- PostgreSQL for storing the order journal.  
- Docker Compose for containerization and deployment.  
  
## Getting Started  
  
To run the pizzatime! service locally, please follow the instructions below:  
  
### Prerequisites  
  
- Docker should be installed on your machine.  
- A compatible web browser such as Google Chrome or Mozilla Firefox.  
  
### Installation  
  
1. Clone the repository from GitHub:  
   ```git clone https://github.com/veceloe/pizzatime.git``` 
2. Navigate to the project directory:
  ```cd pizzatime```
4. Build and run the Docker containers using Docker Compose:
  ```docker-compose up``` 
5. Wait for the containers to start and initialize.
Access the pizzatime! service in your web browser at http://localhost:9090.

### Usage

- Browse the available pizza items and customize them according to your preferences.
- Add selected items to your cart by clicking on the "Add to Cart" button.
- Review your order and make any necessary changes in the cart section.
- Provide your contact number for order-related communication.
- Proceed to checkout and complete the order process.
- Receive an email confirmation and receipt for your order.
- Operators can access customer information and address details by querying the PostgreSQL database.

### Contributing
Thank you for your interest in contributing to pizzatime! If you would like to contribute, please follow these guidelines:Fork the repository and create your feature branch.
- Commit your changes and push them to your forked repository.
- Submit a pull request describing the changes you have made and any additional details.
### License

The pizzatime! project is licensed under the MIT License.
### Contact
If you have any questions or suggestions regarding pizzatime!, please feel free to contact us at pizzatime@gmail.com
Happy Pizza Ordering!
