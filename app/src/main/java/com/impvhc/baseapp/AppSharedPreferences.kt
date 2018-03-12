package com.impvhc.baseapp

import android.content.Context

/**
 * Created by victor on 2/13/18.
 */
class AppSharedPreferences(context: Context) {
    private val SHARED_PREFERENCES = "com.impvhc.baseapp.SHARED_PREFS"

    private val mSharedPreferences = context.getSharedPreferences("SHARED_PREFERENCES", Context.MODE_PRIVATE)

    private val MEMBER_ID = "member_id"

    fun getMemberID() = mSharedPreferences.getString(MEMBER_ID,"0")
}