package net.baallou.customerservice.mapper;

import net.baallou.customerservice.dto.CustomerDTO;
import net.baallou.customerservice.entities.Customer;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {
    // l'instancer directement parce que ça c'est pas
    // un composant spring, pas besoin d'injecter
    CustomerMapper underTest = new CustomerMapper();

    @Test
    public void shouldMapCustomerToCustomerDTO() {
        Customer givenCustomer = Customer.builder()
                .id(1L).lastName("baallou").firstName("hicham")
                .email("baallou@gmail.com").build();
        CustomerDTO expected = CustomerDTO.builder()
                .id(1L).lastName("baallou").firstName("hicham")
                .email("baallou@gmail.com").build();

        CustomerDTO result = underTest.fromCustomer(givenCustomer);

        assertThat(result).isNotNull();
        assertThat(expected).usingRecursiveComparison().isEqualTo(result);

    }

    @Test
    public void shouldMapCustomerDTOToCustomer() {
        CustomerDTO givenCustomerDTO = CustomerDTO.builder()
                .id(1L).lastName("baallou")
                .firstName("hicham")
                .email("baallou@gmail.com").build();
        Customer expected = Customer.builder()
                .id(1L).lastName("baallou")
                .firstName("hicham")
                .email("baallou@gmail.com").build();

        Customer result = underTest.fromCustomerDTO(givenCustomerDTO);

        assertThat(result).isNotNull();
        assertThat(expected).usingRecursiveComparison().isEqualTo(result);

    }

    @Test
    public void shouldMapListOFCustomersToListOfCustomers() {
        List<Customer> givenCustomers = List.of(
                Customer.builder()
                        .id(1L).lastName("baallou")
                        .firstName("hicham")
                        .email("baallou@gmail.com").build(),
                Customer.builder()
                        .id(2L).lastName("ouarssas")
                        .firstName("oumaima")
                        .email("oumaima@gmail.com").build(),
                Customer.builder()
                        .id(3L).lastName("lghrour")
                        .firstName("moaid")
                        .email("moaid@gmail.com").build()
        );
        List<CustomerDTO> expected = List.of(
                CustomerDTO.builder()
                        .id(1L).lastName("baallou")
                        .firstName("hicham")
                        .email("baallou@gmail.com").build(),
                CustomerDTO.builder()
                        .id(2L).lastName("ouarssas")
                        .firstName("oumaima")
                        .email("oumaima@gmail.com").build(),
                CustomerDTO.builder()
                        .id(3L).lastName("lghrour")
                        .firstName("moaid")
                        .email("moaid@gmail.com").build()
        );
        List<CustomerDTO> result = underTest.fromListCustomers(givenCustomers);
        assertThat(result).isNotNull();
        assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    public void shouldNotMapNullCustomerToCustomerDTO() {
        Customer givenCustomer = null;
        AssertionsForClassTypes.assertThatThrownBy(() -> underTest.fromCustomer(givenCustomer))
                .isInstanceOf(IllegalArgumentException.class);
    }
}