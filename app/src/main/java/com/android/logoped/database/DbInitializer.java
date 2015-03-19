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
        wordsList.add(new Word("<u>a</u>pă", "apa.png", "a", -2));
        wordsList.add(new Word("m<u>a</u>c", "mac.png", "a", -2));
        wordsList.add(new Word("fat<u>a</u>", "fata.png", "a", -2));
        //e
        wordsList.add(new Word("<u>e</u>lefant", "elefant.png", "e", -2));
        wordsList.add(new Word("b<u>e</u>c", "bec.png", "e", -2));
        wordsList.add(new Word("peşt<u>e</u>", "peste.png", "e", -2));
        //i
        wordsList.add(new Word("<u>i</u>ed", "ied.png", "i", -2));
        wordsList.add(new Word("cam<u>i</u>on", "camion.png", "i", -2));
        wordsList.add(new Word("copi<u>i</u>", "copii.png", "i", -2));
        //o
        wordsList.add(new Word("<u>o</u>u", "ou.png", "o", -2));
        wordsList.add(new Word("f<u>o</u>c", "foc.png", "o", -2));
        wordsList.add(new Word("radi<u>o</u>", "radio.png", "o", -2));
        //u
        wordsList.add(new Word("<u>u</u>şă", "usa.png", "u", -2));
        wordsList.add(new Word("c<u>u</u>ţit", "cutit.png", "u", -2));
        wordsList.add(new Word("le<u>u</u>", "leu.png", "u", -2));
        //ă
        wordsList.add(new Word("m<u>ă</u>r", "mar.png", "ă", -2));
        wordsList.add(new Word("cas<u>ă</u>", "casa.png", "ă", -2));
        //î
        wordsList.add(new Word("<u>î</u>ngheţată", "inghetata.png", "î", -2));
        wordsList.add(new Word("c<u>â</u>ine", "caine.png", "â", -2));
        //p
        wordsList.add(new Word("<u>p</u>at", "pat.png", "p", -2));
        wordsList.add(new Word("ie<u>p</u>ure", "iepure.png", "p", -2));
        wordsList.add(new Word("lu<u>p</u>", "lup.png", "p", -2));
        //b
        wordsList.add(new Word("<u>b</u>anană", "banana.png", "b", -2));
        wordsList.add(new Word("to<u>b</u>ă", "toba.png", "b", -2));
        wordsList.add(new Word("glo<u>b</u>", "glob.png", "b", -2));
        //t
        wordsList.add(new Word("<u>t</u>ren", "tren.png", "t", -2));
        wordsList.add(new Word("sala<u>t</u>ă", "salata.png", "t", -2));
        wordsList.add(new Word("co<u>t</u>", "cot.png", "t", -2));
        //d
        wordsList.add(new Word("<u>d</u>ulap", "dulap.png", "d", -2));
        wordsList.add(new Word("omi<u>d</u>ă", "omida.png", "d", -2));
        wordsList.add(new Word("bra<u>d</u>", "brad.png", "d", -2));
        //c
        wordsList.add(new Word("<u>c</u>ocoş", "cocos.png", "c", -2));
        wordsList.add(new Word("va<u>c</u>ă", "vaca.png", "c", -2));
        wordsList.add(new Word("sa<u>c</u>", "sac.png", "c", -2));
        //g
        wordsList.add(new Word("<u>g</u>ăină", "gaina.png", "g", -2));
        wordsList.add(new Word("mă<u>g</u>ar", "magar.png", "g", -2));
        wordsList.add(new Word("stea<u>g</u>", "steag.png", "g", -2));
        //f
        wordsList.add(new Word("<u>f</u>luture", "fluture.png", "f", -2));
        wordsList.add(new Word("tele<u>f</u>on", "telefon.png", "f", -2));
        wordsList.add(new Word("carto<u>f</u>", "cartof.png", "f", -2));
        //v
        wordsList.add(new Word("<u>v</u>olan", "volan.png", "v", -2));
        wordsList.add(new Word("a<u>v</u>ion", "avion.png", "v", -2));
        wordsList.add(new Word("morco<u>v</u>", "morcov.png", "v", -2));
        //m
        wordsList.add(new Word("<u>m</u>aimuţă", "maimuta.png", "m", -2));
        wordsList.add(new Word("că<u>m</u>aşă", "camasa.png", "m", -2));
        wordsList.add(new Word("po<u>m</u>", "pom.png", "m", -2));
        //n
        wordsList.add(new Word("<u>n</u>as", "nas.png", "n", -2));
        wordsList.add(new Word("ca<u>n</u>ă", "cana.png", "n", -2));
        wordsList.add(new Word("pingui<u>n</u>", "pinguin.png", "n", -2));
        //h
        wordsList.add(new Word("<u>h</u>alat", "halat.png", "h", -2));
        wordsList.add(new Word("pa<u>h</u>ar", "pahar.png", "h", -2));
        wordsList.add(new Word("şa<u>h</u>", "sah.png", "h", -2));
        //s
        wordsList.add(new Word("<u>s</u>anie", "sanie.png", "s", -2));
        wordsList.add(new Word("pi<u>s</u>ică", "pisica.png", "s", -2));
        wordsList.add(new Word("ur<u>s</u>", "urs.png", "s", -2));
        //z
        wordsList.add(new Word("<u>z</u>ar", "zar.png", "z", -2));
        wordsList.add(new Word("bar<u>z</u>ă", "barza.png", "z", -2));
        wordsList.add(new Word("autobu<u>z</u>", "autobuz.png", "z", -2));
        //ţ
        wordsList.add(new Word("<u>ţ</u>ap", "tap.png", "ţ", -2));
        wordsList.add(new Word("ra<u>ţ</u>ă", "rata.png", "ţ", -2));
        wordsList.add(new Word("lan<u>ţ</u>", "lant.png", "ţ", -2));
        //ş
        wordsList.add(new Word("<u>ş</u>oarece", "soarece.png", "ş", -2));
        wordsList.add(new Word("ma<u>ş</u>ină", "masina.png", "ş", -2));
        wordsList.add(new Word("co<u>ş</u>", "cos.png", "ş", -2));
        //j
        wordsList.add(new Word("<u>j</u>ucării", "jucarii.png", "j", -2));
        wordsList.add(new Word("pi<u>j</u>ama", "pijama.png", "j", -2));
        wordsList.add(new Word("ru<u>j</u>", "ruj.png", "j", -2));
        //l
        wordsList.add(new Word("<u>l</u>ac", "lac.png", "l", -2));
        wordsList.add(new Word("ba<u>l</u>on", "balon.png", "l", -2));
        wordsList.add(new Word("ca<u>l</u>", "cal.png", "l", -2));
        //r
        wordsList.add(new Word("<u>r</u>oşie", "rosie.png", "r", -2));
        wordsList.add(new Word("va<u>r</u>ză", "varza.png", "r", -2));
        wordsList.add(new Word("vapo<u>r</u>", "vapor.png", "r", -2));
        //ce-ci
        wordsList.add(new Word("<u>ce</u>apă", "ceapa.png", "ce", -2));
        wordsList.add(new Word("că<u>ci</u>ulă", "caciula.png", "ci", -2));
        wordsList.add(new Word("ari<u>ci</u>", "arici.png", "ci", -2));
        //ge-gi
        wordsList.add(new Word("<u>gi</u>rafă", "girafa.png", "gi", -2));
        wordsList.add(new Word("măr<u>ge</u>le", "margele.png", "ge", -2));
        wordsList.add(new Word("min<u>ge</u>", "minge.png", "ge", -2));
    }

    public static List<Word> getWordsList() {
        return wordsList;
    }
}
