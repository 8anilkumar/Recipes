package com.anil.recipes.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(remoteDataScrouce: RemoteDataScrouce){
    val remote = remoteDataScrouce
}