#!/usr/bin/bash

# Update the System
sudo apt upgrade
sudo apt install

# Install the common packages
sudo apt install netplan.io ufw iptables-persistent

# Install the optional packages
sudo apt install tcpdump vim

# Install the specific packages
sudo apt install openjdk-17-jdk-headless maven