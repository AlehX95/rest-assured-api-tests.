package com.alex.api.base;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;



import io.qameta.allure.testng.AllureTestNg;


@Listeners({ AllureTestNg.class })
public class BaseTest {

    protected BaseRequest baseRequest;

    @BeforeClass
    public void setup() {
        baseRequest = new BaseRequest();
    }

}
