package com.yuvrajpatel.didemo.model

import dagger.Binds
import dagger.Module

@Module
abstract class SandDiskMemoryCardModule {
    @Binds
    abstract fun provideSandDiskMemoryCard( sandDiskMemoryCard: SandDiskMemoryCard) : MemoryCard
}