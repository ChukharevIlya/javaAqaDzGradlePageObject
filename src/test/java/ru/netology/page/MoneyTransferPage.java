package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MoneyTransferPage {
    private SelenideElement heading = $x("//h1[text()=\"Пополнение карты\"]");
    private SelenideElement transferAmountField = $x("//*[@data-test-id=\"amount\"] //input[@class]");
    private SelenideElement moneySourceField = $x("//*[@data-test-id=\"from\"]//input");
    private SelenideElement refillButton = $x("//*[@data-test-id=\"action-transfer\"]//span[text()=\"Пополнить\"]");
    private SelenideElement cancelButton = $x("//*[@data-test-id=\"action-cancel\"]//span[text()=\"Отмена\"]");


    public DashboardPage transfer(DataHelper.CardInfo moneySource, String transferAmount) {
        heading.shouldBe(visible);
        transferAmountField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        transferAmountField.setValue(transferAmount);
        moneySourceField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        moneySourceField.setValue(moneySource.getNumber());
        refillButton.click();
        return new DashboardPage();
    }
}
