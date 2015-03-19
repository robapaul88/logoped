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
        wordsList.add(new Word("<u>a</u>pă", "apa.png", "apa", "a", -2));
        wordsList.add(new Word("m<u>a</u>c", "mac.png", "mac", "a", -2));
        wordsList.add(new Word("fat<u>a</u>", "fata.png", "fata", "a", -2));
        //e
        wordsList.add(new Word("<u>e</u>lefant", "elefant.png", "elefant", "e", -2));
        wordsList.add(new Word("b<u>e</u>c", "bec.png", "bec", "e", -2));
        wordsList.add(new Word("peşt<u>e</u>", "peste.png", "peste", "e", -2));
        //i
        wordsList.add(new Word("<u>i</u>ed", "ied.png", "ied", "i", -2));
        wordsList.add(new Word("cam<u>i</u>on", "camion.png", "camion", "i", -2));
        wordsList.add(new Word("copi<u>i</u>", "copii.png", "copii", "i", -2));
        //o
        wordsList.add(new Word("<u>o</u>u", "ou.png", "ou", "o", -2));
        wordsList.add(new Word("f<u>o</u>c", "foc.png", "foc", "o", -2));
        wordsList.add(new Word("radi<u>o</u>", "radio.png", "radio", "o", -2));
        //u
        wordsList.add(new Word("<u>u</u>şă", "usa.png", "usa", "u", -2));
        wordsList.add(new Word("c<u>u</u>ţit", "cutit.png", "cutit", "u", -2));
        wordsList.add(new Word("le<u>u</u>", "leu.png", "leu", "u", -2));
        //ă
        wordsList.add(new Word("m<u>ă</u>r", "mar.png", "mar", "ă", -2));
        wordsList.add(new Word("cas<u>ă</u>", "casa.png", "casa", "ă", -2));
        //î
        wordsList.add(new Word("<u>î</u>ngheţată", "inghetata.png", "inghetata", "î", -2));
        wordsList.add(new Word("c<u>â</u>ine", "caine.png", "caine", "â", -2));
        //p
        wordsList.add(new Word("<u>p</u>at", "pat.png", "pat", "p", -2));
        wordsList.add(new Word("ie<u>p</u>ure", "iepure.png", "iepure", "p", -2));
        wordsList.add(new Word("lu<u>p</u>", "lup.png", "lup", "p", -2));
        //b
        wordsList.add(new Word("<u>b</u>anană", "banana.png", "banana", "b", -2));
        wordsList.add(new Word("to<u>b</u>ă", "toba.png", "toba", "b", -2));
        wordsList.add(new Word("glo<u>b</u>", "glob.png", "glob", "b", -2));
        //t
        wordsList.add(new Word("<u>t</u>ren", "tren.png", "tren", "t", -2));
        wordsList.add(new Word("sala<u>t</u>ă", "salata.png", "salata", "t", -2));
        wordsList.add(new Word("co<u>t</u>", "cot.png", "cot", "t", -2));
        //d
        wordsList.add(new Word("<u>d</u>ulap", "dulap.png", "dulap", "d", -2));
        wordsList.add(new Word("omi<u>d</u>ă", "omida.png", "omida", "d", -2));
        wordsList.add(new Word("bra<u>d</u>", "brad.png", "brad", "d", -2));
        //c
        wordsList.add(new Word("<u>c</u>ocoş", "cocos.png", "cocos", "c", -2));
        wordsList.add(new Word("va<u>c</u>ă", "vaca.png", "vaca", "c", -2));
        wordsList.add(new Word("sa<u>c</u>", "sac.png", "sac", "c", -2));
        //g
        wordsList.add(new Word("<u>g</u>ăină", "gaina.png", "gaina", "g", -2));
        wordsList.add(new Word("mă<u>g</u>ar", "magar.png", "magar", "g", -2));
        wordsList.add(new Word("stea<u>g</u>", "steag.png", "steag", "g", -2));
        //f
        wordsList.add(new Word("<u>f</u>luture", "fluture.png", "fluture", "f", -2));
        wordsList.add(new Word("tele<u>f</u>on", "telefon.png", "telefon", "f", -2));
        wordsList.add(new Word("carto<u>f</u>", "cartof.png", "cartof", "f", -2));
        //v
        wordsList.add(new Word("<u>v</u>olan", "volan.png", "volan", "v", -2));
        wordsList.add(new Word("a<u>v</u>ion", "avion.png", "avion", "v", -2));
        wordsList.add(new Word("morco<u>v</u>", "morcov.png", "morcov", "v", -2));
        //m
        wordsList.add(new Word("<u>m</u>aimuţă", "maimuta.png", "maimuta", "m", -2));
        wordsList.add(new Word("că<u>m</u>aşă", "camasa.png", "camasa", "m", -2));
        wordsList.add(new Word("po<u>m</u>", "pom.png", "pom", "m", -2));
        //n
        wordsList.add(new Word("<u>n</u>as", "nas.png", "nas", "n", -2));
        wordsList.add(new Word("ca<u>n</u>ă", "cana.png", "cana", "n", -2));
        wordsList.add(new Word("pingui<u>n</u>", "pinguin.png", "pinguin", "n", -2));
        //h
        wordsList.add(new Word("<u>h</u>alat", "halat.png", "halat", "h", -2));
        wordsList.add(new Word("pa<u>h</u>ar", "pahar.png", "pahar", "h", -2));
        wordsList.add(new Word("şa<u>h</u>", "sah.png", "sah", "h", -2));
        //s
        wordsList.add(new Word("<u>s</u>anie", "sanie.png", "sanie", "s", -2));
        wordsList.add(new Word("pi<u>s</u>ică", "pisica.png", "pisica", "s", -2));
        wordsList.add(new Word("ur<u>s</u>", "urs.png", "urs", "s", -2));
        //z
        wordsList.add(new Word("<u>z</u>ar", "zar.png", "zar", "z", -2));
        wordsList.add(new Word("bar<u>z</u>ă", "barza.png", "barza", "z", -2));
        wordsList.add(new Word("autobu<u>z</u>", "autobuz.png", "autobuz", "z", -2));
        //ţ
        wordsList.add(new Word("<u>ţ</u>ap", "tap.png", "tap", "ţ", -2));
        wordsList.add(new Word("ra<u>ţ</u>ă", "rata.png", "rata", "ţ", -2));
        wordsList.add(new Word("lan<u>ţ</u>", "lant.png", "lant", "ţ", -2));
        //ş
        wordsList.add(new Word("<u>ş</u>oarece", "soarece.png", "soarece", "ş", -2));
        wordsList.add(new Word("ma<u>ş</u>ină", "masina.png", "masina", "ş", -2));
        wordsList.add(new Word("co<u>ş</u>", "cos.png", "cos", "ş", -2));
        //j
        wordsList.add(new Word("<u>j</u>ucării", "jucarii.png", "jucarii", "j", -2));
        wordsList.add(new Word("pi<u>j</u>ama", "pijama.png", "pijama", "j", -2));
        wordsList.add(new Word("ru<u>j</u>", "ruj.png", "ruj", "j", -2));
        //l
        wordsList.add(new Word("<u>l</u>ac", "lac.png", "lac", "l", -2));
        wordsList.add(new Word("ba<u>l</u>on", "balon.png", "balon", "l", -2));
        wordsList.add(new Word("ca<u>l</u>", "cal.png", "cal", "l", -2));
        //r
        wordsList.add(new Word("<u>r</u>oşie", "rosie.png", "rosie", "r", -2));
        wordsList.add(new Word("va<u>r</u>ză", "varza.png", "varza", "r", -2));
        wordsList.add(new Word("vapo<u>r</u>", "vapor.png", "vapor", "r", -2));
        //ce-ci
        wordsList.add(new Word("<u>ce</u>apă", "ceapa.png", "ceapa", "ce", -2));
        wordsList.add(new Word("că<u>ci</u>ulă", "caciula.png", "caciula", "ci", -2));
        wordsList.add(new Word("ari<u>ci</u>", "arici.png", "arici", "ci", -2));
        //ge-gi
        wordsList.add(new Word("<u>gi</u>rafă", "girafa.png", "girafa", "gi", -2));
        wordsList.add(new Word("măr<u>ge</u>le", "margele.png", "margele", "ge", -2));
        wordsList.add(new Word("min<u>ge</u>", "minge.png", "minge", "ge", -2));
    }

    public static List<Word> getWordsList() {
        return wordsList;
    }
}
