package com.gymfinder.canada

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gymfinder.canada.data.local.dao.GymDao
import com.gymfinder.canada.data.local.database.GymDatabase
import com.gymfinder.canada.data.local.seed.GymSeedData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GymDaoTest {

    private lateinit var db: GymDatabase
    private lateinit var dao: GymDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, GymDatabase::class.java).build()
        dao = db.gymDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndCountGyms_returnsExact96Gyms() = runBlocking {
        val seedGyms = GymSeedData.getInitialGyms()
        dao.insertAll(seedGyms)

        val totalCount = dao.getTotalGymCount().first()
        assertEquals(96, totalCount)

        val torontoCount = dao.getGymCountByCity("city_toronto").first()
        assertEquals(8, torontoCount)
    }

    @Test
    fun queryFeaturedGymsInToronto_returns4Gyms() = runBlocking {
        val seedGyms = GymSeedData.getInitialGyms()
        dao.insertAll(seedGyms)

        val featuredToronto = dao.getFeaturedGymsByCity("city_toronto").first()
        assertEquals(4, featuredToronto.size)
    }
}
