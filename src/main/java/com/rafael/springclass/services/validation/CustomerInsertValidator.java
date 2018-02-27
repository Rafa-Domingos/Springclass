package com.rafael.springclass.services.validation;

import com.rafael.springclass.domain.enums.CustomerType;
import com.rafael.springclass.dto.NewCustomerDTO;
import com.rafael.springclass.resources.exceptions.FieldMessage;
import com.rafael.springclass.services.validation.Utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, NewCustomerDTO> {

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

        for (final FieldMessage fieldMessage : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
                    .addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
