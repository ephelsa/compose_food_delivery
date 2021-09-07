package com.github.ephelsa.framework.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
internal annotation class DefaultDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
internal annotation class IODispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
internal annotation class MainDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
internal annotation class MainImmediateDispatcher