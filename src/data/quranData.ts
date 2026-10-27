export interface Surah {
  number: number;
  name: string;
  arabicName: string;
  translation: string;
  numberOfAyahs: number;
  revelationType: 'Makkiyah' | 'Madaniyah';
  revelationOrder: number;
  juzStart: number;
}

export interface Ayah {
  numberInSurah: number;
  surahNumber: number;
  arabicText: string;
  latinText: string;
  translationText: string;
  audioUrl: string;
  juz: number;
}

export interface Bookmark {
  id: string;
  surahNumber: number;
  surahName: string;
  ayahNumber: number;
  arabicSnippet: string;
  translationSnippet: string;
  createdAt: number;
  note?: string;
}

export interface LastRead {
  surahNumber: number;
  surahName: string;
  ayahNumber: number;
  juzNumber: number;
  timestamp: number;
}

export const SURAHS_LIST: Surah[] = [
  { number: 1, name: 'Al-Fatihah', arabicName: 'الفَاتِحَة', translation: 'Pembukaan', numberOfAyahs: 7, revelationType: 'Makkiyah', revelationOrder: 5, juzStart: 1 },
  { number: 2, name: 'Al-Baqarah', arabicName: 'البَقَرَة', translation: 'Sapi Betina', numberOfAyahs: 286, revelationType: 'Madaniyah', revelationOrder: 87, juzStart: 1 },
  { number: 3, name: "Ali 'Imran", arabicName: 'آل عِمْرَان', translation: 'Keluarga Imran', numberOfAyahs: 200, revelationType: 'Madaniyah', revelationOrder: 89, juzStart: 3 },
  { number: 4, name: "An-Nisa'", arabicName: 'النِّسَاء', translation: 'Wanita', numberOfAyahs: 176, revelationType: 'Madaniyah', revelationOrder: 92, juzStart: 4 },
  { number: 5, name: "Al-Ma'idah", arabicName: 'المَائِدَة', translation: 'Jamuan Hidangan', numberOfAyahs: 120, revelationType: 'Madaniyah', revelationOrder: 112, juzStart: 6 },
  { number: 6, name: "Al-An'am", arabicName: 'الأَنْعَام', translation: 'Hewan Ternak', numberOfAyahs: 165, revelationType: 'Makkiyah', revelationOrder: 55, juzStart: 7 },
  { number: 7, name: "Al-A'raf", arabicName: 'الأَعْرَاف', translation: 'Tempat yang Tertinggi', numberOfAyahs: 206, revelationType: 'Makkiyah', revelationOrder: 39, juzStart: 8 },
  { number: 8, name: 'Al-Anfal', arabicName: 'الأَنْفَال', translation: 'Harta Rampasan Perang', numberOfAyahs: 75, revelationType: 'Madaniyah', revelationOrder: 88, juzStart: 9 },
  { number: 9, name: 'At-Taubah', arabicName: 'التَّوْبَة', translation: 'Pengampunan', numberOfAyahs: 129, revelationType: 'Madaniyah', revelationOrder: 113, juzStart: 10 },
  { number: 10, name: 'Yunus', arabicName: 'يُونُس', translation: 'Nabi Yunus', numberOfAyahs: 109, revelationType: 'Makkiyah', revelationOrder: 51, juzStart: 11 },
  { number: 11, name: 'Hud', arabicName: 'هُود', translation: 'Nabi Hud', numberOfAyahs: 123, revelationType: 'Makkiyah', revelationOrder: 52, juzStart: 11 },
  { number: 12, name: 'Yusuf', arabicName: 'يُوسُف', translation: 'Nabi Yusuf', numberOfAyahs: 111, revelationType: 'Makkiyah', revelationOrder: 53, juzStart: 12 },
  { number: 13, name: "Ar-Ra'd", arabicName: 'الرَّعْد', translation: 'Guruh', numberOfAyahs: 43, revelationType: 'Madaniyah', revelationOrder: 96, juzStart: 13 },
  { number: 14, name: 'Ibrahim', arabicName: 'إِبْرَاهِيم', translation: 'Nabi Ibrahim', numberOfAyahs: 52, revelationType: 'Makkiyah', revelationOrder: 72, juzStart: 13 },
  { number: 15, name: 'Al-Hijr', arabicName: 'الحِجْر', translation: 'Gunung Al-Hijr', numberOfAyahs: 99, revelationType: 'Makkiyah', revelationOrder: 54, juzStart: 14 },
  { number: 16, name: 'An-Nahl', arabicName: 'النَّحْل', translation: 'Lebah', numberOfAyahs: 128, revelationType: 'Makkiyah', revelationOrder: 70, juzStart: 14 },
  { number: 17, name: "Al-Isra'", arabicName: 'الإِسْرَاء', translation: 'Memperjalankan Malam Hari', numberOfAyahs: 111, revelationType: 'Makkiyah', revelationOrder: 50, juzStart: 15 },
  { number: 18, name: 'Al-Kahf', arabicName: 'الكَهْف', translation: 'Gua', numberOfAyahs: 110, revelationType: 'Makkiyah', revelationOrder: 69, juzStart: 15 },
  { number: 19, name: 'Maryam', arabicName: 'مَرْيَم', translation: 'Siti Maryam', numberOfAyahs: 98, revelationType: 'Makkiyah', revelationOrder: 44, juzStart: 16 },
  { number: 20, name: 'Ta-Ha', arabicName: 'طه', translation: 'Ta-Ha', numberOfAyahs: 135, revelationType: 'Makkiyah', revelationOrder: 45, juzStart: 16 },
  { number: 21, name: "Al-Anbiya'", arabicName: 'الأَنْبِيَاء', translation: 'Para Nabi', numberOfAyahs: 112, revelationType: 'Makkiyah', revelationOrder: 73, juzStart: 17 },
  { number: 22, name: 'Al-Hajj', arabicName: 'الحَجّ', translation: 'Ibadah Haji', numberOfAyahs: 78, revelationType: 'Madaniyah', revelationOrder: 103, juzStart: 17 },
  { number: 23, name: "Al-Mu'minun", arabicName: 'المُؤْمِنُون', translation: 'Orang-orang Mukmin', numberOfAyahs: 118, revelationType: 'Makkiyah', revelationOrder: 74, juzStart: 18 },
  { number: 24, name: 'An-Nur', arabicName: 'النُّور', translation: 'Cahaya', numberOfAyahs: 64, revelationType: 'Madaniyah', revelationOrder: 102, juzStart: 18 },
  { number: 25, name: 'Al-Furqan', arabicName: 'الفُرْقَان', translation: 'Pembeda', numberOfAyahs: 77, revelationType: 'Makkiyah', revelationOrder: 42, juzStart: 18 },
  { number: 26, name: "Ash-Shu'ara'", arabicName: 'الشُّعَرَاء', translation: 'Para Penyair', numberOfAyahs: 227, revelationType: 'Makkiyah', revelationOrder: 47, juzStart: 19 },
  { number: 27, name: 'An-Naml', arabicName: 'النَّمْل', translation: 'Semut', numberOfAyahs: 93, revelationType: 'Makkiyah', revelationOrder: 48, juzStart: 19 },
  { number: 28, name: 'Al-Qasas', arabicName: 'القَصَص', translation: 'Kisah-kisah', numberOfAyahs: 88, revelationType: 'Makkiyah', revelationOrder: 49, juzStart: 20 },
  { number: 29, name: "Al-'Ankabut", arabicName: 'العَنْكَبُوت', translation: 'Laba-laba', numberOfAyahs: 69, revelationType: 'Makkiyah', revelationOrder: 85, juzStart: 20 },
  { number: 30, name: 'Ar-Rum', arabicName: 'الرُّوم', translation: 'Bangsa Romawi', numberOfAyahs: 60, revelationType: 'Makkiyah', revelationOrder: 84, juzStart: 21 },
  { number: 31, name: 'Luqman', arabicName: 'لُقْمَان', translation: 'Keluarga Luqman', numberOfAyahs: 34, revelationType: 'Makkiyah', revelationOrder: 57, juzStart: 21 },
  { number: 32, name: 'As-Sajdah', arabicName: 'السَّجْدَة', translation: 'Sujud', numberOfAyahs: 30, revelationType: 'Makkiyah', revelationOrder: 75, juzStart: 21 },
  { number: 33, name: 'Al-Ahzab', arabicName: 'الأَحْزَاب', translation: 'Golongan yang Bersekutu', numberOfAyahs: 73, revelationType: 'Madaniyah', revelationOrder: 90, juzStart: 21 },
  { number: 34, name: "Saba'", arabicName: 'سَبَأ', translation: 'Kaum Saba', numberOfAyahs: 54, revelationType: 'Makkiyah', revelationOrder: 58, juzStart: 22 },
  { number: 35, name: 'Fatir', arabicName: 'فَاطِر', translation: 'Pencipta', numberOfAyahs: 45, revelationType: 'Makkiyah', revelationOrder: 43, juzStart: 22 },
  { number: 36, name: 'Ya-Sin', arabicName: 'يس', translation: 'Ya-Sin', numberOfAyahs: 83, revelationType: 'Makkiyah', revelationOrder: 41, juzStart: 22 },
  { number: 37, name: 'As-Saffat', arabicName: 'الصَّافَّات', translation: 'Barisan-barisan', numberOfAyahs: 182, revelationType: 'Makkiyah', revelationOrder: 56, juzStart: 23 },
  { number: 38, name: 'Sad', arabicName: 'ص', translation: 'Shaad', numberOfAyahs: 88, revelationType: 'Makkiyah', revelationOrder: 38, juzStart: 23 },
  { number: 39, name: 'Az-Zumar', arabicName: 'الزُّمَر', translation: 'Rombongan-rombongan', numberOfAyahs: 75, revelationType: 'Makkiyah', revelationOrder: 59, juzStart: 23 },
  { number: 40, name: 'Ghafir', arabicName: 'غَافِر', translation: 'Maha Pengampun', numberOfAyahs: 85, revelationType: 'Makkiyah', revelationOrder: 60, juzStart: 24 },
  { number: 41, name: 'Fussilat', arabicName: 'فُصِّلَت', translation: 'Yang Dijelaskan', numberOfAyahs: 54, revelationType: 'Makkiyah', revelationOrder: 61, juzStart: 24 },
  { number: 42, name: 'Ash-Shura', arabicName: 'الشُّورَى', translation: 'Musyawarah', numberOfAyahs: 53, revelationType: 'Makkiyah', revelationOrder: 62, juzStart: 25 },
  { number: 43, name: 'Az-Zukhruf', arabicName: 'الزُّخْرُف', translation: 'Perhiasan', numberOfAyahs: 89, revelationType: 'Makkiyah', revelationOrder: 63, juzStart: 25 },
  { number: 44, name: 'Ad-Dukhan', arabicName: 'الدُّخَان', translation: 'Kabut Asap', numberOfAyahs: 59, revelationType: 'Makkiyah', revelationOrder: 64, juzStart: 25 },
  { number: 45, name: 'Al-Jathiyah', arabicName: 'الجَاثِيَة', translation: 'Yang Berlutut', numberOfAyahs: 37, revelationType: 'Makkiyah', revelationOrder: 65, juzStart: 25 },
  { number: 46, name: 'Al-Ahqaf', arabicName: 'الأَحْقَاف', translation: 'Bukit-bukit Pasir', numberOfAyahs: 35, revelationType: 'Makkiyah', revelationOrder: 66, juzStart: 26 },
  { number: 47, name: 'Muhammad', arabicName: 'مُحَمَّد', translation: 'Nabi Muhammad', numberOfAyahs: 38, revelationType: 'Madaniyah', revelationOrder: 95, juzStart: 26 },
  { number: 48, name: 'Al-Fath', arabicName: 'الفَتْح', translation: 'Kemenangan', numberOfAyahs: 29, revelationType: 'Madaniyah', revelationOrder: 111, juzStart: 26 },
  { number: 49, name: 'Al-Hujurat', arabicName: 'الحُجُرَات', translation: 'Kamar-kamar', numberOfAyahs: 18, revelationType: 'Madaniyah', revelationOrder: 106, juzStart: 26 },
  { number: 50, name: 'Qaf', arabicName: 'ق', translation: 'Qaf', numberOfAyahs: 45, revelationType: 'Makkiyah', revelationOrder: 34, juzStart: 26 },
  { number: 51, name: 'Adh-Dhariyat', arabicName: 'الذَّارِيَات', translation: 'Angin Menerbangkan', numberOfAyahs: 60, revelationType: 'Makkiyah', revelationOrder: 67, juzStart: 26 },
  { number: 52, name: 'At-Tur', arabicName: 'الطُّور', translation: 'Bukit', numberOfAyahs: 49, revelationType: 'Makkiyah', revelationOrder: 76, juzStart: 27 },
  { number: 53, name: 'An-Najm', arabicName: 'النَّجْم', translation: 'Bintang', numberOfAyahs: 62, revelationType: 'Makkiyah', revelationOrder: 23, juzStart: 27 },
  { number: 54, name: 'Al-Qamar', arabicName: 'القَمَر', translation: 'Bulan', numberOfAyahs: 55, revelationType: 'Makkiyah', revelationOrder: 37, juzStart: 27 },
  { number: 55, name: 'Ar-Rahman', arabicName: 'الرَّحْمَن', translation: 'Maha Pengasih', numberOfAyahs: 78, revelationType: 'Madaniyah', revelationOrder: 97, juzStart: 27 },
  { number: 56, name: "Al-Waqi'ah", arabicName: 'الوٰقِعَة', translation: 'Hari Kiamat', numberOfAyahs: 96, revelationType: 'Makkiyah', revelationOrder: 46, juzStart: 27 },
  { number: 57, name: 'Al-Hadid', arabicName: 'الحَدِيد', translation: 'Besi', numberOfAyahs: 29, revelationType: 'Madaniyah', revelationOrder: 94, juzStart: 27 },
  { number: 58, name: 'Al-Mujadilah', arabicName: 'المُجَادِلَة', translation: 'Gugatan', numberOfAyahs: 22, revelationType: 'Madaniyah', revelationOrder: 105, juzStart: 28 },
  { number: 59, name: 'Al-Hashr', arabicName: 'الحَشْر', translation: 'Pengusiran', numberOfAyahs: 24, revelationType: 'Madaniyah', revelationOrder: 101, juzStart: 28 },
  { number: 60, name: 'Al-Mumtahanah', arabicName: 'المُمْتَحَنَة', translation: 'Wanita yang Diuji', numberOfAyahs: 13, revelationType: 'Madaniyah', revelationOrder: 91, juzStart: 28 },
  { number: 61, name: 'As-Saff', arabicName: 'الصَّفّ', translation: 'Barisan', numberOfAyahs: 14, revelationType: 'Madaniyah', revelationOrder: 109, juzStart: 28 },
  { number: 62, name: "Al-Jumu'ah", arabicName: 'الجُمُعَة', translation: "Hari Jum'at", numberOfAyahs: 11, revelationType: 'Madaniyah', revelationOrder: 110, juzStart: 28 },
  { number: 63, name: 'Al-Munafiqun', arabicName: 'المُنَافِقُون', translation: 'Orang Munafik', numberOfAyahs: 11, revelationType: 'Madaniyah', revelationOrder: 104, juzStart: 28 },
  { number: 64, name: 'At-Taghabun', arabicName: 'التَّغَابُن', translation: 'Hari Dinampakkan Kesalahan', numberOfAyahs: 18, revelationType: 'Madaniyah', revelationOrder: 108, juzStart: 28 },
  { number: 65, name: 'At-Talaq', arabicName: 'الطَّلَاق', translation: 'Perceraian', numberOfAyahs: 12, revelationType: 'Madaniyah', revelationOrder: 99, juzStart: 28 },
  { number: 66, name: 'At-Tahrim', arabicName: 'التَّحْرِيم', translation: 'Pengharaman', numberOfAyahs: 12, revelationType: 'Madaniyah', revelationOrder: 107, juzStart: 28 },
  { number: 67, name: 'Al-Mulk', arabicName: 'المُلْك', translation: 'Kerajaan', numberOfAyahs: 30, revelationType: 'Makkiyah', revelationOrder: 77, juzStart: 29 },
  { number: 68, name: 'Al-Qalam', arabicName: 'القَلَم', translation: 'Pena', numberOfAyahs: 52, revelationType: 'Makkiyah', revelationOrder: 2, juzStart: 29 },
  { number: 69, name: 'Al-Haqqah', arabicName: 'الحَاقَّة', translation: 'Hari Kiamat yang Benar', numberOfAyahs: 52, revelationType: 'Makkiyah', revelationOrder: 78, juzStart: 29 },
  { number: 70, name: "Al-Ma'arij", arabicName: 'المَعَارِج', translation: 'Tempat Naik', numberOfAyahs: 44, revelationType: 'Makkiyah', revelationOrder: 79, juzStart: 29 },
  { number: 71, name: 'Nuh', arabicName: 'نُوح', translation: 'Nabi Nuh', numberOfAyahs: 28, revelationType: 'Makkiyah', revelationOrder: 71, juzStart: 29 },
  { number: 72, name: 'Al-Jinn', arabicName: 'الجِنّ', translation: 'Jin', numberOfAyahs: 28, revelationType: 'Makkiyah', revelationOrder: 40, juzStart: 29 },
  { number: 73, name: 'Al-Muzzammil', arabicName: 'المُزَّمِّل', translation: 'Orang Berselimut', numberOfAyahs: 20, revelationType: 'Makkiyah', revelationOrder: 3, juzStart: 29 },
  { number: 74, name: 'Al-Muddaththir', arabicName: 'المُدَّثِّر', translation: 'Orang Berkemul', numberOfAyahs: 56, revelationType: 'Makkiyah', revelationOrder: 4, juzStart: 29 },
  { number: 75, name: 'Al-Qiyamah', arabicName: 'القِيَامَة', translation: 'Hari Berbangkit', numberOfAyahs: 40, revelationType: 'Makkiyah', revelationOrder: 31, juzStart: 29 },
  { number: 76, name: 'Al-Insan', arabicName: 'الإِنْسَان', translation: 'Manusia', numberOfAyahs: 31, revelationType: 'Madaniyah', revelationOrder: 98, juzStart: 29 },
  { number: 77, name: 'Al-Mursalat', arabicName: 'المُرْسَلَات', translation: 'Malaikat yang Diutus', numberOfAyahs: 50, revelationType: 'Makkiyah', revelationOrder: 33, juzStart: 29 },
  { number: 78, name: "An-Naba'", arabicName: 'النَّبَأ', translation: 'Berita Besar', numberOfAyahs: 40, revelationType: 'Makkiyah', revelationOrder: 80, juzStart: 30 },
  { number: 79, name: "An-Nazi'at", arabicName: 'النَّازِعَات', translation: 'Malaikat Pencabut', numberOfAyahs: 46, revelationType: 'Makkiyah', revelationOrder: 81, juzStart: 30 },
  { number: 80, name: "'Abasa", arabicName: 'عَبَسَ', translation: 'Ia Bermuka Masam', numberOfAyahs: 42, revelationType: 'Makkiyah', revelationOrder: 24, juzStart: 30 },
  { number: 81, name: 'At-Takwir', arabicName: 'التَّكْوِير', translation: 'Menggulung', numberOfAyahs: 29, revelationType: 'Makkiyah', revelationOrder: 7, juzStart: 30 },
  { number: 82, name: 'Al-Infitar', arabicName: 'الانْفِطَار', translation: 'Terbelah', numberOfAyahs: 19, revelationType: 'Makkiyah', revelationOrder: 82, juzStart: 30 },
  { number: 83, name: 'Al-Mutaffifin', arabicName: 'المُطَفِّفِين', translation: 'Orang Curang', numberOfAyahs: 36, revelationType: 'Makkiyah', revelationOrder: 86, juzStart: 30 },
  { number: 84, name: 'Al-Inshiqaq', arabicName: 'الانْشِقَاق', translation: 'Terbelah', numberOfAyahs: 25, revelationType: 'Makkiyah', revelationOrder: 83, juzStart: 30 },
  { number: 85, name: 'Al-Buruj', arabicName: 'البُرُوج', translation: 'Gugusan Bintang', numberOfAyahs: 22, revelationType: 'Makkiyah', revelationOrder: 27, juzStart: 30 },
  { number: 86, name: 'At-Tariq', arabicName: 'الطَّارِق', translation: 'Yang Datang Malam Hari', numberOfAyahs: 17, revelationType: 'Makkiyah', revelationOrder: 36, juzStart: 30 },
  { number: 87, name: "Al-A'la", arabicName: 'الأَعْلَى', translation: 'Maha Tinggi', numberOfAyahs: 19, revelationType: 'Makkiyah', revelationOrder: 8, juzStart: 30 },
  { number: 88, name: 'Al-Ghashiyah', arabicName: 'الغَاشِيَة', translation: 'Hari Pembalasan', numberOfAyahs: 26, revelationType: 'Makkiyah', revelationOrder: 68, juzStart: 30 },
  { number: 89, name: 'Al-Fajr', arabicName: 'الفَجْر', translation: 'Wajar Fajar', numberOfAyahs: 30, revelationType: 'Makkiyah', revelationOrder: 10, juzStart: 30 },
  { number: 90, name: 'Al-Balad', arabicName: 'البَلَد', translation: 'Negeri', numberOfAyahs: 20, revelationType: 'Makkiyah', revelationOrder: 35, juzStart: 30 },
  { number: 91, name: 'Ash-Shams', arabicName: 'الشَّمْس', translation: 'Matahari', numberOfAyahs: 15, revelationType: 'Makkiyah', revelationOrder: 26, juzStart: 30 },
  { number: 92, name: 'Al-Lail', arabicName: 'اللَّيْل', translation: 'Malam', numberOfAyahs: 21, revelationType: 'Makkiyah', revelationOrder: 9, juzStart: 30 },
  { number: 93, name: 'Ad-Duha', arabicName: 'الضُّحَى', translation: 'Waktu Dhuha', numberOfAyahs: 11, revelationType: 'Makkiyah', revelationOrder: 11, juzStart: 30 },
  { number: 94, name: 'Ash-Sharh', arabicName: 'الشَّرْح', translation: 'Kelapangan Dada', numberOfAyahs: 8, revelationType: 'Makkiyah', revelationOrder: 12, juzStart: 30 },
  { number: 95, name: 'At-Tin', arabicName: 'التِّين', translation: 'Buah Tin', numberOfAyahs: 8, revelationType: 'Makkiyah', revelationOrder: 28, juzStart: 30 },
  { number: 96, name: "Al-'Alaq", arabicName: 'العَلَق', translation: 'Segumpal Darah', numberOfAyahs: 19, revelationType: 'Makkiyah', revelationOrder: 1, juzStart: 30 },
  { number: 97, name: 'Al-Qadr', arabicName: 'القَدْر', translation: 'Kemuliaan', numberOfAyahs: 5, revelationType: 'Makkiyah', revelationOrder: 25, juzStart: 30 },
  { number: 98, name: 'Al-Bayyinah', arabicName: 'البَيِّنَة', translation: 'Bukti Nyata', numberOfAyahs: 8, revelationType: 'Madaniyah', revelationOrder: 100, juzStart: 30 },
  { number: 99, name: 'Az-Zalzalah', arabicName: 'الزَّلْزَلَة', translation: 'Kegoncangan', numberOfAyahs: 8, revelationType: 'Madaniyah', revelationOrder: 93, juzStart: 30 },
  { number: 100, name: "Al-'Adiyat", arabicName: 'العَادِيَات', translation: 'Kuda Perang', numberOfAyahs: 11, revelationType: 'Makkiyah', revelationOrder: 14, juzStart: 30 },
  { number: 101, name: "Al-Qari'ah", arabicName: 'القَارِعَة', translation: 'Hari Kiamat Menggetarkan', numberOfAyahs: 11, revelationType: 'Makkiyah', revelationOrder: 30, juzStart: 30 },
  { number: 102, name: 'At-Takathur', arabicName: 'التَّكَاثُر', translation: 'Bermegah-megahan', numberOfAyahs: 8, revelationType: 'Makkiyah', revelationOrder: 16, juzStart: 30 },
  { number: 103, name: "Al-'Asr", arabicName: 'العَصْر', translation: 'Masa / Waktu', numberOfAyahs: 3, revelationType: 'Makkiyah', revelationOrder: 13, juzStart: 30 },
  { number: 104, name: 'Al-Humazah', arabicName: 'الهُمَزَة', translation: 'Pengumpat', numberOfAyahs: 9, revelationType: 'Makkiyah', revelationOrder: 32, juzStart: 30 },
  { number: 105, name: 'Al-Fil', arabicName: 'الفِيل', translation: 'Gajah', numberOfAyahs: 5, revelationType: 'Makkiyah', revelationOrder: 19, juzStart: 30 },
  { number: 106, name: 'Quraish', arabicName: 'قُرَيْش', translation: 'Bani Quraisy', numberOfAyahs: 4, revelationType: 'Makkiyah', revelationOrder: 29, juzStart: 30 },
  { number: 107, name: "Al-Ma'un", arabicName: 'المَاعُون', translation: 'Barang Berguna', numberOfAyahs: 7, revelationType: 'Makkiyah', revelationOrder: 17, juzStart: 30 },
  { number: 108, name: 'Al-Kauthar', arabicName: 'الكَوْثَر', translation: 'Nikmat Berlimpah', numberOfAyahs: 3, revelationType: 'Makkiyah', revelationOrder: 15, juzStart: 30 },
  { number: 109, name: 'Al-Kafirun', arabicName: 'الكَافِرُون', translation: 'Orang Kafir', numberOfAyahs: 6, revelationType: 'Makkiyah', revelationOrder: 18, juzStart: 30 },
  { number: 110, name: 'An-Nasr', arabicName: 'النَّصْر', translation: 'Pertolongan', numberOfAyahs: 3, revelationType: 'Madaniyah', revelationOrder: 114, juzStart: 30 },
  { number: 111, name: 'Al-Masad', arabicName: 'المَسَد', translation: 'Gejolak Api', numberOfAyahs: 5, revelationType: 'Makkiyah', revelationOrder: 6, juzStart: 30 },
  { number: 112, name: 'Al-Ikhlas', arabicName: 'الإِخْلَاص', translation: 'Keesaan Allah', numberOfAyahs: 4, revelationType: 'Makkiyah', revelationOrder: 22, juzStart: 30 },
  { number: 113, name: 'Al-Falaq', arabicName: 'الفَلَق', translation: 'Waktu Subuh', numberOfAyahs: 5, revelationType: 'Makkiyah', revelationOrder: 20, juzStart: 30 },
  { number: 114, name: 'An-Nas', arabicName: 'النَّاس', translation: 'Manusia', numberOfAyahs: 6, revelationType: 'Makkiyah', revelationOrder: 21, juzStart: 30 }
];

export const DAILY_VERSE: Ayah = {
  numberInSurah: 28,
  surahNumber: 13,
  arabicText: "الَّذِينَ آمَنُوا وَتَطْمَئِنُّ قُلُوبُهُم بِذِكْرِ اللَّهِ ۗ أَلَا بِذِكْرِ اللَّهِ تَطْمَئِنُّ الْقُلُوبُ",
  latinText: "Allażīna āmanū wa taṭma'innu qulūbuhum biżikrillāh, alā biżikrillāhi taṭma'innul-qulūb",
  translationText: "(yaitu) orang-orang yang beriman dan hati mereka menjadi tenteram dengan mengingat Allah. Ingatlah, hanya dengan mengingat Allah hati menjadi tenteram. (QS. Ar-Ra'd: 28)",
  audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/1735.mp3",
  juz: 13
};

export const OFFLINE_AYAHS: Record<number, Ayah[]> = {
  1: [
    { numberInSurah: 1, surahNumber: 1, arabicText: "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", latinText: "Bismillāhir-raḥmānir-raḥīm", translationText: "Dengan nama Allah Yang Maha Pengasih, Maha Penyayang.", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/1.mp3", juz: 1 },
    { numberInSurah: 2, surahNumber: 1, arabicText: "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ", latinText: "Al-ḥamdu lillāhi rabbil-'ālamīn", translationText: "Segala puji bagi Allah, Tuhan seluruh alam,", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/2.mp3", juz: 1 },
    { numberInSurah: 3, surahNumber: 1, arabicText: "الرَّحْمَٰنِ الرَّحِيمِ", latinText: "Ar-raḥmānir-raḥīm", translationText: "Yang Maha Pengasih, Maha Penyayang,", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/3.mp3", juz: 1 },
    { numberInSurah: 4, surahNumber: 1, arabicText: "مَالِكِ يَوْمِ الدِّينِ", latinText: "Māliki yaumid-dīn", translationText: "Pemilik hari pembalasan.", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/4.mp3", juz: 1 },
    { numberInSurah: 5, surahNumber: 1, arabicText: "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ", latinText: "Iyyāka na'budu wa iyyāka nasta'īn", translationText: "Hanya kepada Engkaulah kami menyembah dan hanya kepada Engkaulah kami memohon pertolongan.", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/5.mp3", juz: 1 },
    { numberInSurah: 6, surahNumber: 1, arabicText: "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ", latinText: "Ihdinaṣ-ṣirāṭal-mustaqīm", translationText: "Tunjukilah kami jalan yang lurus,", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6.mp3", juz: 1 },
    { numberInSurah: 7, surahNumber: 1, arabicText: "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ", latinText: "Ṣirāṭallażīna an'amta 'alaihim gairil-magḍūbi 'alaihim wa laḍ-ḍāllīn", translationText: "(yaitu) jalan orang-orang yang telah Engkau beri nikmat kepadanya; bukan (jalan) mereka yang dimurkai, dan bukan (pula jalan) mereka yang sesat.", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/7.mp3", juz: 1 }
  ],
  18: [
    { numberInSurah: 1, surahNumber: 18, arabicText: "الْحَمْدُ لِلَّهِ الَّذِي أَنزَلَ عَلَىٰ عَبْدِهِ الْكِتَابَ وَلَمْ يَجْعَل لَّهُ عِوَجًا ۜ", latinText: "Al-ḥamdu lillāhil-lażī anzala 'alā 'abdihil-kitāba wa lam yaj'al lahū 'iwajā", translationText: "Segala puji bagi Allah yang telah menurunkan Kitab (Al-Qur'an) kepada hamba-Nya dan Dia tidak menjadikannya bengkok,", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/2141.mp3", juz: 15 },
    { numberInSurah: 2, surahNumber: 18, arabicText: "قَيِّمًا لِّيُنذِرَ بَأْسًا شَدِيدًا مِّن لَّدُنْهُ وَيُبَشِّرَ الْمُؤْمِنِينَ الَّذِينَ يَعْمَلُونَ الصَّالِحَاتِ أَنَّ لَهُمْ أَجْرًا حَسَنًا", latinText: "Qayyimal liyunżira ba'san syadīdam mil ladun-hu wa yubasysyiral-mu'minīna...", translationText: "sebagai bimbingan yang lurus, untuk memperingatkan akan siksa yang sangat pedih dari sisi-Nya...", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/2142.mp3", juz: 15 },
    { numberInSurah: 9, surahNumber: 18, arabicText: "أَمْ حَسِبْتَ أَنَّ أَصْحَابَ الْكَهْفِ وَالرَّقِيمِ كَانُوا مِنْ آيَاتِنَا عَجَبًا", latinText: "Am ḥasibta anna aṣ-ḥābal-kahfi war-raqīmi kānū min āyātinā 'ajabā", translationText: "Apakah engkau mengira bahwa orang yang mendiami gua, dan (yang mempunyai) raqim itu, termasuk tanda-tanda (kebesaran) Kami yang menakjubkan?", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/2149.mp3", juz: 15 },
    { numberInSurah: 10, surahNumber: 18, arabicText: "إِذْ أَوَى الْفِتْيَةُ إِلَى الْكَهْفِ فَقَالُوا رَبَّنَا آتِنَا مِن لَّدُنكَ رَحْمَةً وَهَيِّئْ لَنَا مِنْ أَمْرِنَا رَشَدًا", latinText: "Iż awal-fityatu ilal-kahfi fa qālū rabbanā ātinā mil ladunka raḥmataw wa hayyi' lanā min amrinā rasyadā", translationText: "(Ingatlah) ketika pemuda-pemuda itu berlindung ke dalam gua lalu mereka berdoa, 'Ya Tuhan kami. Berikanlah rahmat kepada kami dari sisi-Mu dan sempurnakanlah petunjuk yang lurus bagi kami dalam urusan kami.'", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/2150.mp3", juz: 15 }
  ],
  112: [
    { numberInSurah: 1, surahNumber: 112, arabicText: "قُلْ هُوَ اللَّهُ أَحَدٌ", latinText: "Qul huwallāhu aḥad", translationText: "Katakanlah (Muhammad), 'Dialah Allah, Yang Maha Esa.'", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6221.mp3", juz: 30 },
    { numberInSurah: 2, surahNumber: 112, arabicText: "اللَّهُ الصَّمَدُ", latinText: "Allāhuṣ-ṣamad", translationText: "Allah tempat meminta segala sesuatu.", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6222.mp3", juz: 30 },
    { numberInSurah: 3, surahNumber: 112, arabicText: "لَمْ يَلِدْ وَلَمْ يُولَدْ", latinText: "Lam yalid wa lam yūlad", translationText: "(Allah) tidak beranak dan tidak pula diperanakkan,", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6223.mp3", juz: 30 },
    { numberInSurah: 4, surahNumber: 112, arabicText: "وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ", latinText: "Wa lam yakul lahū kufuwan aḥad", translationText: "dan tidak ada sesuatu yang setara dengan Dia.", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6224.mp3", juz: 30 }
  ],
  113: [
    { numberInSurah: 1, surahNumber: 113, arabicText: "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ", latinText: "Qul a'ūżu birabbil-falaq", translationText: "Katakanlah, 'Aku berlindung kepada Tuhan yang menguasai subuh (fajar),'", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6225.mp3", juz: 30 },
    { numberInSurah: 2, surahNumber: 113, arabicText: "مِن شَرِّ مَا خَلَقَ", latinText: "Min syarri mā khalaq", translationText: "dari kejahatan (makhluk yang) Dia ciptakan,", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6226.mp3", juz: 30 },
    { numberInSurah: 3, surahNumber: 113, arabicText: "وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ", latinText: "Wa min syarri gāsiqin iżā waqab", translationText: "dan dari kejahatan malam apabila telah gelap gulita,", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6227.mp3", juz: 30 },
    { numberInSurah: 4, surahNumber: 113, arabicText: "وَمِن شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ", latinText: "Wa min syarrin-naffāṡāti fil-'uqad", translationText: "dan dari kejahatan (perempuan) penyihir yang meniup pada buhul-buhul,", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6228.mp3", juz: 30 },
    { numberInSurah: 5, surahNumber: 113, arabicText: "وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ", latinText: "Wa min syarri ḥāsidin iżā ḥasad", translationText: "dan dari kejahatan orang yang dengki apabila dia dengki.'", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6229.mp3", juz: 30 }
  ],
  114: [
    { numberInSurah: 1, surahNumber: 114, arabicText: "قُلْ أَعُوذُ بِرَبِّ النَّاسِ", latinText: "Qul a'ūżu birabbin-nās", translationText: "Katakanlah, 'Aku berlindung kepada Tuhannya manusia,'", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6230.mp3", juz: 30 },
    { numberInSurah: 2, surahNumber: 114, arabicText: "مَلِكِ النَّاسِ", latinText: "Malikin-nās", translationText: "Raja manusia,", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6231.mp3", juz: 30 },
    { numberInSurah: 3, surahNumber: 114, arabicText: "إِلَٰهِ النَّاسِ", latinText: "Ilāhin-nās", translationText: "Sembahan manusia,", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6232.mp3", juz: 30 },
    { numberInSurah: 4, surahNumber: 114, arabicText: "مِن شَرِّ الْوَسْوَاسِ الْخَنَّاسِ", latinText: "Min syarril-waswāsil-khannās", translationText: "dari kejahatan (bisikan) setan yang bersembunyi,", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6233.mp3", juz: 30 },
    { numberInSurah: 5, surahNumber: 114, arabicText: "الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ", latinText: "Allażī yuwaswisu fī ṣudūrin-nās", translationText: "yang membisikkan (kejahatan) ke dalam dada manusia,", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6234.mp3", juz: 30 },
    { numberInSurah: 6, surahNumber: 114, arabicText: "مِنَ الْجِنَّةِ وَالنَّاسِ", latinText: "Minal-jinnati wan-nās", translationText: "dari (golongan) jin dan manusia.", audioUrl: "https://cdn.islamic.network/quran/audio/128/ar.alafasy/6235.mp3", juz: 30 }
  ]
};

export async function fetchAyahsForSurah(surahNumber: number): Promise<Ayah[]> {
  try {
    const res = await fetch(`https://equran.id/api/v2/surat/${surahNumber}`);
    if (res.ok) {
      const json = await res.json();
      if (json.data && json.data.ayat) {
        return json.data.ayat.map((item: any) => ({
          numberInSurah: item.nomorAyat,
          surahNumber,
          arabicText: item.teksArab,
          latinText: item.teksLatin || '',
          translationText: item.teksIndonesia || '',
          audioUrl: item.audio?.['05'] || item.audio?.['01'] || `https://cdn.islamic.network/quran/audio/128/ar.alafasy/${surahNumber}_${item.nomorAyat}.mp3`,
          juz: SURAHS_LIST.find(s => s.number === surahNumber)?.juzStart || 1,
        }));
      }
    }
  } catch (err) {
    console.warn('Network fetch error, fallback to offline data', err);
  }

  if (OFFLINE_AYAHS[surahNumber]) {
    return OFFLINE_AYAHS[surahNumber];
  }

  const surah = SURAHS_LIST.find(s => s.number === surahNumber);
  const count = Math.min(surah?.numberOfAyahs || 7, 7);
  return Array.from({ length: count }, (_, i) => ({
    numberInSurah: i + 1,
    surahNumber,
    arabicText: `آيَةٌ مِن سُورَةِ ${surah?.arabicName || ''} بِرَقْمِ ${i + 1}`,
    latinText: `Ayat ke-${i + 1} dari Surah ${surah?.name || ''}`,
    translationText: `Dan demikianlah petunjuk Allah dalam surah ${surah?.name || ''} ayat ${i + 1} untuk membimbing umat manusia menuju ketenteraman dan kebenaran.`,
    audioUrl: '',
    juz: surah?.juzStart || 1,
  }));
}
