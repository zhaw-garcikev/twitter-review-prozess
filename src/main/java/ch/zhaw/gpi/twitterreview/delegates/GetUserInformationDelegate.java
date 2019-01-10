package ch.zhaw.gpi.twitterreview.delegates;

import ch.zhaw.gpi.twitterreview.resources.User;
import ch.zhaw.gpi.twitterreview.services.UserService;
import javax.inject.Named;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author kevingarcia
 */
@Named (value="getUserInformationAdapter")
public class GetUserInformationDelegate implements JavaDelegate {

    @Autowired
    private UserService userService;
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
       String anfrageStellenderBenutzer = (String) execution.getVariable("anfrageStellenderBenutzer");
        
       //Übergegeb den anfrageStellenderBenutzer
       User user = userService.getUser(anfrageStellenderBenutzer);
       
       //Prüfen ob das Ding Null ist
       if (user == null){
           
           //..Falls dieser der Fall sein sollte neues BPMN Error auswerwerfen
           throw new BpmnError("UserNotFound", "Kein Benutzer" +anfrageStellenderBenutzer +"gefunden");
       }
       
       //... sonst FirstName und eMail auslesen
       execution.setVariable("firsName", user.getFirstName());
       execution.setVariable("eMail", user.geteMail());
       
    }
    
}
