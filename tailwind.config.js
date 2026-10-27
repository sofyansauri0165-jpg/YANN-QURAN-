/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  darkMode: 'class',
  theme: {
    extend: {
      colors: {
        'brand-emerald': '#008653',
        'brand-deep': '#004D35',
        'brand-dark': '#003D2B',
        'brand-gold': '#F4BE45',
        'brand-soft-gold': '#FFE39A',
        'brand-dark-gold': '#B78013',
        'brand-warm-white': '#FFFEF9',
        'brand-soft-surface': '#EAF3EC',
        'brand-dark-bg': '#071F18',
        'brand-dark-surface': '#103528',
        'brand-dark-text': '#F5F0E2',
      },
      fontFamily: {
        arabic: ['Amiri', 'serif'],
        sans: ['"Plus Jakarta Sans"', 'sans-serif'],
      },
    },
  },
  plugins: [],
};
