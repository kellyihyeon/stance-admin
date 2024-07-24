package com.github.kellyihyeon.stanceadmin.presentation.eventdeposit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.kellyihyeon.stanceadmin.models.EventDepositInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventDepositTransactionController.class)
class EventDepositTransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("이벤트 입금 내역 등록 API 요청을 하면서 필수값을 누락한 경우 에러가 발생한다.")
    void 필수값이_누락된_경우() throws Exception {
        EventDepositInput invalidInput = new EventDepositInput();
        invalidInput.eventId(null);
        invalidInput.depositorIds(List.of(1L, 2L));
        invalidInput.depositDate(new Date());
        invalidInput.amount((double) 70000);
        invalidInput.description("test data!");

        String url = "/account-transactions/deposits/event";
        String json = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(invalidInput);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }
}