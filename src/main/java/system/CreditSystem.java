
package system;
import model.*;
import java.util.*;
import java.text.DecimalFormat;

public class CreditSystem implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private List<Credit> creditList = new ArrayList<>();
    private Map<Integer, List<String>> history = new HashMap<>();

    // === ДОДАНО для тестів ===
    public List<Credit> getCredits() {
        return creditList;
    }

    public Credit getCreditById(int id) {
        return findById(id);
    }


    public List<Credit> getAllCredits() {
        return creditList;
    }

    public void addCredit(Credit c) {
        creditList.add(c);
        record(c.getId(), "Додано кредит: " + c.getName());
    }

    public void removeCredit(int id) {
        creditList.removeIf(c -> c.getId() == id);
        record(id, "Видалено кредит");
    }

    public Credit findById(int id) {
        for (Credit c : creditList)
            if (c.getId() == id)
                return c;
        return null;
    }

    public void updatePartial(int id, String name, String ssum, String sterm, String srate) {
        Credit c = findById(id);
        if (c == null) return;

        if (name != null) c.setName(name);
        if (ssum != null) { try { c.setSum(Double.parseDouble(ssum)); } catch (Exception e) {} }
        if (sterm != null) { try { c.setTermMonths(Integer.parseInt(sterm)); } catch (Exception e) {} }
        if (srate != null) { try { c.setInterestRate(Double.parseDouble(srate)); } catch (Exception e) {} }

        record(id, "Оновлено кредит");
    }

    public void listCredits() {
        if (creditList.isEmpty()) System.out.println("Немає кредитів");
        else creditList.forEach(System.out::println);
    }

    public void save(String fname) throws Exception {
        DataFileManager.saveObject(this, fname);
    }

    public void load(String fname) throws Exception {
        Object o = DataFileManager.loadObject(fname);
        if (o instanceof CreditSystem) {
            CreditSystem cs = (CreditSystem) o;
            this.creditList = cs.creditList;
            this.history = cs.history;
        } else throw new Exception("Невірний файл");
    }

    // payments and operations
    public void makePayment(int id, double amount) {
        Credit c = findById(id);
        if (c == null) {
            System.out.println("Кредит не знайдено");
            return;
        }
        c.setSum(c.getSum() - amount);
        record(id, "Платіж: " + amount);

        if (c.getSum() <= 0) {
            c.setStatus(CreditStatus.CLOSED);
            record(id, "Кредит закрито");
            System.out.println("Кредит закрито");
        } else {
            System.out.println("Залишок: " + c.getSum());
        }
    }

    public void earlyPayoff(int id, double amount) {
        makePayment(id, amount);
        record(id, "Дострокове погашення: " + amount);
    }

    public void refinance(int id, double newSum, double newRate, int newTerm) {
        Credit c = findById(id);
        if (c == null) {
            System.out.println("Кредит не знайдено");
            return;
        }

        if (newSum > 0) c.setSum(newSum);
        if (newRate >= 0) c.setInterestRate(newRate);
        if (newTerm > 0) c.setTermMonths(newTerm);

        record(id, "Рефінансовано");
        System.out.println("Оновлено: " + c);
    }

    public List<Credit> searchByParams(Double maxRate, Double minSum, Integer term) {
        List<Credit> res = new ArrayList<>();
        for (Credit c : creditList) {
            if (maxRate != null && c.getInterestRate() > maxRate) continue;
            if (minSum != null && c.getSum() < minSum) continue;
            if (term != null && c.getTermMonths() != term) continue;
            res.add(c);
        }
        return res;
    }

    public Credit findOptimal(double desiredSum, int desiredTerm, Double maxRate) {
        double bestScore = Double.MAX_VALUE;
        Credit best = null;

        for (Credit c : creditList) {
            if (desiredSum > 0 && c.getSum() < desiredSum) continue;
            if (desiredTerm > 0 && c.getTermMonths() != desiredTerm) continue;
            if (maxRate != null && c.getInterestRate() > maxRate) continue;

            double score = c.overpay();
            if (score < bestScore) {
                bestScore = score;
                best = c;
            }
        }
        return best;
    }

    public Credit smartHelper() {
        if (creditList.isEmpty()) return null;

        double bestR = Double.MAX_VALUE;
        Credit best = null;

        for (Credit c : creditList) {
            double payment = c.calculateMonthlyPayment();
            double over = c.overpay();
            double r = c.getInterestRate();
            double R = 0.5 * payment + 0.4 * over + 0.1 * r;

            if (R < bestR) {
                bestR = R;
                best = c;
            }
        }
        return best;
    }

    public void printSchedule(int id) {
        Credit c = findById(id);
        if (c == null) {
            System.out.println("Кредит не знайдено");
            return;
        }

        double P = c.getSum();
        int n = c.getTermMonths();
        double monthly = (c.getInterestRate() / 100.0) / 12.0;
        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println("Місяць | Платіж | Відсотки | Принципал | Залишок");

        if (monthly <= 0) {
            double pay = P / n;
            double rem = P;

            for (int i = 1; i <= n; i++) {
                double interest = 0;
                double principal = pay;
                rem -= principal;
                System.out.printf("%d | %s | %s | %s | %s\n",
                        i, df.format(pay), df.format(interest), df.format(principal), df.format(Math.max(rem, 0.0)));
            }
        } else {
            double pay = (monthly * P) / (1 - Math.pow(1 + monthly, -n));
            double rem = P;

            for (int i = 1; i <= n; i++) {
                double interest = rem * monthly;
                double principal = pay - interest;
                rem -= principal;
                System.out.printf("%d | %s | %s | %s | %s\n",
                        i, df.format(pay), df.format(interest), df.format(principal), df.format(Math.max(rem, 0.0)));
            }
        }
    }

    // history
    private void record(int id, String event) {
        history.computeIfAbsent(id, k -> new ArrayList<>()).add(event);
    }

    public List<String> getHistory(int id) {
        return history.getOrDefault(id, new ArrayList<>());
    }
}
