package com.example.demo.repository;

import com.example.demo.entity.Client;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TestEntityManager testEntityManager;
    @Test
    public void save(){
        //Given
        Client client = new Client();
        client.setNom("Luciani");
        client.setPrenom("Clara");

        //When
        clientRepository.save(client);

        //Then
        List<Client> allClient = clientRepository.findAll();
        Assertions.assertThat(allClient).hasSize(1);
        Assertions.assertThat(allClient.get(0).getId()).isNotNull();
        Assertions.assertThat(allClient.get(0).getPrenom()).isEqualTo("Clara");
        Assertions.assertThat(allClient.get(0).getNom()).isEqualToIgnoringCase("LUCIANI");
        Assertions.assertThat(allClient.get(0).getDateNaissance()).isNull();
    }

}