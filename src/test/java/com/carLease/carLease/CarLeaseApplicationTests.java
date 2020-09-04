package com.carLease.carLease;

import com.carLease.carLease.controller.CreditApplicationController;
import com.carLease.carLease.model.CreditApplication;
import com.carLease.carLease.service.CreditApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CreditApplicationController.class)
class CarLeaseApplicationTests {

	@Autowired
	MockMvc mvc;

	@MockBean
	CreditApplicationService creditApplicationService;

	CreditApplication creditApplication;

	@BeforeEach
	public void setUp() {
		creditApplication = new CreditApplication();
		creditApplication.setId(1L);
		creditApplication.setLeasingDecisionStatus(true);
		creditApplication.setPersonIncome(300.00);
		creditApplication.setCoApplicantIncome(300.00);
		creditApplication.setVinNumber("4Y1SL65848Z411439");
		creditApplication.setCoApplicantId(3L);
		creditApplication.setPersonId(2L);
	}

	@Test
    public void getCreditApplication() throws Exception {
        when(creditApplicationService.getOne(anyLong())).thenReturn(creditApplication);
        mvc.perform(get("/api/credit-application/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.vinNumber", is("4Y1SL65848Z411439")))
                .andExpect(jsonPath("$.personId", is(2)))
                .andExpect(jsonPath("$.personIncome", is(300.00)))
                .andExpect(jsonPath("$.coApplicantId", is(3)))
                .andExpect(jsonPath("$.coApplicantIncome", is(300.00)));
    }

    @Test
    public void getCreditApplications() throws Exception{

        CreditApplication creditApplication2;
        creditApplication2 = new CreditApplication();
        creditApplication2.setId(4L);
        creditApplication2.setPersonIncome(209.00);
        creditApplication2.setCoApplicantIncome(15.00);
        creditApplication2.setLeasingDecisionStatus(false);
        creditApplication2.setVinNumber("1HGBH41JXMN109186");
		creditApplication2.setPersonId(5L);
        creditApplication2.setCoApplicantId(6L);

        ArrayList<CreditApplication> arr = new ArrayList<>();
        arr.add(creditApplication);
        arr.add(creditApplication2);


		when(creditApplicationService.getAll()).thenReturn(arr);
        mvc.perform(MockMvcRequestBuilders.get("/api/credit-application")
                .content(asJsonString(arr))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].leasingDecisionStatus", is(true)))
                .andExpect(jsonPath("$[0].vinNumber", is("4Y1SL65848Z411439")))
                .andExpect(jsonPath("$[0].personId", is(2)))
                .andExpect(jsonPath("$[0].personIncome", is(300.00)))
                .andExpect(jsonPath("$[0].coApplicantId", is(3)))
                .andExpect(jsonPath("$[0].coApplicantIncome", is(300.00)))
                .andExpect(jsonPath("$[1].id", is(4)))
                .andExpect(jsonPath("$[1].leasingDecisionStatus", is(false)))
                .andExpect(jsonPath("$[1].vinNumber", is("1HGBH41JXMN109186")))
                .andExpect(jsonPath("$[1].personId", is(5)))
                .andExpect(jsonPath("$[1].personIncome", is(209.00)))
                .andExpect(jsonPath("$[1].coApplicantId", is(6)))
                .andExpect(jsonPath("$[1].coApplicantIncome", is(15.00)));

    }

    @Test
	public void addCreditApplicationTest() throws Exception {
		when(creditApplicationService.save(Mockito.any(CreditApplication.class))).thenReturn(creditApplication);
		mvc.perform(MockMvcRequestBuilders.post("/api/credit-application")
				.content(asJsonString(creditApplication))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void makeDecisionTest(){
		CreditApplicationService tempService = new CreditApplicationService();
		tempService.makeDecision(creditApplication);
		assertTrue(creditApplication.isLeasingDecisionStatus());
	}

	@Test
	public void makeDecisionFalseTest(){
		CreditApplicationService tempService = new CreditApplicationService();
		creditApplication.setPersonIncome(299.00);
		tempService.makeDecision(creditApplication);
		assertFalse(creditApplication.isLeasingDecisionStatus());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
