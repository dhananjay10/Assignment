package com.ddk.wiproassignment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.ddk.wiproassignment.ui.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val component = TestComponentRule(InstrumentationRegistry.getInstrumentation().targetContext)

    @Rule @JvmField
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {

    }

    @Test
    fun testCheckViewsDisplay(){
        onView(withId(R.id.rv_facts))
            .check(matches(isDisplayed()))
    }

}