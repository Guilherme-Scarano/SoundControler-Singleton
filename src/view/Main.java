package view;

import model.SoundController;

public class Main {
    public static void main(String[] args) {

        SoundController sc1 = SoundController.getInstance();
        sc1.setVolume(30);
        sc1.setOutputDevice("Alto-falantes USB");

        SoundController sc2 = SoundController.getInstance();

        System.out.println("Volume via sc2: " + sc2.getVolume());
        System.out.println("Device via sc2: " + sc2.getOutputDevice());

        System.out.println("sc1 == sc2? " + (sc1 == sc2));

        sc2.mute();
        System.out.println("sc1.isMuted(): " + sc1.isMuted());
    }
}