package model;

public class SoundController {

	// Instancia unica (eager ou lazy) - foi usado lazy initialization com double-checked locking
    private static volatile SoundController instance;

    private int volume;
    private boolean muted;
    private String outputDevice;

    private SoundController() {
        this.volume = 50;
        this.muted = false;
        this.outputDevice = "Padrão (Default)";
    }

    // Metodo de acesso publico com thread-safety (double-checked locking)
    public static SoundController getInstance() {
        if (instance == null) {
            synchronized (SoundController.class) {
                if (instance == null) {
                    instance = new SoundController();
                }
            }
        }
        return instance;
    }

    public void setVolume(int volume) {
        if (volume < 0) volume = 0;
        if (volume > 100) volume = 100;
        this.volume = volume;

        if (this.volume > 0) this.muted = false;

        System.out.println("Volume ajustado para: " + this.volume);
    }

    public int getVolume() {
        return this.volume;
    }

    public void mute() {
        this.muted = true;
        System.out.println("Som silenciado.");
    }

    public void unmute() {
        this.muted = false;
        System.out.println("Som desmutado.");
    }

    public boolean isMuted() {
        return this.muted;
    }

    public void setOutputDevice(String device) {
        if (device == null || device.trim().isEmpty()) return;
        this.outputDevice = device;

        System.out.println("Dispositivo de saida alterado para: " + this.outputDevice);
    }

    public String getOutputDevice() {
        return this.outputDevice;
    }
}
