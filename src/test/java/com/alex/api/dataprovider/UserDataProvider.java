package com.alex.api.dataprovider;

import java.util.Arrays;

import org.testng.annotations.DataProvider;

import com.alex.api.models.AddressModel;
import com.alex.api.models.UserModel;

public class UserDataProvider {

    @DataProvider(name = "validUsers")
    public Object[][] validUsersDP() {

        return new Object[][] {

            { new UserModel(
                    "Alex",
                    "QA Engineer",
                    30,
                    Arrays.asList("admin", "qa"),
                    new AddressModel("Dublin", "D01")
            ) },

            { new UserModel(
                    "Ivan",
                    "Automation Tester",
                    28,
                    Arrays.asList("qa"),
                    new AddressModel("Cork", "C02")
            ) },

            { new UserModel(
                    "Ana",
                    "Developer",
                    26,
                    Arrays.asList("dev", "frontend"),
                    new AddressModel("Galway", "G04")
            ) }
        };
    }
}
