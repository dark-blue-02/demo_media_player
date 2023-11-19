package app.mp.common.di

import android.content.Context
import app.mp.model.local.database.TrackDatabase
import app.mp.model.remote.SpotifyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context) = lazy {
        TrackDatabase.getDatabase(context = context, scope = CoroutineScope(SupervisorJob()))
    }.value

    @Provides
    @Singleton
    fun provideSpotifyApi(): SpotifyApi = Retrofit.Builder()
        .baseUrl("https://accounts.spotify.com/api/")
        .build()
        .create(SpotifyApi::class.java)
}