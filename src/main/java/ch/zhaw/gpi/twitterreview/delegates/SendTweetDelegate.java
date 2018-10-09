package ch.zhaw.gpi.twitterreview.delegates;

import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * Implementation des Service Tasks 'Tweet senden'
 * 
 * @author kevingarcia
 */
@Named("sendTweetAdapter")
public class SendTweetDelegate implements JavaDelegate {

    /**
     * 1. Prozessvariable tweetContent wird ausgelesen
     * 2. Dieser Text wird in der Konsole ausgegeben
     * 
     * @param execution            Objekt, welches die Verknüpfung zur Process
     * Engine und aktuellen Execution enthält
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String tweetContent = (String) execution.getVariable("tweetConten");
        System.out.println("!!!!!!!!!!!!!!!!! Folgender Tweet wird veröffentlicht:"
                + tweetContent);
    }
         
}
