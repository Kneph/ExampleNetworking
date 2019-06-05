package com.example.examplenetworking

import androidx.multidex.MultiDexApplication
import io.realm.Realm
import io.realm.RealmConfiguration



class NetworkingApplication: MultiDexApplication() {

    private val singleton: NetworkingApplication? = null

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val realmConfig = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(realmConfig)
    }

    fun getInstance(): NetworkingApplication? {
        return singleton
    }



}