package pl.bobkins.Currency;

public class Currency {
    String name;
    String code;
    double factor;
    double rate;

    public Currency(String name, String code, double factor, double rate) {
        this.name = name;
        this.code = code;
        this.factor = factor;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return this.code.equals(currency.getCode());
    }
}
