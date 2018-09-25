package com.example.devtrainee.aadharbridgedemo;

public class AadhaarNumberFormatter {

    private AadhaarNumberFormatter(){

    }

public static String formatWithSpaces(String aadhaarNumber){
//    ("(.{4})(?!$)", "$1 ")
    aadhaarNumber= aadhaarNumber.replace(" ", "").replaceAll("(.{4})(?!$)","$1 ");

    return aadhaarNumber;
}

}
