import React, { useState, useEffect, useRef } from 'react';
import {
  BookOpen,
  Bookmark as BookmarkIcon,
  BookmarkCheck,
  Check,
  ChevronRight,
  Copy,
  FolderHeart,
  History,
  Moon,
  Pause,
  Play,
  RotateCcw,
  Search,
  Share2,
  Sliders,
  Sparkles,
  Square,
  Star,
  Sun,
  Volume2,
  X
} from 'lucide-react';
import {
  SURAHS_LIST,
  DAILY_VERSE,
  fetchAyahsForSurah,
  Surah,
  Ayah,
  Bookmark,
  LastRead
} from './data/quranData';

export default function App() {
  // Navigation
  const [activeTab, setActiveTab] = useState<'beranda' | 'quran' | 'bookmark' | 'pengaturan'>('beranda');
  const [activeSurah, setActiveSurah] = useState<Surah | null>(null);
  const [ayahs, setAyahs] = useState<Ayah[]>([]);
  const [isLoadingAyahs, setIsLoadingAyahs] = useState<boolean>(false);

  // Search & Filter
  const [searchQuery, setSearchQuery] = useState('');
  const [filterType, setFilterType] = useState<'all' | 'makkiyah' | 'madaniyah' | 'juz'>('all');
  const [selectedJuz, setSelectedJuz] = useState<number>(1);

  // Persistence (LocalStorage)
  const [lastRead, setLastRead] = useState<LastRead>(() => {
    const saved = localStorage.getItem('yann_last_read');
    return saved ? JSON.parse(saved) : { surahNumber: 18, surahName: 'Al-Kahf', ayahNumber: 10, juzNumber: 15, timestamp: Date.now() };
  });

  const [bookmarks, setBookmarks] = useState<Bookmark[]>(() => {
    const saved = localStorage.getItem('yann_bookmarks');
    return saved ? JSON.parse(saved) : [
      {
        id: '1',
        surahNumber: 18,
        surahName: 'Al-Kahf',
        ayahNumber: 10,
        arabicSnippet: 'إِذْ أَوَى الْفِتْيَةُ إِلَى الْكَهْفِ فَقَالُوا رَبَّنَا آتِنَا مِن لَّدُنكَ رَحْمَةً',
        translationSnippet: '(Ingatlah) ketika pemuda-pemuda itu berlindung ke dalam gua lalu mereka berdoa...',
        createdAt: Date.now() - 3600000,
        note: 'Doa pemuda Al-Kahfi'
      }
    ];
  });

  const [favoriteSurahs, setFavoriteSurahs] = useState<number[]>(() => {
    const saved = localStorage.getItem('yann_favorites');
    return saved ? JSON.parse(saved) : [1, 18, 36, 55, 67];
  });

  // Settings
  const [darkMode, setDarkMode] = useState<boolean>(() => {
    return localStorage.getItem('yann_theme') === 'dark';
  });
  const [arabicFontSize, setArabicFontSize] = useState<number>(() => {
    return Number(localStorage.getItem('yann_arabic_font_size')) || 28;
  });
  const [translationFontSize, setTranslationFontSize] = useState<number>(() => {
    return Number(localStorage.getItem('yann_trans_font_size')) || 15;
  });
  const [showLatin, setShowLatin] = useState<boolean>(() => {
    return localStorage.getItem('yann_show_latin') !== 'false';
  });
  const [showTranslation, setShowTranslation] = useState<boolean>(() => {
    return localStorage.getItem('yann_show_trans') !== 'false';
  });
  const [selectedReciter, setSelectedReciter] = useState<string>('Mishary Rashid Alafasy');

  // Audio Playback
  const [playingAyah, setPlayingAyah] = useState<number | null>(null);
  const [isPlayingAudio, setIsPlayingAudio] = useState<boolean>(false);
  const audioRef = useRef<HTMLAudioElement | null>(null);

  // Quick settings modal inside reader
  const [showReaderSettings, setShowReaderSettings] = useState(false);

  // Sync to localStorage
  useEffect(() => {
    localStorage.setItem('yann_last_read', JSON.stringify(lastRead));
  }, [lastRead]);

  useEffect(() => {
    localStorage.setItem('yann_bookmarks', JSON.stringify(bookmarks));
  }, [bookmarks]);

  useEffect(() => {
    localStorage.setItem('yann_favorites', JSON.stringify(favoriteSurahs));
  }, [favoriteSurahs]);

  useEffect(() => {
    localStorage.setItem('yann_theme', darkMode ? 'dark' : 'light');
    if (darkMode) {
      document.documentElement.classList.add('dark');
    } else {
      document.documentElement.classList.remove('dark');
    }
  }, [darkMode]);

  useEffect(() => {
    localStorage.setItem('yann_arabic_font_size', arabicFontSize.toString());
    localStorage.setItem('yann_trans_font_size', translationFontSize.toString());
    localStorage.setItem('yann_show_latin', showLatin.toString());
    localStorage.setItem('yann_show_trans', showTranslation.toString());
  }, [arabicFontSize, translationFontSize, showLatin, showTranslation]);

  // Audio lifecycle
  useEffect(() => {
    return () => {
      if (audioRef.current) {
        audioRef.current.pause();
        audioRef.current = null;
      }
    };
  }, []);

  const openSurah = async (surahNumber: number, targetAyah: number = 1) => {
    const surah = SURAHS_LIST.find(s => s.number === surahNumber);
    if (!surah) return;
    setActiveSurah(surah);
    setIsLoadingAyahs(true);
    stopAudio();

    const data = await fetchAyahsForSurah(surahNumber);
    setAyahs(data);
    setIsLoadingAyahs(false);

    // Scroll to ayah
    if (targetAyah > 1) {
      setTimeout(() => {
        const el = document.getElementById(`ayah-${targetAyah}`);
        if (el) el.scrollIntoView({ behavior: 'smooth', block: 'center' });
      }, 300);
    }
  };

  const closeReader = () => {
    stopAudio();
    setActiveSurah(null);
    setAyahs([]);
  };

  const handlePlayAudio = (ayah: Ayah) => {
    if (playingAyah === ayah.numberInSurah && isPlayingAudio) {
      audioRef.current?.pause();
      setIsPlayingAudio(false);
      return;
    }

    if (audioRef.current) {
      audioRef.current.pause();
    }

    if (!ayah.audioUrl) {
      alert('Audio untuk ayat ini belum tersedia.');
      return;
    }

    const audio = new Audio(ayah.audioUrl);
    audioRef.current = audio;
    setPlayingAyah(ayah.numberInSurah);
    setIsPlayingAudio(true);

    audio.play().catch(e => {
      console.error(e);
      setIsPlayingAudio(false);
      setPlayingAyah(null);
    });

    audio.onended = () => {
      setIsPlayingAudio(false);
      // Auto-next
      const nextAyah = ayahs.find(a => a.numberInSurah === ayah.numberInSurah + 1);
      if (nextAyah && nextAyah.audioUrl) {
        handlePlayAudio(nextAyah);
      } else {
        setPlayingAyah(null);
      }
    };
  };

  const stopAudio = () => {
    if (audioRef.current) {
      audioRef.current.pause();
      audioRef.current = null;
    }
    setIsPlayingAudio(false);
    setPlayingAyah(null);
  };

  const toggleBookmark = (ayah: Ayah) => {
    if (!activeSurah) return;
    const exists = bookmarks.some(b => b.surahNumber === ayah.surahNumber && b.ayahNumber === ayah.numberInSurah);
    if (exists) {
      setBookmarks(bookmarks.filter(b => !(b.surahNumber === ayah.surahNumber && b.ayahNumber === ayah.numberInSurah)));
    } else {
      const newBm: Bookmark = {
        id: Date.now().toString(),
        surahNumber: ayah.surahNumber,
        surahName: activeSurah.name,
        ayahNumber: ayah.numberInSurah,
        arabicSnippet: ayah.arabicText.slice(0, 60),
        translationSnippet: ayah.translationText.slice(0, 120),
        createdAt: Date.now(),
      };
      setBookmarks([newBm, ...bookmarks]);
    }
  };

  const markLastRead = (ayah: Ayah) => {
    if (!activeSurah) return;
    setLastRead({
      surahNumber: activeSurah.number,
      surahName: activeSurah.name,
      ayahNumber: ayah.numberInSurah,
      juzNumber: activeSurah.juzStart,
      timestamp: Date.now(),
    });
  };

  const toggleFavorite = (surahNumber: number) => {
    if (favoriteSurahs.includes(surahNumber)) {
      setFavoriteSurahs(favoriteSurahs.filter(id => id !== surahNumber));
    } else {
      setFavoriteSurahs([...favoriteSurahs, surahNumber]);
    }
  };

  const copyToClipboard = (text: string) => {
    navigator.clipboard.writeText(text);
    alert('Ayat berhasil disalin ke clipboard!');
  };

  const shareText = (title: string, text: string) => {
    if (navigator.share) {
      navigator.share({ title, text }).catch(() => {});
    } else {
      copyToClipboard(text);
    }
  };

  // Filtered surahs
  const filteredSurahs = SURAHS_LIST.filter(surah => {
    const matchesSearch =
      surah.name.toLowerCase().includes(searchQuery.toLowerCase()) ||
      surah.translation.toLowerCase().includes(searchQuery.toLowerCase()) ||
      surah.arabicName.includes(searchQuery) ||
      surah.number.toString() === searchQuery;

    if (!matchesSearch) return false;
    if (filterType === 'makkiyah') return surah.revelationType === 'Makkiyah';
    if (filterType === 'madaniyah') return surah.revelationType === 'Madaniyah';
    if (filterType === 'juz') return surah.juzStart === selectedJuz;
    return true;
  });

  return (
    <div className={`min-h-screen ${darkMode ? 'dark bg-[#071F18] text-[#F5F0E2]' : 'bg-[#FFFEF9] text-[#18372B]'}`}>
      {/* Top Navbar */}
      <header className="sticky top-0 z-40 bg-[#004D35] text-white shadow-md">
        <div className="max-w-4xl mx-auto px-4 h-16 flex items-center justify-between">
          <div className="flex items-center space-x-3">
            {activeSurah ? (
              <button
                onClick={closeReader}
                className="p-2 -ml-2 rounded-lg hover:bg-white/10 transition-colors"
                title="Kembali"
              >
                <ChevronRight className="w-6 h-6 rotate-180" />
              </button>
            ) : (
              <div className="w-10 h-10 rounded-xl bg-[#F4BE45]/20 flex items-center justify-center border border-[#F4BE45]/40 shadow-inner">
                <BookOpen className="w-5 h-5 text-[#F4BE45]" />
              </div>
            )}

            <div>
              <div className="flex items-center space-x-2">
                <h1 className="font-bold text-lg tracking-wide">
                  {activeSurah ? activeSurah.name : 'YANN QURAN'}
                </h1>
                {!activeSurah && (
                  <span className="w-2 h-2 rounded-full bg-[#F4BE45] inline-block animate-pulse"></span>
                )}
              </div>
              <p className="text-xs text-[#FFE39A] opacity-90 truncate max-w-[200px] sm:max-w-xs">
                {activeSurah
                  ? `${activeSurah.translation} • ${activeSurah.numberOfAyahs} Ayat`
                  : 'Temukan Ketenangan dalam Setiap Ayat'}
              </p>
            </div>
          </div>

          <div className="flex items-center space-x-2">
            {activeSurah ? (
              <button
                onClick={() => setShowReaderSettings(true)}
                className="p-2 rounded-lg hover:bg-white/10 text-white transition-colors flex items-center space-x-1"
                title="Pengaturan Teks"
              >
                <Sliders className="w-5 h-5" />
              </button>
            ) : (
              <button
                onClick={() => setDarkMode(!darkMode)}
                className="p-2 rounded-lg hover:bg-white/10 text-[#FFE39A] transition-colors"
                title={darkMode ? 'Mode Terang' : 'Mode Gelap'}
              >
                {darkMode ? <Sun className="w-5 h-5 text-[#F4BE45]" /> : <Moon className="w-5 h-5 text-[#FFE39A]" />}
              </button>
            )}
          </div>
        </div>
      </header>

      {/* Main Content Area */}
      <main className="max-w-4xl mx-auto px-4 pb-24 pt-4">
        {activeSurah ? (
          /* Reader Screen */
          <div>
            {/* Bismillah Header (except At-Taubah #9) */}
            {activeSurah.number !== 9 && (
              <div className="my-4 p-6 rounded-2xl bg-gradient-to-r from-[#EAF3EC] to-[#f4faf6] dark:from-[#103528] dark:to-[#0c2c21] border border-[#F4BE45]/30 text-center shadow-sm">
                <p className="font-arabic text-3xl sm:text-4xl text-[#004D35] dark:text-[#FFE39A]">
                  بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ
                </p>
                <p className="text-xs text-[#66736A] dark:text-[#D4E0D7] mt-2 italic">
                  Dengan nama Allah Yang Maha Pengasih, Maha Penyayang
                </p>
              </div>
            )}

            {isLoadingAyahs ? (
              <div className="py-20 text-center text-[#66736A] dark:text-[#D4E0D7]">
                <div className="w-8 h-8 border-4 border-[#008653] border-t-transparent rounded-full animate-spin mx-auto mb-3"></div>
                <p>Memuat ayat Al-Qur'an...</p>
              </div>
            ) : (
              <div className="space-y-4">
                {ayahs.map(ayah => {
                  const isBookmarked = bookmarks.some(
                    b => b.surahNumber === ayah.surahNumber && b.ayahNumber === ayah.numberInSurah
                  );
                  const isLast =
                    lastRead.surahNumber === ayah.surahNumber && lastRead.ayahNumber === ayah.numberInSurah;
                  const isPlaying = isPlayingAudio && playingAyah === ayah.numberInSurah;

                  return (
                    <div
                      key={ayah.numberInSurah}
                      id={`ayah-${ayah.numberInSurah}`}
                      className={`p-5 rounded-2xl transition-all duration-200 border ${
                        isPlaying
                          ? 'bg-[#EAF3EC] dark:bg-[#164434] border-[#F4BE45] shadow-md ring-2 ring-[#F4BE45]/30'
                          : isLast
                          ? 'bg-[#EAF3EC]/60 dark:bg-[#164434]/60 border-[#008653]/40'
                          : 'bg-white dark:bg-[#103528] border-[#E5E9E4] dark:border-[#1E523F] hover:border-[#008653]/30 shadow-sm'
                      }`}
                    >
                      {/* Action Bar */}
                      <div className="flex items-center justify-between pb-3 border-b border-[#E5E9E4] dark:border-[#1E523F]/60 mb-4">
                        <div className="flex items-center space-x-2">
                          <span className="w-8 h-8 rounded-lg bg-[#004D35] text-[#FFE39A] font-bold text-xs flex items-center justify-center border border-[#F4BE45]/40 shadow-sm">
                            {ayah.numberInSurah}
                          </span>
                          <span className="text-xs text-[#66736A] dark:text-[#D4E0D7] font-medium">
                            Juz {ayah.juz}
                          </span>
                        </div>

                        <div className="flex items-center space-x-1">
                          <button
                            onClick={() => handlePlayAudio(ayah)}
                            className={`p-2 rounded-lg transition-colors ${
                              isPlaying
                                ? 'bg-[#008653] text-white'
                                : 'text-[#66736A] dark:text-[#D4E0D7] hover:bg-[#EAF3EC] dark:hover:bg-[#164434]'
                            }`}
                            title="Putar Audio"
                          >
                            {isPlaying ? <Pause className="w-4 h-4" /> : <Play className="w-4 h-4" />}
                          </button>

                          <button
                            onClick={() => toggleBookmark(ayah)}
                            className={`p-2 rounded-lg transition-colors ${
                              isBookmarked
                                ? 'text-[#B78013] hover:bg-[#FFE39A]/20'
                                : 'text-[#66736A] dark:text-[#D4E0D7] hover:bg-[#EAF3EC] dark:hover:bg-[#164434]'
                            }`}
                            title="Bookmark Ayat"
                          >
                            {isBookmarked ? (
                              <BookmarkCheck className="w-4 h-4 fill-current text-[#B78013]" />
                            ) : (
                              <BookmarkIcon className="w-4 h-4" />
                            )}
                          </button>

                          <button
                            onClick={() => markLastRead(ayah)}
                            className={`p-2 rounded-lg transition-colors ${
                              isLast
                                ? 'text-[#008653] font-bold'
                                : 'text-[#66736A] dark:text-[#D4E0D7] hover:bg-[#EAF3EC] dark:hover:bg-[#164434]'
                            }`}
                            title="Tandai Terakhir Dibaca"
                          >
                            <History className="w-4 h-4" />
                          </button>

                          <button
                            onClick={() =>
                              copyToClipboard(
                                `${ayah.arabicText}\n\n${ayah.latinText}\n\n"${ayah.translationText}" (QS. ${activeSurah.name}: ${ayah.numberInSurah})`
                              )
                            }
                            className="p-2 rounded-lg text-[#66736A] dark:text-[#D4E0D7] hover:bg-[#EAF3EC] dark:hover:bg-[#164434] transition-colors"
                            title="Salin Ayat"
                          >
                            <Copy className="w-4 h-4" />
                          </button>

                          <button
                            onClick={() =>
                              shareText(
                                `QS. ${activeSurah.name}: ${ayah.numberInSurah}`,
                                `${ayah.arabicText}\n\n${ayah.latinText}\n\n"${ayah.translationText}"\n\n— QS. ${activeSurah.name}: ${ayah.numberInSurah} (YANN QURAN)`
                              )
                            }
                            className="p-2 rounded-lg text-[#66736A] dark:text-[#D4E0D7] hover:bg-[#EAF3EC] dark:hover:bg-[#164434] transition-colors"
                            title="Bagikan Ayat"
                          >
                            <Share2 className="w-4 h-4" />
                          </button>
                        </div>
                      </div>

                      {/* Arabic Text */}
                      <p
                        className="font-arabic text-right my-4 text-[#18372B] dark:text-[#F5F0E2]"
                        style={{ fontSize: `${arabicFontSize}px` }}
                      >
                        {ayah.arabicText}
                      </p>

                      {/* Latin Transliteration */}
                      {showLatin && ayah.latinText && (
                        <p className="text-[#008653] dark:text-[#FFE39A] italic text-sm mb-2 font-medium">
                          {ayah.latinText}
                        </p>
                      )}

                      {/* Indonesian Translation */}
                      {showTranslation && ayah.translationText && (
                        <p
                          className="text-[#45584C] dark:text-[#D4E0D7] leading-relaxed"
                          style={{ fontSize: `${translationFontSize}px` }}
                        >
                          {ayah.translationText}
                        </p>
                      )}
                    </div>
                  );
                })}
              </div>
            )}

            {/* Sticky Audio Bar if playing */}
            {isPlayingAudio && (
              <div className="fixed bottom-0 left-0 right-0 z-50 bg-[#004D35] text-white border-t border-[#F4BE45]/30 shadow-2xl py-3 px-4">
                <div className="max-w-4xl mx-auto flex items-center justify-between">
                  <div className="flex items-center space-x-3">
                    <div className="w-9 h-9 rounded-full bg-[#F4BE45] text-[#004D35] flex items-center justify-center font-bold">
                      <Volume2 className="w-5 h-5 animate-pulse" />
                    </div>
                    <div>
                      <p className="font-semibold text-sm">
                        Memutar Ayat {playingAyah}
                      </p>
                      <p className="text-xs text-[#FFE39A]">
                        {activeSurah.name} • {selectedReciter}
                      </p>
                    </div>
                  </div>

                  <div className="flex items-center space-x-2">
                    <button
                      onClick={stopAudio}
                      className="p-2 rounded-lg bg-red-600/80 hover:bg-red-600 text-white transition-colors"
                      title="Berhenti"
                    >
                      <Square className="w-4 h-4" />
                    </button>
                  </div>
                </div>
              </div>
            )}
          </div>
        ) : (
          /* Main 4 Tabs */
          <div>
            {activeTab === 'beranda' && (
              <div className="space-y-6">
                {/* Hero Section */}
                <div className="relative overflow-hidden rounded-3xl bg-gradient-to-br from-[#004D35] via-[#003D2B] to-[#071F18] text-white p-6 sm:p-8 shadow-xl border border-[#F4BE45]/20">
                  <div className="relative z-10 max-w-lg">
                    <div className="inline-flex items-center space-x-2 bg-[#F4BE45]/20 text-[#FFE39A] px-3 py-1 rounded-full text-xs font-semibold mb-3 border border-[#F4BE45]/30">
                      <Sparkles className="w-3.5 h-3.5 text-[#F4BE45]" />
                      <span>Al-Qur'an Digital Elegan</span>
                    </div>

                    <h2 className="text-2xl sm:text-3xl font-extrabold text-white tracking-tight leading-snug mb-2">
                      Temukan Ketenangan dalam Setiap Ayat.
                    </h2>

                    <p className="text-sm text-white/80 leading-relaxed mb-6">
                      Baca Al-Qur’an dengan nyaman, lanjutkan tilawah, dan simpan ayat favorit Anda dalam satu pengalaman digital yang bersih dan intuitif.
                    </p>

                    <div className="flex flex-wrap gap-3">
                      <button
                        onClick={() => openSurah(lastRead.surahNumber, lastRead.ayahNumber)}
                        className="inline-flex items-center space-x-2 bg-[#F4BE45] hover:bg-[#e5b03b] text-[#004D35] font-bold px-5 py-2.5 rounded-xl shadow-md transition-all active:scale-95"
                      >
                        <BookOpen className="w-4 h-4" />
                        <span>Mulai Membaca</span>
                      </button>

                      <button
                        onClick={() => setActiveTab('quran')}
                        className="inline-flex items-center space-x-2 bg-white/10 hover:bg-white/20 text-white font-semibold px-5 py-2.5 rounded-xl border border-white/20 transition-all active:scale-95"
                      >
                        <span>Jelajahi Surah</span>
                        <ChevronRight className="w-4 h-4" />
                      </button>
                    </div>
                  </div>
                </div>

                {/* Terakhir Dibaca Card (Exact guidelines) */}
                <div>
                  <h3 className="font-bold text-base mb-3 text-[#18372B] dark:text-[#F5F0E2]">
                    Aktivitas Tilawah
                  </h3>
                  <div
                    onClick={() => openSurah(lastRead.surahNumber, lastRead.ayahNumber)}
                    className="p-5 rounded-2xl bg-[#EAF3EC] dark:bg-[#103528] border border-[#008653]/30 shadow-sm cursor-pointer hover:shadow-md transition-all group"
                  >
                    <div className="flex items-center justify-between mb-3">
                      <div className="flex items-center space-x-2 text-[#66736A] dark:text-[#D4E0D7] text-xs font-semibold">
                        <BookmarkIcon className="w-4 h-4 text-[#B78013] fill-[#B78013]" />
                        <span>Terakhir Dibaca</span>
                      </div>
                      <span className="text-xs font-bold text-[#004D35] dark:text-[#FFE39A] bg-[#004D35]/10 dark:bg-white/10 px-2.5 py-1 rounded-md">
                        Juz {lastRead.juzNumber}
                      </span>
                    </div>

                    <div className="flex items-center justify-between">
                      <div>
                        <h4 className="font-bold text-xl text-[#004D35] dark:text-[#F5F0E2] group-hover:text-[#008653] transition-colors">
                          {lastRead.surahName}
                        </h4>
                        <p className="text-sm text-[#66736A] dark:text-[#D4E0D7] mt-0.5">
                          Ayat {lastRead.ayahNumber} • Juz {lastRead.juzNumber}
                        </p>
                      </div>

                      <button className="inline-flex items-center space-x-2 bg-[#004D35] hover:bg-[#003D2B] text-white text-xs font-bold px-4 py-2 rounded-xl transition-all shadow-sm">
                        <span>Lanjutkan Tilawah</span>
                        <ChevronRight className="w-4 h-4" />
                      </button>
                    </div>
                  </div>
                </div>

                {/* Surah Pilihan (Quick Surahs) */}
                <div>
                  <div className="flex items-center justify-between mb-3">
                    <h3 className="font-bold text-base text-[#18372B] dark:text-[#F5F0E2]">
                      Surah Pilihan
                    </h3>
                    <button
                      onClick={() => setActiveTab('quran')}
                      className="text-xs font-bold text-[#008653] dark:text-[#FFE39A] hover:underline"
                    >
                      Lihat Semua →
                    </button>
                  </div>

                  <div className="grid grid-cols-2 sm:grid-cols-3 gap-3">
                    {[
                      { num: 18, name: 'Al-Kahfi' },
                      { num: 36, name: 'Ya-Sin' },
                      { num: 67, name: 'Al-Mulk' },
                      { num: 55, name: 'Ar-Rahman' },
                      { num: 56, name: "Al-Waqi'ah" },
                      { num: 1, name: 'Al-Fatihah' }
                    ].map(item => (
                      <div
                        key={item.num}
                        onClick={() => openSurah(item.num)}
                        className="p-3.5 rounded-xl bg-white dark:bg-[#103528] border border-[#E5E9E4] dark:border-[#1E523F] hover:border-[#008653] hover:shadow-sm cursor-pointer transition-all flex items-center space-x-3"
                      >
                        <span className="w-8 h-8 rounded-lg bg-[#004D35] text-[#FFE39A] font-bold text-xs flex items-center justify-center border border-[#F4BE45]/30">
                          {item.num}
                        </span>
                        <div>
                          <p className="font-bold text-sm text-[#18372B] dark:text-[#F5F0E2]">
                            {item.name}
                          </p>
                          <p className="text-[11px] text-[#66736A] dark:text-[#D4E0D7]">
                            Surah ke-{item.num}
                          </p>
                        </div>
                      </div>
                    ))}
                  </div>
                </div>

                {/* Ayat Hari Ini (Daily Verse) */}
                <div className="p-6 rounded-2xl bg-white dark:bg-[#103528] border border-[#F4BE45]/40 shadow-sm">
                  <div className="flex items-center justify-between mb-4">
                    <span className="text-xs font-bold text-[#008653] dark:text-[#FFE39A] bg-[#008653]/10 dark:bg-white/10 px-3 py-1 rounded-full">
                      Ayat Hari Ini • QS. Ar-Ra'd : 28
                    </span>
                    <div className="flex items-center space-x-1">
                      <button
                        onClick={() =>
                          copyToClipboard(`${DAILY_VERSE.arabicText}\n\n"${DAILY_VERSE.translationText}"`)
                        }
                        className="p-1.5 rounded-lg text-[#66736A] dark:text-[#D4E0D7] hover:bg-[#EAF3EC] dark:hover:bg-[#164434] transition-colors"
                        title="Salin"
                      >
                        <Copy className="w-4 h-4" />
                      </button>
                      <button
                        onClick={() =>
                          shareText('Ayat Hari Ini', `${DAILY_VERSE.arabicText}\n\n"${DAILY_VERSE.translationText}"`)
                        }
                        className="p-1.5 rounded-lg text-[#66736A] dark:text-[#D4E0D7] hover:bg-[#EAF3EC] dark:hover:bg-[#164434] transition-colors"
                        title="Bagikan"
                      >
                        <Share2 className="w-4 h-4" />
                      </button>
                    </div>
                  </div>

                  <p className="font-arabic text-right text-2xl sm:text-3xl text-[#18372B] dark:text-[#F5F0E2] my-4 leading-loose">
                    {DAILY_VERSE.arabicText}
                  </p>

                  <p className="text-sm text-[#45584C] dark:text-[#D4E0D7] leading-relaxed">
                    {DAILY_VERSE.translationText}
                  </p>
                </div>
              </div>
            )}

            {activeTab === 'quran' && (
              <div className="space-y-4">
                {/* Search Bar */}
                <div className="relative">
                  <Search className="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-[#66736A] dark:text-[#D4E0D7]" />
                  <input
                    type="text"
                    value={searchQuery}
                    onChange={e => setSearchQuery(e.target.value)}
                    placeholder="Cari surah, arti, atau nomor..."
                    className="w-full pl-12 pr-10 py-3.5 rounded-2xl bg-white dark:bg-[#103528] border border-[#E5E9E4] dark:border-[#1E523F] focus:outline-none focus:ring-2 focus:ring-[#008653] text-sm text-[#18372B] dark:text-[#F5F0E2] shadow-sm"
                  />
                  {searchQuery && (
                    <button
                      onClick={() => setSearchQuery('')}
                      className="absolute right-3.5 top-1/2 -translate-y-1/2 p-1 text-gray-400 hover:text-gray-600"
                    >
                      <X className="w-4 h-4" />
                    </button>
                  )}
                </div>

                {/* Filters */}
                <div className="flex items-center space-x-2 overflow-x-auto pb-2">
                  <button
                    onClick={() => setFilterType('all')}
                    className={`px-4 py-2 rounded-xl text-xs font-semibold whitespace-nowrap transition-colors ${
                      filterType === 'all'
                        ? 'bg-[#004D35] text-[#FFE39A]'
                        : 'bg-white dark:bg-[#103528] border border-[#E5E9E4] dark:border-[#1E523F] text-[#66736A] dark:text-[#D4E0D7]'
                    }`}
                  >
                    Semua (114)
                  </button>
                  <button
                    onClick={() => setFilterType('makkiyah')}
                    className={`px-4 py-2 rounded-xl text-xs font-semibold whitespace-nowrap transition-colors ${
                      filterType === 'makkiyah'
                        ? 'bg-[#004D35] text-[#FFE39A]'
                        : 'bg-white dark:bg-[#103528] border border-[#E5E9E4] dark:border-[#1E523F] text-[#66736A] dark:text-[#D4E0D7]'
                    }`}
                  >
                    Makkiyah (86)
                  </button>
                  <button
                    onClick={() => setFilterType('madaniyah')}
                    className={`px-4 py-2 rounded-xl text-xs font-semibold whitespace-nowrap transition-colors ${
                      filterType === 'madaniyah'
                        ? 'bg-[#004D35] text-[#FFE39A]'
                        : 'bg-white dark:bg-[#103528] border border-[#E5E9E4] dark:border-[#1E523F] text-[#66736A] dark:text-[#D4E0D7]'
                    }`}
                  >
                    Madaniyah (28)
                  </button>
                  <button
                    onClick={() => setFilterType('juz')}
                    className={`px-4 py-2 rounded-xl text-xs font-semibold whitespace-nowrap transition-colors ${
                      filterType === 'juz'
                        ? 'bg-[#004D35] text-[#FFE39A]'
                        : 'bg-white dark:bg-[#103528] border border-[#E5E9E4] dark:border-[#1E523F] text-[#66736A] dark:text-[#D4E0D7]'
                    }`}
                  >
                    Juz 1-30
                  </button>
                </div>

                {/* Juz Selector if active */}
                {filterType === 'juz' && (
                  <div className="flex items-center space-x-2 overflow-x-auto py-2">
                    {Array.from({ length: 30 }, (_, i) => i + 1).map(juz => (
                      <button
                        key={juz}
                        onClick={() => setSelectedJuz(juz)}
                        className={`w-9 h-9 rounded-xl text-xs font-bold flex-shrink-0 transition-colors ${
                          selectedJuz === juz
                            ? 'bg-[#F4BE45] text-[#004D35]'
                            : 'bg-white dark:bg-[#103528] border border-[#E5E9E4] dark:border-[#1E523F]'
                        }`}
                      >
                        {juz}
                      </button>
                    ))}
                  </div>
                )}

                {/* Surah List */}
                <div className="space-y-2.5">
                  {filteredSurahs.map(surah => {
                    const isFav = favoriteSurahs.includes(surah.number);
                    return (
                      <div
                        key={surah.number}
                        onClick={() => openSurah(surah.number)}
                        className="p-4 rounded-2xl bg-white dark:bg-[#103528] border border-[#E5E9E4] dark:border-[#1E523F] hover:border-[#008653] cursor-pointer transition-all flex items-center justify-between shadow-sm group"
                      >
                        <div className="flex items-center space-x-3.5">
                          <span className="w-10 h-10 rounded-xl bg-[#004D35] text-[#FFE39A] font-bold text-sm flex items-center justify-center border border-[#F4BE45]/30">
                            {surah.number}
                          </span>
                          <div>
                            <h4 className="font-bold text-base text-[#18372B] dark:text-[#F5F0E2] group-hover:text-[#008653] transition-colors">
                              {surah.name}
                            </h4>
                            <p className="text-xs text-[#66736A] dark:text-[#D4E0D7]">
                              {surah.translation} • {surah.revelationType} • {surah.numberOfAyahs} Ayat
                            </p>
                          </div>
                        </div>

                        <div className="flex items-center space-x-3">
                          <span className="font-arabic font-bold text-xl text-[#004D35] dark:text-[#FFE39A]">
                            {surah.arabicName}
                          </span>
                          <button
                            onClick={e => {
                              e.stopPropagation();
                              toggleFavorite(surah.number);
                            }}
                            className="p-2 rounded-lg text-gray-400 hover:text-[#F4BE45] transition-colors"
                          >
                            <Star className={`w-5 h-5 ${isFav ? 'text-[#F4BE45] fill-[#F4BE45]' : ''}`} />
                          </button>
                        </div>
                      </div>
                    );
                  })}
                </div>
              </div>
            )}

            {activeTab === 'bookmark' && (
              <div className="space-y-4">
                <h3 className="font-bold text-lg text-[#18372B] dark:text-[#F5F0E2]">
                  Bookmark & Koleksi Tilawah
                </h3>

                {bookmarks.length === 0 ? (
                  <div className="p-12 text-center rounded-2xl bg-white dark:bg-[#103528] border border-[#E5E9E4] dark:border-[#1E523F]">
                    <BookmarkIcon className="w-10 h-10 text-gray-400 mx-auto mb-3" />
                    <p className="font-bold text-base">Belum Ada Ayat Tersimpan</p>
                    <p className="text-xs text-[#66736A] dark:text-[#D4E0D7] mt-1">
                      Sentuh ikon bookmark pada ayat saat membaca untuk menyimpannya di sini.
                    </p>
                  </div>
                ) : (
                  <div className="space-y-3">
                    {bookmarks.map(bm => (
                      <div
                        key={bm.id}
                        onClick={() => openSurah(bm.surahNumber, bm.ayahNumber)}
                        className="p-4 rounded-2xl bg-white dark:bg-[#103528] border border-[#E5E9E4] dark:border-[#1E523F] cursor-pointer hover:border-[#008653] transition-all shadow-sm"
                      >
                        <div className="flex items-center justify-between mb-2">
                          <span className="font-bold text-sm text-[#004D35] dark:text-[#FFE39A]">
                            {bm.surahName} : Ayat {bm.ayahNumber}
                          </span>
                          <button
                            onClick={e => {
                              e.stopPropagation();
                              setBookmarks(bookmarks.filter(b => b.id !== bm.id));
                            }}
                            className="text-xs text-red-500 hover:underline"
                          >
                            Hapus
                          </button>
                        </div>
                        <p className="font-arabic text-right text-lg text-[#18372B] dark:text-[#F5F0E2] my-2">
                          {bm.arabicSnippet}
                        </p>
                        <p className="text-xs text-[#45584C] dark:text-[#D4E0D7] italic">
                          "{bm.translationSnippet}"
                        </p>
                      </div>
                    ))}
                  </div>
                )}
              </div>
            )}

            {activeTab === 'pengaturan' && (
              <div className="space-y-4">
                <h3 className="font-bold text-lg text-[#18372B] dark:text-[#F5F0E2]">
                  Pengaturan Membaca
                </h3>

                {/* Theme Setting */}
                <div className="p-4 rounded-2xl bg-white dark:bg-[#103528] border border-[#E5E9E4] dark:border-[#1E523F] shadow-sm">
                  <h4 className="font-semibold text-sm mb-3">Tema Tampilan</h4>
                  <div className="grid grid-cols-2 gap-3">
                    <button
                      onClick={() => setDarkMode(false)}
                      className={`p-3 rounded-xl border flex items-center justify-center space-x-2 text-xs font-semibold ${
                        !darkMode
                          ? 'border-[#008653] bg-[#EAF3EC] text-[#004D35]'
                          : 'border-gray-300 dark:border-gray-700'
                      }`}
                    >
                      <Sun className="w-4 h-4 text-[#F4BE45]" />
                      <span>Warm White (Terang)</span>
                    </button>
                    <button
                      onClick={() => setDarkMode(true)}
                      className={`p-3 rounded-xl border flex items-center justify-center space-x-2 text-xs font-semibold ${
                        darkMode
                          ? 'border-[#008653] bg-[#164434] text-[#FFE39A]'
                          : 'border-gray-300 dark:border-gray-700'
                      }`}
                    >
                      <Moon className="w-4 h-4 text-[#F4BE45]" />
                      <span>Deep Dark (Gelap)</span>
                    </button>
                  </div>
                </div>

                {/* Font Sizes */}
                <div className="p-4 rounded-2xl bg-white dark:bg-[#103528] border border-[#E5E9E4] dark:border-[#1E523F] shadow-sm space-y-4">
                  <div>
                    <div className="flex justify-between text-xs font-semibold mb-2">
                      <span>Ukuran Teks Arab: {arabicFontSize}px</span>
                    </div>
                    <input
                      type="range"
                      min="20"
                      max="40"
                      value={arabicFontSize}
                      onChange={e => setArabicFontSize(Number(e.target.value))}
                      className="w-full accent-[#008653]"
                    />
                    <p className="font-arabic text-center text-xl text-[#004D35] dark:text-[#FFE39A] mt-2">
                      بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ
                    </p>
                  </div>

                  <div>
                    <div className="flex justify-between text-xs font-semibold mb-2">
                      <span>Ukuran Teks Terjemahan: {translationFontSize}px</span>
                    </div>
                    <input
                      type="range"
                      min="12"
                      max="22"
                      value={translationFontSize}
                      onChange={e => setTranslationFontSize(Number(e.target.value))}
                      className="w-full accent-[#008653]"
                    />
                  </div>
                </div>

                {/* Toggles */}
                <div className="p-4 rounded-2xl bg-white dark:bg-[#103528] border border-[#E5E9E4] dark:border-[#1E523F] shadow-sm space-y-3">
                  <div className="flex items-center justify-between">
                    <div>
                      <p className="font-semibold text-sm">Teks Latin (Transliterasi)</p>
                      <p className="text-xs text-gray-500">Membantu membaca bagi yang belajar</p>
                    </div>
                    <input
                      type="checkbox"
                      checked={showLatin}
                      onChange={e => setShowLatin(e.target.checked)}
                      className="w-5 h-5 accent-[#008653]"
                    />
                  </div>

                  <div className="flex items-center justify-between pt-3 border-t border-gray-100 dark:border-gray-800">
                    <div>
                      <p className="font-semibold text-sm">Terjemahan Bahasa Indonesia</p>
                      <p className="text-xs text-gray-500">Arti resmi Kemenag RI</p>
                    </div>
                    <input
                      type="checkbox"
                      checked={showTranslation}
                      onChange={e => setShowTranslation(e.target.checked)}
                      className="w-5 h-5 accent-[#008653]"
                    />
                  </div>
                </div>

                {/* About Card */}
                <div className="p-6 rounded-2xl bg-[#004D35] text-white space-y-3">
                  <h4 className="font-bold text-base text-[#F4BE45]">YANN QURAN Digital</h4>
                  <p className="text-xs text-white/90 leading-relaxed">
                    Dirancang dengan perpaduan warna hijau zamrud, emas premium, dan tipografi Arab yang bersih untuk menghadirkan kenyamanan dan ketenangan dalam setiap tilawah.
                  </p>
                  <p className="text-[11px] text-[#FFE39A]">Versi 1.0.0 • Web & Android</p>
                </div>
              </div>
            )}
          </div>
        )}
      </main>

      {/* Reader Settings Modal */}
      {showReaderSettings && (
        <div className="fixed inset-0 z-50 bg-black/60 flex items-end sm:items-center justify-center p-0 sm:p-4">
          <div className="w-full max-w-md bg-white dark:bg-[#103528] rounded-t-3xl sm:rounded-2xl p-6 shadow-2xl">
            <div className="flex items-center justify-between mb-4">
              <h3 className="font-bold text-base">Pengaturan Bacaan</h3>
              <button onClick={() => setShowReaderSettings(false)} className="p-1 text-gray-500">
                <X className="w-5 h-5" />
              </button>
            </div>

            <div className="space-y-4">
              <div>
                <p className="text-xs font-semibold mb-1">Ukuran Huruf Arab ({arabicFontSize}px)</p>
                <input
                  type="range"
                  min="22"
                  max="42"
                  value={arabicFontSize}
                  onChange={e => setArabicFontSize(Number(e.target.value))}
                  className="w-full accent-[#008653]"
                />
              </div>

              <div>
                <p className="text-xs font-semibold mb-1">Ukuran Terjemahan ({translationFontSize}px)</p>
                <input
                  type="range"
                  min="12"
                  max="20"
                  value={translationFontSize}
                  onChange={e => setTranslationFontSize(Number(e.target.value))}
                  className="w-full accent-[#008653]"
                />
              </div>

              <div className="flex items-center justify-between pt-2">
                <span className="text-xs font-semibold">Tampilkan Latin</span>
                <input
                  type="checkbox"
                  checked={showLatin}
                  onChange={e => setShowLatin(e.target.checked)}
                  className="w-4 h-4 accent-[#008653]"
                />
              </div>

              <div className="flex items-center justify-between">
                <span className="text-xs font-semibold">Tampilkan Terjemahan</span>
                <input
                  type="checkbox"
                  checked={showTranslation}
                  onChange={e => setShowTranslation(e.target.checked)}
                  className="w-4 h-4 accent-[#008653]"
                />
              </div>
            </div>
          </div>
        </div>
      )}

      {/* Bottom Navigation Bar */}
      {!activeSurah && (
        <nav className="fixed bottom-0 left-0 right-0 z-40 bg-[#004D35] text-white shadow-2xl border-t border-[#F4BE45]/30">
          <div className="max-w-md mx-auto flex items-center justify-around py-2">
            {[
              { id: 'beranda', label: 'Beranda', icon: BookOpen },
              { id: 'quran', label: "Al-Qur'an", icon: BookOpen },
              { id: 'bookmark', label: 'Bookmark', icon: BookmarkIcon },
              { id: 'pengaturan', label: 'Pengaturan', icon: Sliders }
            ].map(tab => {
              const Icon = tab.icon;
              const isActive = activeTab === tab.id;
              return (
                <button
                  key={tab.id}
                  onClick={() => setActiveTab(tab.id as any)}
                  className={`flex flex-col items-center py-1 px-3 rounded-xl transition-all ${
                    isActive ? 'text-[#FFE39A]' : 'text-white/70 hover:text-white'
                  }`}
                >
                  <div className={`p-1 rounded-full ${isActive ? 'bg-[#008653]/40' : ''}`}>
                    <Icon className="w-5 h-5" />
                  </div>
                  <span className={`text-[11px] font-medium mt-0.5 ${isActive ? 'font-bold' : ''}`}>
                    {tab.label}
                  </span>
                </button>
              );
            })}
          </div>
        </nav>
      )}
    </div>
  );
}
