package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WaitUtils;

import java.util.*;

public class CaterpillarSearchResultPage extends AbstractPage {
    private final String productsSearchResultListXpath = "//ul[@id=\"search-result-items\"]/li";
    private final String emptySearchResultMessageXpath = "//div[@id=\"primary\"]/div/p";
    private final String obtainedProductNameXpath = "//a[@class=\"name-link\"]";
    private final String obtainedProductPriceXpath = "//span[@class=\"product-sales-price\"]";
    private final String filterProductSizeXpath = "/html/body/div[1]/div[4]/div[4]/div[2]/div/div[2]/div/div/ul/li[5]/a";

    @FindBy(xpath = productsSearchResultListXpath)
    private List<WebElement> productsSearchResultList;

    @FindBy(xpath = emptySearchResultMessageXpath)
    private WebElement emptySearchResultMessage;

    @FindBy(xpath = filterProductSizeXpath)
    private WebElement filterProductSize;

    @FindBy(xpath = obtainedProductNameXpath)
    private WebElement obtainedProductName;

    @FindBy(xpath = obtainedProductPriceXpath)
    private WebElement obtainedProductPrice;

    public CaterpillarSearchResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public List<String> getSearchedItemsText() {
        ArrayList<String> itemTextList = new ArrayList<>();
        for (WebElement productItem : productsSearchResultList) {
            itemTextList.add(productItem.getText());
        }
        return itemTextList;
    }

    public String getProductName() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(obtainedProductNameXpath, driver);

        return obtainedProductName.getText();
    }
    public String getProductPrice() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(obtainedProductPriceXpath, driver);

        return obtainedProductPrice.getText().substring(0, obtainedProductPrice.getText().length() - 1);
    }

    public CaterpillarSearchResultPage selectFilterProductSize() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(filterProductSizeXpath, driver);

        filterProductSize.click();

        return this;
    }

    public String getEmptySearchResultMessage() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(emptySearchResultMessageXpath, driver);

        return emptySearchResultMessage.getText();
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}

