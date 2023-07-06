package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.widget.Toolbar;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Matchers;
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
     onView(withText("CardiacRecorder")).check(matches(isDisplayed()));
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


        // Click on the tvDate view to open the DatePicker dialog
        onView(withId(R.id.tvDate)).perform(ViewActions.click());

        // Set the desired date using a custom ViewAction
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2023, 7, 4));

        // Close the DatePicker dialog and set the date in the text box
        onView(withId(android.R.id.button1)).perform(ViewActions.click());


        // Click on the tvTime view to open the TimePicker dialog
        onView(withId(R.id.tvTime)).perform(ViewActions.click());

        // Set the desired time using a custom ViewAction
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(23, 50));

        // Close the TimePicker dialog
        onView(withId(android.R.id.button1)).perform(ViewActions.click());




        onView(withId(R.id.editTextSysPressure)).perform(ViewActions.typeText("120"));
        onView(withId(R.id.editTextDysPressure)).perform(ViewActions.typeText("80"));
        onView(withId(R.id.editTextHeartRate)).perform(ViewActions.typeText("72"));
        Espresso.pressBack();
        onView(withId(R.id.editTextComment)).perform(ViewActions.typeText("abcd"));
        Espresso.pressBack();
        Espresso.onView(ViewMatchers.withId(R.id.buttonSave)).perform(click());
    }


}
