/**
 * EX - SFSB - Count - demo
 *
 * Purpose : A simple stateful bean to count the number of times a client calls the getGreetings()
 *
 * EJB - Stateful Session Bean - CountBean
 *
 * This is the stateful bean
 */
package session;

import java.util.ArrayList;
import javax.ejb.Stateful;


@Stateful
public class CountBean implements CountBeanRemote {

    int counter; // count how many times getGreetings() been called

    /*
    * emulate a shopping cart
    * (a word added to the wordList = an item added to the shopping cart)
     */
    private ArrayList<String> wordList;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public CountBean() {
        counter = 0;
        wordList = new ArrayList<>();
    }

    @Override
    public String getGreetings(String userName) {
        String hello;

        count();
        if (userName == null) {
            hello = "Hello, World!";
        } else {
            hello = "Hello, " + userName + "!";
        }

        return hello + " Number of calls : " + counter;
    }

    private void count() {
        counter++;
    }

    @Override
    public boolean addWord(String word) {
        boolean result = false;

        try {
            wordList.add(word);
            result = true;
        } catch (Exception ex) {
            // do nothing - should not be here
        }

        return result;
    }

    @Override
    public ArrayList<String> getWordList() {
        return wordList;
    }

}
