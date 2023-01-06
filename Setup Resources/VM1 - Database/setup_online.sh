#!/usr/bin/bash

# Update the System
sudo apt upgrade
sudo apt install

# Install the common packages
sudo apt install -y netplan.io ufw iptables-persistent

# Install the optional packages
sudo apt install -y tcpdump vim net-tools

# Install the specific packages
sudo apt install -y wget
wget https://dev.mysql.com/get/mysql-apt-config_0.8.24-1_all.deb
sudo apt install -y ./mysql-apt-config_0.8.24-1_all.debvim 
sudo systemctl enable mysql
sudo systemctl start mysql