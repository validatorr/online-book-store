package v.hryhoryk.onlinebookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FiledMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstField;
    private String secondField;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstField = constraintAnnotation.firstField();
        this.secondField = constraintAnnotation.secondField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object firstFieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(firstField);
        Object secondFieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(secondField);
        return firstFieldValue != null && firstFieldValue.equals(secondFieldValue);
    }
}
