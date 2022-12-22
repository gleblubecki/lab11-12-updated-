package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CaterpillarHomePage extends AbstractPage {
    public static String CAT_HOME_PAGE_URL = "https://www.catfootwear.com/US/en/home";
    private final String searchInputXpath = "//*[@data-placeholder=\"Search\"]";
    private  final  String closeSigInModalButtonXpath = "//span[@class=\"nav-signin-popup-header-close\"]";
    private final String searchButtonXpath = "//button[@class=\"submit-btn\"]";

    @FindBy(xpath = searchInputXpath)
    private WebElement searchInput;
    @FindBy(xpath = closeSigInModalButtonXpath)
    private WebElement closeSigInModalButton;
    @FindBy(xpath = searchButtonXpath)
    private WebElement searchButton;

    public CaterpillarHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public CaterpillarSearchResultPage searchForTerms(String searchQuery) {
        closeSigInModalButton.click();
        searchInput.sendKeys(searchQuery);
        searchButton.click();

        return new CaterpillarSearchResultPage(driver);
    }

    @Override
    public CaterpillarHomePage openPage() {
        driver.get(CAT_HOME_PAGE_URL);

        return this;
    }
}
