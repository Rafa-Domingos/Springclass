package com.rafael.springclass.services.validation;

import com.rafael.springclass.domain.enums.CustomerType;
import com.rafael.springclass.dto.NewCustomerDTO;
import com.rafael.springclass.repositories.CustomerRepository;
import com.rafael.springclass.resources.exceptions.FieldMessage;
import com.rafael.springclass.services.validation.Utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, NewCustomerDTO> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void initialize(final CustomerInsert customerInsert) {

    }

    @Override
    public boolean isValid(final NewCustomerDTO newCustomerDTO,
                           final ConstraintValidatorContext constraintValidatorContext) {
        final List<FieldMessage> list = new ArrayList<>();

        if (newCustomerDTO.isNatural() && !BR.isValidCPF(newCustomerDTO.getDocument())) {
            list.add(new FieldMessage("document", "Invalid document for a Natural person"));
        } else if (newCustomerDTO.isLegal() && !BR.isValidCNPJ(newCustomerDTO.getDocument())) {
            list.add(new FieldMessage("document", "Invalid document for a Legal person"));
        }

        if (customerRepository.findByEmail(newCustomerDTO.getEmail()) != null) {
            list.add(new FieldMessage("email", newCustomerDTO.getEmail() + " already exists"));
        }

        for (final FieldMessage fieldMessage : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
                    .addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
