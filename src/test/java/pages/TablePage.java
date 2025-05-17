package pages;


import herokuApp.Person;
import org.openqa.selenium.By;
import utils.Browser;

import java.util.List;
import java.util.stream.Collectors;

public class TablePage {
    public void open() {
        utils.Browser.visit("https://the-internet.herokuapp.com/tables");
    }

   public List<Person> getAllDues(String tableNumber) {

        List<Person> list = Browser.getElements(By.xpath(String.format("//table[@id='table%s']/tbody/tr",tableNumber)))
                .stream()
                .map(row ->
                        row.findElements(By.tagName("td"))
                )
                .map(cells ->
                        new Person(
                                cells.get(0).getText(), cells.get(1).getText(), cells.get(3).getText())
                )
                .collect(Collectors.toList())
                ;

        return list;
    }
    public double getMaxDue(String tableNumber) {
        return getAllDues(tableNumber).stream().mapToDouble(Person::getDue).max().orElse(0);
    }
    public double getMinDue(String tableNumber) {
        return getAllDues(tableNumber).stream().mapToDouble(Person::getDue).min().orElse(0);
    }
    public List<Object> getMaxDuePersons(String tableNumber) {
        return getAllDues(tableNumber)
                .stream()
                .filter(person -> person.getDue() == getMaxDue(tableNumber)).map(Person::getFullName)
                .collect(Collectors.toList())
                ;
    }
    public List<Object> getMinDuePersons(String tableNumber) {
        return getAllDues(tableNumber)
                .stream()
                .filter(person -> person.getDue() == getMinDue(tableNumber)).map(Person::getFullName)
                .collect(Collectors.toList())
                ;
    }

}
