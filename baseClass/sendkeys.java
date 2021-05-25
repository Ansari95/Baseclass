package baseClass;

import java.io.IOException;

import org.openqa.selenium.By;

public class sendkeys extends AllBaseClass {

	public static void main(String[] args) throws IOException {
	
		lanchBrowser();
		senkeys(driver.findElement(By.id("email")), "hello");
		
	}

}
