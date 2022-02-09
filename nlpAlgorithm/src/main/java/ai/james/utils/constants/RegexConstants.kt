package ai.james.utils.constants

interface RegexConstants {

    companion object {

        const val empty: String         =   " ";

        const val WhSymbol: String      =   "Wh";

        const val howSymbol: String     =   "how";

        const val todoSymbol: String    =   "to do";

        const val tobeSymbol: String    =   "to be";
        // while 没有   AUX:系动词
        val whSymbol                    =   listOf("what","where","when","why","who","whom","which","whether")
    }
}