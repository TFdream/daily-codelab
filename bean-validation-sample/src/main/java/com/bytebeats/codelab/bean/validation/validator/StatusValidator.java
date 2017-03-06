package com.bytebeats.codelab.bean.validation.validator;

import com.bytebeats.codelab.bean.validation.annotation.Status;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class StatusValidator implements ConstraintValidator<Status, String> {
    private final List<String> ALL_STATUS = Arrays.asList("created", "paid", "shipped", "closed");

    @Override
    public void initialize(Status status) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (ALL_STATUS.contains(value))
            return true;
        return false;
    }
}