#!/usr/bin/bash

#Set up firewall
sudo ufw default deny incoming
sudo ufw default allow outgoing
sudo ufw allow from 192.168.1.10 to any port 8443
sudo ufw allow from any to 192.168.0.10 port 3306
sudo ufw deny in icmp

#Enable firewall
sudo ufw enable

#Set up IP configuration
echo "
network:
    version: 2
    renderer: NetworkManager
    ethernets:
        enp0s3:
            addresses:
                - 192.168.0.11/24
            nameservers:
                addresses: [8.8.8.8, 8.8.4.4]
        enp0s8:
            addresses:
                - 192.168.1.11/24
            routes:
                - to: 0.0.0.0/0
                via 192.168.1.10
            nameservers:
                addresses: [8.8.8.8, 8.8.4.4]
" | sudo tee /etc/netplan/01-network-manager-all.yaml

#Apply the IP configuration
sudo netplan generate
sudo netplan apply
sudo systemctl restart NetworkManager