package ir.geek.parvaneh;

public final class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", "behdad.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "behdad.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "behdad.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "behdad.ttf");
    }
}