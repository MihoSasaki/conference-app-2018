package io.github.droidkaigi.confsched2018.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.support.multidex.MultiDexApplication
import android.support.v7.app.AppCompatDelegate
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.github.droidkaigi.confsched2018.R
import io.github.droidkaigi.confsched2018.di.AppInjector
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject

@SuppressLint("Registered")
open class App : MultiDexApplication(), HasActivityInjector {
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        setupFirebase()
        setupVectorDrawable()
        setupThreeTenABP()
        setupDagger()
        setupCalligraphy()
    }

    private fun setupFirebase() {
        if (!FirebaseApp.getApps(this).isEmpty()) {
            val fireStore = FirebaseFirestore.getInstance()
            val settings = FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(true)
                    .build()
            fireStore.firestoreSettings = settings
        }
    }

    private fun setupVectorDrawable() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    private fun setupThreeTenABP() {
        AndroidThreeTen.init(this)
    }

    open fun setupDagger() {
        AppInjector.init(this)
    }

    private fun setupCalligraphy() {
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFont(R.font.notosans_medium)
                .build())
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> =
            dispatchingAndroidInjector
}
