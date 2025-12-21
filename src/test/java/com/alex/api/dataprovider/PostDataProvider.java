package com.alex.api.dataprovider;

import org.testng.annotations.DataProvider;

public class PostDataProvider {

    @DataProvider(name = "validPosts")
    public Object[][] validPostsDP() {
        return new Object[][] {
            {"Alex", "QA Engineer", 1},
            {"Ivan", "Automation Tester", 2},
            {"Ana", "Developer", 3},
            {"Clara", "Support Specialist", 4}
        };
    }
}
