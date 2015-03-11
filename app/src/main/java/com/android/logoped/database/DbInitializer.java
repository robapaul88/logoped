package com.android.logoped.database;

import com.android.logoped.model.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 17/02/15.
 */
public class DbInitializer {
    private static List<Word> wordsList;

    static {
        wordsList = new ArrayList<Word>();

        //a
        wordsList.add(new Word("apă", "apa.png", "a", -2));
        wordsList.add(new Word("floare", "floare.png", "a", -2));
        wordsList.add(new Word("fata", "fata.png", "a", -2));
        //e
        wordsList.add(new Word("elefant", "elefant.png", "e", -2));
        wordsList.add(new Word("bec", "bec.png", "e", -2));
        wordsList.add(new Word("peşte", "peste.png", "e", -2));
        //i
        wordsList.add(new Word("ied", "ied.png", "i", -2));
        wordsList.add(new Word("camion", "camion.png", "i", -2));
        wordsList.add(new Word("copii", "copii.png", "i", -2));
        //o
        wordsList.add(new Word("ou", "ou.png", "o", -2));
        wordsList.add(new Word("foc", "foc.png", "o", -2));
        wordsList.add(new Word("radio", "radio.png", "o", -2));
        //u
        wordsList.add(new Word("uşă", "usa.png", "u", -2));
        wordsList.add(new Word("cuţit", "cutit.png", "u", -2));
        wordsList.add(new Word("leu", "leu.png", "u", -2));
        //ă
        wordsList.add(new Word("măr", "mar.png", "ă", -2));
        wordsList.add(new Word("casă", "casa.png", "ă", -2));
        //î
        wordsList.add(new Word("îngheţată", "inghetata.png", "î", -2));
        wordsList.add(new Word("câine", "caine.png", "â", -2));
        //p
        wordsList.add(new Word("pat", "pat.png", "p", -2));
        wordsList.add(new Word("iepure", "iepure.png", "p", -2));
        wordsList.add(new Word("lup", "lup.png", "p", -2));
        //b
        wordsList.add(new Word("banană", "banana.png", "b", -2));
        wordsList.add(new Word("toba", "toba.png", "b", -2));
        wordsList.add(new Word("glob", "glob.png", "b", -2));
        //t
        wordsList.add(new Word("tren", "tren.png", "t", -2));
        wordsList.add(new Word("salată", "salata.png", "t", -2));
        wordsList.add(new Word("cot", "cot.png", "t", -2));
        //d
        wordsList.add(new Word("dulap", "dulap.png", "d", -2));
        wordsList.add(new Word("omidă", "omida.png", "d", -2));
        wordsList.add(new Word("brad", "brad.png", "d", -2));
        //c
        wordsList.add(new Word("cocoş", "cocos.png", "c", -2));
        wordsList.add(new Word("vaca", "vaca.png", "c", -2));
        wordsList.add(new Word("sac", "sac.png", "c", -2));
        //g
        wordsList.add(new Word("găină", "gaina.png", "g", -2));
        wordsList.add(new Word("măgar", "magar.png", "g", -2));
        wordsList.add(new Word("steag", "steag.png", "g", -2));
        //f
        wordsList.add(new Word("fluture", "fluture.png", "f", -2));
        wordsList.add(new Word("telefon", "telefon.png", "f", -2));
        wordsList.add(new Word("cartof", "cartof.png", "f", -2));
        //v
        wordsList.add(new Word("volan", "volan.png", "v", -2));
        wordsList.add(new Word("avion", "avion.png", "v", -2));
        wordsList.add(new Word("morcov", "morcov.png", "v", -2));
        //m
        wordsList.add(new Word("maimuţă", "maimuta.png", "m", -2));
        wordsList.add(new Word("cămaşă", "camasa.png", "m", -2));
        wordsList.add(new Word("pom", "pom.png", "m", -2));
        //n
        wordsList.add(new Word("nas", "nas.png", "n", -2));
        wordsList.add(new Word("cană", "cana.png", "n", -2));
        wordsList.add(new Word("pinguin", "pinguin.png", "n", -2));
        //h
        wordsList.add(new Word("halat", "halat.png", "h", -2));
        wordsList.add(new Word("pahar", "pahar.png", "h", -2));
        wordsList.add(new Word("şah", "sah.png", "h", -2));
        //s
        wordsList.add(new Word("sanie", "sanie.png", "s", -2));
        wordsList.add(new Word("pisică", "pisica.png", "s", -2));
        wordsList.add(new Word("urs", "urs.png", "s", -2));
        //z
        wordsList.add(new Word("zar", "zar.png", "z", -2));
        wordsList.add(new Word("barză", "barza.png", "z", -2));
        wordsList.add(new Word("autobuz", "autobuz.png", "z", -2));
        //ţ
        wordsList.add(new Word("ţap", "tap.png", "ţ", -2));
        wordsList.add(new Word("raţă", "rata.png", "ţ", -2));
        wordsList.add(new Word("lanţ", "lant.png", "ţ", -2));
        //ş
        wordsList.add(new Word("şoarece", "soarece.png", "ş", -2));
        wordsList.add(new Word("maşină", "masina.png", "ş", -2));
        wordsList.add(new Word("coş", "cos.png", "ş", -2));
        //j
        wordsList.add(new Word("jucării", "jucarii.png", "j", -2));
        wordsList.add(new Word("pijama", "pijama.png", "j", -2));
        wordsList.add(new Word("ruj", "ruj.png", "j", -2));
        //l
        wordsList.add(new Word("lac", "lac.png", "l", -2));
        wordsList.add(new Word("balon", "balon.png", "l", -2));
        wordsList.add(new Word("cal", "cal.png", "l", -2));
        //r
        wordsList.add(new Word("roşie", "rosie.png", "r", -2));
        wordsList.add(new Word("varză", "varza.png", "r", -2));
        wordsList.add(new Word("vapor", "vapor.png", "r", -2));
        //ce-ci
        wordsList.add(new Word("ceapă", "ceapa.png", "ce", -2));
        wordsList.add(new Word("căciulă", "caciula.png", "ci", -2));
        wordsList.add(new Word("arici", "arici.png", "ci", -2));
        //ge-gi
        wordsList.add(new Word("girafă", "girafa.png", "gi", -2));
        wordsList.add(new Word("mărgele", "margele.png", "ge", -2));
        wordsList.add(new Word("minge", "minge.png", "ge", -2));
    }

    public static List<Word> getWordsList() {
        return wordsList;
    }
}
