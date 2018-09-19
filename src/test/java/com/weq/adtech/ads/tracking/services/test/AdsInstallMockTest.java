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
import com.weq.adtech.ads.tracking.model.Install;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdsInstallMockTest {

	private MockMvc mockMvc;

	@Autowired
	private WeqAdsTrackingController controller;

	@Autowired
	private Gson gson;

	private Install install = new Install("144cf0db-ba28-4c5f-8c9c-2bf11ee42988", "fff54b83-49ff-476f-8bfb-2ec22b252c32", new Date());

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testAdsInstall() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ads/install").content(gson.toJson(install))
				.contentType(MediaType.APPLICATION_JSON);

		MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

		assertEquals(200, response.getStatus());
	}	
}