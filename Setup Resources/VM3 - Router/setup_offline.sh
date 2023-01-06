#!/usr/bin/bash

# Set up the firewall
sudo ufw default deny incoming
sudo ufw default allow outgoing
sudo ufw allow from 192.168.1.11 to any port 25
sudo ufw allow from 192.168.1.11 to any port 465
sudo ufw allow from 192.168.1.11 to any port 8443
sudo ufw allow 8443
sudo ufw enable


# Set up IP configuration
echo "
network:
    version: 2
    renderer: NetworkManager
    ethernets:
        enp0s3:
            addresses:
                - 192.168.1.10/24
            nameservers:
                addresses: [8.8.8.8, 8.8.4.4]
        enp0s8:
            dhcp4: yes
            nameservers:
                addresses: [8.8.8.8, 8.8.4.4]
" | sudo tee /etc/netplan/01-network-manager-all.yaml

# Apply the IP configuration
sudo netplan generate
sudo netplan apply

# Enable IP forwarding
echo "1" | sudo tee /proc/sys/net/ipv4/ip_forward

sudo systemctl restart NetworkManager

# Create the nat table if it doesn't exist
sudo iptables -t nat -F


# Allow traffic to pass through NAT
sudo iptables -t nat -A POSTROUTING -o enp0s8 -j MASQUERADE

# Allow incoming traffic on port 8443
sudo iptables -A INPUT -p tcp --dport 8443 -j ACCEPT

# Allow incoming traffic on port 465
sudo iptables -A INPUT -p tcp --dport 465 -j ACCEPT

# Allow incoming traffic on port 25
sudo iptables -A INPUT -p tcp --dport 25 -j ACCEPT

# Save the iptables rules
sudo iptables-save | sudo tee /etc/iptables/rules.v4
