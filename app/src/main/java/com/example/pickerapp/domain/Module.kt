package com.example.pickerapp.domain

import android.app.Application
import android.content.Context
import com.example.pickerapp.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    fun provideRepository(context: Context): PickerRepository{
        return PickerRepository(context)
    }
}