package com.alex.api.dataprovider;

import java.util.Arrays;

import org.testng.annotations.DataProvider;

import com.alex.api.models.AddressModel;
import com.alex.api.models.UserModel;

public class PutPojoDataProvider {

    @DataProvider(name = "validPutUsers")
    public Object[][] validPutUsersDP() {

        return new Object[][] {

            { new UserModel(
                    "Alex Updated",
                    "Senior QA Engineer",
                    31,
                    Arrays.asList("admin", "qa"),
                    new AddressModel("Cork", "C02")
            ) },

            { new UserModel(
                    "Ivan Updated",
                    "Automation Lead",
                    29,
                    Arrays.asList("qa", "automation"),
                    new AddressModel("Dublin", "D01")
            ) },

            { new UserModel(
                    "Ana Updated",
                    "Senior Developer",
                    27,
                    Arrays.asList("dev", "frontend"),
                    new AddressModel("Galway", "G04")
            ) }
        };
    }
}
