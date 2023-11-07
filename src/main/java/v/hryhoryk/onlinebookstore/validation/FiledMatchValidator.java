package v.hryhoryk.onlinebookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class FiledMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String[] fields;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.fields = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        Object firstFieldValue = beanWrapper.getPropertyValue(fields[0]);
        for (int i = 1; i < fields.length; i++) {
            Object fieldValue = beanWrapper.getPropertyValue(fields[i]);
            if (fieldValue == null || !fieldValue.equals(firstFieldValue)) {
                return false;
            }
        }
        return true;
    }
}
