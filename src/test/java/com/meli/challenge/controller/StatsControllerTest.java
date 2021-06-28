package com.meli.challenge.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.meli.challenge.constant.ResourceEndpoint;
import com.meli.challenge.delegate.IStatsDelegate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StatsController.class)
class StatsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private IStatsDelegate statsDelegate;

  @Test
  void given_thenReturnStatics() throws Exception {
    mockMvc.perform(
        get(ResourceEndpoint.STATS)
    ).andExpect(status().isOk());
  }
}