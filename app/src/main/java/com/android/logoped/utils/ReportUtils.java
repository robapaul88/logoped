package com.android.logoped.utils;

/**
 * Created by paul on 17/02/15.
 */
public class ReportUtils {

    public enum ConsoaneProducere {
        OCLUSIVE("p", "b", "t", "d", "c", "g"),
        FRICATIVE("s", "z", "ş", "j", "f", "v", "h"),
        AFRICATE("ţ", "ci", "ce", "gi", "ge"),
        NAZALE("m", "n"),
        SONANTE("r", "l", "m", "n");

        public String[] mItems;

        ConsoaneProducere(String... items) {
            this.mItems = items;
        }

        public boolean contains(String letter) {
            for (String s : mItems) {
                if (s.equals(letter)) {
                    return true;
                }
            }
            return false;
        }
    }

    public enum ConsoaneArticulare {
        BILABIALE("p", "b", "m"),
        LABIODENTALE("f", "v"),
        APICO_DENTALE("t", "d", "n", "s", "z", "ţ", "r", "l"),
        ALVEOLARE("ş", "j"),
        PALATALE("ci", "ce", "gi", "ge"),
        VELARE("c", "g"),
        LARINGALE("h");

        public String[] mItems;

        ConsoaneArticulare(String... items) {
            this.mItems = items;
        }

        public boolean contains(String letter) {
            for (String s : mItems) {
                if (s.equals(letter)) {
                    return true;
                }
            }
            return false;
        }
    }

    public enum VocaleArticulare {
        ANTERIOARE("e", "i"),
        MEDIANE("a", "ă", "î", "â"),
        POSTERIOARE("o", "u");

        public String[] mItems;

        VocaleArticulare(String... items) {
            this.mItems = items;
        }

        public boolean contains(String letter) {
            for (String s : mItems) {
                if (s.equals(letter)) {
                    return true;
                }
            }
            return false;
        }
    }

    public enum VocaleDeschidereCavitateBucala {
        DESCHISE("a"),
        SEMIDESCHISE("e", "ă", "o"),
        INCHISE("i", "î", "u", "â");

        public String[] mItems;

        VocaleDeschidereCavitateBucala(String... items) {
            this.mItems = items;
        }

        public boolean contains(String letter) {
            for (String s : mItems) {
                if (s.equals(letter)) {
                    return true;
                }
            }
            return false;
        }
    }

    public enum VocaleParticipareBuze {
        ROTUNJITE("o", "u"),
        NEROTUNJITE("e", "i", "a", "ă", "î", "â");

        public String[] mItems;

        VocaleParticipareBuze(String... items) {
            this.mItems = items;
        }

        public boolean contains(String letter) {
            for (String s : mItems) {
                if (s.equals(letter)) {
                    return true;
                }
            }
            return false;
        }
    }
}
