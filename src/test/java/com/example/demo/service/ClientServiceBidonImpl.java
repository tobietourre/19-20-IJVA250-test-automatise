package java.com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceBidonImpl implements ClientService {
    @Override
    public List<Client> findAllClients() {
        List<Client> list = new ArrayList<>();

        Client client = new Client();
        client.setNom("PETRILLO");
        client.setPrenom("Alexandre");
        client.setDateNaissance(LocalDate.now());
        list.add(client) ;

        return list;
    }
}
