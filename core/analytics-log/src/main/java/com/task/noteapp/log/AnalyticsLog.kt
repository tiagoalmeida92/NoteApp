package com.task.noteapp.log

import com.task.noteapp.analytics.Analytics
import com.task.noteapp.analytics.AnalyticsEvent

class AnalyticsLog(private val logger: LogService) : Analytics {

    override fun track(event: AnalyticsEvent) {
        logger.log("${event.screen} - ${event.event}")
    }
}