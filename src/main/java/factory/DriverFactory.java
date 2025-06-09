package factory;

import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager om;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	
	public WebDriver launchBrowser(Properties prop, String url) {
		
		String bname = prop.getProperty("browser");
		om= new OptionsManager(prop);
		
		if(bname.equalsIgnoreCase("chrome")) {
			// driver = new ChromeDriver(om.getChromeOptions());
			tlDriver.set(new ChromeDriver(om.getChromeOptions()));
		}
		else if (bname.equalsIgnoreCase("firefox")) {
			// driver= new FirefoxDriver(om.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(om.getFirefoxOptions()));
		}
		
		
		
		
		//driver.get("https://www.validaide.com/");
		getDriver().get(prop.getProperty(url));
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		return getDriver();
		
		
	}
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;

		// commnd line args --> maven
		// mvn clean install -Denv="stage" -Dbrowser="chrome"
		// mvn clean install

		String envName = System.getProperty("env");
		//String envName = System.getenv("env");
		System.out.println("Running test cases on environment: " + envName);

		if (envName == null) {
			System.out.println("No env is given...hence running it on QA env by default....");
			try {
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/config.config.properties");
					break;

				default:
					System.out.println("Please pass the right environment.... " + envName);
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	
	/**
	 * take screenshot
	 */
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		//Users/naveenautomationlabs/Documents/workspace1/
		String path = System.getProperty("user.dir")+"/screenshot/" + methodName +System.currentTimeMillis()+ ".png";
		System.out.println("HERE IS THE PATH ********" +path);
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		return srcFile;
	}

	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

	}

}
