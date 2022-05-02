/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Matt
 */
@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String password = (String) value;
        if (!RegexHelper.HasMatch(password, "[0-9]")) {
            throw new ValidatorException(new FacesMessage("Password Must Include a Number"));
        }
        if (!RegexHelper.HasMatch(password, "[a-z]")) {
            throw new ValidatorException(new FacesMessage("Password Must Include a Lowercase Letter"));
        }
        if (!RegexHelper.HasMatch(password, "[A-Z]")) {
            throw new ValidatorException(new FacesMessage("Password Must Include an Uppercase Letter"));
        }

        UIInput passwordMatchInput = (UIInput) ComponentHelper.findComponent("password");
        String passwordMatchValue = (String) ComponentHelper.getComponentValue(passwordMatchInput);
        
        if (!password.equals(passwordMatchValue)) {
            throw new ValidatorException(new FacesMessage("Passwords must match"));
        }
    }
}
