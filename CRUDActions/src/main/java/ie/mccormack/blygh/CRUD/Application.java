package ie.mccormack.blygh.CRUD;

import ie.mccormack.blygh.CRUD.domain.Record;
import ie.mccormack.blygh.CRUD.domain.RecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application  {

    @Autowired
    private RecordRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //used for testing
    
}
