package br.com.springboot.bo;

import java.time.LocalDate;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.springboot.model.Client;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ClientBOTest {
    
    @Autowired
    private ClientBO bo;

    @Test
    @Order(1)
    public void insert(){
        Client client = new Client();
        client.setName("José da Silva");
        client.setCpf("0123456789");
        client.setBirthDate(LocalDate.of(2000, 1, 8));
        client.setTelephone("0123456789");
        client.setCellphone("01234567890");
        client.setEnabled(true);
        client.setEmail("jose@gmail.com");
        bo.insert(client);
    }

    @Test
    @Order(2)
    public void findById(){
        Client client = bo.findById(1L);
        System.out.println(client);
    }

    @Test
    @Order(3)
    public void update(){
        Client client = bo.findById(1L);
        client.setCpf("98765432100");
        bo.update(client);
    }
}