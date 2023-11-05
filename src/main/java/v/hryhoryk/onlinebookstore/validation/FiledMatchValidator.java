package v.hryhoryk.onlinebookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class FiledMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String[] fields;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.fields = new String[]
                {constraintAnnotation.firstField(), constraintAnnotation.secondField()};
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        for (int i = 0; i < fields.length - 1; i++) {
            Object fieldValue = beanWrapper.getPropertyValue(fields[i]);
            Object nextFieldValue = beanWrapper.getPropertyValue(fields[i + 1]);
            if (fieldValue == null || !fieldValue.equals(nextFieldValue)) {
                return false;
            }
        }
        return true;
    }
}
