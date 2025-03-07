import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class dashboard {
    public static void main(String[] args) throws InterruptedException {
        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Open Proxify login page
            driver.get("https://proxify.gg/profile-details");
            driver.manage().window().maximize();

            // Login process
            driver.findElement(By.cssSelector("input[type='email']")).sendKeys("your-email@example.com");
            driver.findElement(By.cssSelector("input[type='password']")).sendKeys("your-password");
            
            // Wait for manual CAPTCHA solving
            System.out.println("Solve the CAPTCHA manually...");
            Thread.sleep(15000); // Wait for user to solve CAPTCHA
            
            // Click the login button
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            Thread.sleep(5000); // Wait for the dashboard to load

            // Verify Dashboard Elements
            validateDashboard(driver);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    public static void validateDashboard(WebDriver driver) {
        try {
            String bandwidthUsage = driver.findElement(By.xpath("//h2[contains(text(),'Bandwidth Usage')]")).getText();
            String activeProxies = driver.findElement(By.xpath("//div[contains(text(),'Active Proxies')]/following-sibling::div")).getText();
            String bandwidthUsed = driver.findElement(By.xpath("//div[contains(text(),'Bandwidth Used')]/following-sibling::div")).getText();
            String remainingBandwidth = driver.findElement(By.xpath("//div[contains(text(),'Remaining Bandwidth')]/following-sibling::div")).getText();
            String totalReferrals = driver.findElement(By.xpath("//div[contains(text(),'Total Referrals')]/following-sibling::div")).getText();

            System.out.println("📊 Dashboard Data:");
            System.out.println("✅ Bandwidth Usage: " + bandwidthUsage);
            System.out.println("✅ Active Proxies: " + activeProxies);
            System.out.println("✅ Bandwidth Used: " + bandwidthUsed);
            System.out.println("✅ Remaining Bandwidth: " + remainingBandwidth);
            System.out.println("✅ Total Referrals: " + totalReferrals);
        } catch (Exception e) {
            System.out.println("Dashboard validation failed: " + e.getMessage());
        }
    }
}
