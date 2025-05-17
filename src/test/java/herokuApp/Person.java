package herokuApp;

public class Person {
    private String firstName;
    private String lastName;
    private String due;
    public Person(String lastName,String firstName,String due){
        this.firstName=firstName;
        this.lastName=lastName;
        this.due = due;
    }
    public String getFullName(){
        return String.format("%s %s", firstName,lastName);
    }
    public double getDue(){
        return Double.valueOf(due.replace("$",""));
    }
}
