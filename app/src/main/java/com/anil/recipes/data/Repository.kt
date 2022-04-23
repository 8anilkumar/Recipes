package com.anil.recipes.data

import com.anil.recipes.data.network.LocalDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(remoteDataScrouce: RemoteDataScrouce,localDataSource: LocalDataSource){
    val remote = remoteDataScrouce
    val local = localDataSource

}