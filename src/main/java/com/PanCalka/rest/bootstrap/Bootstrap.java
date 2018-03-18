package com.PanCalka.rest.bootstrap;

import com.PanCalka.rest.domain.Category;
import com.PanCalka.rest.domain.Customer;
import com.PanCalka.rest.repositories.CategoryRepository;
import com.PanCalka.rest.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner{

    private CategoryRepository categoryRepository;

    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        Customer goodClient = new Customer();
        goodClient.setName("Ted");
        Customer badClient = new Customer();
        goodClient.setName("Fred");
        Customer angryClient = new Customer();
        goodClient.setName("Ed");
        Customer sadClient = new Customer();
        goodClient.setName("Ped");

        customerRepository.save(goodClient);
        customerRepository.save(badClient);
        customerRepository.save(angryClient);
        customerRepository.save(sadClient);
    }
}
