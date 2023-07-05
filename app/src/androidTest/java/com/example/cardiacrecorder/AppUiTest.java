package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.appcompat.widget.Toolbar;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class AppUiTest {
    @Rule
    public ActivityScenarioRule<HomeActivity> activityRule = new ActivityScenarioRule<HomeActivity>(HomeActivity.class);

    /**
     * Test app name,update record,delete record and status comment
     */

    @Test
    public void testAppName() {
     Espresso.onView(withText("CardiacRecorder")).check(matches(isDisplayed()));
    }

    @Test
    public void testSignupButtonNavigatesToSignupActivity(){

        //Check if the SignupActivity is displayed
        Espresso.onView(withId(R.id.activityLogin))
                .check(matches(isDisplayed()));
        // Perform a click action on the signup text
        onView(withId(R.id.textviewsignup)).perform(ViewActions.click());

        // Check if the HomeActivity layout is displayed
        onView(withId(R.id.signupActivity))
                .check(matches(isDisplayed()));

        //Insert the information of signup
        onView(withId(R.id.admininputusername)).perform(ViewActions.typeText("abcd"));
        onView(withId(R.id.admininputuseremail)).perform(ViewActions.typeText("abcd@gmail.com"));
        onView(withId(R.id.admininputuserpassword2)).perform(ViewActions.typeText("123456"));
        onView(withId(R.id.admininputconfirmuserpassword)).perform(ViewActions.typeText("123456"));
        onView(withId(R.id.admininputuserheight)).perform(ViewActions.typeText("5.3"));
        onView(withId(R.id.admininputuserweight)).perform(ViewActions.typeText("50"));
        Espresso.pressBack();
        onView(withId(R.id.admininputgender)).perform(ViewActions.typeText("male"));
        Espresso.pressBack();

        // Perform a click action on the signup button
        onView(withId(R.id.buttonnewregisteradmin)).perform(ViewActions.click());


    }

    @Test
    public void testLoginButtonNavigatesToHomeActivity() {
         //Homepage is opening first

        //Redirect to Login Page
        Espresso.onView(withId(R.id.activityLogin))
                .check(matches(isDisplayed()));

        // Enter email
        onView(withId(R.id.admininputemail)).perform(ViewActions.typeText("sumiyabarsha3@gmail.com"));

        // Enter password
        onView(withId(R.id.admininputuserpassword2)).perform(ViewActions.typeText("123456"));

        // Perform a click action on the login button
        onView(withId(R.id.adminbtnlogin)).perform(ViewActions.longClick());

        // Check if the HomeActivity layout is displayed
        Espresso.onView(withId(R.id.homeActivity))
               .check(matches(isDisplayed()));
    }




    @Test
    public void testClickFabOpensAddUpdateActivity() {

        // Wait for the view to be fully displayed
        onView(withId(R.id.fabAdd)).check(matches(isDisplayed()));

        // Perform a click action on the floating action button
        Espresso.onView(ViewMatchers.withId(R.id.fabAdd)).perform(click());

        // Check if the AddUpdateActivity is opened
        Espresso.onView(ViewMatchers.withId(R.id.addUpdateActivity))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }


}
