package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
  private SelenideElement heading = $("[data-test-id=dashboard]");
  private SelenideElement refillFirstCardButton = $x("//*[@data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]//span[text()=\"Пополнить\"]");
  private SelenideElement refillSecondCardButton = $x("//*[@data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]//span[text()=\"Пополнить\"]");
  private SelenideElement updateButton = $x("//*[@data-test-id=\"action-reload\"]//span[@class=\"button__text\"]");

  public void getRefillFirstCardButton() {
    refillFirstCardButton.click();
  }

  public void getRefillSecondCardButton() {
    refillSecondCardButton.click();
  }

  public void getUpdateButton() {
    updateButton.click();
  }

  private ElementsCollection cards = $$(".list__item div");
  private final String balanceStart = "баланс: ";
  private final String balanceFinish = " р.";

  public DashboardPage() {
    heading.shouldBe(visible);
  }

  public int getCardBalance(String id) {
    String text = cards.findBy(text(DataHelper.getCardInfo(id).getNumber().substring(16))).text();
    return extractBalance(text);
  }

  private int extractBalance(String text) {
    val start = text.indexOf(balanceStart);
    val finish = text.indexOf(balanceFinish);
    val value = text.substring(start + balanceStart.length(), finish);
    return Integer.parseInt(value);
  }
}
