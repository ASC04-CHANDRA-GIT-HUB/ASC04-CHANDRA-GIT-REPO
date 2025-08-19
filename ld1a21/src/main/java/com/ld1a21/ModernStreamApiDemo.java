package com.ld1a21;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ModernStreamApiDemo{
    public static void main(String args[]){
        List<Transaction> transactions= Arrays.asList();
    }
}
class Transaction{
    private final String id;
    private final BigDecimal amount;

    public  Transaction(String id, BigDecimal amount){
        this.id=id;
        this.amount=amount;
    }

    public String getId(){
        return id;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    @Override
    public String toString(){
        return "Transaction("+"amount="+amount+",id='"+id+'\''+')';
    }
}