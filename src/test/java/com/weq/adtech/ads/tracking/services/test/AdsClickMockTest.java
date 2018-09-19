package com.weq.adtech.ads.tracking.services.test;

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
import com.weq.adtech.ads.tracking.model.Click;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdsClickMockTest {

	private MockMvc mockMvc;

	@Autowired
	private WeqAdsTrackingController controller;

	@Autowired
	private Gson gson;

	private Click click = new Click(new Date(), "fff54b83-49ff-476f-8bfb-2ec22b252c32", "244cf0db-ba28-4c5f-8c9c-2bf11ee42988");

	private Click failureClick = new Click(new Date(), "fff54b83-49ff-476f-8bfb-2ec22b252c32", "55cf0db-ba28-4c5f-8c9c-2bf11ee42988");

	@Before
	public void setUp() { 
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testAdsClick() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ads/click").content(gson.toJson(click))
				.contentType(MediaType.APPLICATION_JSON);

		MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

		assertEquals(200, response.getStatus());
	}

	@Test
	public void testAdsClickFailure() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ads/click").content(gson.toJson(null))
				.contentType(MediaType.APPLICATION_JSON);

		MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

		assertEquals(400, response.getStatus());
	}

	@Test
	public void testAdsClickByNeverReceivedDelivery() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ads/click").content(gson.toJson(failureClick))
				.contentType(MediaType.APPLICATION_JSON);

		MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

		assertEquals(404, response.getStatus());
	}
}