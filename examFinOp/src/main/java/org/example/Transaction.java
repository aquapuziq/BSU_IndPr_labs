package org.example;

import java.util.Objects;

public class Transaction {
    String Date;
    String Description;
    double Amount;
    String Type = "Доход";

    public Transaction(String Date, String Description, double Amount, String Type) {
        this.Date = Date;
        this.Description = Description;
        this.Amount = Amount;
        this.Type = Type;
    }

    public String getDate(){
        return Date;
    }

    public String getDescription(){
        return Description;
    }

    public String getType(){
        return Type;
    }

    public double getAmount(){
        return Amount;
    }

    @Override
    public String toString() {
        return "Транзакция: " +
                "Дата ='" + Date + '\'' +
                ", Описание ='" + Description + '\'' +
                ", Сумма =" + Amount +
                ", Тип ='" + Type + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.Amount, Amount) == 0 &&
                Objects.equals(Date, that.Date) &&
                Objects.equals(Description, that.Description) &&
                Objects.equals(Type, that.Type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Date, Description, Amount, Type);
    }
}
