package com.example.cis4034_n.data.di

import com.example.cis4034_n.data.repositories.QuestionMainRepository
import com.example.cis4034_n.data.repositories.QuestionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryDI {

    @Binds
    abstract fun getQuestionsRepository(questionMainRepository: QuestionMainRepository): QuestionRepository

}