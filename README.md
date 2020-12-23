# The Tuition Reimbursement System (TRMS)

## Project Description

The Tuition Reimbursement System, TRMS, allows users to submit reimbursements for courses and training. The submitted reimbursement must be approved by that employee's supervisor, department head, and benefits coordinator. The benefits coordinator then reviews the grade received before finalizing the reimbursement.

## Technologies Used

* Java
* HTML
* CSS
* JDBC
* SQL
* JavaScript
* AJAX
* Azure
* Javalin

## Features

>List of features ready and TODOs for future development
* Allows users to submit reimbursements for courses and training.
* The submitted reimbursement must be approved by that employee's supervisor, The department Head, and a Benefits Coordinator in that order.
* The user can then add a grade to the reimbursement for final approval.
* The grade can be accepted by coordinator only
* The reimbursement amount can be altered by coordinator only

>To-do List:
* Implement email notification
* Implement auto approve the request if not approved in time


## Getting Started
   
- git clone https://github.com/PrativaG/project1-benco-trms.git

> Setup your code 

- Within the connectionUtil.java, set your URL, Username, and Password in order to connect to your database
- Run the included SQL file in your database to setup tables and dummy data(replace dummy data as needed)
- If running within the Spring Tool Suit 4, within ServerDriver.java, specify your prefered port.
- Run the serverDriver.java and navigate to http://localhost:'port'/hello to test connectivity

## Usage

- Navigate to http://localhost:'port'/ to be redirected to the login page. Use the provided dummy data to explore all features.
