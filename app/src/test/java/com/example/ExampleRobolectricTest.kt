package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.QuranDataSource
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("YANN QURAN", appName)
  }

  @Test
  fun `verify full 114 surahs available`() {
    val surahs = QuranDataSource.surahs
    assertEquals(114, surahs.size)
    assertEquals("Al-Fatihah", surahs.first().name)
    assertEquals("An-Nas", surahs.last().name)
  }
}
