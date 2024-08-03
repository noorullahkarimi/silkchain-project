package com.silkchain.Silkchain.service;
import java.math.BigInteger;

public class DataConverter {

    // تبدیل هگزادسیمال به BigInteger
    public static BigInteger hexToBigInteger(String hex) {
        return new BigInteger(hex, 16);
    }

    // تبدیل BigInteger به هگزادسیمال
    public static String bigIntegerToHex(BigInteger bigInt) {
        return bigInt.toString(16);
    }

    public static void main(String[] args) {
        String hexData = "0x5b8a9d";
        BigInteger bigInt = hexToBigInteger(hexData.replace("0x", ""));
        System.out.println("BigInteger: " + bigInt);
        System.out.println("Hex: " + bigIntegerToHex(bigInt));
    }
}