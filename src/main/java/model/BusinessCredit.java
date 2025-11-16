package model;
public class BusinessCredit extends model.Credit {
    private String businessType;
    public BusinessCredit(int id, String name, double sum, int termMonths, double interestRate, String businessType){
        super(id, name, sum, termMonths, interestRate, model.CreditType.BUSINESS);
        this.businessType = businessType;
    }
    public String getBusinessType(){ return businessType; }
    public void setBusinessType(String b){ businessType = b; }
    @Override public String toString(){ return super.toString() + String.format(" | businessType=%s", businessType); }
}
