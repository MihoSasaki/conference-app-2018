package io.github.droidkaigi.confsched2018.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.droidkaigi.confsched2018.presentation.sponsors.SponsorsActivity

@Module interface SponsorsActivityBuilder {
    @ContributesAndroidInjector(
            modules = [
                FragmentBuildersModule::class,
                SponsorsActivityModule::class
            ]
    )
    fun contributeSponsorsActivity(): SponsorsActivity
}
