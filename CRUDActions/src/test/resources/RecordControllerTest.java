
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import ie.mccormack.blygh.CRUD.Application;
import ie.mccormack.blygh.CRUD.domain.Record;
import ie.mccormack.blygh.CRUD.domain.RecordRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class RecordControllerTest {


	Record record1 = new Record("Blygh", "123456", "25/10/1992", "0876774522");
	Record record2= new Record("bill", "1234567", "25/10/1993", "0876774523");
	Record record3 = new Record("bob", "12345678", "25/10/1994", "0876774524");
	Record record4 = new Record("tom", "12345698", "25/10/1991", "0876774525");
	

	@Autowired
	private RecordRepository recordsRepository;

	@Test
	public void testCreateRecord() throws JsonProcessingException {

		Record record = new Record("Blygh", "123456", "25/10/1992", "0876774522");
		recordsRepository.save(record);

		assertEquals(record.getPps(), record1.getPps());
	}
	
	@Test
	public void testDeleteRecord() throws JsonProcessingException {

		recordsRepository.save(record4);
		recordsRepository.save(record1);
		recordsRepository.save(record2);
		recordsRepository.save(record3);

		assertEquals("Should be 4 Records before deletion", 4, recordsRepository.count());
		recordsRepository.delete(record1);
		assertEquals("Should be 3 Records after deletion", 3, recordsRepository.count());

	}

}