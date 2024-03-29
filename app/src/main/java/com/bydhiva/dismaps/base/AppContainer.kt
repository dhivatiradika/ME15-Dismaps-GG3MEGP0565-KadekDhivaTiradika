package com.bydhiva.dismaps.base

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.bydhiva.dismaps.data.DisasterRepository
import com.bydhiva.dismaps.data.datastore.SettingPreferences
import com.bydhiva.dismaps.domain.usecase.DisasterUseCases
import com.bydhiva.dismaps.domain.usecase.GetReports

class AppContainer {
    var mainContainer: MainContainer? = null
    var settingContainer: SettingContainer? = null
}

class MainContainer(private val disasterRepository: DisasterRepository) {
    val disasterUseCases by lazy {
        DisasterUseCases(
            getReports = GetReports(disasterRepository)
        )
    }
}

class SettingContainer(private val dataStore: DataStore<Preferences>) {
    val settingPreferences by lazy {
        SettingPreferences(dataStore)
    }
}