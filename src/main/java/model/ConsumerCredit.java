package model;

public class ConsumerCredit extends Credit {

    private boolean insuranceIncluded;

    public ConsumerCredit(int id, String name, double sum, int termMonths, double interestRate, boolean insuranceIncluded){
        super(id, name, sum, termMonths, interestRate, CreditType.CONSUMER);
        this.insuranceIncluded = insuranceIncluded;
    }

    // правильний геттер
    public boolean isInsurance() {
        return insuranceIncluded;
    }

    // стандартні геттер/сеттер
    public boolean isInsuranceIncluded(){
        return insuranceIncluded;
    }

    public void setInsuranceIncluded(boolean v){
        insuranceIncluded = v;
    }

    @Override
    public String toString(){
        return super.toString() + String.format(" | insurance=%s", insuranceIncluded);
    }
}
