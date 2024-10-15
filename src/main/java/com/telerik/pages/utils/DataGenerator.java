package com.telerik.pages.utils;

import java.util.UUID;

public class DataGenerator {
    public static String generateUniqueTitle() {
        return "Mastering Automation with Telerik Testing - " + UUID.randomUUID().toString();
    }

    public static String generateUniqueDescription() {
        return "This is a test description that exceeds 100 characters in length. " +
                "It demonstrates the ability to create unique content, which is essential " +
                "for the success of the topic in the forum. Adding more details and information " +
                "to ensure it meets the requirements while remaining informative. " +
                UUID.randomUUID().toString();
    }
}
