package br.com.spring.mvc.basics.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.spring.mvc.basics.model.Product;

/**
 * This class makes the validation of the product bean. <br/>
 * 
 * <b>It doesn't has utility anymore because the validation bean is being used
 * to make the validations. But it was stored in the project as a matter of
 * documentation.</b>
 * 
 * @author mushira4
 *
 */
public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		return Product.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required");

		Product product = (Product) target;
		if (product.getPages() == 0) {
			errors.rejectValue("pages", "field.required");
		}

	}

}
