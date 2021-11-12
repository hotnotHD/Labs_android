package com.example.myapplication


import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.pressBackUnconditionally
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    //+check on fragments
    @Test
    fun testAboutA() {
        openAbout()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun testAboutB() {
        onView(withId(R.id.bnToSecond)).perform(click())
        openAbout()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
    }

    @Test
    fun testAboutC() {
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
    }

    @Test
    fun startFragmentIsReal(){
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun testJumpFrFirstToThird(){
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
    }

    @Test
    fun testJumpFromThirdToSec(){
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
    }

    @Test
    fun testJumpFromThirdToFirst(){
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun pressBackTests(){
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        pressBack()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun backStackTestOne(){
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        pressBack()
        pressBack()
        pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun backStackTestTwo(){
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToFirst)).perform(click())
        pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    // 3 -> 1 was not checked
    @Test
    fun backStackTestThree(){
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToFirst)).perform(click())
        pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun testMidBackA() {
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        pressBack()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun destroyTest(){
        activityRule.scenario.recreate()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        activityRule.scenario.recreate()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).perform(click())
        activityRule.scenario.recreate()
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        activityRule.scenario.recreate()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).perform(click())
        activityRule.scenario.recreate()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        activityRule.scenario.recreate()
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun navUpTestA(){
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun navUpTestB(){
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
    }

    @Test
    fun navUpTestAboutA(){
        openAbout()
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun navUpTestAboutB(){
        onView(withId(R.id.bnToSecond)).perform(click())
        openAbout()
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
    }

    @Test
    fun navUpTestAboutC(){
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
    }
}