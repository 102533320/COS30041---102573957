/**
 * EX - SFSB - Count - demo
 *
 * Purpose : A simple stateful bean to count the number of times a client calls the getGreetings()
 *
 * EJB - Stateful Session Bean - CountBean
 *
 * This is the remote interface for the stateful bean
 */
package session;

import java.util.ArrayList;
import javax.ejb.Remote;


@Remote
public interface CountBeanRemote {
    
    public String getGreetings(String userName);

    public boolean addWord(String word);
    
    public ArrayList<String> getWordList();

    
}
