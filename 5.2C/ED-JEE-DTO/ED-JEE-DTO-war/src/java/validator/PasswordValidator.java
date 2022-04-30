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
        if (!RegexHelper.HasMatch((String)value, "[0-9]")) throw new ValidatorException(new FacesMessage("Password Must Include a Number"));
        if (!RegexHelper.HasMatch((String)value, "[a-z]")) throw new ValidatorException(new FacesMessage("Password Must Include a Lowercase Letter"));
        if (!RegexHelper.HasMatch((String)value, "[A-Z]")) throw new ValidatorException(new FacesMessage("Password Must Include an Uppercase Letter"));
        
        UIInput passwordMatchInput = (UIInput)component.getParent().findComponent("password");
        String passwordMatchValue = (String) passwordMatchInput.getSubmittedValue();
        if (!((String)value).equals(passwordMatchValue)) throw new ValidatorException(new FacesMessage("Passwords must match"));
    }
}