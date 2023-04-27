package com.artemy.example

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.artemy.example.app.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainPageTest {
	@get:Rule
	var mActivity = ActivityScenarioRule(MainActivity::class.java)

	@Test
	fun testInitialQueryValue() {
		onView(withId(R.id.input_field)).check(matches(withText("fruits")))
	}
}