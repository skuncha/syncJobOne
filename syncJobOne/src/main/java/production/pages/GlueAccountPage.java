package production.pages;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import net.thucydides.core.Thucydides;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.csv.CSVTestDataSource;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author srinivasa.kuncha
 *
 */
//@DefaultUrl("http://test.salesforce.com")
@DefaultUrl("https://eu1.salesforce.com/")
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
	private WebElementFacade selectAnyContact()     { return element(By.xpath("//*[@id='j_id0:j_id1:i:f:pb:d:Contact.input']/option[2]")); 			}
	private WebElementFacade finish() 				{ return element(By.id("j_id0:j_id1:i:f:pb:pbb:bottom:finish"));								}
	private WebElementFacade orderStatus()   		{ return element(By.id("Order.Identification:order.statusCode"));								}
	private WebElementFacade orderPurchaseNo()   	{ return element(By.id("Order.Identification:order.purchaseOrderNo"));							}
    private WebElementFacade orderNote()         	{ return element(By.id("Order.Identification:order.note"));					   					}
    private WebElementFacade orderUrgentNote()   	{ return element(By.id("Order.Identification:order.message"));									}
    private WebElementFacade orderSalesRepId()   	{ return element(By.id("Order.SalesTerritory:order.primarySalesRep.id"));						}
    private WebElementFacade orderSalesTerritory()  { return element(By.xpath("//*[@id='MaterialAccordion']/div/div[2]/div/div[2]/div/fieldset/div[2]/div/div/span/button/i"));	}
    private WebElementFacade orderSTInput()			{ return element(By.xpath("//*[@id='salesforce-plugin']/div[2]/div/div/form/div[2]/div/table/thead/tr[2]/th[1]/input")); 	}
    private WebElementFacade orderSTselect()			{ return element(By.xpath("//*[@id='salesforce-plugin']/div[2]/div/div/form/div[2]/div/table/tbody/tr/td[1]/span")); 	}
    private WebElementFacade addPackage()   	 	{ return element(By.xpath("//h4/a/span[1]/input"));												}
    private WebElementFacade title() 			 	{ return element(By.id("Order.Schedule:sched.titleCode"));										}
    private WebElementFacade selectPublication() 	{ return element(By.id("Order.Schedule:sched.publicationCode"));								}
    private WebElementFacade webSiteCategory()   	{ return element(By.id("Order.Schedule:sched.webSiteCategoryCode"));							}
    private WebElementFacade selectSection()     	{ return element(By.id("Order.Schedule:sched.sectionCode"));									}
    private WebElementFacade webSite()   		 	{ return element(By.id("Order.Schedule:sched.webSiteCode"));									}
    private WebElementFacade selectSubSection()  	{ return element(By.id("Order.Schedule:sched.subSectionCode"));									}
    private WebElementFacade selectZone()   	 	{ return element(By.id("Order.Schedule:"));														}
    private WebElementFacade distribution()   	 	{ return element(By.id("rder.Schedule:sched.distributionCode"));								}
    private WebElementFacade noOfInserts()   	 	{ return element(By.id("Order.Schedule:sched.noOfInserts"));									}
    private WebElementFacade saveOrder()   	     	{ return element(By.xpath("//nav[button='Save']/button[2]"));									}
    private WebElementFacade selectModule()   	 	{ return element(By.id("Order.Schedule.Material:material.moduleCode"));							}
    private WebElementFacade productionNote()   	{ return element(By.id("Order.Schedule.Material:material.productionNote"));						}
    private WebElementFacade ABCopySplit()			{ return element(By.id(":propval.propertyBoolValue"));											}
    private WebElementFacade regionalCopySplit()   	{ return element(By.id("Order.Properties:Regional Copy Split"));								}
    private WebElementFacade spreadAd()   			{ return element(By.id("Order.Schedule.Material:material.isSpreadAd"));							}
    private WebElementFacade color()   				{ return element(By.id("Order.Schedule.Material:material.bookedCMYKColors "));					}
    private WebElementFacade pageRequest()   		{ return element(By.id("Order.Schedule:schedule.pageRequest"));									}
    private WebElementFacade nextMonth()   	 	 	{ return element(By.xpath("//thead/tr[1]/th/span[4]/span[3]/i"));								}
    private WebElementFacade selectPrice()   	 	{ return element(By.xpath(".//*[@id='SchedulingAccordion']/div/div[3]/div[1]/h4/a/span"));		}
    private WebElementFacade selectRevenue()   	 	{ return element(By.id("Order.Price:UserRevenue"));												}
    private WebElementFacade updateRevenue()   	 	{ return element(By.xpath("//fieldset/div/div[2]/div/div/span/button"));						}
    private WebElementFacade acceptOrder()   	 	{ return element(By.xpath("//nav[button='Accept']/button[3]"));									} 
    private WebElementFacade orderID()      	 	{ return element(By.xpath(".//*[@id='SchedulingAccordion']/div/div[1]/div[2]/div/div[2]/fieldset/div[1]/div/p")); }
    private WebElementFacade selectHeight()     	{ return element(By.xpath("//*[@id='Order.Schedule.Material:material.bookedHeight']"));			}
    private WebElementFacade selectWidth()     		{ return element(By.xpath("//*[@id='Order.Schedule.Material:material.bookedWidth']"));			}
  
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
											}catch (Exception exp){clickNext().click();}
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
								
/********************************************************************************************************************************************************************************************************************************************/							
//								finish().click();
							
		//************** Launch OrderPlugin and Create Order*************************************************//*	
							
//						 	 waitFor(14).seconds();
				/*	     	 	 String sourceURL =getDriver().findElement(By.xpath("//*[@id='j_id0:j_id8']/div[2]/iframe")).getAttribute("src");
					     	 	 String[] firstsplit = sourceURL.split("authorizationcode=");
					     	 	 String[] secondpartsplit = firstsplit[1].split("\\&clientid=");
								 String acode = secondpartsplit[0];
								 String add = "&clientid=";
								 acode =record.get("authCode");
								 String date = record.get("addDate");
								 String redirect = "&bookings=[{\"packagename\":\"DM Display\",\"dates\":[\""+ date + "\"]}]";
								 String OrderURL=firstsplit[0].concat(acode).concat(add).concat(secondpartsplit[1]).concat(redirect);
								 getDriver().get(OrderURL);
								 waitFor(12).seconds(); 
								 
								  
		*//************************************ Supply Order Details ******************************************//*
					    	 orderPurchaseNo().sendKeys(record.get("PONumber"));
					    	 orderNote().sendKeys(record.get("orderNote"));
					    	 orderUrgentNote().sendKeys(record.get("urgentNote"));
					    	 orderStatus().selectByVisibleText(record.get("orderStatus"));
					    	 String salesrep = record.get("salesPerson");	
					    	 	if (!" ".equals(salesrep))
					    	 	{
					    	 		orderSalesRepId().selectByVisibleText(record.get("salesPerson"));
					    	 		waitFor(1).second();
					    	 	}
					    	 	orderSalesTerritory().click();
					    		waitFor(2).seconds();
					    		orderSTInput().type(record.get("salesTerritory"));
					    		waitFor(1).seconds();
					    		orderSTselect().click();
					    		waitFor(1).seconds();
					    	 order = orderID().getText();
					    	 waitFor(1).second();
					    	 Thucydides.takeScreenshot();
		*//************************************ Supply Package Details*****************************************//*
					    	 addPackage().click();  	
					    	 		waitFor(4).seconds();
					    	 addPackage().click(); 
					    	 		waitFor(4).seconds();
						     selectPublication().selectByVisibleText(record.get("publication"));
						    	 	waitFor(3).seconds();
						     selectSection().selectByVisibleText(record.get("section"));
						    	 	waitFor(5).seconds();
						     selectZone().selectByVisibleText(record.get("zones"));
						    	 	waitFor(5).seconds();
						     selectSubSection().selectByVisibleText(record.get("subsection"));
						    		waitFor(5).seconds();
						     productionNote().sendKeys(record.get("productionNote"));
								 	waitFor(1).seconds();
						     selectModule().selectByVisibleText(record.get("module"));
							    	waitFor(5).seconds();
							 String zoneType = record.get("zones");
							 		
							 		 rcs = record.get("regionalCopySplit");
							 		if (rcs.equalsIgnoreCase("England & Walescot & N Ire")){
							 			rcs = "England & Wales\\Scot & N Ire";
							 		}
							 		if (rcs.equalsIgnoreCase("England & Walescot Ireire")){
							 			rcs = "England & Wales\\Scot\\N Ire\\Eire";
							 		}
							 		if (rcs.equalsIgnoreCase("Nat ex Lonon Only")){
							 			rcs = "Nat ex Lon\\Lon Only";
							 		}
							 		if (rcs.equalsIgnoreCase("Nat ex N Ire and Scot Ire Onlycot Only")){
							 			rcs = "Nat ex N Ire and Scot\\N Ire Only\\Scot Only";
							 		}
							 		if (rcs.equalsIgnoreCase("Nat ex N Ire Ire Only")){
							 			rcs = "Nat ex N Ire\\N Ire Only";
							 		}
							 		if (rcs.equalsIgnoreCase("Nat ex Scotcot Only")){
							 			rcs = "Nat ex Scot\\Scot Only";
							 		}
							 		if (rcs.equalsIgnoreCase("Nthth")){
							 			rcs = "Nth\\Sth";
							 		}
							 		if (rcs.equalsIgnoreCase("Nththcot")){
							 			rcs = "Nth\\Sth\\Scot";
							 		}
							 		if (rcs.equalsIgnoreCase("Nth ex Scotcot Only")){
							 			rcs = "Nth ex Scot\\Scot Only";
							 		}
							 		if (rcs.equalsIgnoreCase("Sth ex Lonon Only")){
							 			rcs = "Sth ex Lon\\Lon Only";
							 		}
							 		if (zoneType.equalsIgnoreCase("01. National") || zoneType.equalsIgnoreCase("02. North") || zoneType.equalsIgnoreCase("03. South"))
							 		{
								 		if (!" ".equals(rcs))
								 		{
								 			 waitFor(1).second();
								 			 regionalCopySplit().selectByVisibleText(rcs);
											 waitFor(2).seconds();
								 		}	
								 	}
							 		String ABcopySplitValue = record.get("copySplit");
							 		if (ABcopySplitValue.equalsIgnoreCase("Y"))
							 		{
							 			ABCopySplit().click();
							 			waitFor(2).second();
							 		}
					    	 Thucydides.takeScreenshot();
					    	
		*//************************************ Supply Price Details ******************************************//*
					    	 selectPrice().click();
					    	 		waitFor(2).seconds();
					    	 selectRevenue().sendKeys(record.get("revenue"));
					    	 		waitFor(2).seconds();
					    	 updateRevenue().click(); 
					    	 		waitFor(4).seconds();
					    	 Thucydides.takeScreenshot();	
		*//************************************ Accept Order *************************************************//*	
					    	 System.out.println("       " +rowNum + " . " + " Order ID : " +order );	
*/
/********************************************************************************************************************************************************************************************************************************************/	
					    	 /*acceptOrder().click();
					    	 waitFor(2).seconds();
					    	 if(str.equalsIgnoreCase("Private Advertiser") || str.equalsIgnoreCase("Direct Advertiser")|| str.equalsIgnoreCase("Brand") || str.equalsIgnoreCase("Charity"))  {
					    		 	waitFor(1).seconds();
					    		 	try {
								    	 WebElement prepaymentwindow1 = getDriver().switchTo().activeElement();
								    	 		waitFor(1).second();
								    	 prepaymentwindow1.findElement(By.xpath("//input[@value='Prepay']")).click();
								    	 		waitFor(3).seconds();
								    	 WebElement prepaymentwindow2 = getDriver().switchTo().activeElement();
								    	 		waitFor(1).second(); 
								    	 prepaymentwindow2.findElement(By.xpath("//input[@value='OK']")).click();
					    		 	} catch (Exception e) {}
					    	 }
					    	 waitFor(8).seconds();*/
					    	 
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