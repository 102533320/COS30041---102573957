/**
 * EX - SFSB - Count - demo
 *
 * Purpose : A simple stateful bean to count the number of times a client calls the getGreetings()
 *
 * EJB - Stateful Session Bean - CountBean
 *
 * This is the application client
 */
package session;

import java.util.ArrayList;
import javax.ejb.EJB;

public class CountAppClient {

    @EJB
    private static CountBeanRemote countBean;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        String user1 = null;
        String user2 = "World";
        String user3 = "Edmonds";
        String result;

        CountAppClient client = new CountAppClient();

        System.out.println("Calling with null");
        result = client.getGreetingResult(user1);
        System.out.println(result);

        System.out.println("Calling with World");
        result = client.getGreetingResult(user2);
        System.out.println(result);

        System.out.println("Calling with Edmonds");
        result = client.getGreetingResult(user3);
        System.out.println(result);

        String word1 = "Creating";
        String word2 = "Secure";
        String word3 = "and";
        String word4 = "Scalable";
        String word5 = "Software";

        client.addWord2List(word1);
        client.addWord2List(word2);
        client.addWord2List(word3);
        client.addWord2List(word4);
        client.addWord2List(word5);

        ArrayList<String> list = client.getWordList();

        for (String w : list) {
            System.out.println(w);
        }
    }

    public String getGreetingResult(String user) {
        return countBean.getGreetings(user);
    }

    public boolean addWord2List(String word) {
        return countBean.addWord(word);
    }

    public ArrayList<String> getWordList() {
        return countBean.getWordList();
    }

}
