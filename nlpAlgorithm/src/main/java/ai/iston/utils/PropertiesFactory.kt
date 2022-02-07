package ai.iston.utils

import java.util.*

class PropertiesFactory {

    var key     : String? = null;
    var value   : String? = null;

    companion object{
        fun setAnnotator(key: String, value: String): Properties {
            val properties = Properties();
            properties.setProperty(key, value);
            return properties;
        }
    }
}