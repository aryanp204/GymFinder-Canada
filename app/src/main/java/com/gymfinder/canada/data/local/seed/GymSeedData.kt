package com.gymfinder.canada.data.local.seed

import com.gymfinder.canada.data.local.entity.GymEntity
import com.gymfinder.canada.util.CityDataProvider

object GymSeedData {

    fun getInitialGyms(): List<GymEntity> {
        val gyms = mutableListOf<GymEntity>()

        // 1. TORONTO, ONTARIO (8 gyms)
        gyms.add(
            GymEntity(
                id = "gym_toronto_1",
                name = "IronPeak Fitness",
                cityId = "city_toronto",
                cityName = "Toronto",
                province = "Ontario",
                address = "120 King Street West, Toronto, ON M5H 3T9",
                description = "Built for every kind of strong. Discover premium equipment, expert coaching, and a community that keeps you coming back.",
                monthlyPrice = 39.99,
                annualPrice = 399.99,
                joiningFee = 29.99,
                rating = 4.8,
                reviewCount = 230,
                openingHours = "24/7 Access",
                isOpen24Hours = true,
                hasParking = true,
                hasSwimmingPool = false,
                hasSauna = true,
                hasPersonalTraining = true,
                hasGroupClasses = true,
                hasWiFi = true,
                hasShowers = true,
                isWomenOnly = false,
                category = "Strength Training",
                imageReference = "ic_gym_ironpeak",
                phone = "(416) 555-0101",
                email = "info@ironpeak-example.com",
                isFeatured = true,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
        )
        gyms.add(
            GymEntity(
                id = "gym_toronto_2",
                name = "UrbanFit Studio",
                cityId = "city_toronto",
                cityName = "Toronto",
                province = "Ontario",
                address = "450 Yonge Street, Toronto, ON M4Y 1W9",
                description = "Modern boutique fitness center offering high-intensity interval training, sauna relaxation, and lap pool access.",
                monthlyPrice = 44.99,
                annualPrice = 449.99,
                joiningFee = 0.00,
                rating = 4.6,
                reviewCount = 197,
                openingHours = "5:00 AM - 11:00 PM",
                isOpen24Hours = false,
                hasParking = false,
                hasSwimmingPool = true,
                hasSauna = true,
                hasPersonalTraining = true,
                hasGroupClasses = true,
                hasWiFi = true,
                hasShowers = true,
                isWomenOnly = false,
                category = "Boutique Studio",
                imageReference = "ic_gym_urbanfit",
                phone = "(416) 555-0102",
                email = "contact@urbanfit-example.com",
                isFeatured = true,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
        )
        gyms.add(
            GymEntity(
                id = "gym_toronto_3",
                name = "EliteCore Athletics",
                cityId = "city_toronto",
                cityName = "Toronto",
                province = "Ontario",
                address = "780 Queen Street West, Toronto, ON M6J 1E9",
                description = "Comprehensive athletic performance facility featuring Olympic lifting platforms, turf sprint lanes, and high-speed Wi-Fi.",
                monthlyPrice = 49.99,
                annualPrice = 499.99,
                joiningFee = 19.99,
                rating = 4.7,
                reviewCount = 165,
                openingHours = "24/7 Access",
                isOpen24Hours = true,
                hasParking = false,
                hasSwimmingPool = false,
                hasSauna = false,
                hasPersonalTraining = true,
                hasGroupClasses = true,
                hasWiFi = true,
                hasShowers = true,
                isWomenOnly = false,
                category = "Performance Training",
                imageReference = "ic_gym_elitecore",
                phone = "(416) 555-0103",
                email = "toronto@elitecore-example.com",
                isFeatured = true,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
        )
        gyms.add(
            GymEntity(
                id = "gym_toronto_4",
                name = "NorthStar Training",
                cityId = "city_toronto",
                cityName = "Toronto",
                province = "Ontario",
                address = "210 Bloor Street West, Toronto, ON M5S 1T8",
                description = "Budget-friendly neighborhood gym equipped with state-of-the-art cardio machines, showers, and free parking.",
                monthlyPrice = 34.99,
                annualPrice = 349.99,
                joiningFee = 0.00,
                rating = 4.5,
                reviewCount = 142,
                openingHours = "6:00 AM - 10:00 PM",
                isOpen24Hours = false,
                hasParking = true,
                hasSwimmingPool = false,
                hasSauna = false,
                hasPersonalTraining = false,
                hasGroupClasses = true,
                hasWiFi = true,
                hasShowers = true,
                isWomenOnly = false,
                category = "Budget Fitness",
                imageReference = "ic_gym_northstar",
                phone = "(416) 555-0104",
                email = "support@northstar-example.com",
                isFeatured = true,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
        )
        gyms.add(
            GymEntity(
                id = "gym_toronto_5",
                name = "Titan Strength Club",
                cityId = "city_toronto",
                cityName = "Toronto",
                province = "Ontario",
                address = "55 Mercer Street, Toronto, ON M5V 9G9",
                description = "Hardcore powerlifting and bodybuilding warehouse with heavy dumbbells up to 150 lbs and specialized squat racks.",
                monthlyPrice = 59.99,
                annualPrice = 599.99,
                joiningFee = 39.99,
                rating = 4.9,
                reviewCount = 310,
                openingHours = "24/7 Access",
                isOpen24Hours = true,
                hasParking = true,
                hasSwimmingPool = false,
                hasSauna = true,
                hasPersonalTraining = true,
                hasGroupClasses = false,
                hasWiFi = true,
                hasShowers = true,
                isWomenOnly = false,
                category = "Strength Training",
                imageReference = "ic_gym_ironpeak",
                phone = "(416) 555-0105",
                email = "titan@example.com",
                isFeatured = false,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
        )
        gyms.add(
            GymEntity(
                id = "gym_toronto_6",
                name = "FlexZone Fitness",
                cityId = "city_toronto",
                cityName = "Toronto",
                province = "Ontario",
                address = "90 Spadina Avenue, Toronto, ON M5V 2J2",
                description = "Affordable, clean, and spacious community gym offering functional training equipment and 24/7 keycard access.",
                monthlyPrice = 29.99,
                annualPrice = 299.99,
                joiningFee = 0.00,
                rating = 4.4,
                reviewCount = 180,
                openingHours = "24/7 Access",
                isOpen24Hours = true,
                hasParking = true,
                hasSwimmingPool = false,
                hasSauna = false,
                hasPersonalTraining = true,
                hasGroupClasses = true,
                hasWiFi = true,
                hasShowers = true,
                isWomenOnly = false,
                category = "Budget Fitness",
                imageReference = "ic_gym_northstar",
                phone = "(416) 555-0106",
                email = "flexzone@example.com",
                isFeatured = false,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
        )
        gyms.add(
            GymEntity(
                id = "gym_toronto_7",
                name = "Summit Performance",
                cityId = "city_toronto",
                cityName = "Toronto",
                province = "Ontario",
                address = "320 Front Street West, Toronto, ON M5V 3B6",
                description = "Premium wellness and athletic training center equipped with infrared saunas, recovery plunge tubs, and personal training.",
                monthlyPrice = 64.99,
                annualPrice = 649.99,
                joiningFee = 49.99,
                rating = 4.8,
                reviewCount = 150,
                openingHours = "5:30 AM - 10:30 PM",
                isOpen24Hours = false,
                hasParking = true,
                hasSwimmingPool = true,
                hasSauna = true,
                hasPersonalTraining = true,
                hasGroupClasses = true,
                hasWiFi = true,
                hasShowers = true,
                isWomenOnly = false,
                category = "Premium Fitness",
                imageReference = "ic_gym_urbanfit",
                phone = "(416) 555-0107",
                email = "summit@example.com",
                isFeatured = false,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
        )
        gyms.add(
            GymEntity(
                id = "gym_toronto_8",
                name = "HerStrength Studio",
                cityId = "city_toronto",
                cityName = "Toronto",
                province = "Ontario",
                address = "140 Danforth Avenue, Toronto, ON M4K 1N1",
                description = "Exclusive women-only fitness center offering supportive group empowerment classes, yoga, and personal coaching.",
                monthlyPrice = 39.99,
                annualPrice = 399.99,
                joiningFee = 0.00,
                rating = 4.7,
                reviewCount = 135,
                openingHours = "6:00 AM - 9:00 PM",
                isOpen24Hours = false,
                hasParking = true,
                hasSwimmingPool = false,
                hasSauna = true,
                hasPersonalTraining = true,
                hasGroupClasses = true,
                hasWiFi = true,
                hasShowers = true,
                isWomenOnly = true,
                category = "Women's Fitness",
                imageReference = "ic_gym_elitecore",
                phone = "(416) 555-0108",
                email = "herstrength@example.com",
                isFeatured = false,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
        )

        // GENERATE SEED GYMS FOR ALL OTHER 11 CITIES (8 gyms per city = 88 gyms + 8 Toronto = 96 TOTAL)
        val otherCities = CityDataProvider.cities.filter { it.id != "city_toronto" }
        val gymTemplates = listOf(
            Triple("Forge Strength Centre", "Strength Training", "ic_gym_ironpeak"),
            Triple("CoreLab Athletics", "Cross Training", "ic_gym_elitecore"),
            Triple("Pulse Fitness Studio", "Boutique Studio", "ic_gym_urbanfit"),
            Triple("Elevate Fitness Club", "General Fitness", "ic_gym_northstar"),
            Triple("Apex Strength", "Performance Training", "ic_gym_ironpeak"),
            Triple("TrueNorth Fitness", "Budget Fitness", "ic_gym_northstar"),
            Triple("PeakForm Studio", "Premium Fitness", "ic_gym_urbanfit"),
            Triple("EmpowerWomen Fitness", "Women's Fitness", "ic_gym_elitecore")
        )

        otherCities.forEachIndexed { cityIndex, city ->
            gymTemplates.forEachIndexed { templateIndex, (baseName, category, imgRef) ->
                val gymNum = templateIndex + 1
                val isFeaturedGym = templateIndex < 2 // First 2 gyms per city are featured
                val price = when (templateIndex) {
                    0 -> 42.99
                    1 -> 48.99
                    2 -> 39.99
                    3 -> 29.99
                    4 -> 54.99
                    5 -> 24.99
                    6 -> 69.99
                    else -> 38.99
                }
                val ratingValue = 4.3 + ((cityIndex + templateIndex) % 7) * 0.1
                val roundedRating = Math.round(ratingValue * 10.0) / 10.0

                gyms.add(
                    GymEntity(
                        id = "gym_${city.id}_$gymNum",
                        name = "$baseName ${city.name}",
                        cityId = city.id,
                        cityName = city.name,
                        province = city.province,
                        address = "${100 + gymNum * 25} Main Street, ${city.name}, ${city.province}",
                        description = "Top-rated $category facility in ${city.name}. Equipped with modern workout machinery, clean locker rooms, and certified trainers.",
                        monthlyPrice = price,
                        annualPrice = price * 10,
                        joiningFee = if (price > 40) 25.00 else 0.00,
                        rating = roundedRating,
                        reviewCount = 80 + (cityIndex * 12) + (templateIndex * 15),
                        openingHours = if (templateIndex % 2 == 0) "24/7 Access" else "5:30 AM - 10:00 PM",
                        isOpen24Hours = templateIndex % 2 == 0,
                        hasParking = true,
                        hasSwimmingPool = templateIndex == 2 || templateIndex == 6,
                        hasSauna = templateIndex % 3 == 0,
                        hasPersonalTraining = templateIndex != 3,
                        hasGroupClasses = templateIndex != 0,
                        hasWiFi = true,
                        hasShowers = true,
                        isWomenOnly = templateIndex == 7,
                        category = category,
                        imageReference = imgRef,
                        phone = "(${604 + cityIndex * 10}) 555-${1000 + gymNum}",
                        email = "info@${baseName.lowercase().replace(" ", "")}-${city.name.lowercase()}example.com",
                        isFeatured = isFeaturedGym,
                        createdAt = System.currentTimeMillis(),
                        updatedAt = System.currentTimeMillis()
                    )
                )
            }
        }

        return gyms
    }
}
