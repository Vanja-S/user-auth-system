# user-auth-system

A proof-of-concept user authentication and management SaaS system for college subject Modern Server Infrastructure

## Technical Description

The user authentication system works as a BaaS where requests from all types of apps can be made to our REST API, requesting validation, authentication and authorization of users.

The project uses Java's framework Spring 3.1.5 for backend logic, MySQL relational database for storage and app persistence and NGINX as a web server (later it will be used as a load balancer as well).

### Stages of the project

The project will be deployed in a few stages:

#### Vagrant and Cloud-init

Using Vagrant and it's third party Azure provider (vagrant-azure) and a dummy box (azure-dummy), it will deploy a VM to Azure cloud which will contain the whole application stack.
