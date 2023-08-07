package com.victor.firetracker_app.di

import android.app.Application
import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.victor.firetracker_app.data.repository.SharedPreferencesManager
import com.victor.firetracker_app.presentation.viewmodels.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideSharedPreferencesManager(@ApplicationContext context: Context): SharedPreferencesManager {
        return SharedPreferencesManager(context = context)
    }
}

