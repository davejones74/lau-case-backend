package uk.gov.hmcts.reform.laubackend.cases.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import uk.gov.hmcts.reform.laubackend.cases.repository.CaseViewAuditRepository;
import uk.gov.hmcts.reform.laubackend.cases.service.CaseViewService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@SuppressWarnings({"PMD.UnusedPrivateField"})
public class GetWelcomeTest {

    @Autowired
    private transient MockMvc mockMvc;

    @MockBean
    private CaseViewService caseViewService;

    @MockBean
    private CaseViewAuditRepository caseViewAuditRepository;

    @DisplayName("Should welcome upon root request with 200 response code")
    @Test
    public void welcomeRootEndpoint() throws Exception {
        MvcResult response = mockMvc.perform(get("/")).andExpect(status().isOk()).andReturn();

        assertThat(response.getResponse().getContentAsString()).startsWith("Welcome");
    }
}
