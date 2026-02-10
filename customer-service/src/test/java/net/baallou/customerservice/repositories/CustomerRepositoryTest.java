package net.baallou.customerservice.repositories;

import net.baallou.customerservice.entities.Customer;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        System.out.println("----------------------------------------");
        customerRepository.save(Customer.builder().firstName("Hicham")
                .lastName("Baallou").email("hicham@gmail.com").build());
        customerRepository.save(Customer.builder().firstName("Mohamed")
                .lastName("Hamdaoui").email("mohamed@gmail.com").build());
        customerRepository.save(Customer.builder().firstName("Rida")
                .lastName("Tahiri").email("rida@gmail.com").build());
        System.out.println("----------------------------------------");
    }

    @Test
    public void shouldFindCustomerByEmail() {
        String givenEmail = "hicham@gmail.com";
        Optional<Customer> result = customerRepository.findByEmail(givenEmail);
        assertThat(result).isPresent();
    }

    @Test
    public void shouldNotFindCustomerByEmail() {
        String givenEmail = "xxxx@gmail.com";
        Optional<Customer> result = customerRepository.findByEmail(givenEmail);
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldFindCustomersByFirstName() {
        String keyword = "m";
        List<Customer> expected = List.of(
                Customer.builder().firstName("Hicham")
                        .lastName("Baallou").email("hicham@gmail.com").build(),
                Customer.builder().firstName("Mohamed")
                        .lastName("Hamdaoui").email("mohamed@gmail.com").build()
        );
        List<Customer> result = customerRepository.findByFirstNameContainsIgnoreCase(keyword);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(expected.size());
        assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);

    }
}