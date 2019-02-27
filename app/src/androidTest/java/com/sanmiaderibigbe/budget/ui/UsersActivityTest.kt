package com.sanmiaderibigbe.budget.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.sanmiaderibigbe.budget.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsersActivityTest{

    @get:Rule
    var activityRule  = ActivityTestRule(UsersActivity::class.java)

    @Test
    fun fabAddNewUser_ShouldCreateNewUser(){
        val randomNUmber = (0..100000).random()
        onView(withId(R.id.fab_add_new_user))
            .perform(click())

        onView(withId(R.id.name_edit_txt))
            .perform(typeText("sanmi$randomNUmber"))
        onView(withId(R.id.amount_edit_txt))
            .perform(typeText(randomNUmber.toString()))


        onView(withText("CREATE")).inRoot(isDialog())
            .check(matches(isDisplayed()))
            .perform(click())
    }
}