package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class signuptest {

    @Rule
    public ActivityScenarioRule<loginActivity> activityRule = new ActivityScenarioRule<loginActivity>(loginActivity.class);

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
}
