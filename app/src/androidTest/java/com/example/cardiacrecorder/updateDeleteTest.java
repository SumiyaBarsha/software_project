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

        SystemClock.sleep(5000);
        // Perform a click action on the floating action button
        Espresso.onView(withId(R.id.rvList)).perform(longClick());
        SystemClock.sleep(1000);



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
        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.rvList)).perform(longClick());
        SystemClock.sleep(1000);
        onView(withId(R.id.tvDelete)).perform(click());
        SystemClock.sleep(5000);
    }


}
