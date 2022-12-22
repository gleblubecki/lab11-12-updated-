package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WaitUtils;

public class CaterpillarCartPage extends AbstractPage {
    public static String CAT_CART_PAGE_URL = "https://www.catfootwear.com/US/en/cart";
    private final String productNameXpath = "//div[@class=\"name\"]/a";
    private final String productPriceXpath = "//span[@class=\"price-sales bfx-price bfx-list-price\"]";
    private final String productTotalPriceXpath = "//span[@class=\"price-sales bfx-price bfx-list-price\"]";
    private final String removeItemButtonXpath = "//button[@class=\"button-text remove-item\"]";
    private final String addTheSameItemButtonXpath = "//button[@class=\"quantity-plus\"]";
    private final String approvalOfProductRemovalMessageXpath = "//div[@class=\"cart-empty\"]";
    private final String expandPromoCodeInputXpath = "//a[@aria-controls=\"promo-code-content\"]";
    private final String promoCodeInputXpath = "//input[@id=\"dwfrm_cart_couponCode\"]";
    private final String promoCodeSubmitButtonXpath = "//*[@id=\"add-coupon\"]";
    private final String promoCodeStatusXpath = "//div[@class=\"error\"]";
    private final String amountOfProductsInCartXpath = "//span[@data-bonusqty=\"0\"]";


    @FindBy(xpath = productNameXpath)
    private WebElement productName;

    @FindBy(xpath = productPriceXpath)
    private WebElement productPrice;

    @FindBy(xpath = productTotalPriceXpath)
    private WebElement productTotalPrice;

    @FindBy(xpath = removeItemButtonXpath)
    private WebElement removeItemButton;

    @FindBy(xpath = addTheSameItemButtonXpath)
    private WebElement addTheSameItemButton;

    @FindBy(xpath = approvalOfProductRemovalMessageXpath)
    private WebElement approvalOfProductRemovalMessage;

    @FindBy(xpath = expandPromoCodeInputXpath)
    private WebElement expandPromoCodeInput;

    @FindBy(xpath = promoCodeInputXpath)
    private WebElement promoCodeInput;

    @FindBy(xpath = promoCodeSubmitButtonXpath)
    private WebElement promoCodeSubmitButton;

    @FindBy(xpath = promoCodeStatusXpath)
    private WebElement promoCodeStatusMessage;

    @FindBy(xpath = amountOfProductsInCartXpath)
    private WebElement amountOfProductsInCart;

    public CaterpillarCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getProductName() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(productNameXpath, driver);
        return productName.getText();
    }

    public String getProductPrice() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(productPriceXpath, driver);
        return productPrice.getText().substring(0, productPrice.getText().length() - 1);
    }

    public String getProductTotalPrice() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(productTotalPriceXpath, driver);
        return productTotalPrice.getText();
    }

    public CaterpillarCartPage removeItemFromCart() {
        removeItemButton.click();
        return this;
    }

    public CaterpillarCartPage addTheSameItemToCart() {
        addTheSameItemButton.click();
        return this;
    }

    public String getApprovalOfProductRemovalMessage() {
        return approvalOfProductRemovalMessage.getText();
    }

    public CaterpillarCartPage getExpandPromoCodeInput() {
        expandPromoCodeInput.click();
        return this;
    }

    public CaterpillarCartPage enterPromoCode(String promoCode) {
        promoCodeInput.sendKeys(promoCode);
        promoCodeSubmitButton.click();
        return this;
    }

    public String getPromoCodeStatusMessage() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(promoCodeStatusXpath, driver);
        return promoCodeStatusMessage.getText();
    }

    public String getAmountOfCartProducts() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(amountOfProductsInCartXpath ,driver);
        WaitUtils.waitForNumberOfElementsLocatedByXpathToBe(amountOfProductsInCartXpath , 2 , driver );
        return amountOfProductsInCart.getText();
    }

    @Override
    public CaterpillarCartPage openPage() {
        driver.get(CAT_CART_PAGE_URL);
        return this;
    }

}
