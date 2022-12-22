package test;

import model.Product;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.CaterpillarCartPage;
import page.CaterpillarItemPage;
import service.ProductCreator;
import service.TestDataReader;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CaterpillarCartTest extends CommonConditions {
    public final String APPROVAL_REMOVAL_MESSAGE = TestDataReader.getTestData("testdata.approval_removal_message");
    public final String INVALID_PROMO_CODE = TestDataReader.getTestData("testdata.invalid_promo_code");
    public final String INVALID_PROMO_CODE_MESSAGE = TestDataReader.getTestData("testdata.invalid_promo_code_message");
    public static String AMOUNT_OF_PRODUCTS = TestDataReader.getTestData("testdata.amount_of_products");
    public static String DOUBLE_AMOUNT_OF_PRODUCTS = TestDataReader.getTestData("testdata.double_amount_of_products");
    public static String NO_SELECT_SIZE_MESSAGE = TestDataReader.getTestData("testdata.no_select_size_message");

    @BeforeMethod(onlyForGroups = {"addedItemToCartPreconditionIsNeeded"})
    public void addItemToCart() {
        new CaterpillarItemPage(driver)
                .openPage()
                .clickSizeButton()
                .addItemToCart();
    }

    @Test
    public void addItemToCartTest() {
        Product testProduct = ProductCreator.withCredentialsFromProperty();

        new CaterpillarItemPage(driver)
                .openPage()
                .clickSizeButton()
                .addItemToCart();

        CaterpillarCartPage catCartPage = new CaterpillarCartPage(driver);

        String addedProductName = catCartPage
                .openPage()
                .getProductName();

        String addedProductPrice = catCartPage
                .getProductPrice();

        assertThat(addedProductName, is(equalTo(testProduct.getName())));
        assertThat(addedProductPrice, is(equalTo(testProduct.getPrice())));
    }

    @Test(groups = {"addedItemToCartPreconditionIsNeeded"})
    public void removeItemFromCartTest() {
        String messageAfterProductRemoval = new CaterpillarCartPage(driver)
                .openPage()
                .removeItemFromCart()
                .getApprovalOfProductRemovalMessage();

        assertThat(messageAfterProductRemoval, is(equalTo(APPROVAL_REMOVAL_MESSAGE)));
    }

    @Test
    public void getErrorMessage() {
        String errorMessage = new CaterpillarItemPage(driver)
                .openPage()
                .addItemToCart()
                .getNoSelectSizeMessage();

        assertThat(errorMessage, is(equalTo(NO_SELECT_SIZE_MESSAGE)));
    }

    @Test(groups = {"addedItemToCartPreconditionIsNeeded"})
    public void getAmountOfCartProducts() {
        String amountOfCartProducts = new CaterpillarItemPage(driver)
                .getAmountOfCartProducts();

        assertThat(amountOfCartProducts, is(equalTo(AMOUNT_OF_PRODUCTS)));
    }

    @Test(groups = {"addedItemToCartPreconditionIsNeeded"})
    public void getDoubleAmountOfCartProducts() throws InterruptedException {
        Thread.sleep(1000L);
        
        String amountOfDoubleCartProducts = new CaterpillarCartPage(driver)
                .openPage()
                .addTheSameItemToCart()
                .getAmountOfCartProducts();

        assertThat(amountOfDoubleCartProducts, is(equalTo(DOUBLE_AMOUNT_OF_PRODUCTS)));
    }

    @Test(groups = {"addedItemToCartPreconditionIsNeeded"})
    public void useInvalidPromoCodeTest() {
        String promoCodeStatusMessage = new CaterpillarCartPage(driver)
                .openPage()
                .getExpandPromoCodeInput()
                .enterPromoCode(INVALID_PROMO_CODE)
                .getPromoCodeStatusMessage();

        Assert.assertTrue(promoCodeStatusMessage.contains(INVALID_PROMO_CODE_MESSAGE),
                "The error message of using invalid promo code was not shown!");
    }
}
