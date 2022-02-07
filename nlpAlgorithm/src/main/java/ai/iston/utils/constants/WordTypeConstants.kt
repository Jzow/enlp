package ai.iston.utils.constants

interface WordTypeConstants {

    companion object {
        val NNWord          = listOf("NN","NNS","NNP","NNPS")

        val VVWord          = listOf("VB","VBD","VBG","VBN","VBP","VBZ")

        val ADJWod          = listOf("JJ","JJR","JJS")

        val ADVWord         = listOf("RB","RBR","RBS")

        const val NOUNType  = "NOUN";

        const val VERBType  = "VERB";

        const val ADJType   = "ADJ";

        const val ADVType   = "ADV";
    }
}