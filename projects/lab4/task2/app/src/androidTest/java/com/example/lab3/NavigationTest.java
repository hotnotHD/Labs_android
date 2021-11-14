package com.example.lab3;


import androidx.lifecycle.Lifecycle;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.pressBackUnconditionally;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;

import static com.example.lab3.AboutUtils.openAbout;
import static org.junit.Assert.assertTrue;

import android.app.Activity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NavigationTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    //+check on fragments
    @Test
    public void testAboutA() {
        openAbout();
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()));
    }

    @Test
    public void testAboutB() {
        onView(withId(R.id.bnToSecond)).perform(click());
        openAbout();
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()));
    }

    @Test
    public void testAboutC() {
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToThird)).perform(click());
        openAbout();
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()));
    }

    @Test
    public void startFragmentIsReal(){
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()));
    }

    @Test
    public void testJumpFrFirstToThird(){
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()));
        onView(withId(R.id.bnToThird)).perform(click());
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()));
    }

    @Test
    public void testJumpFromThirdToSec(){
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToThird)).perform(click());
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()));
    }

    @Test
    public void testJumpFromThirdToFirst(){
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToThird)).perform(click());
        onView(withId(R.id.bnToFirst)).perform(click());
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()));
    }

    @Test
    public void pressBackTests(){
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToThird)).perform(click());
        pressBack();
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()));
        pressBackUnconditionally();
        assertTrue(activityRule.getScenario().getState().isAtLeast(Lifecycle.State.DESTROYED));
    }

    @Test
    public void backStackTestOne(){
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToThird)).perform(click());
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToThird)).perform(click());
        pressBack();
        pressBack();
        pressBackUnconditionally();
        assertTrue(activityRule.getScenario().getState().isAtLeast(Lifecycle.State.DESTROYED));
    }

    @Test
    public void backStackTestTwo(){
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToFirst)).perform(click());
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToFirst)).perform(click());
        pressBackUnconditionally();
        assertTrue(activityRule.getScenario().getState().isAtLeast(Lifecycle.State.DESTROYED));
    }

    // 3 -> 1 was not checked
    @Test
    public void backStackTestThree(){
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToThird)).perform(click());
        onView(withId(R.id.bnToFirst)).perform(click());
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToThird)).perform(click());
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToFirst)).perform(click());
        pressBackUnconditionally();
        assertTrue(activityRule.getScenario().getState().isAtLeast(Lifecycle.State.DESTROYED));
    }

    @Test
    public void testMidBackA() {
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToThird)).perform(click());
        onView(withId(R.id.bnToSecond)).perform(click());
        pressBack();
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()));
    }

    @Test
    public void destroyTest(){
        activityRule.getScenario().onActivity(Activity::recreate);
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()));
        onView(withId(R.id.bnToSecond)).perform(click());
        activityRule.getScenario().onActivity(Activity::recreate);
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()));
        onView(withId(R.id.bnToThird)).perform(click());
        activityRule.getScenario().onActivity(Activity::recreate);
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()));
        onView(withId(R.id.bnToSecond)).perform(click());
        activityRule.getScenario().onActivity(Activity::recreate);
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()));
        onView(withId(R.id.bnToFirst)).perform(click());
        activityRule.getScenario().onActivity(Activity::recreate);
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()));
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToThird)).perform(click());
        activityRule.getScenario().onActivity(Activity::recreate);
        onView(withId(R.id.bnToFirst)).perform(click());
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()));
    }

    @Test
    public void navUpTestA(){
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click());
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()));
    }

    @Test
    public void navUpTestB(){
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToThird)).perform(click());
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click());
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()));
    }

    @Test
    public void navUpTestAboutA(){
        openAbout();
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click());
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()));
    }

    @Test
    public void navUpTestAboutB(){
        onView(withId(R.id.bnToSecond)).perform(click());
        openAbout();
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click());
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()));
    }

    @Test
    public void navUpTestAboutC(){
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToThird)).perform(click());
        openAbout();
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click());
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()));
    }

}