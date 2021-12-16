package com.a7medkenawy.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.a7medkenawy.newsapp.model.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(Converters::class)
abstract class ArticleDatabase() :RoomDatabase(){

    abstract fun getArticleDao():ArticleDao

    companion object {

        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        fun buildDatabase(context: Context): ArticleDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    ArticleDatabase::class.java, "user_database"
                ).build()
                INSTANCE = instance
                return instance

            }
        }
    }
}