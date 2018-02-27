package com.rafael.springclass.services.validation;

import com.rafael.springclass.domain.Customer;
import com.rafael.springclass.dto.CustomerDTO;
import com.rafael.springclass.repositories.CustomerRepository;
import com.rafael.springclass.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerDTO> {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void initialize(final CustomerUpdate customerUpdate) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean isValid(final CustomerDTO customerDTO,
                           final ConstraintValidatorContext constraintValidatorContext) {
        final List<FieldMessage> list = new ArrayList<>();
        final Map<String, String> requestFields = (Map<String, String>) httpServletRequest
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        final Customer dbCustomer = customerRepository.findByEmail(customerDTO.getEmail());

        if (dbCustomer != null && !dbCustomer.getId().equals(Integer.parseInt(requestFields.get("id")))) {
            list.add(new FieldMessage("email", customerDTO.getEmail() + " already exists"));
        }

        for (final FieldMessage fieldMessage : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
                    .addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
