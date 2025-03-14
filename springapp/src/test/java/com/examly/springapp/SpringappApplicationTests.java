package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class SpringappApplicationTests {

	@Autowired
    private MockMvc mockMvc;
	
	@Test
	@Order(1)
	public void testPostData() throws Exception {
		String st = "{\"id\": 1, \"name\": \"SampleEmployee\",\"address\": \"SampleAddress\",\"phoneNumber\": \"93371023233\",\"email\": \"sample@gmail.com\",\"jobTitle\": \"Manager\",\"department\": \"Finance\",\"salary\":30000, \"hireDate\": \"2020-12-12\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/employee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(st)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(jsonPath("$.name").value("SampleEmployee"))
				.andExpect(jsonPath("$.email").value("sample@gmail.com"))
				.andExpect(jsonPath("$.jobTitle").value("Manager"))
				.andExpect(jsonPath("$.department").value("Finance"))
				.andReturn();
	}
	

    @Test
	@Order(3)
    public void testGetEmployeeByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("SampleEmployee"))
				.andExpect(jsonPath("$.email").value("sample@gmail.com"))
                .andReturn();
    }

    @Test
	@Order(2)
    public void testGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employee")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[*].name").exists()) // Check if 'name' exists for all entries
                .andExpect(jsonPath("$[*].department").exists()) 
                .andExpect(jsonPath("$[*].email").exists())

                .andReturn();
    }
    
    @Test
	@Order(4)
    public void testGetEmployeeByHireDate() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/hired/2020-12-12")                
        		.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[*].name").exists()) 
                .andExpect(jsonPath("$[*].department").exists())
                .andExpect(jsonPath("$[*].hireDate").exists())
                .andReturn();
    }
    
    
    @Test
	@Order(5)
    public void testGetFirstThreeCharacterNames() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/first-three-characters-of-name")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").value("Sam")) // Assuming the first name starts with "Sam"
                .andExpect(jsonPath("$[*]").value(org.hamcrest.Matchers.everyItem(org.hamcrest.Matchers.hasLength(3)))) // Verify all strings have length 3
                .andReturn();
    }

	
	@Test
	public void testControllerFile() {
		String filePath = "src/main/java/com/examly/springapp/controller/EmployeeController.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
	
	@Test
	public void testModelFile() {
		String filePath = "src/main/java/com/examly/springapp/model/Employee.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
	
	@Test
	public void testRepositoryFile() {
		String filePath = "src/main/java/com/examly/springapp/repository/EmployeeRepo.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
	
	@Test 
	   public void testModelFolder() { 
	       String directoryPath = "src/main/java/com/examly/springapp/model"; // Replace with the path to your directory 
	       File directory = new File(directoryPath); 
	       assertTrue(directory.exists() && directory.isDirectory()); 
	   }

	@Test
	public void testServiceFile() {
		String filePath = "src/main/java/com/examly/springapp/service/EmployeeService.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
	
	
}
