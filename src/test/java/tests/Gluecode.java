package tests;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import pages.Homepage;
import pages.SendsmsPage;

public class Gluecode 
{
    public WebDriver driver;
    public WebDriverWait wait;
    public Homepage hp;
    public SendsmsPage sp;
    public Scenario s;
    public Properties pro;
    
    @Before
    public void  method1(Scenario s) throws Exception
    {
    	//use scenario object for current scenario
    	this.s=s;
    	//load properties file for current scenario
    	pro=new Properties();
    	FileInputStream fip=new FileInputStream("E:\\batch239\\org.Way2ms\\src\\test\\resources\\mypack\\way2sms.properties");
    	pro.load(fip);
    }
    @Given("^launch site$")
    public void method()
    {
    	System.out.println("launch a site");
    }
    @When("^browser is chrome$")
    public void methoda()
    {
    	System.out.println("Browser is chrome");
    }
    @And("^follow the remaining scenarios$")
    public void methodb()
    {
    	System.out.println("follow the remaining scenarios");
    }
    		
    
    @Given("^launch site using \"(.*)\"$")
    public void method2(String b)
    {
    	//open browser for current scenario
    	if(b.equals("chrome"))
    	{
    		System.setProperty("webdriver.chrome.driver", pro.getProperty("cdriver"));
    		driver=new ChromeDriver();
    	}
    	else if(b.equals("firefox"))
    	{
    		System.setProperty("webdriver.gecko.driver", pro.getProperty("ffdriver"));
    		driver=new FirefoxDriver();
    		
    	}
    	else if(b.equals("opera"))
    	{
    		OperaOptions oo=new OperaOptions();
    		oo.setBinary("C:\\Users\\Lenovo\\AppData\\Local\\Programs\\Opera\\55.0.2994.44\\opera.exe");
    		System.setProperty("webdriver.opera.driver", pro.getProperty("oodriver"));
    		driver=new OperaDriver(oo);
    		
    	}
    	else
    	{
    		System.setProperty("webdriver.ie.driver",pro.getProperty("iedriver"));
    		driver=new InternetExplorerDriver();
    	}
    	//create objects for pageclasses for current scenario
    	hp=new Homepage(driver);
    	sp=new SendsmsPage(driver);
    	//open site
    	
    	driver.get(pro.getProperty("url"));
    	
    	//Define wait object
    	
    	wait=new WebDriverWait(driver,20);
    	
    	wait.until(ExpectedConditions.visibilityOf(hp.mbno));
    	driver.manage().window().maximize();
    }
    @Then("^title contains \"(.*)\"$")
    public void method3(String a)
    {
    	wait.until(ExpectedConditions.visibilityOf(hp.mbno));
		String t = driver.getTitle();
		if (t.contains(a))
		{
			if(s.isFailed())
			{
			byte ssbyte[] = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			s.embed(ssbyte, "title test passed");
			s.write("title test passed");
		} 
			}
		
		else
		{
			byte ssbyte[] = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			s.embed(ssbyte, "title test failed");
			Assert.fail();
		}
    }
    @And("^close site$")
	public void method4()
	{
		driver.close();
	}
    @When("^enter mobile number as \"(.*)\"$")
    public void method5(String u)
    {
    	wait.until(ExpectedConditions.visibilityOf(hp.mbno));
    	hp.fillmbno(u);
    }
    @And("^enter password as \"(.*)\"$")
    public void method6(String p)
    {
    	wait.until(ExpectedConditions.visibilityOf(hp.pwd));
    	hp.fillpwd(p);
    }
    @And("^click login$")
    public void method7()
    {
    	wait.until(ExpectedConditions.visibilityOf(hp.signin));
    	hp.clickSignin();
    }
    @Then("^validate output for criteria as \"(.*)\"$")
    public void method8(String c) throws Exception
    {
    	wait.until(temp ->((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));
    	Thread.sleep(5000);
    	try
    	{
    		if(c.equals("all_valid") && sp.sendsms.isDisplayed())
    		{
    			s.write("Test Passed for valid Data");
    			
    			
    		}
    		else if(c.equals("mbno_blank") && hp.mbno_blank_err.isDisplayed())
                 {
                         s.write("Test passed for Mobile number blank");	
                }
                 else if(c.equals("mbno_invalid") &&hp.mbno_invalid_err.isDisplayed())
                       {
	                       s.write("Test passed for mob number invalid");
                        }
                 else if(c.equals("pwd_blank") && hp.pwd_blank_err.isDisplayed())
                 {
                	 s.write("test passed for pwd blank");
                 }
                 else if(c.equals("pwd_invalid") && hp.pwd_invalid_err.isDisplayed())
                 {
                	 s.write("Test  passsed for pwd invalid");
                 }
                 else
                 {
                	 byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                	 s.embed(ssbytes,"Login test failed");
                	 Assert.fail();
                 }
    	}
    	catch(Exception ex)
    	{
    		byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    		s.embed(ssbytes,ex.getMessage());
    		Assert.fail();
    	}
    }
    	@When("^do login with valid data$")
    	public void method9(DataTable dt)
    	{
    		List<List<String>> l=dt.asLists(String.class);
    		hp.fillmbno(l.get(1).get(0));
    		hp.fillpwd(l.get(1).get(1));
    		hp.clickSignin();
    				
    	}
    	@And("^do logout$")
    	public void method10()
    	{
    		wait.until(temp ->((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));
    		try
    		{
    			Thread.sleep(5000);
    			driver.findElement(By.xpath("//*[@class='logout']")).click();
    		}
    		catch(Exception ex)
    		
    		{
    		   byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    		   s.embed(ssbytes,ex.getMessage());
    		}
    	}
    		@Then("^homepage will be reopened$")
    		public void method11() 
    		{
    			if(hp.mbno.isDisplayed())
    			{
    				
    				s.write("logout successful");
    				 byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
  	    		   s.embed(ssbytes,"Test passed");
  	    		  
    			}
    			else
    			{
    				if(s.isFailed())
    				{
    				  byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    	    		   s.embed(ssbytes,"Test failed");
    	    		   Assert.fail();
    				}
    			}
    				
    		}
    

    	}
