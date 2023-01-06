# Group 35 | Legal: ALA

### Built With

Include an outline of the technologies in the project, such as framework (Rails/iOS/Android), as well as programming language, database,  links to any related projects (for example, whether this API has corresponding iOS or Android clients), links to online tools related to the application (such as the project web site, the shared file storage).
If you mention something, please provide links.

* [Java](https://openjdk.java.net/) - Programming Language and Platform
* [Maven](https://maven.apache.org/) - Build Tool and Dependency Management
* [Spring](https://spring.io) - Application framework and inversion of control container for the Java platform
* [Spring Security](https://spring.io/projects/spring-security) - Java/Java EE framework that provides authentication, authorization and other security features for enterprise applications
* [MySQL](https://www.mysql.com) - Relational Database Management System
* [Thymeleaf](https://www.thymeleaf.org) - Java XML/XHTML/HTML5 template engine

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

You'll need 3 Virtual Machines, preferably on VirtualBox, to get the project running. These machines should run Debian 11. The Desktop Manager used for development is Xfce4 and it's the recommended one, but it's not essential for the project execution.

Every virtual machine requires some kind of privilege elevation (sudo or root privilege) to execute commands as root, namely starting services and configuring the network, so the creation of a new user might be necessary.

## Setup

For each machine, you can find the setup scripts in the [Setup Resources](Setup%20Resources) folder.
There, you'll find 2 scripts: `install_online.sh` and `install_offline.sh`. These scripts have comments providing a description of what each section does, and there's also a `README.md` with extra information about the server.

Move the corresponding folder into the server. Make sure the `setup_*.sh` files are executable. Run `chmod +x setup_offline.sh setup_online.sh` if necessary.

`setup_online.sh` requires a connection to the internet to download the required packages for each machine, and should be executed with a single adapter in either a NAT or a Bridged setup. After executing the script, shutdown the machine, setup the network adapters as described below, turn the machine back on and run `setup_offline.sh` to setup the network and finish configuring the machine.

### Network Adapters Configuration

**VM1 - The Database Server**

Has 1 network adapter, connected to an **Internal Network** through the `sw-db` switch.

**VM2 - The Web Server**

Has 2 network adapters. The first one is connected to an **Internal Network** through the `sw-db` switch. The second one is connected to an **Internal Network** through the `sw-dmz` switch.

**VM3 - The Router**

Has 2 network adapters. The first one is connected to an **Internal Network** through the `sw-dmz` switch. The second one is **Bridged** to connect to the Internet.

