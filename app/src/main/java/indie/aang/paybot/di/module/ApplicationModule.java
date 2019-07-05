package indie.aang.paybot.di.module;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import indie.aang.paybot.BuildConfig;
import indie.aang.paybot.data.db.AppDatabase;
import indie.aang.paybot.data.network.ApiService;
import indie.aang.paybot.data.network.FdpApi;
import indie.aang.paybot.data.network.RetrofitInterceptor;
import indie.aang.paybot.data.prefs.AppPreferencesHelper;
import indie.aang.paybot.data.prefs.PreferencesHelper;
import indie.aang.paybot.di.Scope.ApplicationContext;
import indie.aang.paybot.utilities.AppConstants;
import indie.aang.paybot.utilities.DatabaseMigrations;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by AangJnr on 19, September, 2018 @ 11:04 PM
 * Work Mail cibrahim@grameenfoundation.org
 * Personal mail aang.jnr@gmail.com
 */


@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application app) {
        application = app;
    }


    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }


    @Provides
    Application providesApplication() {
        return application;
    }

    @Singleton
    @Provides
    public AppDatabase providesDatabase() {

        return Room.databaseBuilder(application, AppDatabase.class, AppConstants.DATABASE_NAME)
                .allowMainThreadQueries()
                //.fallbackToDestructiveMigration()
                .addMigrations(DatabaseMigrations.MIGRATION_1_2, DatabaseMigrations.MIGRATION_2_3)
                 .build();
    }




    @Singleton
    @Provides
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Singleton
    @Provides
    SharedPreferences providesSharedPrefs() {
        return application.getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE);
    }


    @Singleton
    @Provides
    CompositeDisposable providesCompositeDisposable(){
        return new CompositeDisposable();
    }


    @Singleton
    @Provides
    RetrofitInterceptor providesRetrofitInterceptor(){
        return new RetrofitInterceptor(providesSharedPrefs().getString("PREF_KEY_ACCESS_TOKEN", null));
    }





    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        if(BuildConfig.DEBUG) {
            return new OkHttpClient.Builder()
                    //.addInterceptor(providesRetrofitInterceptor())
                    .addInterceptor(new HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
        }else{
            return new OkHttpClient.Builder()
                    //.addInterceptor(providesRetrofitInterceptor())
                    .build();
        }
    }




    @Singleton
    @Provides
    Retrofit providesRetrofit(OkHttpClient client){
                 return new Retrofit.Builder().baseUrl(providesSharedPrefs().getString(AppConstants.SERVER_URL, BuildConfig.END_POINT))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
    }




    @Singleton
    @Provides
    FdpApi providesFdpApi(Retrofit retrofit){

        return retrofit.create(FdpApi.class);
    }




    @Singleton
    @Provides
    ApiService providesApiService(FdpApi fdpApi) {
        return new ApiService(fdpApi);
    }




}