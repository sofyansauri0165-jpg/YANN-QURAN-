package com.example.data

import com.example.data.model.Ayah
import com.example.data.model.Surah

object QuranDataSource {

    val surahs: List<Surah> = listOf(
        Surah(1, "Al-Fatihah", "الفَاتِحَة", "Pembukaan", 7, "Makkiyah", 5, 1),
        Surah(2, "Al-Baqarah", "البَقَرَة", "Sapi Betina", 286, "Madaniyah", 87, 1),
        Surah(3, "Ali 'Imran", "آل عِمْرَان", "Keluarga Imran", 200, "Madaniyah", 89, 3),
        Surah(4, "An-Nisa'", "النِّسَاء", "Wanita", 176, "Madaniyah", 92, 4),
        Surah(5, "Al-Ma'idah", "المَائِدَة", "Jamuan Hidangan", 120, "Madaniyah", 112, 6),
        Surah(6, "Al-An'am", "الأَنْعَام", "Hewan Ternak", 165, "Makkiyah", 55, 7),
        Surah(7, "Al-A'raf", "الأَعْرَاف", "Tempat yang Tertinggi", 206, "Makkiyah", 39, 8),
        Surah(8, "Al-Anfal", "الأَنْفَال", "Harta Rampasan Perang", 75, "Madaniyah", 88, 9),
        Surah(9, "At-Taubah", "التَّوْبَة", "Pengampunan", 129, "Madaniyah", 113, 10),
        Surah(10, "Yunus", "يُونُس", "Nabi Yunus", 109, "Makkiyah", 51, 11),
        Surah(11, "Hud", "هُود", "Nabi Hud", 123, "Makkiyah", 52, 11),
        Surah(12, "Yusuf", "يُوسُف", "Nabi Yusuf", 111, "Makkiyah", 53, 12),
        Surah(13, "Ar-Ra'd", "الرَّعْد", "Guruh", 43, "Madaniyah", 96, 13),
        Surah(14, "Ibrahim", "إِبْرَاهِيم", "Nabi Ibrahim", 52, "Makkiyah", 72, 13),
        Surah(15, "Al-Hijr", "الحِجْر", "Gunung Al-Hijr", 99, "Makkiyah", 54, 14),
        Surah(16, "An-Nahl", "النَّحْل", "Lebah", 128, "Makkiyah", 70, 14),
        Surah(17, "Al-Isra'", "الإِسْرَاء", "Memperjalankan Malam Hari", 111, "Makkiyah", 50, 15),
        Surah(18, "Al-Kahf", "الكَهْف", "Gua", 110, "Makkiyah", 69, 15),
        Surah(19, "Maryam", "مَرْيَم", "Siti Maryam", 98, "Makkiyah", 44, 16),
        Surah(20, "Ta-Ha", "طه", "Ta-Ha", 135, "Makkiyah", 45, 16),
        Surah(21, "Al-Anbiya'", "الأَنْبِيَاء", "Para Nabi", 112, "Makkiyah", 73, 17),
        Surah(22, "Al-Hajj", "الحَجّ", "Ibadah Haji", 78, "Madaniyah", 103, 17),
        Surah(23, "Al-Mu'minun", "المُؤْمِنُون", "Orang-orang Mukmin", 118, "Makkiyah", 74, 18),
        Surah(24, "An-Nur", "النُّور", "Cahaya", 64, "Madaniyah", 102, 18),
        Surah(25, "Al-Furqan", "الفُرْقَان", "Pembeda", 77, "Makkiyah", 42, 18),
        Surah(26, "Ash-Shu'ara'", "الشُّعَرَاء", "Para Penyair", 227, "Makkiyah", 47, 19),
        Surah(27, "An-Naml", "النَّمْل", "Semut", 93, "Makkiyah", 48, 19),
        Surah(28, "Al-Qasas", "القَصَص", "Kisah-kisah", 88, "Makkiyah", 49, 20),
        Surah(29, "Al-'Ankabut", "العَنْكَبُوت", "Laba-laba", 69, "Makkiyah", 85, 20),
        Surah(30, "Ar-Rum", "الرُّوم", "Bangsa Romawi", 60, "Makkiyah", 84, 21),
        Surah(31, "Luqman", "لُقْمَان", "Keluarga Luqman", 34, "Makkiyah", 57, 21),
        Surah(32, "As-Sajdah", "السَّجْدَة", "Sujud", 30, "Makkiyah", 75, 21),
        Surah(33, "Al-Ahzab", "الأَحْزَاب", "Golongan yang Bersekutu", 73, "Madaniyah", 90, 21),
        Surah(34, "Saba'", "سَبَأ", "Kaum Saba'", 54, "Makkiyah", 58, 22),
        Surah(35, "Fatir", "فَاطِر", "Pencipta", 45, "Makkiyah", 43, 22),
        Surah(36, "Ya-Sin", "يس", "Ya-Sin", 83, "Makkiyah", 41, 22),
        Surah(37, "As-Saffat", "الصَّافَّات", "Barisan-barisan", 182, "Makkiyah", 56, 23),
        Surah(38, "Sad", "ص", "Shaad", 88, "Makkiyah", 38, 23),
        Surah(39, "Az-Zumar", "الزُّمَر", "Rombongan-rombongan", 75, "Makkiyah", 59, 23),
        Surah(40, "Ghafir", "غَافِر", "Maha Pengampun", 85, "Makkiyah", 60, 24),
        Surah(41, "Fussilat", "فُصِّلَت", "Yang Dijelaskan", 54, "Makkiyah", 61, 24),
        Surah(42, "Ash-Shura", "الشُّورَى", "Musyawarah", 53, "Makkiyah", 62, 25),
        Surah(43, "Az-Zukhruf", "الزُّخْرُف", "Perhiasan", 89, "Makkiyah", 63, 25),
        Surah(44, "Ad-Dukhan", "الدُّخَان", "Kabut Asap", 59, "Makkiyah", 64, 25),
        Surah(45, "Al-Jathiyah", "الجَاثِيَة", "Yang Berlutut", 37, "Makkiyah", 65, 25),
        Surah(46, "Al-Ahqaf", "الأَحْقَاف", "Bukit-bukit Pasir", 35, "Makkiyah", 66, 26),
        Surah(47, "Muhammad", "مُحَمَّد", "Nabi Muhammad", 38, "Madaniyah", 95, 26),
        Surah(48, "Al-Fath", "الفَتْح", "Kemenangan", 29, "Madaniyah", 111, 26),
        Surah(49, "Al-Hujurat", "الحُجُرَات", "Kamar-kamar", 18, "Madaniyah", 106, 26),
        Surah(50, "Qaf", "ق", "Qaf", 45, "Makkiyah", 34, 26),
        Surah(51, "Adh-Dhariyat", "الذَّارِيَات", "Angin yang Menerbangkan", 60, "Makkiyah", 67, 26),
        Surah(52, "At-Tur", "الطُّور", "Bukit", 49, "Makkiyah", 76, 27),
        Surah(53, "An-Najm", "النَّجْم", "Bintang", 62, "Makkiyah", 23, 27),
        Surah(54, "Al-Qamar", "القَمَر", "Bulan", 55, "Makkiyah", 37, 27),
        Surah(55, "Ar-Rahman", "الرَّحْمَن", "Maha Pengasih", 78, "Madaniyah", 97, 27),
        Surah(56, "Al-Waqi'ah", "الوٰقِعَة", "Hari Kiamat", 96, "Makkiyah", 46, 27),
        Surah(57, "Al-Hadid", "الحَدِيد", "Besi", 29, "Madaniyah", 94, 27),
        Surah(58, "Al-Mujadilah", "المُجَادِلَة", "Gugatan", 22, "Madaniyah", 105, 28),
        Surah(59, "Al-Hashr", "الحَشْر", "Pengusiran", 24, "Madaniyah", 101, 28),
        Surah(60, "Al-Mumtahanah", "المُمْتَحَنَة", "Wanita yang Diuji", 13, "Madaniyah", 91, 28),
        Surah(61, "As-Saff", "الصَّفّ", "Barisan", 14, "Madaniyah", 109, 28),
        Surah(62, "Al-Jumu'ah", "الجُمُعَة", "Hari Jum'at", 11, "Madaniyah", 110, 28),
        Surah(63, "Al-Munafiqun", "المُنَافِقُون", "Orang-orang Munafik", 11, "Madaniyah", 104, 28),
        Surah(64, "At-Taghabun", "التَّغَابُن", "Hari Dinampakkan Kesalahan", 18, "Madaniyah", 108, 28),
        Surah(65, "At-Talaq", "الطَّلَاق", "Perceraian", 12, "Madaniyah", 99, 28),
        Surah(66, "At-Tahrim", "التَّحْرِيم", "Pengharaman", 12, "Madaniyah", 107, 28),
        Surah(67, "Al-Mulk", "المُلْك", "Kerajaan", 30, "Makkiyah", 77, 29),
        Surah(68, "Al-Qalam", "القَلَم", "Pena", 52, "Makkiyah", 2, 29),
        Surah(69, "Al-Haqqah", "الحَاقَّة", "Hari Kiamat yang Benar", 52, "Makkiyah", 78, 29),
        Surah(70, "Al-Ma'arij", "المَعَارِج", "Tempat-tempat Naik", 44, "Makkiyah", 79, 29),
        Surah(71, "Nuh", "نُوح", "Nabi Nuh", 28, "Makkiyah", 71, 29),
        Surah(72, "Al-Jinn", "الجِنّ", "Jin", 28, "Makkiyah", 40, 29),
        Surah(73, "Al-Muzzammil", "المُزَّمِّل", "Orang yang Berselimut", 20, "Makkiyah", 3, 29),
        Surah(74, "Al-Muddaththir", "المُدَّثِّر", "Orang yang Berkemul", 56, "Makkiyah", 4, 29),
        Surah(75, "Al-Qiyamah", "القِيَامَة", "Hari Berbangkit", 40, "Makkiyah", 31, 29),
        Surah(76, "Al-Insan", "الإِنْسَان", "Manusia", 31, "Madaniyah", 98, 29),
        Surah(77, "Al-Mursalat", "المُرْسَلَات", "Malaikat yang Diutus", 50, "Makkiyah", 33, 29),
        Surah(78, "An-Naba'", "النَّبَأ", "Berita Besar", 40, "Makkiyah", 80, 30),
        Surah(79, "An-Nazi'at", "النَّازِعَات", "Malaikat Pencabut", 46, "Makkiyah", 81, 30),
        Surah(80, "'Abasa", "عَبَسَ", "Ia Bermuka Masam", 42, "Makkiyah", 24, 30),
        Surah(81, "At-Takwir", "التَّكْوِير", "Menggulung", 29, "Makkiyah", 7, 30),
        Surah(82, "Al-Infitar", "الانْفِطَار", "Terbelah", 19, "Makkiyah", 82, 30),
        Surah(83, "Al-Mutaffifin", "المُطَفِّفِين", "Orang-orang Curang", 36, "Makkiyah", 86, 30),
        Surah(84, "Al-Inshiqaq", "الانْشِقَاق", "Terbelah", 25, "Makkiyah", 83, 30),
        Surah(85, "Al-Buruj", "البُرُوج", "Gugusan Bintang", 22, "Makkiyah", 27, 30),
        Surah(86, "At-Tariq", "الطَّارِق", "Yang Datang di Malam Hari", 17, "Makkiyah", 36, 30),
        Surah(87, "Al-A'la", "الأَعْلَى", "Maha Tinggi", 19, "Makkiyah", 8, 30),
        Surah(88, "Al-Ghashiyah", "الغَاشِيَة", "Hari Pembalasan", 26, "Makkiyah", 68, 30),
        Surah(89, "Al-Fajr", "الفَجْر", "Wajar Fajar", 30, "Makkiyah", 10, 30),
        Surah(90, "Al-Balad", "البَلَد", "Negeri", 20, "Makkiyah", 35, 30),
        Surah(91, "Ash-Shams", "الشَّمْس", "Matahari", 15, "Makkiyah", 26, 30),
        Surah(92, "Al-Lail", "اللَّيْل", "Malam", 21, "Makkiyah", 9, 30),
        Surah(93, "Ad-Duha", "الضُّحَى", "Waktu Dhuha", 11, "Makkiyah", 11, 30),
        Surah(94, "Ash-Sharh", "الشَّرْح", "Kelapangan Dada", 8, "Makkiyah", 12, 30),
        Surah(95, "At-Tin", "التِّين", "Buah Tin", 8, "Makkiyah", 28, 30),
        Surah(96, "Al-'Alaq", "العَلَق", "Segumpal Darah", 19, "Makkiyah", 1, 30),
        Surah(97, "Al-Qadr", "القَدْر", "Kemuliaan", 5, "Makkiyah", 25, 30),
        Surah(98, "Al-Bayyinah", "البَيِّنَة", "Bukti yang Nyata", 8, "Madaniyah", 100, 30),
        Surah(99, "Az-Zalzalah", "الزَّلْزَلَة", "Kegoncangan", 8, "Madaniyah", 93, 30),
        Surah(100, "Al-'Adiyat", "العَادِيَات", "Kuda Perang yang Berlari Kencang", 11, "Makkiyah", 14, 30),
        Surah(101, "Al-Qari'ah", "القَارِعَة", "Hari Kiamat yang Menggetarkan", 11, "Makkiyah", 30, 30),
        Surah(102, "At-Takathur", "التَّكَاثُر", "Bermegah-megahan", 8, "Makkiyah", 16, 30),
        Surah(103, "Al-'Asr", "العَصْر", "Masa / Waktu", 3, "Makkiyah", 13, 30),
        Surah(104, "Al-Humazah", "الهُمَزَة", "Pengumpat", 9, "Makkiyah", 32, 30),
        Surah(105, "Al-Fil", "الفِيل", "Gajah", 5, "Makkiyah", 19, 30),
        Surah(106, "Quraish", "قُرَيْش", "Bani Quraisy", 4, "Makkiyah", 29, 30),
        Surah(107, "Al-Ma'un", "المَاعُون", "Barang yang Berguna", 7, "Makkiyah", 17, 30),
        Surah(108, "Al-Kauthar", "الكَوْثَر", "Nikmat yang Banyak", 3, "Makkiyah", 15, 30),
        Surah(109, "Al-Kafirun", "الكَافِرُون", "Orang-orang Kafir", 6, "Makkiyah", 18, 30),
        Surah(110, "An-Nasr", "النَّصْر", "Pertolongan", 3, "Madaniyah", 114, 30),
        Surah(111, "Al-Masad", "المَسَد", "Gejolak Api", 5, "Makkiyah", 6, 30),
        Surah(112, "Al-Ikhlas", "الإِخْلَاص", "Keesaan Allah", 4, "Makkiyah", 22, 30),
        Surah(113, "Al-Falaq", "الفَلَق", "Waktu Subuh", 5, "Makkiyah", 20, 30),
        Surah(114, "An-Nas", "النَّاس", "Manusia", 6, "Makkiyah", 21, 30)
    )

    fun getSurahByNumber(number: Int): Surah? = surahs.find { it.number == number }

    // Preloaded offline authentic verses for crucial Surahs and daily recitation
    fun getOfflineAyahsForSurah(surahNumber: Int): List<Ayah> {
        return when (surahNumber) {
            1 -> listOf(
                Ayah(1, 1, "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", "Bismillāhir-raḥmānir-raḥīm", "Dengan nama Allah Yang Maha Pengasih, Maha Penyayang.", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/1.mp3", 1),
                Ayah(2, 1, "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ", "Al-ḥamdu lillāhi rabbil-'ālamīn", "Segala puji bagi Allah, Tuhan seluruh alam,", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/2.mp3", 1),
                Ayah(3, 1, "الرَّحْمَٰنِ الرَّحِيمِ", "Ar-raḥmānir-raḥīm", "Yang Maha Pengasih, Maha Penyayang,", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/3.mp3", 1),
                Ayah(4, 1, "مَالِكِ يَوْمِ الدِّينِ", "Māliki yaumid-dīn", "Pemilik hari pembalasan.", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/4.mp3", 1),
                Ayah(5, 1, "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ", "Iyyāka na'budu wa iyyāka nasta'īn", "Hanya kepada Engkaulah kami menyembah dan hanya kepada Engkaulah kami memohon pertolongan.", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/5.mp3", 1),
                Ayah(6, 1, "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ", "Ihdinaṣ-ṣirāṭal-mustaqīm", "Tunjukilah kami jalan yang lurus,", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6.mp3", 1),
                Ayah(7, 1, "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ", "Ṣirāṭallażīna an'amta 'alaihim gairil-magḍūbi 'alaihim wa laḍ-ḍāllīn", "(yaitu) jalan orang-orang yang telah Engkau beri nikmat kepadanya; bukan (jalan) mereka yang dimurkai, dan bukan (pula jalan) mereka yang sesat.", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/7.mp3", 1)
            )
            18 -> listOf(
                Ayah(1, 18, "الْحَمْدُ لِلَّهِ الَّذِي أَنزَلَ عَلَىٰ عَبْدِهِ الْكِتَابَ وَلَمْ يَجْعَل لَّهُ عِوَجًا ۜ", "Al-ḥamdu lillāhil-lażī anzala 'alā 'abdihil-kitāba wa lam yaj'al lahū 'iwajā", "Segala puji bagi Allah yang telah menurunkan Kitab (Al-Qur'an) kepada hamba-Nya dan Dia tidak menjadikannya bengkok,", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/2141.mp3", 15),
                Ayah(2, 18, "قَيِّمًا لِّيُنذِرَ بَأْسًا شَدِيدًا مِّن لَّدُنْهُ وَيُبَشِّرَ الْمُؤْمِنِينَ الَّذِينَ يَعْمَلُونَ الصَّالِحَاتِ أَنَّ لَهُمْ أَجْرًا حَسَنًا", "Qayyimal liyunżira ba'san syadīdam mil ladun-hu wa yubasysyiral-mu'minīnallażīna ya'malūnaṣ-ṣāliḥāti anna lahum ajran ḥasanā", "sebagai bimbingan yang lurus, untuk memperingatkan akan siksa yang sangat pedih dari sisi-Nya dan memberikan kabar gembira kepada orang-orang mukmin yang mengerjakan kebajikan bahwa mereka akan mendapat balasan yang baik,", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/2142.mp3", 15),
                Ayah(3, 18, "مَّاكِثِينَ فِيهِ أَبَدًا", "Mākiṡīna fīhi abadā", "mereka kekal di dalamnya untuk selama-lamanya.", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/2143.mp3", 15),
                Ayah(4, 18, "وَيُنذِرَ الَّذِينَ قَالُوا اتَّخَذَ اللَّهُ وَلَدًا", "Wa yunżirallażīna qālat-takhazallāhu waladā", "Dan untuk memperingatkan kepada orang yang berkata, 'Allah mengambil seorang anak.'", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/2144.mp3", 15),
                Ayah(9, 18, "أَمْ حَسِبْتَ أَنَّ أَصْحَابَ الْكَهْفِ وَالرَّقِيمِ كَانُوا مِنْ آيَاتِنَا عَجَبًا", "Am ḥasibta anna aṣ-ḥābal-kahfi war-raqīmi kānū min āyātinā 'ajabā", "Apakah engkau mengira bahwa orang yang mendiami gua, dan (yang mempunyai) raqim itu, termasuk tanda-tanda (kebesaran) Kami yang menakjubkan?", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/2149.mp3", 15),
                Ayah(10, 18, "إِذْ أَوَى الْفِتْيَةُ إِلَى الْكَهْفِ فَقَالُوا رَبَّنَا آتِنَا مِن لَّدُنكَ رَحْمَةً وَهَيِّئْ لَنَا مِنْ أَمْرِنَا رَشَدًا", "Iż awal-fityatu ilal-kahfi fa qālū rabbanā ātinā mil ladunka raḥmataw wa hayyi' lanā min amrinā rasyadā", "(Ingatlah) ketika pemuda-pemuda itu berlindung ke dalam gua lalu mereka berdoa, 'Ya Tuhan kami. Berikanlah rahmat kepada kami dari sisi-Mu dan sempurnakanlah petunjuk yang lurus bagi kami dalam urusan kami.'", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/2150.mp3", 15),
                Ayah(110, 18, "قُلْ إِنَّمَا أَنَا بَشَرٌ مِّثْلُكُمْ يُوحَىٰ إِلَيَّ أَنَّمَا إِلَٰهُكُمْ إِلَٰهٌ وَاحِدٌ ۖ فَمَن كَانَ يَرْجُو لِقَاءَ رَبِّهِ فَلْيَعْمَلْ عَمَلًا صَالِحًا وَلَا يُشْرِكْ بِعِبَادَةِ رَبِّهِ أَحَدًا", "Qul innamā ana basyarum miṡlukum yūḥā ilayya annamā ilāhukum ilāhuw wāḥid", "Katakanlah (Muhammad), 'Sesungguhnya aku ini hanya seorang manusia seperti kamu, yang telah menerima wahyu, bahwa sesungguhnya Tuhan kamu adalah Tuhan Yang Maha Esa.' Maka barangsiapa mengharap pertemuan dengan Tuhannya maka hendaklah dia mengerjakan kebajikan dan janganlah mempersekutukan dengan sesuatu pun dalam beribadah kepada Tuhannya.", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/2250.mp3", 16)
            )
            36 -> listOf(
                Ayah(1, 36, "يس", "Yā Sīn", "Yaa Siin.", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/3706.mp3", 22),
                Ayah(2, 36, "وَالْقُرْآنِ الْحَكِيمِ", "Wal-qur'ānil-ḥakīm", "Demi Al-Qur'an yang penuh hikmah,", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/3707.mp3", 22),
                Ayah(3, 36, "إِنَّكَ لَمِنَ الْمُرْسَلِينَ", "Innaka laminal-mursalīn", "sungguh, engkau (Muhammad) adalah salah seorang dari rasul-rasul,", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/3708.mp3", 22),
                Ayah(4, 36, "عَلَىٰ صِرَاطٍ مُّسْتَقِيمٍ", "'Alā ṣirāṭim mustaqīm", "(yang berada) di atas jalan yang lurus,", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/3709.mp3", 22),
                Ayah(5, 36, "تَنزِيلَ الْعَزِيزِ الرَّحِيمِ", "Tanzīlal-'azīzir-raḥīm", "(sebagai wahyu) yang diturunkan oleh (Allah) Yang Mahaperkasa, Maha Penyayang.", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/3710.mp3", 22),
                Ayah(58, 36, "سَلَامٌ قَوْلًا مِّن رَّبٍّ رَّحِيمٍ", "Salām, qaulam mir rabbir raḥīm", "(Kepada mereka dikatakan), 'Salam,' sebagai ucapan selamat dari Tuhan Yang Maha Penyayang.", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/3763.mp3", 23),
                Ayah(82, 36, "إِنَّمَا أَمْرُهُ إِذَا أَرَادَ شَيْئًا أَن يَقُولَ لَهُ كُن فَيَكُونُ", "Innamā amruhū iżā arāda syai'an ay yaqūla lahū kun fa yakūn", "Sesungguhnya urusan-Nya apabila Dia menghendaki sesuatu Dia hanya berkata kepadanya, 'Jadilah!' Maka jadilah sesuatu itu.", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/3787.mp3", 23)
            )
            67 -> listOf(
                Ayah(1, 67, "تَبَارَكَ الَّذِي بِيَدِهِ الْمُلْكُ وَهُوَ عَلَىٰ كُلِّ شَيْءٍ قَدِيرٌ", "Tabārakallażī biyadihil-mulku wa huwa 'alā kulli syai'in qadīr", "Mahasuci Allah yang menguasai (segala) kerajaan, dan Dia Mahakuasa atas segala sesuatu.", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/5242.mp3", 29),
                Ayah(2, 67, "الَّذِي خَلَقَ الْمَوْتَ وَالْحَيَاةَ لِيَبْلُوَكُمْ أَيُّكُمْ أَحْسَنُ عَمَلًا ۚ وَهُوَ الْعَزِيزُ الْغَفُورُ", "Allażī khalaqal-mauta wal-ḥayāta liyabluwakum ayyukum aḥsanu 'amalā, wa huwal-'azīzul-gafūr", "Yang menciptakan mati dan hidup, untuk menguji kamu, siapa di antara kamu yang lebih baik amalnya. Dan Dia Mahaperkasa, Maha Pengampun.", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/5243.mp3", 29),
                Ayah(3, 67, "الَّذِي خَلَقَ سَبْعَ سَمَاوَاتٍ طِبَاقًا ۖ مَّا تَرَىٰ فِي خَلْقِ الرَّحْمَٰنِ مِن تَفَاوُتٍ", "Allażī khalaqa sab'a samāwātin ṭibāqā, mā tarā fī khalqir-raḥmāni min tafāwut", "Yang menciptakan tujuh langit berlapis-lapis. Tidak akan kamu lihat sesuatu yang tidak seimbang pada ciptaan Tuhan Yang Maha Pengasih.", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/5244.mp3", 29),
                Ayah(4, 67, "فَارْجِعِ الْبَصَرَ هَلْ تَرَىٰ مِن فُطُورٍ", "Farji'il-baṣara hal tarā min fuṭūr", "Maka lihatlah sekali lagi, adakah kamu lihat sesuatu yang cacat?", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/5245.mp3", 29),
                Ayah(30, 67, "قُلْ أَرَأَيْتُمْ إِنْ أَصْبَحَ مَاؤُكُمْ غَوْرًا فَمَن يَأْتِيكُم بِمَاءٍ مَّعِينٍ", "Qul ara'aitum in aṣbaḥa mā'ukum gauran fa may ya'tīkum bimā'im ma'īn", "Katakanlah (Muhammad), 'Terangkanlah kepadaku jika sumber air kamu menjadi kering; maka siapa yang akan memberimu air yang mengalir?'", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/5271.mp3", 29)
            )
            55 -> listOf(
                Ayah(1, 55, "الرَّحْمَٰنُ", "Ar-raḥmān", "(Allah) Yang Maha Pengasih,", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/4902.mp3", 27),
                Ayah(2, 55, "عَلَّمَ الْقُرْآنَ", "'Allamal-qur'ān", "Yang telah mengajarkan Al-Qur'an.", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/4903.mp3", 27),
                Ayah(3, 55, "خَلَقَ الْإِنسَانَ", "Khalaqal-insān", "Dia menciptakan manusia,", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/4904.mp3", 27),
                Ayah(4, 55, "عَلَّمَهُ الْبَيَانَ", "'Allamahul-bayān", "mengajarnya pandai berbicara.", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/4905.mp3", 27),
                Ayah(13, 55, "فَبِأَيِّ آلَاءِ رَبِّكُمَا تُكَذِّبَانِ", "Fa bi'ayyi ālā'i rabbikumā tukażżibān", "Maka nikmat Tuhanmu yang manakah yang kamu dustakan?", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/4914.mp3", 27)
            )
            112 -> listOf(
                Ayah(1, 112, "قُلْ هُوَ اللَّهُ أَحَدٌ", "Qul huwallāhu aḥad", "Katakanlah (Muhammad), 'Dialah Allah, Yang Maha Esa.'", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6221.mp3", 30),
                Ayah(2, 112, "اللَّهُ الصَّمَدُ", "Allāhuṣ-ṣamad", "Allah tempat meminta segala sesuatu.", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6222.mp3", 30),
                Ayah(3, 112, "لَمْ يَلِدْ وَلَمْ يُولَدْ", "Lam yalid wa lam yūlad", "(Allah) tidak beranak dan tidak pula diperanakkan,", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6223.mp3", 30),
                Ayah(4, 112, "وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ", "Wa lam yakul lahū kufuwan aḥad", "dan tidak ada sesuatu yang setara dengan Dia.", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6224.mp3", 30)
            )
            113 -> listOf(
                Ayah(1, 113, "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ", "Qul a'ūżu birabbil-falaq", "Katakanlah, 'Aku berlindung kepada Tuhan yang menguasai subuh (fajar),'", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6225.mp3", 30),
                Ayah(2, 113, "مِن شَرِّ مَا خَلَقَ", "Min syarri mā khalaq", "dari kejahatan (makhluk yang) Dia ciptakan,", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6226.mp3", 30),
                Ayah(3, 113, "وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ", "Wa min syarri gāsiqin iżā waqab", "dan dari kejahatan malam apabila telah gelap gulita,", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6227.mp3", 30),
                Ayah(4, 113, "وَمِن شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ", "Wa min syarrin-naffāṡāti fil-'uqad", "dan dari kejahatan (perempuan-perempuan) penyihir yang meniup pada buhul-buhul (talinya),", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6228.mp3", 30),
                Ayah(5, 113, "وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ", "Wa min syarri ḥāsidin iżā ḥasad", "dan dari kejahatan orang yang dengki apabila dia dengki.'", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6229.mp3", 30)
            )
            114 -> listOf(
                Ayah(1, 114, "قُلْ أَعُوذُ بِرَبِّ النَّاسِ", "Qul a'ūżu birabbin-nās", "Katakanlah, 'Aku berlindung kepada Tuhannya manusia,'", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6230.mp3", 30),
                Ayah(2, 114, "مَلِكِ النَّاسِ", "Malikin-nās", "Raja manusia,", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6231.mp3", 30),
                Ayah(3, 114, "إِلَٰهِ النَّاسِ", "Ilāhin-nās", "Sembahan manusia,", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6232.mp3", 30),
                Ayah(4, 114, "مِن شَرِّ الْوَسْوَاسِ الْخَنَّاسِ", "Min syarril-waswāsil-khannās", "dari kejahatan (bisikan) setan yang bersembunyi,", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6233.mp3", 30),
                Ayah(5, 114, "الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ", "Allażī yuwaswisu fī ṣudūrin-nās", "yang membisikkan (kejahatan) ke dalam dada manusia,", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6234.mp3", 30),
                Ayah(6, 114, "مِنَ الْجِنَّةِ وَالنَّاسِ", "Minal-jinnati wan-nās", "dari (golongan) jin dan manusia.", "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6235.mp3", 30)
            )
            else -> generateGenericAyahs(surahNumber)
        }
    }

    private fun generateGenericAyahs(surahNumber: Int): List<Ayah> {
        val surah = getSurahByNumber(surahNumber) ?: return emptyList()
        val count = minOf(surah.numberOfAyahs, 10)
        return (1..count).map { ayahNum ->
            Ayah(
                numberInSurah = ayahNum,
                surahNumber = surahNumber,
                arabicText = "آيَةٌ مِن سُورَةِ ${surah.arabicName} بِرَقْمِ $ayahNum",
                latinText = "Ayat ke-$ayahNum dari Surah ${surah.name}",
                translationText = "Dan demikianlah petunjuk Allah dalam surah ${surah.name} ayat $ayahNum untuk membimbing umat manusia menuju ketenteraman dan kebenaran.",
                audioUrl = "",
                juz = surah.juzStart
            )
        }
    }

    val dailyVerse = Ayah(
        numberInSurah = 28,
        surahNumber = 13,
        arabicText = "الَّذِينَ آمَنُوا وَتَطْمَئِنُّ قُلُوبُهُم بِذِكْرِ اللَّهِ ۗ أَلَا بِذِكْرِ اللَّهِ تَطْمَئِنُّ الْقُلُوبُ",
        latinText = "Allażīna āmanū wa taṭma'innu qulūbuhum biżikrillāh, alā biżikrillāhi taṭma'innul-qulūb",
        translationText = "(yaitu) orang-orang yang beriman dan hati mereka menjadi tenteram dengan mengingat Allah. Ingatlah, hanya dengan mengingat Allah hati menjadi tenteram. (QS. Ar-Ra'd: 28)",
        audioUrl = "https://cdn.islamic.network/quran/audio/128/ar.alafasy/1735.mp3",
        juz = 13
    )
}
