package Problems.Thanos;

import Problems.Problema;

import java.util.ArrayList;

public class ThanosNode implements Problema {

    Char thanos, thanosFinal;
    ArrayList<Char> chars;
    int rodada;
    int ultimoAtacante;
    ArrayList<String> roteiro;

    ThanosNode pai;

    public ThanosNode(){//********** NO THANOSNODE INICIAL GERAR UM ULTIMO ATACANTE ALEATORIO ************

        thanosFinal = new Char("ThanosFinal", 0, 0, null, 0);
        roteiro = new ArrayList<>();
    }

    public ArrayList<Problema> gerarSucessores() {

        ArrayList<Problema> filhos = new ArrayList<>();
        ThanosNode aux;

        ataque(thanos, chars.get(ultimoAtacante));
        if(chars.get(ultimoAtacante).vida < 1)
            chars.remove(ultimoAtacante);

        for(int x = 0; x < chars.size(); x++){

            aux = (ThanosNode)this.criaFilho();

            ataque(aux.chars.get(x), aux.thanos);
            if(aux.thanos.vida < 0)
                aux.thanos.vida = 0;

            aux.ultimoAtacante = x;

            filhos.add(aux);
        }

        return filhos;
    }

    public void ataque(Char atacante, Char defensor){

        Skill skill;
        for(int x = 0; x < atacante.skills.size(); x++){

            skill = atacante.skills.get(x);
            if(skill.indicaTurno < rodada){

                defensor.vida -= skill.dano;
                skill.indicaTurno += skill.cooldown;
                roteiro.add(atacante.nome + " atacou " + defensor.nome + " utilizando " + skill.nome + ".");
                return;
            }
        }

        defensor.vida -= atacante.danoBase;
        roteiro.add(atacante.nome + " atacou " + defensor.nome + " utilizando seu golpe padrao.");
    }

    @Override
    public boolean equals(Problema problema) {//este equals compara a vida do thanos

        ThanosNode thanosNode = (ThanosNode) problema;

        if(thanosNode.thanos.vida == this.thanos.vida)
            return true;

        return false;
    }

    public Problema getFinal() {

        ThanosNode thanosNode = new ThanosNode();
        thanosNode.thanos = thanosFinal;

        return thanosNode;
    }

    public Problema criaFilho() {

        ArrayList<Char> chars0 = new ArrayList<>();
        for(Char c : chars)
            chars0.add(c.copy());

        ThanosNode thanosNode = new ThanosNode();
        thanosNode.thanos = this.thanos.copy();
        thanosNode.pai = this;
        thanosNode.thanosFinal = this.thanosFinal;
        thanosNode.chars = chars0;
        thanosNode.ultimoAtacante = this.ultimoAtacante;
        thanosNode.rodada = this.rodada++;
        thanosNode.roteiro = this.roteiro;

        return thanosNode;
    }

    public void printa() {

        //************* VER COMO VOU FAZER PRA PRINTAR ********************** (se pa vou ter q mudar como funfa o roteiro)
    }

    public Problema getPai() {

        return pai;
    }

    public Object[] base() {

        return null;
    }

    public Object[] alvo() {

        return null;
    }

    public int totalPossibilidades() {
        return 0;
    }

    public int pesoHeuristica() {

        if(chars.isEmpty())
            return Integer.MAX_VALUE;


        int total = 0;

        total = total + rodada;//somo a rodada

        for(Char c : chars){

            total -= c.peso;//diminuo o peso dos personagens vivos
        }

        total = total + (thanos.vida / 10);//somo a vida do thanos / 100

        return total;
    }

    public int tipo() {

        return 0;
    }

    public void geraInicial() {

        ArrayList<Char> todos = new ArrayList<>();

        ArrayList<Skill> skills0 = new ArrayList<>();
        skills0.add(new Skill("Raio com a Joia do Poder", 200, 12, 12));
        skills0.add(new Skill("Raio com todas as Joias", 500, 50, 50));
        skills0.add(new Skill("Golpe com a Espada do Infinito", 70, 5, 5));

        Char char0 = new Char("Thanos", 40000, 30, skills0, 100);
        thanos = char0;

        ArrayList<Skill> skills1 = new ArrayList<>();
        skills1.add(new Skill("Flecha explosiva", 50, 8, 8));
        skills1.add(new Skill("Flecha superexplosiva", 80, 20, 20));
        skills1.add(new Skill("Felcha tripla", 45, 5, 5));
        skills1.add(new Skill("Felcha explosiva tripla", 150, 32, 32));
        todos.add(new Char("HawkEye", 900, 15, skills1, 45));

        ArrayList<Skill> skills2 = new ArrayList<>();
        skills2.add(new Skill("Canhao de fotons", 200, 13, 13));
        skills2.add(new Skill("Espada de nanorobos", 70, 3, 3));
        skills2.add(new Skill("Misseis explosivos", 130, 20, 20));
        skills2.add(new Skill("Martelo de nanorono", 80, 10, 10));
        todos.add(new Char("IronMan", 7000, 22, skills2, 70));

        ArrayList<Skill> skills3 = new ArrayList<>();
        skills3.add(new Skill("Lancamento de escudo", 20, 4, 4));
        skills3.add(new Skill("Golpe com escudo", 40, 3, 3));
        todos.add(new Char("CaptainAmerica", 3000, 10, skills3, 27));

        ArrayList<Skill> skills4 = new ArrayList<>();
        skills4.add(new Skill("Soco com salto", 110, 10, 10));
        skills4.add(new Skill("HULK ESMAGA", 300, 50, 50));
        skills4.add(new Skill("Corrida com soco", 90, 6, 6));
        skills4.add(new Skill("Sequencia agressiva", 150, 16, 16));
        todos.add(new Char("Hulk", 13000, 70, skills4, 95));

        ArrayList<Skill> skills5 = new ArrayList<>();
        skills5.add(new Skill("Arremesso rompedor de tormentas", 500, 35, 35));
        skills5.add(new Skill("Salto com trovao", 150, 5, 5));
        skills5.add(new Skill("O Deus to Trovao", 300, 40, 40));
        todos.add(new Char("Thor", 10000, 80, skills5, 100));

        /*ArrayList<Skill> skills6 = new ArrayList<>();
        todos.add(new Char("BlackWidow"));

        ArrayList<Skill> skills7 = new ArrayList<>();
        todos.add(new Char("DrStrange"));

        ArrayList<Skill> skills8 = new ArrayList<>();
        todos.add(new Char("BlackPanther"));

        ArrayList<Skill> skills9 = new ArrayList<>();
        todos.add(new Char("SpiderMan"));

        ArrayList<Skill> skills10 = new ArrayList<>();
        todos.add(new Char("AntMan"));

        ArrayList<Skill> skills11 = new ArrayList<>();
        todos.add(new Char("WarMachine"));

        ArrayList<Skill> skills12 = new ArrayList<>();
        todos.add(new Char("Falcon"));

        ArrayList<Skill> skills13 = new ArrayList<>();
        todos.add(new Char("Vision"));

        ArrayList<Skill> skills14 = new ArrayList<>();
        todos.add(new Char("ScarletWitch"));

        ArrayList<Skill> skills15 = new ArrayList<>();
        todos.add(new Char("WinterSoldier"));

        ArrayList<Skill> skills16 = new ArrayList<>();
        todos.add(new Char("Loki"));

        ArrayList<Skill> skills17 = new ArrayList<>();
        todos.add(new Char("StarLord"));

        ArrayList<Skill> skills18 = new ArrayList<>();
        todos.add(new Char("Gamora"));

        ArrayList<Skill> skills19 = new ArrayList<>();
        todos.add(new Char("Drax"));

        ArrayList<Skill> skills20 = new ArrayList<>();
        todos.add(new Char("RocketRacoon"));

        ArrayList<Skill> skills21 = new ArrayList<>();
        todos.add(new Char("Groot"));

        ArrayList<Skill> skills22 = new ArrayList<>();
        todos.add(new Char("Nebula"));

        ArrayList<Skill> skills23 = new ArrayList<>();
        todos.add(new Char("Heimdall"));

        ArrayList<Skill> skills24 = new ArrayList<>();
        todos.add(new Char("Shuri"));

        ArrayList<Skill> skills25 = new ArrayList<>();
        todos.add(new Char("Okoye"));

        ArrayList<Skill> skills26 = new ArrayList<>();
        todos.add(new Char("MBaku"));

        ArrayList<Skill> skills27 = new ArrayList<>();
        todos.add(new Char("Wong"));

        ArrayList<Skill> skills28 = new ArrayList<>();
        todos.add(new Char("Mantis"));*/

        chars.addAll(todos);//mudar isso para pegar aleatorios

    }
}
