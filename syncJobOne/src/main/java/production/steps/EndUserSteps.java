package production.steps;

import java.io.IOException;

import net.thucydides.core.steps.ScenarioSteps;
import production.pages.GlueAccountPage;

/**
 * @author srinivasa.kuncha
 *
 */
@SuppressWarnings("serial")
public class EndUserSteps extends ScenarioSteps {

    GlueAccountPage dictionaryPage;

        public void supplyLoginCredientials(String username, String password){
    	dictionaryPage.supplyLogin_Credientials(username, password);
    	dictionaryPage.clickOnLogin();
    }

    public void is_the_home_page() {
        dictionaryPage.open();
    }

    public void migrationDataFile(String file) throws IOException{
    	    	
    	dictionaryPage.readfile(file);
    }
    
    public void verfiyOrdersuccessfullyPlaced(){
    	System.out.println("           XXXXXXXXXXXXXXX             ORDERS PLACED SUCCESSFULLY       XXXXXXXXXXXXXXXX           ");
    }
}