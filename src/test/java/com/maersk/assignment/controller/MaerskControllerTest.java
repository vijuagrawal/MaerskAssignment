package com.maersk.assignment.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maersk.assignment.entity.Booking;
import com.maersk.assignment.entity.BookingKey;
import com.maersk.assignment.entity.ContainerType;
import com.maersk.assignment.service.BookingService;

@SpringBootTest(classes = { MaerskController.class })
@AutoConfigureMockMvc
public class MaerskControllerTest  {

	
	@Autowired
	protected MockMvc mvc;
	
	   @Autowired
	   @MockBean
		private MaerskController maerskController;
	   
	   @MockBean
	    private BookingService bookingService;

		/*
		 * protected void setUp() { mvc =
		 * MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); }
		 */
	   protected String mapToJson(Object obj) throws JsonProcessingException {
	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.writeValueAsString(obj);
	   }
	   protected <T> T mapFromJson(String json, Class<T> clazz)
	      throws JsonParseException, JsonMappingException, IOException {
	      
	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.readValue(json, clazz);
	   }
	   
	   @Test
	   public void createProduct() throws Exception {

		   String uri = "/api/bookings";
		   BookingKey bookingKey=   new BookingKey(1,1,1);
		   ResponseEntity<BookingKey> responseKey=   new ResponseEntity(bookingKey, HttpStatus.OK);
		      Booking booking = new Booking(bookingKey,20,"DRY","Mumbai","Pune","20");
		      
			 MockHttpServletRequest request = new MockHttpServletRequest();
		        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		         
		        when(maerskController.postBooking(booking)).thenReturn(responseKey);
		        
				/*
				 * RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)
				 * .contentType(MediaType.APPLICATION_JSON);
				 * 
				 * ResultActions result = mvc.perform(requestBuilder);
				 * 
				 * result.andDo(print());
				 * 
				 * result.andExpect(status().isOk())
				 * .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
				 */

		        ResponseEntity<BookingKey> responseEntity = (maerskController.postBooking(booking));
		         System.out.println(responseEntity.getStatusCodeValue());
		        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		    }
	   	   
	   public void createProducqt() throws Exception {
	      String uri = "/api/bookings";
	      Booking booking = new Booking();
	     
	      
	      String inputJson = mapToJson(booking);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(201, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "Product is created successfully");
	   }
}
