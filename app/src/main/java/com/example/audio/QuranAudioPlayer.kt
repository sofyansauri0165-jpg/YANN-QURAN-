package com.example.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuranAudioPlayer(private val context: Context) {

    private var mediaPlayer: MediaPlayer? = null

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _currentPlayingAyah = MutableStateFlow<Int?>(null)
    val currentPlayingAyah: StateFlow<Int?> = _currentPlayingAyah.asStateFlow()

    private val _currentPlayingSurah = MutableStateFlow<Int?>(null)
    val currentPlayingSurah: StateFlow<Int?> = _currentPlayingSurah.asStateFlow()

    fun playAyah(surahNumber: Int, ayahNumber: Int, audioUrl: String, onFinished: (() -> Unit)? = null) {
        stop()

        if (audioUrl.isBlank()) {
            Log.w("QuranAudioPlayer", "Audio URL is empty")
            return
        }

        try {
            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(audioUrl)
                setOnPreparedListener { mp ->
                    mp.start()
                    _isPlaying.value = true
                    _currentPlayingSurah.value = surahNumber
                    _currentPlayingAyah.value = ayahNumber
                }
                setOnCompletionListener {
                    _isPlaying.value = false
                    _currentPlayingAyah.value = null
                    onFinished?.invoke()
                }
                setOnErrorListener { _, what, extra ->
                    Log.e("QuranAudioPlayer", "MediaPlayer error: what=$what, extra=$extra")
                    _isPlaying.value = false
                    _currentPlayingAyah.value = null
                    true
                }
                prepareAsync()
            }
        } catch (e: Exception) {
            Log.e("QuranAudioPlayer", "Failed to initialize MediaPlayer", e)
            _isPlaying.value = false
            _currentPlayingAyah.value = null
        }
    }

    fun pause() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                _isPlaying.value = false
            }
        }
    }

    fun resume() {
        mediaPlayer?.let {
            if (!it.isPlaying) {
                it.start()
                _isPlaying.value = true
            }
        }
    }

    fun stop() {
        try {
            mediaPlayer?.let {
                if (it.isPlaying) {
                    it.stop()
                }
                it.reset()
                it.release()
            }
        } catch (e: Exception) {
            Log.e("QuranAudioPlayer", "Error stopping player", e)
        } finally {
            mediaPlayer = null
            _isPlaying.value = false
            _currentPlayingAyah.value = null
        }
    }
}
