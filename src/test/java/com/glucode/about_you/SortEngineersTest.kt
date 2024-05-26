package com.glucode.about_you

import com.glucode.about_you.engineers.EngineersFragment
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Ignore

class SortEngineersTest {

    private lateinit var engineersFragment: EngineersFragment
    private lateinit var engineersList: List<Engineer>

    @Before
    fun setUp() {
        engineersFragment = EngineersFragment()
        engineersList = MockData.engineers
    }

    @Ignore
    @Test
    fun `sort engineers by years`() {
        val sortedEngineers = getSortedEngineersBy("years")
        val expectedSortedEngineers = MockData.getEngineersSortedByYears()

        assertEquals(expectedSortedEngineers, sortedEngineers)
    }

    @Ignore
    @Test
    fun `sort engineers by coffees`() {
        val sortedEngineers = getSortedEngineersBy("coffees")
        val expectedSortedEngineers = MockData.getEngineersSortedByCoffees()
        assertEquals(expectedSortedEngineers, sortedEngineers)
    }

    @Ignore
    @Test
    fun `sort engineers by bugs`() {
        val sortedEngineers = getSortedEngineersBy("bugs")
        val expectedSortedEngineers = MockData.getEngineersSortedByBugs()
        assertEquals(expectedSortedEngineers, sortedEngineers)
    }


    private fun getSortedEngineersBy(criteria: String): List<Engineer> {
        val sortEngineersByMethod = EngineersFragment::class.java.getDeclaredMethod(
            "sortEngineersBy",
            String::class.java
        )
        sortEngineersByMethod.isAccessible = true
        sortEngineersByMethod.invoke(engineersFragment, criteria)
        val engineersField = EngineersFragment::class.java.getDeclaredField("engineersList")
        engineersField.isAccessible = true
        @Suppress("UNCHECKED_CAST")
        return engineersField.get(engineersFragment) as List<Engineer>
    }
}