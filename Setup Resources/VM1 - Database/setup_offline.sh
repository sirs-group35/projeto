#!/usr/bin/bash

# Set up firewall
sudo ufw default deny incoming
sudo ufw default allow outgoing
sudo ufw allow in on enp0s3 from 192.168.0.11 proto tcp to any port 3306
sudo ufw enable

# Set up IP configuration
echo "
network:
  version: 2
  renderer: NetworkManager
  ethernets:
    enp0s3:
      addresses:
        - 192.168.0.10/24
      nameservers:
        addresses: [8.8.8.8, 8.8.4.4]
" | sudo tee /etc/netplan/01-network-manager-all.yaml

# Apply the IP configuration
sudo netplan generate
sudo netplan apply
sudo systemctl restart NetworkManager
