    package com.PanCalka.rest.controllers;

    import com.PanCalka.rest.api.v1.model.CustomerDTO;
    import com.PanCalka.rest.services.CustomerService;
    import org.junit.Before;
    import org.junit.Test;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.MockitoAnnotations;
    import org.springframework.http.MediaType;
    import org.springframework.test.web.servlet.MockMvc;
    import org.springframework.test.web.servlet.setup.MockMvcBuilders;

    import java.util.Arrays;

    import static com.PanCalka.rest.controllers.AbstractRestControllerTest.asJsonString;
    import static org.hamcrest.Matchers.equalTo;
    import static org.mockito.ArgumentMatchers.any;
    import static org.mockito.ArgumentMatchers.anyLong;
    import static org.mockito.Mockito.verify;
    import static org.mockito.Mockito.when;
    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    public class CustomerControllerTest {

        @Mock
        CustomerService customerService;

        @InjectMocks
        CustomerController customerController;

        MockMvc mockMvc;

        @Before
        public void setUp() throws Exception {
            MockitoAnnotations.initMocks(this);

            mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        }

        @Test
        public void testListCustomers() throws Exception {

            //given
            CustomerDTO customer1 = new CustomerDTO();
            customer1.setFirstName("Tom");
            customer1.setName("Joe");
            customer1.setCustomerUrl("/api/v1/customer/1");

            CustomerDTO customer2 = new CustomerDTO();
            customer2.setFirstName("Joe");
            customer2.setName("Tom");
            customer2.setCustomerUrl("/api/v1/customer/2");

            when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

            mockMvc.perform(get("/api/v1/customers/")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

        @Test
        public void testGetCustomerById() throws Exception {

            //given
            CustomerDTO customer1 = new CustomerDTO();
            customer1.setFirstName("Michale");
            customer1.setName("Weston");

            when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

            //when
            mockMvc.perform(get("/api/v1/customers/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

        @Test
        public void createNewCustomer() throws Exception {
            //given
            CustomerDTO customer = new CustomerDTO();
            customer.setFirstName("Barnie");
            customer.setName("Flintstone");

            CustomerDTO returnDTO = new CustomerDTO();
            returnDTO.setFirstName(customer.getFirstName());
            returnDTO.setName(customer.getName());
            returnDTO.setCustomerUrl("/api/v1/customers/1");

            when(customerService.createNewCustomer(customer)).thenReturn(returnDTO);

            //expected
            mockMvc.perform(post("/api/v1/customers/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customer)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.firstName", equalTo("Barnie")))
                    .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
        }

        @Test
        public void shouldUpdateCustomer() throws Exception {
            //given
            CustomerDTO customer = new CustomerDTO();
            customer.setFirstName("Fred");
            customer.setName("Flintstone");

            CustomerDTO returnDTO = new CustomerDTO();
            returnDTO.setFirstName(customer.getFirstName());
            returnDTO.setName(customer.getName());
            returnDTO.setCustomerUrl("/api/v1/customers/1");

            when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

            //expected
            mockMvc.perform(put("/api/v1/customers/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customer)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.firstName", equalTo("Fred")))
                    .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
        }

        @Test
        public void shouldPatchCustomer() throws Exception {
            //given
            CustomerDTO customer = new CustomerDTO();
            customer.setFirstName("Fred");

            CustomerDTO returnDTO = new CustomerDTO();
            returnDTO.setFirstName(customer.getFirstName());
            returnDTO.setName("Flinstone");
            returnDTO.setCustomerUrl("/api/v1/customers/1");

            when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

            //expected
            mockMvc.perform(patch("/api/v1/customers/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customer)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.firstName", equalTo("Fred")))
                    .andExpect(jsonPath("$.name", equalTo("Flinstone")))
                    .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
        }

        @Test
        public void shouldDeleteCustomer() throws Exception {
            // given
            mockMvc.perform(delete("/api/v1/customers/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            //expected
            verify(customerService).deleteCustomerById(anyLong());
        }
    }