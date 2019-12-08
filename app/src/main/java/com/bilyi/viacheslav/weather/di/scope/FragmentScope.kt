package com.bilyi.viacheslav.weather.di.scope

import javax.inject.Scope

// скоуп для определения времени жизни обьектов. Имена не имеют значения, важно только в каком порядке стоят
@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope