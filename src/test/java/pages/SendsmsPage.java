package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SendsmsPage 
{
   public WebDriver driver;
   @FindBy(xpath="//*[text()='SendSMS']")
   public WebElement sendsms;
   
   @FindBy(xpath="//*[@class='fa icon-logout hidden-xs']")
   public WebElement logout;
   
   public SendsmsPage(WebDriver driver)
   {
	   this.driver=driver;
	   PageFactory.initElements(driver, this);
   }
   public void logoutclick()
   {
	   logout.click();
   }
   
}
