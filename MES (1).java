package com.project;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

public class MES {

	WebDriver driver;

	@BeforeTest

	public void Launch() throws InterruptedException

	{

		System.setProperty("webdriver.chrome.driver",
				"D:\\deni_software testing\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://mail.google.com");
		Thread.sleep(5000);

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	// Login Gmail
	@Parameters({ "Email Id", "password" })
	@Test(priority = 1)
	public void Login(String Email, String Pass) {
		WebElement log = driver.findElement(By.id("identifierId"));
		log.sendKeys(Email);

		WebElement nt = driver.findElement(By.xpath("//span[text()='Next']"));
		nt.click();

		WebElement pass = driver.findElement(By.xpath("//div[text()='Enter your password']"));
		pass.sendKeys(Pass);

		WebElement NT = driver.findElement(By.xpath("//span[text()='Next']"));
		NT.click();

	}

	// When the user clicks the compose email button, a new popup should be
	// displayed with the headers as a new message.

	@Test(priority = 2)
	public void Compose() {
		WebElement cp = driver.findElement(By.cssSelector("div[role='button'][gh='cm']"));
		cp.click();

	}

	// Check the user’s ability to enter email ID in the TO, CC, and BCC Sections.

	@Test(priority = 3)
	public void UserAbility() {

		WebElement composeButton = driver.findElement(By.cssSelector("div[role='button'][gh='cm']"));
		composeButton.click();

		WebElement to = driver.findElement(By.name("to"));
		WebElement cc = driver.findElement(By.name("cc"));
		WebElement bcc = driver.findElement(By.name("bcc"));

		to.sendKeys("arodenison1999@gmail.com");
		cc.sendKeys("deniarock46@gmail.com");
		bcc.sendKeys("denison13@gmail.com");

		to.sendKeys(Keys.TAB);
		to.clear();
		to.sendKeys("arodenison1999@gmail.com,deniarock46@gmail.com");

	}

	@Test(priority = 4)
	public void SuggestionList() {

		WebElement composeButton = driver.findElement(By.cssSelector("div[role='button'][gh='cm']"));
		composeButton.click();

		WebElement to = driver.findElement(By.name("to"));

		to.sendKeys("arodenison1999@gmail.com");

		to.sendKeys(Keys.TAB);

		WebElement suggestionList = driver.findElement(By.cssSelector("div[role='listbox']"));
		if (suggestionList.isDisplayed()) {
			System.out.println("Auto-suggestion list is displayed:PASS");
		} else {
			System.out.println("Auto-suggestion list is not displayed:FAIL");
		}
	}

//Check if multiple commas can separate multiple email IDs of TO, CC, and BCC sections.

	@Test(priority = 5)

	public void MultipleCommas() {

		WebElement composeButton = driver.findElement(By.cssSelector("div[role='button'][gh='cm']"));
		composeButton.click();

		WebElement to = driver.findElement(By.name("to"));
		WebElement cc = driver.findElement(By.name("cc"));
		WebElement bcc = driver.findElement(By.name("bcc"));

		to.sendKeys("arodenison1999@gmail.com", "vimalroshan2001@gmail.com");
		cc.sendKeys("deniarock46@gmail.com", "reshma2002@gmail.com");
		bcc.sendKeys("denison13@gmail.com", "chris007@gmail.com");

		if (to.getAttribute("value").equals("arodenison1999@gmail.com", "vimalroshan2001@gmail.com")
				&& cc.getAttribute("value").equals("deniarock46@gmail.com", "reshma2002@gmail.com")
				&& bcc.getAttribute("value").equals("denison13@gmail.com", "chris007@gmail.com")) {
			System.out.println("Multiple commas can separate multiple email IDs: PASS");
		} else {
			System.out.println("Multiple commas cannot separate multiple email IDs: FAIL");
		}

	}

// Check the user can enter the subject line in the subject text box.

	@Test(priority = 6)
	public void Subject() {
		WebElement sub = driver.findElement(By.name("subjectbox"));
		sub.sendKeys("Test Subject");
	}

// The check is the user can add files in the attachment section.

	@Test(priority = 7)
	public void Attachment() {
		WebElement At = driver.findElement(By.xpath("//div[@command='Files']"));
		At.click();
	}

// The check is the user can add images in the email body area.

	@Test(priority = 8)
	public void Image() {
		WebElement Img = driver.findElement(By.cssSelector("div[aria-label='Message Body']"));
		Img.sendKeys(Keys.CONTROL + "v");
	}

// Check all the users who received the emails whose email IDs are mentioned in
	// the TO, CC & BCC sections.

	@Test(priority = 9)
	public void Check_the_users_who_received_the_emails() {
		driver.findElement(By.xpath("//div[@class='T-I T-I-KE L3']")).click();
		driver.findElement(By.id(":v7")).sendKeys("prince@cognizant.com");

		driver.findElement(By.xpath("//*[@id=\":21o\"]")).click();
		driver.findElement(By.xpath("//*[@id=\":248\"]")).sendKeys("math@veritus.com");

		driver.findElement(By.xpath("//*[@id=\":252\"]")).click();
		driver.findElement(By.xpath("//*[@id=\":2c9\"]")).sendKeys("apple@dunzo.com");

		driver.findElement(By.name("subjectbox")).sendKeys("To apply for the offer");
		driver.findElement(By.className("Am aiL Al editable LW-avf tS-tW")).sendKeys("Body of the mail");
		driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")).click();

		// Checking the delivered mail in Sent mail tab
		driver.findElement(By.xpath("(//div[@class='aio UKr6le'])[4]")).click();

		String ActualTo = "prince@cognizant.com";
		String ExpectedTo = "prince@cognizant.com";
		Assert.assertEquals(ActualTo, ExpectedTo);

		String ActualCC = "math@veritus.com";
		String ExpectedCC = "math@veritus.com";
		Assert.assertEquals(ActualCC, ExpectedCC);

		String ActualBCC = "apple@dunzo.com";
		String ExpectedBCC = "apple@dunzo.com";
		Assert.assertEquals(ActualBCC, ExpectedBCC);

	}

// Contact information should be displayed when the user places the mouse hovers
// on the email id.

	@Test(priority = 9)
	public void Mouse() {
		WebElement ms = driver.findElement(By.name("to"));
		Actions act = new Actions(driver);
		act.moveToElement(ms).perform();
	}

// After hitting the send button, the recent send mail should be displayed in
// the sent mail section.

	@Test(priority = 10)
	public void send() {
		WebElement sd = driver.findElement(By.xpath("//div[contains(text(), 'Send')]"));
		sd.click();
	}

// Check after hitting the send button. The email should also be delivered
// successfully to the NON-Gmail ID.

	@Test(priority = 11)
	public void Check_after_hitting_the_send_button() {
		driver.findElement(By.xpath("//div[@class='T-I T-I-KE L3']")).click();
		driver.findElement(By.id(":v7")).sendKeys("prince@cognizant.com");
		driver.findElement(By.name("subjectbox")).sendKeys("To apply for the offer");
		driver.findElement(By.className("Am aiL Al editable LW-avf tS-tW")).sendKeys("Body of the mail");
		driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")).click();

		// Checking the delivered mail in Sent mail tab

		driver.findElement(By.xpath("(//div[@class='aio UKr6le'])[4]")).click();

		// Verifying the sent email with the recipient name using Assert

		String ActualMail = "prince@cognizant.com";
		String ExpectedMail = "prince@cognizant.com";
		Assert.assertEquals(ActualMail, ExpectedMail);
	}

//After composing the email, if not sent, then it should be stored in the Draft section.	
	@Test(priority = 12)
	public void Checking_the_mail_in_drafts() {
		driver.findElement(By.xpath("//div[@class='T-I T-I-KE L3']")).click();
		driver.findElement(By.id(":v7")).sendKeys("prince@cognizant.com");
		driver.findElement(By.name("subjectbox")).sendKeys("To apply for the offer");
		driver.findElement(By.className("Am aiL Al editable LW-avf tS-tW")).sendKeys("Body of the mail");
		driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")).click();

		// Closing the inbox to save the mail in Draft

		driver.findElement(By.id(":1ln")).click();

		// Checking the delivered mail in Draft mail tab

		driver.findElement(By.xpath("(//div[@class='aio UKr6le'])[5]")).click();

		// Verifying the Drafted email with the subject using Assert

		String ActualSubject = "To apply for the offer";
		String ExpectedSubject = "To apply for the offer";
		Assert.assertEquals(ActualSubject, ExpectedSubject);
	}
//Checking_maximum_number_of_email_IDs_that_can_be_added_in_the_TO,_CC_&_BCC_sections

	@Test(priority = 13)
	public void Checking_maximum_number_of_email_IDs(String section) {
		driver.findElement(By.xpath("//div[@class='T-I T-I-KE L3']")).click();
		driver.findElement(By.id(":v7")).sendKeys("prince@cognizant.com");
		driver.findElement(By.name("subjectbox")).sendKeys("To apply for the offer");
		driver.findElement(By.className("Am aiL Al editable LW-avf tS-tW")).sendKeys("Body of the mail");
		driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")).click();

		// Add maximum number of email IDs
		// Check maximum number of email IDs in TO, CC, and BCC sections
		int maxEmailIDs = 5;

		// Find the respective section element (TO, CC, or BCC)
		WebElement sectionElement = driver.findElement(By.name(section.toLowerCase()));

		for (int i = 1; i <= maxEmailIDs; i++) {
			sectionElement.sendKeys("prince" + i + "@cognizant.com");
			sectionElement.sendKeys(",");
		}

		// Try adding one more email ID to check if it's allowed
		sectionElement.sendKeys("deni@accenture.com");

		// Get the text from the section field
		String sectionText = sectionElement.getAttribute("value");

		// Split the text to count the number of email IDs
		String[] emailIDs = sectionText.split(",");

		if (emailIDs.length == maxEmailIDs) {
			System.out.println("Maximum number of email IDs allowed in " + section + " section is validated: PASS");
		} else {
			System.out.println("Maximum number of email IDs allowed in " + section + " section is validated: FAIL");
		}

	}
//Check the maximum number of characters allowed in the email body area

	@Test(priority = 14)
	public void Checking_maximum_number_of_Chars_in_body(String body) {
		driver.findElement(By.xpath("//div[@class='T-I T-I-KE L3']")).click();
		driver.findElement(By.id(":v7")).sendKeys("prince@cognizant.com");
		driver.findElement(By.name("subjectbox")).sendKeys("To apply for the offer");
		driver.findElement(By.className("Am aiL Al editable LW-avf tS-tW")).sendKeys("Body of the mail");
		driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")).click();

		// Add maximum number of Chars in Body
		// Check maximum number of chars in body section
		int maxChars = 500;

		// Find the Body in Mail
		WebElement sectionBody = driver.findElement(By.name(body.toLowerCase()));

		for (int i = 1; i <= maxChars; i++) {
			sectionBody.sendKeys("The random chars" + " ");
			sectionBody.sendKeys(",");
		}

		// Try adding one more email ID to check if it's allowed
		sectionBody.sendKeys("Extra chars");

		if (body.length() == maxChars) {
			System.out.println("Maximum number of Chars allowed in " + body + " section is validated: PASS");
		} else {
			System.out.println("Maximum number of Chars allowed in " + body + " section is validated: FAIL");
		}
	}

//Check the maximum number of characters allowed in the subject text box.			
	@Test(priority = 15)
	public void Checking_maximum_number_of_subject_text_box(String Subject) {
		driver.findElement(By.xpath("//div[@class='T-I T-I-KE L3']")).click();
		driver.findElement(By.id(":v7")).sendKeys("prince@cognizant.com");
		driver.findElement(By.name("subjectbox")).sendKeys("To apply for the offer");
		driver.findElement(By.className("Am aiL Al editable LW-avf tS-tW")).sendKeys("Body of the mail");
		driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")).click();

		// Add maximum number of Chars in Body
		// Check maximum number of chars in body section
		int maxChars = 500;

		// Find the Body in Mail
		WebElement sectionSubject = driver.findElement(By.name(Subject.toLowerCase()));

		for (int i = 1; i <= maxChars; i++) {
			sectionSubject.sendKeys("The random chars" + " ");
			sectionSubject.sendKeys(",");
		}

		// Try adding one more email ID to check if it's allowed
		sectionSubject.sendKeys("Extra chars");

		if (Subject.length() == maxChars) {
			System.out.println("Maximum number of Chars allowed in " + Subject + " section is validated: PASS");
		} else {
			System.out.println("Maximum number of Chars allowed in " + Subject + " section is validated: FAIL");
		}
	}

//Check if special characters are entered mistakenly in the TO, CC, or BCC section

	@Test(priority = 16)
	public void Checking_the_special_characters_are_entered_mistakenly() {
		driver.findElement(By.xpath("//div[@class='T-I T-I-KE L3']")).click();

		// To section
		WebElement to = driver.findElement(By.id(":v7"));
		String s1 = "prince@cognizant.com" + "$%^";
		to.sendKeys(s1);

		String ActualTo = "prince@cognizant.com";
		String ExpectedTo = s1;
		Assert.assertEquals(ActualTo, ExpectedTo);

		driver.findElement(By.name("subjectbox")).sendKeys("To apply for the offer");
		driver.findElement(By.className("Am aiL Al editable LW-avf tS-tW")).sendKeys("Body of the mail");
		driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")).click();

		// Handling the pop-up
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

//Check if the subject is not filled; when you enter the send button, it should display that time. After accepting the popup, the user can enter the email’s subject

	@Test(priority = 17)
	public void Checking_the_subject_is_filled_or_not() {
		driver.findElement(By.xpath("//div[@class='T-I T-I-KE L3']")).click();

		// To section
		WebElement to = driver.findElement(By.id(":v7"));
		String s1 = "prince@cognizant.com";
		to.sendKeys(s1);

		driver.findElement(By.name("subjectbox")).sendKeys(" ");
		driver.findElement(By.className("Am aiL Al editable LW-avf tS-tW")).sendKeys("Body of the mail");
		driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")).click();

		// Handling the pop-up
		Alert alert = driver.switchTo().alert();
		alert.accept();

		driver.findElement(By.name("subjectbox")).sendKeys("To take up the text");

	}
}
