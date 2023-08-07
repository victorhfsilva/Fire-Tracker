package com.victor.firetracker_app.di

import android.content.Context
import com.victor.firetracker_app.presentation.views.TemperatureCircleView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {

    @Provides
    fun provideTemperatureCirclView(@ApplicationContext context: Context): TemperatureCircleView{
        return TemperatureCircleView(context)
    }
}