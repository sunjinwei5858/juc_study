package com.sunjinwei.blockqueue.producerconsumer.version1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SyncVersion {
    public static void main(String[] args) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime time = LocalDateTime.now();
        String localTime = df.format(time);
        LocalDate ldt = LocalDate.parse("2018/06/01",df);

        System.out.println(ldt.toString());
    }
}
