package com.rafaelds.russianhelper

import android.app.Application
import com.rafaelds.russianhelper.di.AppComponent
import com.rafaelds.russianhelper.di.AppModule
import com.rafaelds.russianhelper.di.DaggerAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration

class RussianHelperApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("russian_helper.realm")
            .migration(SchemaMigration())
            .schemaVersion(2L)
            .build()
        Realm.setDefaultConfiguration(config)

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    fun getComponent() = appComponent
}