# user-auth-system

A proof-of-concept user authentication and management SaaS system for college subject Modern Server Infrastructure

## Technical Description

The user authentication system works as a BaaS where requests from all types of apps can be made to our REST API, requesting validation, authentication and authorization of users.

The project uses Java's framework Spring 3.1.5 for backend logic, MySQL relational database for storage and app persistence and built-in Spring framework Tomcat for http serving.

### Stages of the project

The project will be deployed in a few stages:

#### Vagrant and Cloud-init

Using Vagrant and it's Virtual Box provider (virtual) and a ubuntu box (ubuntu/focal64), it will deploy a VM to a local Virtual Box hypervisor instance which will contain the whole application stack.

In this stage the application stack consists of the Java Spring application, a MySQL database instance and optionally ngrok
a free ingress controller, SSL/TLS terminator and load-balancer, which can be setup mannualy just by adding a ngrok rule to the vm "$ngrok http 8080" where the service serves the mentioned **port on the host**:

[DOSTOP IZ NGROK]

The spring application, after being build and put into a jar file, is then packed into a Linux ubuntu service and ran in the background to listen on the already mentioned port 8080. The Vagrantfile then port-forwards this to the hosts 8080 from the VMs 80. The VM is put in a private virtual network with an IP of 10.0.0.10.

[DOSTOP IZ IP-ja]

The Vagrant provisioning file is simply structured, the machine gets provisioned with 4069GB of RAM and 2 VCPUs, the networking aforementioned and then the shell script which populates the VM with the application stack. Among other things it, downloads the Java OpenJDK (17.x), Maven Java project manager (4.0.0) and the source code from this git repo, deploys a MySQL server instance and populates it with the SQL script in the repo for spring to be able to access it, creates a custom service and spins it up through port 80.

---

With Cloud-init the story is as follows
