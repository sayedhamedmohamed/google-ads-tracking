package com.weq.adtech.ads.tracking.services.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.weq.adtech.ads.tracking.controller.WeqAdsTrackingController;
import com.weq.adtech.ads.tracking.model.Delivery;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdsDeliveryMockTest {

	private MockMvc mockMvc;

	@Autowired
	private WeqAdsTrackingController controller;

	@Autowired
	private Gson gson;

	private Delivery delivery = new Delivery(4883, "244cf0db-ba28-4c5f-8c9c-2bf11ee42988", new Date(), "Chrome", "Android", "www.google.com");

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	public void testAdsDelivery() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ads/delivery").content(gson.toJson(delivery))
				.contentType(MediaType.APPLICATION_JSON);

		MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

		assertEquals(200, response.getStatus());

	}
}