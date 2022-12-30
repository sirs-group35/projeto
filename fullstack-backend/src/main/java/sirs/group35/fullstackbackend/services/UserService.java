package sirs.group35.fullstackbackend.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sirs.group35.fullstackbackend.dto.UserDTO;
import sirs.group35.fullstackbackend.model.Client;
import sirs.group35.fullstackbackend.model.Lawyer;
import sirs.group35.fullstackbackend.model.User;
import sirs.group35.fullstackbackend.repository.ClientRepository;
import sirs.group35.fullstackbackend.repository.LawyerRepository;
import sirs.group35.fullstackbackend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LawyerRepository lawyerRepository;

    @Autowired
    private ClientRepository clientRepository;

    public UserDTO registerUser(UserDTO newUserDTO) {
        User newUser;
        if (newUserDTO.isLawyer()) {
            Lawyer lawyer = new Lawyer();
            lawyer.setUsername(newUserDTO.getUsername());
            lawyer.setName(newUserDTO.getName());
            lawyer.setEmail(newUserDTO.getEmail());
            lawyerRepository.save(lawyer);
            newUser = lawyer;
        } else {
            Client client = new Client();
            client.setUsername(newUserDTO.getUsername());
            client.setName(newUserDTO.getName());
            client.setEmail(newUserDTO.getEmail());
            clientRepository.save(client);
            newUser = client;
        }
        userRepository.save(newUser);
        return newUserDTO;
    }

    public List<UserDTO> listAllLayers() {
        List<Lawyer> users = lawyerRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(user.getUsername());
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setLawyer(true);
            userDTO.setManager(false);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    public List<UserDTO> listAllClients() {
        List<Client> users = clientRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(user.getUsername());
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setLawyer(false);
            userDTO.setManager(false);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    public List<UserDTO> listAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(user.getUsername());
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setLawyer(user instanceof Lawyer);
            userDTO.setManager(false);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }
}
