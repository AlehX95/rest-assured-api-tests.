package com.alex.api.dataprovider;

import org.testng.annotations.DataProvider;

import com.alex.api.models.PostModel;

public class PostPojoDataProvider {

    @DataProvider(name = "validPostPojos")
    public Object[][] validPostPojosDP() {

        return new Object[][] {

            { new PostModel(
                    "Welcome Post",
                    "This is the first test post",
                    1
            ) },

            { new PostModel(
                    "QA Automation",
                    "Learning REST Assured with POJOs",
                    2
            ) },

            { new PostModel(
                    "API Testing",
                    "Practicing CRUD operations",
                    3
            ) }

        };
    }
}
