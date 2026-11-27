package com.gymfinder.canada.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gymfinder.canada.data.local.entity.GymEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GymDao {

    @Query("SELECT * FROM gyms ORDER BY rating DESC, reviewCount DESC")
    fun getAllGyms(): Flow<List<GymEntity>>

    @Query("SELECT * FROM gyms WHERE cityId = :cityId ORDER BY rating DESC, reviewCount DESC")
    fun getGymsByCity(cityId: String): Flow<List<GymEntity>>

    @Query("SELECT * FROM gyms WHERE id = :id")
    fun getGymById(id: String): Flow<GymEntity?>

    @Query("SELECT * FROM gyms WHERE cityId = :cityId AND isFeatured = 1 ORDER BY rating DESC")
    fun getFeaturedGymsByCity(cityId: String): Flow<List<GymEntity>>

    @Query("""
        SELECT * FROM gyms 
        WHERE cityId = :cityId 
        AND (
            name LIKE '%' || :query || '%' OR 
            cityName LIKE '%' || :query || '%' OR 
            address LIKE '%' || :query || '%' OR 
            category LIKE '%' || :query || '%' OR 
            description LIKE '%' || :query || '%'
        )
        ORDER BY rating DESC
    """)
    fun searchGymsInCity(cityId: String, query: String): Flow<List<GymEntity>>

    @Query("SELECT COUNT(*) FROM gyms WHERE cityId = :cityId")
    fun getGymCountByCity(cityId: String): Flow<Int>

    @Query("SELECT COUNT(*) FROM gyms")
    fun getTotalGymCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(gyms: List<GymEntity>): List<Long>
}
