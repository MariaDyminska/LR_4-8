package model;
public class AutoCredit extends Credit {
    private String carBrand;
    public AutoCredit(int id, String name, double sum, int termMonths, double interestRate, String carBrand){
        super(id, name, sum, termMonths, interestRate, CreditType.AUTO);
        this.carBrand = carBrand;
    }
    public String getCarBrand(){ return carBrand; }
    public void setCarBrand(String b){ carBrand = b; }
    @Override public String toString(){ return super.toString() + String.format(" | carBrand=%s", carBrand); }
}
