package org.jboss.wise.test.endpoints;

import java.net.URL;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.wise.test.utils.StartPage;
import org.jboss.wise.test.utils.PropUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;
import org.jboss.wise.test.utils.WiseTest;


/**
 * Check handling of data type from start to finish
 */
@RunWith(Arquillian.class)
public class FloatTestCase extends WiseTest {
    @Drone
    private WebDriver browser;

    @Page
    private StartPage homePage;

    @ArquillianResource
    private URL baseURL;

    @Before
    public void before() {
        setBrowser(browser);
        userAuthentication(baseURL.toString());

        Graphene.goTo(StartPage.class);
        Graphene.waitModel().withTimeout(30, TimeUnit.SECONDS);

        loadStepOneOfThree();
    }

    @Test
    public void floatTest(){

        // page: step 1
        confirmPageLoaded(PropUtils.get("page.endpoints"));
        checkStepOneData(PropUtils.get("endpoint.float"), PropUtils.get("tag.wise-gwt-inputBox"));

        // page: step 2
        confirmPageLoaded(PropUtils.get("page.config"));
        checkStepTwoData("191.91", false);
        checkMessageDisclosurePanel("<arg0>191.91</arg0>");
        gotoStepThree();

        // page: step 3
        confirmPageLoaded(PropUtils.get("page.invoke"));
        checkStepThreeData(1);
        checkMessageDisclosurePanel("<return>191.91</return>");
    }
}
