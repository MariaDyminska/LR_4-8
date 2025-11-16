package model;
public class MortgageCredit extends Credit {
    private double propertyValue;
    public MortgageCredit(int id, String name, double sum, int termMonths, double interestRate, double propertyValue){
        super(id, name, sum, termMonths, interestRate, CreditType.MORTGAGE);
        this.propertyValue = propertyValue;
    }
    public double getPropertyValue(){ return propertyValue; }
    public void setPropertyValue(double v){ propertyValue = v; }
    @Override public String toString(){ return super.toString() + String.format(" | propertyValue=%.2f", propertyValue); }
}
