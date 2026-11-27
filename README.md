# GymFinder Canada 🇨🇦

An Android mobile application built with Kotlin and Jetpack Compose for finding, exploring, comparing, and bookmarking gyms across Canada. Developed for college course **PROG3211 – Programming Mobile Applications II**.

---

## 👥 Group Members

| Name | Student ID |
|---|---|
| **Karan Patel** | `8911445` |
| **Aryan Patel** | `8836568` |

**GitHub Repository**: [https://github.com/aryanp204/GymFinder-Canada](https://github.com/aryanp204/GymFinder-Canada)

---

## 🚀 Milestone 2 Overview — Gym Discovery & Room Database Integration

Milestone 2 extends the application with full local SQLite persistence using **Room Database**, real-time search, multi-facility filtering, sorting, city selection across 12 Canadian metropolitan areas, and comprehensive gym details.

### 🏛️ Architecture & Technologies
- **UI Framework**: Jetpack Compose with Material Design 3
- **Local Persistence**: Room SQLite Database with Flow observing queries
- **Architecture**: MVVM with Repository Pattern
- **State Management**: ViewModel, StateFlow, Coroutines
- **Navigation**: Navigation Compose
- **Language**: Kotlin 2.2.10
- **Min SDK**: API 26 (Android 8.0+)

---

## 🌆 Cities & Gym Records

### Supported Cities (12 Canadian Cities)
1. **Toronto, Ontario** (Default)
2. **Ottawa, Ontario**
3. **Mississauga, Ontario**
4. **Brampton, Ontario**
5. **Hamilton, Ontario**
6. **Kitchener, Ontario**
7. **Waterloo, Ontario**
8. **London, Ontario**
9. **Kingston, Ontario**
10. **Belleville, Ontario**
11. **Vancouver, British Columbia**
12. **Calgary, Alberta**

### Seed Database
- **Total Records**: **96 fictional gyms** (Exactly 8 gyms per city across all 12 cities).
- **Atomic Seeding**: Seeding executes once on initial database creation via `RoomDatabase.Callback`.
- **Offline Support**: Full database browsing works completely offline without network dependency.

#### Toronto Featured Gyms (Figma Reference Data)
| Gym Name | Monthly Price | Rating | Reviews | Category |
|---|---:|---:|---:|---|
| **IronPeak Fitness** | CAD $39.99 | 4.8 | 230 | Strength Training |
| **UrbanFit Studio** | CAD $44.99 | 4.6 | 197 | Boutique Studio |
| **EliteCore Athletics** | CAD $49.99 | 4.7 | 165 | Performance Training |
| **NorthStar Training** | CAD $34.99 | 4.5 | 142 | Budget Fitness |

---

## 📱 Implemented Screens & Features in Part 2

1. **Database-Driven Home Screen**:
   - Dynamic featured gyms loaded directly from Room database for the selected city.
   - City selector card displaying currently selected city and opening City Selection Screen.
   - Functional search field submitting queries to the Explore Screen.

2. **Explore / Gym List Screen**:
   - Real-time case-insensitive search by gym name, address, city, category, or facility keywords.
   - Horizontally scrollable filter chips (`All gyms`, `24/7`, `Pool`, `Training`, `Budget (≤$40)`, `Parking`, `Sauna`).
   - Multi-option sorting (`Top Rated`, `Price Low to High`, `Price High to Low`, `Name A to Z`).
   - Dynamic results count ("X gyms found").
   - Custom empty state card with filter reset.

3. **Gym Details Screen**:
   - Hero photography, gym name, location, monthly membership price in CAD, star rating, and review count.
   - 3 Functional Tabs:
     - **Overview**: Gym description, facility badge grid (24/7, Parking, Pool, Sauna, Training, Classes, Wi-Fi, Showers, Women Only), opening hours, and contact details.
     - **Membership**: Monthly pass, annual plan, and joining fee cards formatted in CAD.
     - **Reviews**: Demonstration community feedback.
   - Compare Gym button placeholder ("Comparison feature coming in Part 4!").

4. **City Selection Screen**:
   - Search bar filtering all 12 Canadian cities.
   - City list rows showing city name, province, number of available gyms (8 gyms per city), and orange selected highlight.
   - Updates `SharedCityViewModel` state across Home and Explore screens.

---

## 🧪 Testing & Verification

- **Unit Tests**: `ExploreSearchFilterTest` — **8 passed, 0 failed**. Verifies search, facility filter combinations, budget criteria, and sorting algorithms.
- **DAO Instrumentation Test**: `GymDaoTest` — Verifies Room database creation, 96 gym seed insertion, city queries, and featured gym filtering.
- **Build Verification**: `app:assembleDebug` — **BUILD SUCCESSFUL**.

---

## ⚠️ Demonstration Data Disclaimer
All gym names, addresses, phone numbers, email addresses, ratings, prices, and reviews are fictional demonstration records created for educational course evaluation purposes.

---

## 🔮 Roadmap & Next Milestones

- **Part 3 (Milestone 3)**: Persistent Favorites, Add/Edit Gym forms, DataStore preferences, Settings, and Gym Management.
- **Part 4 (Milestone 4)**: Live weather integration (Open-Meteo REST API), Firebase Cloud sync, Side-by-side Gym Comparison matrix, and final polish.
