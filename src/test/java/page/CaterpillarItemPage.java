package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WaitUtils;

public class CaterpillarItemPage extends AbstractPage{
    public static String CAT_ITEM_PAGE_URL = "https://www.catfootwear.com/US/en/triton-soft-shell-jacket/44270W.html?dwvar_44270W_color=PA1310084-10121#cgid=womens-clothing&start=1";
    private final String buttonSizeXpath = "//label[@data-value='XL']";
    private final String addToCartButtonXpath = "//button[@id=\"add-to-cart\"]";
    private final String noSelectSizeMessageXpath = "//*[@class=\"variation-title error-show\"]/span[@class=\"label\"]";
    private final String amountOfProductsInCartXpath = "//span[@class=\"mini-cart-quantity-bag\"]";

    @FindBy(xpath = buttonSizeXpath)
    private WebElement buttonSize;

    @FindBy(xpath = addToCartButtonXpath)
    private WebElement addToCartButton;

    @FindBy(xpath = noSelectSizeMessageXpath)
    private WebElement noSelectSizeMessage;

    @FindBy(xpath = amountOfProductsInCartXpath)
    private WebElement amountOfProductsInCart;

    public CaterpillarItemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public CaterpillarItemPage clickSizeButton() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(buttonSizeXpath, driver);
        buttonSize.click();
        return this;
    }
    public CaterpillarItemPage addItemToCart() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(addToCartButtonXpath, driver);
        addToCartButton.click();
        return this;
    }

    public String getNoSelectSizeMessage() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(noSelectSizeMessageXpath, driver);
        return noSelectSizeMessage.getText();
    }

    public String getAmountOfCartProducts() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(amountOfProductsInCartXpath ,driver);
        return amountOfProductsInCart.getText();
    }

    @Override
    public CaterpillarItemPage openPage() {
        driver.get(CAT_ITEM_PAGE_URL);
        return this;
    }

}
