package com.oraz.service.locale;

import java.util.Scanner;

public class LocaleDemo {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String language = scanner.next();
            System.out.println(LocaleService.getMessage(language, "greeting.message"));
        }
    }
}
