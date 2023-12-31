package pages;

import commons.GlobalConstants;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.CommonPageUI;
import utils.Driver;

import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class BasePage {

	private final long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private final long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
	private WebDriver driver;

	public BasePage() {
		this.driver = Driver.get();
	}

	public float convertStringToFloat(String priceString) {
		return Float.parseFloat(priceString.replace("$", "").replace(",", ""));
	}

	public void openPageUrl(String pageUrl) {
		driver.get(pageUrl);
	}

	public Set<Cookie> getAllCookies() {
		return this.driver.manage().getCookies();
	}

	public void setCookies(Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(1);
	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
		sleepInSecond(1);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getPageUrl() {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode() {
		return driver.getPageSource();
	}


	public void backToPage() {
		driver.navigate().back();
	}

	public void forwardToPage() {
		driver.navigate().forward();
	}

	public void refreshCurrentPage() {
		driver.navigate().refresh();
		sleepInSecond(1);
	}

	public void waitForAlertPresence(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	protected Alert waitAlertPresence() {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert() {
		waitAlertPresence().accept();
	}

	protected void cancelAlert() {
		waitAlertPresence().dismiss();
	}

	protected void getTextAlert() {
		waitAlertPresence().getText();
	}

	protected void sendkeyToAlert(String textValue) {
		waitAlertPresence().sendKeys(textValue);
	}

	protected void switchToWindowByID(String pageID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if (!id.equals(pageID)) {

				driver.switchTo().window(id);
			}
		}
	}

	protected void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);

			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	protected void closeAllTabWithoutParent(String currentWindowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(currentWindowID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(currentWindowID);
		}
	}

	private By getByLocator(String locatorType) {
		By by = null;

		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
			by = By.id(locatorType.substring(3));

		} else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=")
				|| locatorType.startsWith("Class=")) {
			by = By.className(locatorType.substring(6));

		} else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=")
				|| locatorType.startsWith("Name=")) {
			by = By.name(locatorType.substring(5));

		} else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
			by = By.cssSelector(locatorType.substring(4));

		} else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=")
				|| locatorType.startsWith("Xpath=")) {
			by = By.xpath(locatorType.substring(6));

		} else {
			throw new RuntimeException("Locator type is not correct");
		}
		return by;
	}

	public String getDynamicXpath(String locatorType, String... dynamicValues) {
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		}
		return locatorType;
	}

	public WebElement getWebElement(String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	public List<WebElement> getListWebElement(String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	public List<WebElement> getListWebElement(String locatorType, String... dynamicValues) {
		return driver.findElements(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
	}

	protected void clickToElement(String locatorType) {
		getWebElement(locatorType).click();
	}

	protected void clickToElement(String locatorType, String... dynamicValues) {
		getWebElement(getDynamicXpath(locatorType, dynamicValues)).click();
	}

	protected void sendKeysToElement(String locatorType, String textValue) {
		WebElement element = getWebElement(locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	protected void sendKeysToElement(String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	protected void selectItemInDefaultDropdown(String locatorType, String visibleText) {
		Select select = new Select(getWebElement(locatorType));
		select.selectByVisibleText(visibleText);
	}

	protected void selectItemInDefaultDropdown(String locatorType, String visibleText,
	                                           String... dynamicValues) {
		Select select = new Select(getWebElement(getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(visibleText);
	}

	protected String getSelectedItemDefaultDropdown(String locatorType) {
		Select select = new Select(getWebElement(locatorType));
		return select.getFirstSelectedOption().getText();
	}

	protected String getSelectedItemDefaultDropdown(String locatorType, String... dynamicValues) {
		Select select = new Select(getWebElement(getDynamicXpath(locatorType, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(String locatorType) {
		Select select = new Select(getWebElement(locatorType));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(String parentXpath, String allItemsXPath,
	                                       String expectedTextItem) {
		getWebElement(parentXpath).click();
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));

		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(allItemsXPath)));

		for (WebElement tempItem : allItems) {
			if (tempItem.getText().equals(expectedTextItem)) {
				tempItem.click();
				break;
			}
		}
	}

	public String getElementAttribute(String attributeName, String locatorType) {
		return getWebElement(locatorType).getAttribute(attributeName);
	}

	public String getElementAttribute(String attributeName, String locatorType,
	                                  String... dynamicValues) {
		return getWebElement(getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}

	public String getElementText(String locatorType) {
		return getWebElement(locatorType).getText();
	}

	public String getElementText(String locatorType, String... dynamicValues) {
		return getWebElement(getDynamicXpath(locatorType, dynamicValues)).getText();
	}

	public String getElementCssValue(String locatorType, String propertyName) {
		return getWebElement(locatorType).getCssValue(propertyName);
	}

	public String getElementCssValue(String locatorType, String propertyName,
	                                 String... dynamicValues) {
		return getWebElement(getDynamicXpath(locatorType, dynamicValues)).getCssValue(propertyName);
	}

	protected String getHexaColorByRgbaColor(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getElementSize(String locatorType) {
		return getListWebElement(locatorType).size();
	}

	public int getElementSize(String locatorType, String... dynamicValues) {
		return getListWebElement(getDynamicXpath(locatorType, dynamicValues)).size();
	}

	protected void checkToDefaultCheckboxOrRadio(String locatorType) {
		WebElement element = getWebElement(locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkToDefaultCheckboxOrRadio(String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicXpath(locatorType, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckbox(String locatorType) {
		WebElement element = getWebElement(locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckbox(String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	protected void hoverMouseToElement(String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(locatorType)).perform();
	}

	protected void hoverMouseToElement(String locatorType, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(getDynamicXpath(locatorType, dynamicValues))).perform();
	}

	protected void pressKeyToElement(String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(locatorType), key).perform();
	}

	protected void pressKeyToElement(String locatorType, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}

	protected void scrollToBottomPage() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void highlightElement(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	protected void highlightElement(String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(getDynamicXpath(locatorType, dynamicValues));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	protected void clickToElementByJS(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(locatorType));
	}

	protected void clickToElementByJS(String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();",
				getWebElement(getDynamicXpath(locatorType, dynamicValues)));
	}

	protected void scrollToElement(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(locatorType));
	}

	protected void removeAttributeInDOM(String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(locatorType));
	}

	protected boolean areJQueryAndJSLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected String getElementValidationMessage(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
				getWebElement(locatorType));
	}

	protected boolean isImageLoaded(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(locatorType));
	}

	protected boolean isImageLoaded(String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(getDynamicXpath(locatorType, dynamicValues)));
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void waitForElementVisible(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	protected void waitForElementVisible(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(ExpectedConditions
				.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForAllElementsVisible(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	protected void waitForAllElementsVisible(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void overrideImplicitTimeout(long timeOut) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
	}

	/*
	 * Wait for element is not displayed in DOM or not in DOM and override implicit timeout
	 */
	public void waitForElementNotDisplayed(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		overrideImplicitTimeout(shortTimeout);
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideImplicitTimeout(longTimeout);
	}

	public void waitForElementInvisible(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForAllElementsInvisible(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(locatorType)));
	}

	public void waitForAllElementsInvisible(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(ExpectedConditions
				.invisibilityOfAllElements(getListWebElement(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForElementClickable(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	public void waitForElementClickable(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(
				ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}


	public boolean isElementDisplayed(String locatorType) {
		return getWebElement(locatorType).isDisplayed();
	}

	public boolean isElementDisplayed(String locatorType, String... dynamicValues) {
		return getWebElement(getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}

	public boolean isElementNotDisplayed(String locatorType) {
		overrideImplicitTimeout(shortTimeout);
		List<WebElement> elements = getListWebElement(locatorType);
		overrideImplicitTimeout(longTimeout);

		if (elements.size() == 0) {
			return true;
		} else return !elements.get(0).isDisplayed();
	}

	protected boolean isElementEnabled(String locatorType) {
		return getWebElement(locatorType).isEnabled();
	}

	protected boolean isElementSelected(String locatorType) {
		return getWebElement(locatorType).isSelected();
	}

	protected boolean isElementSelected(String locatorType, String... dynamicValues) {
		return getWebElement(getDynamicXpath(locatorType, dynamicValues)).isSelected();
	}

	protected void switchToIframeFrame(String locatorType) {
		driver.switchTo().frame(getWebElement(locatorType));
	}

	protected void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public boolean isDataSortByNameAsc(String locatorType) {
		List<WebElement> elementList = getListWebElement(locatorType);
		List<String> names = elementList.stream()
				.map(WebElement::getText)
				.collect(Collectors.toList());
		List<String> sortedNames = new ArrayList<>(names);
		sortedNames.sort(Comparator.naturalOrder());
		return names.equals(sortedNames);
	}

	public boolean isDataSortByNameDesc(String locatorType) {
		List<WebElement> elementList = getListWebElement(locatorType);
		List<String> names = elementList.stream()
				.map(WebElement::getText)
				.collect(Collectors.toList());
		List<String> sortedNames = new ArrayList<>(names);
		sortedNames.sort(Comparator.reverseOrder());
		return names.equals(sortedNames);
	}

	public boolean isDataFloatSortedAsc(String locatorType) {
		ArrayList<Float> arrayList = new ArrayList<Float>();
		List<WebElement> elementList = getListWebElement(locatorType);

		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").replace(",", "").trim()));
		}

		ArrayList<Float> sortedList = new ArrayList<Float>();

		for (Float child : arrayList) {
			sortedList.add(child);
		}

		Collections.sort(sortedList);
		return sortedList.equals(arrayList);
	}

	public boolean isDataFloatSortedDesc(String locatorType) {
		ArrayList<Float> arrayList = new ArrayList<Float>();
		List<WebElement> elementList = getListWebElement(locatorType);

		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").replace(",", "").trim()));
		}
		ArrayList<Float> sortedList = new ArrayList<Float>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}

		Collections.sort(sortedList, Comparator.reverseOrder());
		return sortedList.equals(arrayList);
	}

	public void clickToBrowserBackButton() {
		backToPage();
	}

	protected int extractOrderNumber(String input) {
		Pattern pattern = Pattern.compile("(?i)order number: (\\d+)");
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			return Integer.parseInt(matcher.group(1));
		} else {
			throw new IllegalArgumentException("No valid ORDER NUMBER found in the input");
		}
	}



	public void inputToTextboxByLabel(String label, String value) {
		waitForElementClickable(CommonPageUI.LABEL_INPUT, label);
		sendKeysToElement(CommonPageUI.LABEL_INPUT, value, label);
	}


	public void clickToLinkAtHeader(String linkText) {
		waitForElementClickable(CommonPageUI.HEADER_LINKS, linkText);
		clickToElement(CommonPageUI.HEADER_LINKS, linkText);
	}


	public void clickToLinkAtFooter(String linkText) {
		waitForElementClickable(CommonPageUI.FOOTER_LINKS, linkText);
		clickToElement(CommonPageUI.FOOTER_LINKS, linkText);
	}

	public void inputToTextareaByLabel(String label, String value) {
		waitForElementClickable(CommonPageUI.LABEL_TEXTAREA, label);
		sendKeysToElement(CommonPageUI.LABEL_TEXTAREA, value, label);
	}

	public void checkToCheckboxByLabel(String label) {
		waitForElementClickable(CommonPageUI.LABEL_CHECKBOX, label);
		checkToDefaultCheckboxOrRadio(CommonPageUI.LABEL_CHECKBOX, label);
	}

	public void uncheckToCheckboxByLabel(String label) {
		waitForElementClickable(CommonPageUI.LABEL_CHECKBOX, label);
		uncheckToDefaultCheckbox(CommonPageUI.LABEL_CHECKBOX, label);
	}

	public void selectItemInDropdownByLabel(String label, String value) {
		waitForElementClickable(CommonPageUI.LABEL_DROPDOWN, label);
		selectItemInDefaultDropdown(CommonPageUI.LABEL_DROPDOWN, value, label);
	}

	public void openPageByPageName(String pageName) {
		waitForElementClickable(CommonPageUI.LEFT_SIDEBAR_PAGE_LINKS, pageName);
		clickToElement(CommonPageUI.LEFT_SIDEBAR_PAGE_LINKS, pageName);
	}

	public String getMessageAppearAtNotificationBar() {
		waitForElementVisible(CommonPageUI.NOTICICATION_BAR_MESSAGE);
		return getElementText(CommonPageUI.NOTICICATION_BAR_MESSAGE);
	}

	public void clickToCloseButtonAtNotificationBar() {
		waitForElementClickable(CommonPageUI.NOTICICATION_BAR_CLOSE_BUTTON);
		clickToElement(CommonPageUI.NOTICICATION_BAR_CLOSE_BUTTON);
		sleepInSecond(1);
	}

	public void clickToLinkAtNotificationBar(String linkText) {
		waitForElementClickable(CommonPageUI.NOTICICATION_BAR_LINK, linkText);
		clickToElement(CommonPageUI.NOTICICATION_BAR_LINK, linkText);
	}

	public void hoverMouseToMenu(String label) {
		waitForElementVisible(CommonPageUI.MENU_LINK_DYNAMIC, label);
		hoverMouseToElement(CommonPageUI.MENU_LINK_DYNAMIC, label);
	}

	public void clickToLinkAtMenu(String label) {
		waitForElementClickable(CommonPageUI.MENU_LINK_DYNAMIC, label);
		clickToElement(CommonPageUI.MENU_LINK_DYNAMIC, label);
	}




	public void checkToRadioButtonByLabel(String label) {
		waitForElementClickable(CommonPageUI.LABEL_RADIO, label);
		checkToDefaultCheckboxOrRadio(CommonPageUI.LABEL_RADIO, label);
	}

	public void clickToLink(String linkText) {
		waitForElementClickable(CommonPageUI.TEXTVALUE_LINK_DYNAMIC, linkText);
		clickToElement(CommonPageUI.TEXTVALUE_LINK_DYNAMIC, linkText);
	}

	public String getPageBody() {
		waitForElementVisible(CommonPageUI.PAGE_BODY);
		return getElementText(CommonPageUI.PAGE_BODY);
	}

	public List<String> getProductList() {
		return getListWebElement(CommonPageUI.PRODUCT_NAME)
				.stream()
				.map(WebElement::getText)
				.collect(Collectors.toList());
	}

	public void clickToButtonOfAProduct(String productName, String buttonName) {
		waitForElementClickable(CommonPageUI.PRODUCT_BUTTONS_DYNAMIC, productName, buttonName);
		clickToElement(CommonPageUI.PRODUCT_BUTTONS_DYNAMIC, productName, buttonName);
		sleepInSecond(1);
	}

	public void inputToTextboxByPlaceholder(String placeHolder, String value) {
		waitForElementVisible(CommonPageUI.PLACEHOLDER_TEXTBOX_DYNAMIC, placeHolder);
		sendKeysToElement(CommonPageUI.PLACEHOLDER_TEXTBOX_DYNAMIC, value, placeHolder);
	}

	public int getOrderNumber() {
		waitForElementVisible(CommonPageUI.ORDER_NUMBER);
		String orderNumber = getElementText(CommonPageUI.ORDER_NUMBER);
		return extractOrderNumber(orderNumber);
	}

}

