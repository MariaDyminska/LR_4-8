package model;
import java.io.Serializable;
import java.text.DecimalFormat;

public class Credit implements Serializable {
    private static final long serialVersionUID = 1L;
    protected int id;
    protected String name;
    protected double sum;
    protected int termMonths;
    protected double interestRate;
    protected model.CreditStatus status = model.CreditStatus.ACTIVE;
    protected model.CreditType type;

    public Credit(int id, String name, double sum, int termMonths, double interestRate,  model.CreditType type){
        this.id = id; this.name = name; this.sum = sum; this.termMonths = termMonths; this.interestRate = interestRate; this.type = type;
    }

    public int getId(){ return id; }
    public String getName(){ return name; }
    public double getSum(){ return sum; }
    public int getTermMonths(){ return termMonths; }
    public double getInterestRate(){ return interestRate; }
    public model.CreditStatus getStatus(){ return status; }
    public model.CreditType getType(){ return type; }

    public void setName(String n){ this.name = n; }
    public void setSum(double s){ this.sum = s; }
    public void setTermMonths(int t){ this.termMonths = t; }
    public void setInterestRate(double r){ this.interestRate = r; }
    public void setStatus( model.CreditStatus s){ this.status = s; }

    // annuity monthly payment
    public double calculateMonthlyPayment(){
        double P = sum;
        int n = termMonths;
        double r = (interestRate/100.0)/12.0;
        if(n <= 0) return 0;
        if(r <= 0) return P / n;
        return (r * P) / (1 - Math.pow(1 + r, -n));
    }

    public double totalPayment(){ return calculateMonthlyPayment() * termMonths; }
    public double overpay(){ return totalPayment() - sum; }
    public double getAmount() {
        return getSum();
    }
    public double getRate() {
        return interestRate;
    }

    public void setAmount(double amount) {
        setSum(amount);
    }
    public int getTerm() {
        return getTermMonths();
    }
    public void setTerm(int term) {
        setTermMonths(term);
    }
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.##");
        return String.format("%d | %s | sum=%s | term=%d | rate=%s%% | type=%s | status=%s", id, name, df.format(sum), termMonths, df.format(interestRate), type, status);
    }
}
