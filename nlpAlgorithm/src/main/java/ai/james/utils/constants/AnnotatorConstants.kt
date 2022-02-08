package ai.james.utils.constants

interface AnnotatorConstants {

    companion object {
        const val pos:          String  =   "tokenize,ssplit";

        const val lemma:        String  =   "tokenize,ssplit,pos,lemma";

        const val sentiment:    String  =   "tokenize,ssplit,pos,parse";

        const val ner:          String  =   "tokenize,ssplit,pos,lemma,ner,parse";

        const val parse:        String  =    "tokenize,ssplit,parse";

        const val depparse:     String  =    "tokenize,ssplit,pos";
    }

}