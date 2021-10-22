package com.aleksandrov.breakingbad.utils

import io.reactivex.rxjava3.core.Scheduler

interface SchedulersProvider {

    /**
     * Scheduler для работы в фоне
     */
    fun io(): Scheduler

    /**
     * Scheduler для работы в главном потоке
     */
    fun ui(): Scheduler

    /**
     * Scheduler для работы в одном потоке
     */
    fun computation(): Scheduler

}