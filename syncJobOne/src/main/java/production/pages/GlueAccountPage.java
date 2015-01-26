package production.pages;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import net.thucydides.core.Thucydides;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.csv.CSVTestDataSource;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author srinivasa.kuncha
 * @Objective - To test correctness of customer accounts in terms of relationship, CCI sync and contacts association on Booking/Billing accounts
 */

  @DefaultUrl("https://eu1.salesforce.com/")

//@DefaultUrl("http://test.salesforce.com")
public class GlueAccountPage extends PageObject {
	
	long timeNow = System.currentTimeMillis();
	
//	String url = "https://dmgsalescloud--prodmirror.cs7.my.salesforce.com/";
	
	String url = "https://eu1.salesforce.com/";
	
	String order,rowNum;
	String billingType = "Agency";
	static String rcs;
	boolean parentBookingPresence, bookingPresence;
    @FindBy(xpath="//div/input")
    private WebElementFacade searchTerms;

    @FindBy(id="phSearchButton")
    private WebElementFacade lookupButton;

    private WebElementFacade username() 			{ return element(By.id("username"));															}
	private WebElementFacade password() 			{ return element(By.id("password"));															}
	private WebElementFacade loginbutton() 			{ return element(By.id("Login"));																}
	private WebElementFacade billingRef()        	{ return element(By.xpath("//*[@id='j_id0:j_id1:i:f:pb:d:Billing_AgenciesList.input']"));  		}
	private WebElementFacade clickNext() 			{ return element(By.id("j_id0:j_id1:i:f:pb:pbb:bottom:next"));									}
	private WebElementFacade createDirectOrder() 	{ return element(By.cssSelector("input[value='Create Direct Order']"));							}
	private WebElementFacade selectAgencyType()     { return element(By.id("j_id0:j_id1:i:f:pb:d:Agency_Type.input"));								}
	private WebElementFacade bookingSelection() 	{ return element(By.id("j_id0:j_id1:i:f:pb:d:Booking_AgenciesList.input"));						}
	private WebElementFacade parentBookingSel() 	{ return element(By.id("j_id0:j_id1:i:f:pb:d:ParentBooking_Agency.input"));						}
	private WebElementFacade billingSelection() 	{ return element(By.id("j_id0:j_id1:i:f:pb:d:Billing_Selection.input"));						}
	private WebElementFacade billingOption() 		{ return element(By.id("j_id0:j_id1:i:f:pb:d:Billing_Options.input"));							}
	private WebElementFacade billingOption1() 		{ return element(By.id("j_id0:j_id1:i:f:pb:d:Billing_Options_0.input"));						}
	private WebElementFacade selectBillingAgency() 	{ return element(By.id("j_id0:j_id1:i:f:pb:d:BillingAgencies.input"));							}
	private WebElementFacade searchContact()     	{ 	return element(By.id("j_id0:j_id1:i:f:pb:d:Prompt_Contact_Name.input")); 					}
	private WebElementFacade contactSelection()     { return element(By.xpath("//*[@id='j_id0:j_id1:i:f:pb:d:Contact.input']")); 					}
  
    public void supplyLogin_Credientials(String username, String password) {
    	waitFor(3).seconds();
    	getDriver().manage().window().maximize();
    	username().type(username);
    	password().type(password);
    }
    public void clickOnLogin(){
    	waitABit(1000);
    	loginbutton().click();
    	waitFor(8).seconds();
    }
    public void readfile(String fileloc) throws IOException{
    	
		File filePath = new File(fileloc);
		if (filePath.isFile()) {
			System.out.println("\n");
			System.out.println("         OREDRS THAT ARE SUCCESSFULLY CREATED ARE ");
			String file = filePath.getAbsolutePath();
			CSVTestDataSource testDataSrc = new CSVTestDataSource(file);
				for (Map<String, String> record : testDataSrc.getData()) 
				{
						try{
							waitFor(2).seconds();
							String str = record.get("accountType");
							rowNum = record.get("recordNo");
						
							String clinetuniqueID = record.get("uniqueID");
							String clientURL = url.concat(clinetuniqueID);
							getDriver().get(clientURL);
							waitFor(1).seconds();
							try {
						    	 WebDriverWait wait1 = new WebDriverWait(getDriver(), 3);
						    	 if(wait1.until(ExpectedConditions.alertIsPresent())!=null)
						    	      getDriver().switchTo().alert().accept();
						    	 }
						    	 catch (Exception x) {}
							waitFor(8).seconds();
							Thucydides.takeScreenshot();
							createDirectOrder().click();
							waitFor(5).seconds();
								if (str.equalsIgnoreCase("Brand"))
								{
										String selectionType = record.get("billingSelection");
										
										if (selectionType.equalsIgnoreCase("Parent"))
										{
											billingOption1().selectByVisibleText("Parent");
											clickNext().click();
											waitFor(1).second();
										}
										if (selectionType.equalsIgnoreCase("Agency"))
										{
											try{
											billingOption().selectByVisibleText(billingType);
											clickNext().click();
											}
											catch (Exception exp)
											{
												try{
													billingOption1().selectByVisibleText(billingType);
												}catch (Exception exp1){}
												
												clickNext().click();
											}
											waitFor(1).second();
											try {
													parentBookingSel().selectByVisibleText(record.get("bookingAgency"));
													waitFor(1).seconds();
												} catch (Exception e)
												{
													bookingSelection().selectByVisibleText(record.get("bookingAgency"));
													waitFor(1).seconds();
												}
											clickNext().click();
											waitFor(2).second();
											selectBillingAgency().selectByVisibleText(record.get("billingAgency"));
											clickNext().click();
										}
										
										if (selectionType.equalsIgnoreCase("Direct"))
										{
											billingOption().selectByVisibleText(billingType);
											clickNext().click();
											waitFor(1).second();
										}
																		}
								if (str.equalsIgnoreCase("Client") || str.equalsIgnoreCase("DMGT Group"))
								{
										String selectionType = record.get("agencyType");
										if (selectionType.equalsIgnoreCase("Booking Agency"))
										{
											try{
												selectAgencyType().selectByVisibleText(record.get("agencyType"));
												clickNext().click();
											}catch (Exception exp){clickNext().click();}
											waitFor(2).seconds();
											bookingSelection().selectByVisibleText(record.get("bookingAgency"));
											clickNext().click();
												waitFor(3).seconds();
											selectBillingAgency().selectByVisibleText(record.get("billingAgency"));
												waitFor(1).second();
											clickNext().click();
											waitFor(2).second();
										}
										else 
										{ 
											billingRef().selectByVisibleText(record.get("billingAgency"));
												waitFor(1).second();
											clickNext().click();
										}
								}
								if (str.equalsIgnoreCase("Direct Advertiser")|| str.equalsIgnoreCase("Charity")) 
								 {
											String billingType = record.get("billingSelection");
											if (billingType.equalsIgnoreCase("Direct"))
											{
												billingSelection().selectByVisibleText(billingType);
													waitFor(1).second();
												clickNext().click();
											}
											else 
											{
												billingSelection().selectByVisibleText(billingType);
													waitFor(1).second();
												clickNext().click();
													String selectionType = record.get("agencyType");
													if (selectionType.equalsIgnoreCase("Booking Agency"))
													{
														waitFor(2).second();
														bookingSelection().selectByVisibleText(record.get("bookingAgency"));
															waitFor(1).seconds();
														clickNext().click();
														waitFor(2).second();
														selectBillingAgency().selectByVisibleText(record.get("billingAgency"));
														waitFor(1).second();
														clickNext().click();
														waitFor(2).second();
													}
													else 
													{ 
														billingRef().selectByVisibleText(record.get("billingAgency"));
															waitFor(1).second();
														clickNext().click();
													}
											}
								 }
								waitFor(2).seconds();
								try
								{	
									searchContact().type(record.get("customerContact"));
									waitFor(1).second();
									clickNext().click();
										waitFor(3).second();
									contactSelection().selectByVisibleText(record.get("customerContact"));
										waitFor(1).second();
								
								} catch (Exception notfound)
									{
									contactSelection().selectByVisibleText(record.get("customerContact"));
										waitFor(1).second();
									}
							clickNext().click();
							System.out.println("       " +rowNum + " ---> Data looks OK" );
								waitFor(1).seconds();
								
/*************************************************************************************************/
					} catch (Exception e)
						{
//									e.getCause();
									System.out.println("       " +rowNum + " ---> " + "Sorry! DATA or Latency issue please see the report for more details" );
									Thucydides.takeScreenshot();
									 
						}
						try {
					    	 WebDriverWait wait1 = new WebDriverWait(getDriver(), 3);
					    	 if(wait1.until(ExpectedConditions.alertIsPresent())!=null)
					    	      getDriver().switchTo().alert().accept();
					    	 }
					    	 catch (Exception x) {}
				} 	}		
}
}