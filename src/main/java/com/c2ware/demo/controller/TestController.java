package com.c2ware.demo.controller;

import com.c2ware.demo.dto.CustomerDto;
import com.c2ware.demo.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping("hello")
    public String helloWorld() {
        return "Hello world !!!";
    }

    @GetMapping(path = "/customer")
    public List<CustomerDto> getCustomers() {
        return testService.getCustomers();
    }

    @PostMapping(path = "/customer")
    public CustomerDto createCustomer(@RequestBody CustomerDto requestDto) {
        return testService.createCustomer(requestDto);
    }

    @PostMapping(path = "/customer/update")
    public CustomerDto updateCustomer(@RequestBody CustomerDto requestDto) {
        return testService.updateCustomer(requestDto);
    }

    @DeleteMapping(path = "/customer/{id}")
    public String deleteCustomer(@PathVariable(value = "id", required = true) Long id) {
        testService.deleteCustomer(id);

        return "Success";
    }
}
