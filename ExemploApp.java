import java.util.ArrayList;
import java.util.List;

// Sujeito/Publicador (objeto que emite o evento - Loja)
class App {
    
    //Criando o vetor de assinantes
    private List<Assinante> assinantes = new ArrayList<>();
    private boolean calibragemDisponivel = false;
    
    //Adicionar assinante
    public void addObserver(Assinante assinante) {
        assinantes.add(assinante);
    }
    //Remover interessados
    public void removeObserver(Assinante assinante) {
        assinantes.remove(assinante);
    }
    
    //Status da calibragem
    public void setCalibragemDisponivel(boolean disponivel) {
        this.calibragemDisponivel = disponivel;
        notificarAssinantes();
    }
    
    //Notificar
    private void notificarAssinantes() {
        for (Assinante assinante : assinantes) {
            assinante.update(calibragemDisponivel);
        }
    }
}

// Observer (objeto que recebe a notificação - Cliente)
interface Assinante {
    void update(boolean calibragemDisponivel);
}

// Implementação
class Cliente implements Assinante {
    private String nome;
    private String veiculo;

    public Cliente(String nome, String veiculo) {
        this.nome = nome;
        this.veiculo = veiculo;
    }

    @Override
    public void update(boolean calibragemDisponivel) {
        if (calibragemDisponivel) {
            System.out.println(nome + ": " + veiculo + " precisa calibrar os pneus!");
            // Lógica para o cliente reagir à disponibilidade da calibragem
        } else {
            System.out.println(nome + ": Ainda aguardando a calibragem dos pneus ser necessária.");
        }
    }
}

// Uso do padrão Observer
public class ExemploApp {
    public static void main(String[] args) {
        App app = new App();

        // Criando clientes
        Cliente cliente1 = new Cliente("Cliente1", "Chevrolet");
        Cliente cliente2 = new Cliente("Cliente2", "Honda");
        Cliente cliente3 = new Cliente("Cliente2", "Nissan");

        // Adicionando clientes 1 e 2 como observadores
        app.addObserver(cliente1);
        app.addObserver(cliente2);
        
        //Apenas os clientes 1 e 2 irão acompanhar o estado da calibragem
        // Simulação: calibragem dos pneus não necessária
        app.setCalibragemDisponivel(false);

        // Simulação: calibragem dos pneus se torna necessária
        app.setCalibragemDisponivel(true);
    }
}
