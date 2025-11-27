# 🎧 Controlador de Som — Padrão Singleton em Java

Este projeto demonstra a implementação do padrão de projeto **Singleton** aplicado a um **Controlador de Sistema de Som**, garantindo que apenas uma instância do controlador seja criada durante toda a execução do aplicativo.

---

## <span style="color:#00BFFF;">💡 Por que usar Singleton?</span>

O padrão Singleton é útil quando:

- 🔹 Deve existir **apenas um objeto** de uma classe.
- 🔹 Há a necessidade de **controle global** sobre esse objeto.
- 🔹 Instanciar várias vezes poderia **causar problemas**, como configurações duplicadas.

No caso de um controlador de som:
- Não faz sentido existir **dois volumes diferentes** ao mesmo tempo.
- A configuração deve ser **global no sistema**.

---

## <span style="color:#32CD32;">🎯 Objetivo do Projeto</span>

Criar um controlador de som que:

- Ajusta o volume (0 a 100)
- Exibe o volume atual
- Garante que sempre exista **a mesma instância**, independente da quantidade de chamadas

---

## <span style="color:#9370DB;">📌 Diagrama UML</span>

<img width="480" height="814" alt="Image" src="https://github.com/user-attachments/assets/0bd5ea49-b943-4ea5-a19f-70680813ab26" />

## <span style="color:#FF8C00;">📁 Estrutura do Projeto</span>

<pre>
sound-controller-singleton/
├── README.md
└── src
    ├── model
    │   └── SoundController.java
    └── view
        └── Main.java
</pre>


## <span style="color:#9370DB;">4. 💻 Implementação em Java</span>

📌 SoundController.java — pacote model

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


📌 Main.java — pacote view

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


🔎 Detalhes Técnicos Importantes

Instância Única (Singleton): neste projeto foi utilizada a estratégia Lazy Initialization, onde a instância só é criada no primeiro uso.

Thread-safety com Double-Checked Locking: o método getInstance() utiliza double-checked locking, garantindo segurança em ambientes multithread sem perda de desempenho.

Esses conceitos são fundamentais para assegurar que somente uma instância do controlador exista durante toda a execução do sistema.


<span style="color:#DC143C;">✔️ Conclusão</span>

Este projeto demonstra claramente que:

• Há apenas uma única instância do controlador.

• A alteração feita em qualquer parte do sistema impacta todos que usam o Singleton.

• O padrão evita inconsistências e garante um estado centralizado.
