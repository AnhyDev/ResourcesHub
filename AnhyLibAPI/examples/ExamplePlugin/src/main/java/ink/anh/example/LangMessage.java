package ink.anh.example;

import ink.anh.api.lingo.lang.LanguageManager;

// Extends LanguageManager to manage language-specific messages.
public class LangMessage extends LanguageManager {

    // Singleton instance of this class.
    private static LangMessage instance = null;

    // Lock object for thread-safe singleton instantiation.
    private static final Object LOCK = new Object();

    // Private constructor to enforce the singleton pattern.
    private LangMessage(GlobalManager manager) {
        super(manager, "lang"); // Calls the constructor of the parent class.
    }

    // Static method to get the singleton instance of LangMessage.
    public static LangMessage getInstance(GlobalManager manager) {
        if (instance == null) {
            synchronized (LOCK) { // Ensures thread-safe instantiation.
                if (instance == null) {
                    instance = new LangMessage(manager);
                }
            }
        }
        return instance;
    }
}
