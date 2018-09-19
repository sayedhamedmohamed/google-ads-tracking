package com.weq.adtech.ads.tracking.services.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.weq.adtech.ads.tracking.controller.WeqAdsTrackingController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdsStatisticsMockTest {

	private MockMvc mockMvc;

	@Autowired
	private WeqAdsTrackingController controller;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testAdsStatistics() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ads/statistics?start=2018-01-\n" + 
				"07T14:30:00+0000&end=2018-01-07T20:20:00+0000");

		MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

		assertEquals(200, response.getStatus());

	}

	@Test
	public void testAdsStatisticsGroupByBrowser() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ads/statistics?start=2018-01-\n" + 
				"07T14:30:00+0000&end=2018-01-07T20:20:00+0000&group_by=browser");

		MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

		assertEquals(200, response.getStatus());

	}

	@Test
	public void testAdsStatisticsGroupByBrowserAndOs() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ads/statistics?start=2018-01-\n" + 
				"07T14:30:00+0000&end=2018-01-07T20:20:00+0000&group_by=browser&&group_by=os");

		MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

		assertEquals(200, response.getStatus());

	}
}