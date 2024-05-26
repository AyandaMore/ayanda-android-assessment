package com.glucode.about_you

import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.glucode.about_you.engineers.EngineersFragment
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData
import org.mockito.Mockito.mock

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.mockito.Mockito.`when`

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.glucode.about_you", appContext.packageName)
    }

}