package com.github.kellyihyeon.stanceadmin.presentation.bankdeposit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kellyihyeon.stanceadmin.application.bankdeposit.BankDepositTransactionService;
import com.github.kellyihyeon.stanceadmin.models.BankDepositInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

@WebMvcTest(BankDepositTransactionController.class)
class BankDepositTransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BankDepositTransactionService service;


    @Test
    @DisplayName("은행 입금 내역 등록 시 필수값을 누락하면 실패한다(400).")
    void 필수값을_누락한_경우_400() throws Exception {
        BankDepositInput invalidInput = new BankDepositInput();
        invalidInput.amount(null);
        invalidInput.type(BankDepositInput.TypeEnum.CASHBACK);
        invalidInput.depositDate(new Date());
        invalidInput.depositSource("체크카드 캐시백");
        invalidInput.description("test");

        String url = "/account-transactions/deposits/bank";
        String invalidRequestBody = objectMapper.writeValueAsString(invalidInput);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequestBody)

        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("은행 입금 내역 등록 시 필수값을 전달하면 성공한다.(201)")
    void 필수값을_전달한_경우_201() throws Exception {
        BankDepositInput validInput = new BankDepositInput();
        validInput.type(BankDepositInput.TypeEnum.CASHBACK);
        validInput.depositDate(new Date());
        validInput.amount((double) 1100);
        validInput.depositSource("체크카드 캐시백");
        validInput.description("test");

        String url = "/account-transactions/deposits/bank";
        String validRequestBody = objectMapper.writeValueAsString(validInput);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequestBody)

        )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}