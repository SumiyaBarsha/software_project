package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.app.DatePickerDialog;
import android.os.SystemClock;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import java.util.Calendar;

public class updateDeleteTest {
    @Rule
    public ActivityScenarioRule<HomeActivity> activityRule = new ActivityScenarioRule<HomeActivity>(HomeActivity.class);

    /**
     * Tests the update measurement functionality.
     * Simulates user interactions by entering new values for systolic pressure, diastolic pressure,
     * heart rate, and comment, and then clicks the update button.
     */
    @Test
    public  void testUpdateRecord(){

        //SystemClock.sleep(2000);
        // Perform a click action on the floating action button
        Espresso.onView(withId(R.id.rvList)).perform(longClick());
        SystemClock.sleep(1000);

// Click on the view to open the DatePicker dialog
        Espresso.onView(withId(R.id.tvDate)).perform(ViewActions.click());

        // Set the desired date using DatePickerActions
        Espresso.onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2023, Calendar.JULY, 4));

        // Close the DatePicker dialog by clicking the "OK" button
        onView(withText("OK")).perform(ViewActions.click());

        // Verify the selected date is displayed in the input field
        onView(withId(R.id.tvDate)).check(matches(withText("04/07/2023")));

        // Click on the tvTime view to open the TimePicker dialog
        onView(withId(R.id.tvTime)).perform(ViewActions.click());

        // Set the desired time using a custom ViewAction
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(23, 50));

        // Close the TimePicker dialog
        onView(withId(android.R.id.button1)).perform(ViewActions.click());


        // Update the values of other fields
        onView(withId(R.id.editTextSysPressure)).perform(clearText()).perform(typeText("130"));
        onView(withId(R.id.editTextDysPressure)).perform(clearText()).perform(typeText("85"));
        onView(withId(R.id.editTextHeartRate)).perform(clearText()).perform(typeText("75"));
        onView(withId(R.id.editTextComment)).perform(clearText()).perform(typeText("Updated comment"));

       // Perform a click action on the Save button
        onView(withId(R.id.buttonSave)).perform(ViewActions.click());

    }

    /**
     * Tests the delete measurement functionality.
     * Simulates a user long-clicking on a measurement item and clicks the delete button.
     */
    @Test
    public void testDeleteMeasurement(){
        Espresso.onView(withId(R.id.rvList)).perform(longClick());
        SystemClock.sleep(1000);
        onView(withId(R.id.tvDelete)).perform(click());
    }


}
