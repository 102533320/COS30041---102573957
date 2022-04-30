/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Matt
 */
public class RegexHelper {

    static public boolean HasMatch(String text, String pattern) {
        return Pattern.compile(pattern)
                .matcher(text)
                .find();
    }
}
