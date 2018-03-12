package com.impvhc.api.util

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.joda.time.format.ISODateTimeFormat
import java.lang.reflect.Type
import java.util.*

/**
 * Created by victor on 2/13/18.
 */
class DateDeserializer: JsonDeserializer<Date>{
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date? {
        var str : String? = json?.asJsonPrimitive?.asString
        try {
            return ISODateTimeFormat.dateTimeParser().parseDateTime(str).toDate()
        }catch (e: Exception){
            return null
        }
    }
}