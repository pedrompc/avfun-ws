package com.events.domainmodel.event

import com.github.nscala_time.time.Imports._
import com.events.domainmodel.user._

class Event(
    val name: String,
    val location: Location,
    val categories: Traversable[Category],
    val tags: Traversable[Tag],
    val user: User,
    val durations: Traversable[Duration]) 
{
}
