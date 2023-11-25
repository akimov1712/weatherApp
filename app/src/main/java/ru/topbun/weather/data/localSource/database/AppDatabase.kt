package ru.topbun.weather.data.localSource.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.topbun.weather.data.localSource.database.entity.WeatherDbEntity

@Database(
    entities = [
        WeatherDbEntity::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object{

        private var instance: AppDatabase? = null
        private const val DB_NAME = "test.db"

        fun getInstance(application: Application) = instance ?: synchronized(this){
            instance ?: buildDatabase(application).also { instance = it }
        }

        private fun buildDatabase(application: Application) =
            Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                DB_NAME
            ).build()

    }

}