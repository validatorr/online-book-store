# Book Store 📚

![](images/book_photos.png)

## 💡Short overview of the project:  

#### The main problems this project solves are as follows: 

* #### It's a secure application where in order to get access to any endpoints - registration required. 🔒🛡️

* #### There all necessary entities for full cycle of product purchasing: User, Role, Book, Category, Shopping Cart, Cart Item, Order and Order Item. 🛒📚🛍️ 

* #### N + 1 problems excluded, that's why retrieval of any information from database will be fast and optimized. ⚡🚀

* #### Application will be executed on any computer with any OS - Docker included in this project. 🖥️🐳

# 💥 General functionality of the project:

## 📓 _Book Controller_

![](images/book_controller.png)

* #### **Admin** is able to post new books, edit or delete them
* #### **User** can browse through all books, search by parameters or look for a specific book
#
## 📜 _Category Controller_

![](images/category_controller.png)

* #### **Admin** is capable of creating new categories, updating existing ones or deleting them
* #### **User** can check all categories available, look at certain category or get a list of books of a chosen category
#
## 📦 _Order Controller_

![](images/order_controller.png)

* #### **Admin** can update a status of order
* #### **User** have the ability to place an order, check order history and look at specific orders
#
## 🛒 _Shopping Cart Controller_

![](images/shopping-cart_controller.png)
* #### **User** is able to add book to shopping cart, retrieve all the books being placed there, delete book or update its quantity
#
## 🔐 _Authentication Controller_

![](images/authentication_controller.png)
* #### In order to gain access to whole functionality, newcomer needs to register and login
* #### There are two roles here: **Admin**👨🏻‍💼 and **User**🧑🏻‍💻

#
### 👨‍💻 Technologies used:

<details>
  <summary>♨️ Java</summary>

`17 version of Java is used`
</details>

<details>
    <summary>🍃️ Spring Boot</summary>

`3.1.4 version of Spring Boot`
</details>

<details>
    <summary>🐳 Docker</summary>

`Allows to run this application on every OS`
</details>

<details>
    <summary>🐬 MySql</summary>

`Relational database for managing your data`
</details>

<details>
    <summary>📖 Swagger</summary>

`Provides a comfortable documentation and testing environment for this API`
</details>

<details>
    <summary>🛠️ Lombok</summary>

`1.18.22 version of lombok library is used here`
</details>

<details>
  <summary>🧪 + 🚰 Liquibase</summary>

`3.10.3 version of liquibase is used here`
</details>

<details>
    <summary>🗺️ + 🏗️ Mapstruct</summary>

`1.5.3 version of mapstruct library is used here`
</details>

<details>
    <summary>🔒 + 🌱 Spring Security</summary>

`3.1.4 version is used here`
</details>

<details>
    <summary>🛢️ + 🌱 Spring Data JPA</summary>

`3.1.4 version is used here`
</details>

# 🗂️ Project structure: 

![img_1.png](images/project_structure.png)

# 🛢️ Database structure:

![img_2.png](images/database_structure.png)


#


# How to launch this application? 🚀

### Few steps and we are done: 
* We are going to use Docker, so make sure its downloaded on your machine
* You will need an **.env** file, where you should put all the required environment variables
* In terminal, type mvn clean package && docker-compose build && docker-compose up
* The app will be running locally at http://localhost:8081/api
* But it's more comfortable to test it on Swagger, here is a link: http://localhost:8081/api/swagger-ui/index.html#/
    