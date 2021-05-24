<!-- PROJECT LOGO -->
<br />
<p align="center">
  <h3 align="center">Lata Velha</h3>

  <p align="center">
    API Rest developed to project final to Acelera program!
    <br />
  </p>
</p>


<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About the project

The Lata Velha project is a vehicle management system, in which the administrators of a dealership register vehicles for sale. Customers are then able to see all available vehicles and the details of each one.

![swagger-complete-screenshot][swagger-complete-screenshot]


The project architecture is formed by:
* Access endpoints that can be used by a front application
* Endpoints that will be used to list vehicles, brands and users to a relational database
* The API allows access only with a valid authentication token


### Created With

The project was created using the following conditions:
* [Java 11]
* [Spring framework]
* [MySql]


<!-- GETTING STARTED -->
## Getting Started

Follows instructions for running the API.

### Instalando

1. Clone the repo
   ```sh
   git clone https://github.com/CaelumAulas/aceleratw-turma1-grupo3-api
   ```
2. Run the project in the IDE
   ```sh
   run
   ```
3. Access endpoints
   ```sh
   Postman / Insomnia 
   ```
4. Access o Swagger
   ```sh
   localhost:8080/swagger-uui.html
   ```

<!-- USAGE EXAMPLES -->
## Usage

You have access to authentication endpoints.

_Auth Endpoints_
![swagger-auth-screenshot][swagger-auth-screenshot]

_Vehicle Endpoints_
![swagger-vehicle-screenshot][swagger-vehicle-screenshot]

_User Endpoints_
![swagger-user-screenshot][swagger-user-screenshot]

_Brand Endpoints_
![swagger-brand-screenshot][swagger-brand-screenshot]

_Dashboard Endpoints_
![swagger-dashboard-screenshot][swagger-dashboard-screenshot]

_Price Range Endpoints_
![swagger-priceRange-screenshot][swagger-priceRange-screenshot]

_Models_
![swagger-model-screenshot][swagger-model-screenshot]

<!-- CONTACT -->
## Contact

Tainá Medeiros - taina.medeiros@thoughtworks.com

LinkedIn: [/tainamedeiros](https://www.linkedin.com/in/tainamedeiros)

Brenda Matias - brenda.matias@thoughtworks.com

LinkedIn: [/brenda-matias](https://www.linkedin.com/in/brenda-matias)

Kaê Uchôa - kae.uchoa@thoughtworks.com

LinkedIn: [/kaeuchoa](https://www.linkedin.com/in/kaeuchoa/)


<!-- MARKDOWN LINKS & IMAGES -->
[swagger-complete-screenshot]: images/swagger-complete.png
[swagger-auth-screenshot]: images/swagger-auth.png
[swagger-brand-screenshot]: images/swagger-brand.png
[swagger-dashboard-screenshot]: images/swagger-dashboard.png
[swagger-model-screenshot]: images/swagger-model.png
[swagger-priceRange-screenshot]: images/swagger-priceRange.png
[swagger-user-screenshot]: images/swagger-user.png
[swagger-vehicle-screenshot]: images/swagger-vehicle.png