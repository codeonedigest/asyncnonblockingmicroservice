package com.cod.asyncmicroservice.business;


import com.cod.asyncmicroservice.domain.Customer;
import com.cod.asyncmicroservice.domain.FileData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncService {

    @Autowired
    CustomerService customerService;

    @Autowired
    FileService fileService;

    @Async
    public CompletableFuture<List<Customer>> getCustomerByName(String name) {
        log.info("Getting customer {} using Asynch thread", name);
        List<Customer> customerList = customerService.getCustomerByName(name);
        return CompletableFuture.completedFuture(customerList);
    }

    @Async
    public CompletableFuture<Customer> saveCustomer(Customer customer) {
        log.info("Saving customer {} using Asynch thread", customer.getName());
        Customer newCustomer = customerService.addCustomer(customer);
        return CompletableFuture.completedFuture(newCustomer);
    }

    @Async
    public CompletableFuture<String> readFile() {
        log.info("Reading the file using Asynch thread");
        String fileData = fileService.readFile();
        return CompletableFuture.completedFuture(fileData);
    }

    @Async
    public CompletableFuture<Boolean> writeFile(FileData fileData) {
        log.info("Writing to file using Asynch thread");
        boolean result = fileService.writeFile(fileData);
        return CompletableFuture.completedFuture(result);
    }
}
