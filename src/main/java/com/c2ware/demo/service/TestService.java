package com.c2ware.demo.service;

import com.c2ware.demo.dto.CustomerDto;
import com.c2ware.demo.entity.CustomerEntity;
import com.c2ware.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestService {
    private final CustomerRepository customerRepository;

    @Transactional
    public CustomerDto createCustomer(CustomerDto dto) {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .loginId(dto.getLoginId())
                .name(dto.getName())
                .build();

        customerEntity = customerRepository.save(customerEntity);

        return CustomerDto.builder()
                .id(customerEntity.getId())
                .loginId(customerEntity.getLoginId())
                .name(customerEntity.getName())
                .build();
    }

    public List<CustomerDto> getCustomers() {
        List<CustomerEntity> customers = customerRepository.findAll();

        return customers.stream().map(c -> {
            return CustomerDto.builder()
                    .id(c.getId())
                    .loginId(c.getLoginId())
                    .name(c.getName())
                    .build();
        }).collect(Collectors.toList());
    }

    @Transactional
    public CustomerDto updateCustomer(CustomerDto dto) {
        Optional<CustomerEntity> opCustomerEntity = customerRepository.findById(dto.getId());
        if (opCustomerEntity.isEmpty()) {
            return null;
        }

        CustomerEntity customerEntity = opCustomerEntity.get();
        if (!Objects.isNull(dto.getLoginId())) {
            customerEntity.setLoginId(dto.getLoginId());
        }

        if (!Objects.isNull(dto.getName())) {
            customerEntity.setName(dto.getName());
        }

        customerEntity = customerRepository.save(customerEntity);

        return CustomerDto.builder()
                .id(customerEntity.getId())
                .loginId(customerEntity.getLoginId())
                .name(customerEntity.getName())
                .build();
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public String deleteAllCustomer() {
        List<CustomerEntity> customers = customerRepository.findAll();

        if (customers.isEmpty()) {
            return "삭제할 데이터가 없습니다.";
        } else {
            customerRepository.deleteAll();
            return "모든 데이터를 삭제하였습니다.";
        }

    }
}
