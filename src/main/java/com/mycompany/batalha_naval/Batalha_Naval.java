package com.mycompany.batalha_naval;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Batalha_Naval {
    static Scanner ms = new Scanner(System.in);
    static Scanner ms2 = new Scanner(System.in);
    static Random rn = new Random();
        
    static Object[][] batalha = new Object[10][10];
    static String[] letras = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    static int[] numeros = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    static String[] navios = new String[6];
    static String[] navios2x1 = new String[2];
    static String[] navio2x1 = new String[2];
    static String coord = "";
    static int contNavio1;
    static int contNavio2;
    static int tentativas;
    static int pontuacao;
    static boolean continua;
    static String resposta = "";
    static int[] pontos = new int [100];
    static int partidas = 0;
    static double media = 0;
    
    public static void main(String[] args) {   
        for (int i = 0; i < 50; i++) {
            if(coord.equals("0")){
                break;
            }else if(contNavio1 == 6 && contNavio2 == 4){
                break;
            }else if(tentativas == 100){
                break;
            }else{
                partida();
            }
        }
        
        for (int i = 0; i < 50; i++) {
            System.out.println("Deseja jogar outra partida? (Sim - Nao)");
            resposta = ms2.nextLine().toLowerCase();
            
            if(resposta.equals("sim")){
                partida();
            }else{
                break;
            }
        }
        
    }
    
    public static void impressao(){
        System.out.println("               ---BATALHA NAVAL---");
        System.out.println("     1"+"    2"+"    3"+"    4"+"    5"+"     6"+"    7"+"    8"+"    9"+"   10");
        System.out.println("_______________________________________________________");
        for (int i = 0; i < numeros.length; i++) {
            System.out.print(letras[i] + "  ");
            for (int j = 0; j < letras.length; j++) {
                batalha[i][j] = letras[i]+numeros[j];
                System.out.print("|"+" "+batalha[i][j]+" ");
            }
            System.out.print("|\n");
        }
    }
    
    private static boolean posicaoRepetida(String[] navios, String posicao) {
        for (String navio : navios) {
            if (navio != null && navio.equals(posicao)) {
                return true;
            }
        }
        return false;
    }

    
    public static void gerarNavios(){
           for (int i = 0; i < navio2x1.length; i++) {
            int linha = rn.nextInt(10);
            int coluna = rn.nextInt(9); 

            navio2x1[0] = letras[linha] + numeros[coluna];
            navios2x1[0] = letras[linha] + numeros[coluna + 1];
        }

        for (int i = 0; i < navio2x1.length; i++) {
            int linha = rn.nextInt(10);
            int coluna = rn.nextInt(9); 
            if (!Arrays.equals(navio2x1, navios2x1)) {
                navio2x1[1] = letras[linha] + numeros[coluna];
                navios2x1[1] = letras[linha] + numeros[coluna + 1];
            } 
        }
        for (int i = 0; i < navios.length; i++) {
            int linha = 0;
            int coluna = 0;
            boolean posicaoValida = false;

            for (int o = 0; o < 100; o++) {
                if(!posicaoValida) {
                linha = rn.nextInt(10);
                coluna = rn.nextInt(10);
                posicaoValida = true;

                // Verifica se a posição já está ocupada por outro navio
                for (int j = 0; j < i; j++) {
                    if (navios[j] != null && navios[j].equals(letras[linha] + numeros[coluna])) {
                        posicaoValida = false;
                        break;
                    }
                }

                // Verifica se a posição está ocupada pelo navio 2x1
                if (posicaoValida && (navios[i] != null && (navios[i].equals(navio2x1[0]) || navios[i].equals(navio2x1[1])|| navios[i].equals(navios2x1[0]) || navios[i].equals(navios2x1[1])))) {
                    posicaoValida = false;
                }

                // Verifica se há sobreposição com outros navios
                if (posicaoValida) {
                    int tamanhoNavio = i + 2; // Tamanho do navio atual (2x1, 3x1, 4x1, etc.)
                    int linhaFinal = linha + tamanhoNavio - 1;

                    if (linhaFinal >= 10) {
                        posicaoValida = false; // Navio está saindo da grade
                    } else {
                        for (int k = linha; k <= linhaFinal; k++) {
                            String posicao = letras[k] + numeros[coluna];
                            if (posicaoRepetida(navios, posicao)) {
                                posicaoValida = false; // Sobreposição com outro navio
                                break;
                            }
                        }
                    }
                }
            }

                navios[i] = letras[linha] + numeros[coluna];
            }
        }
        
//        for(String navio : navio2x1){
//            System.out.print(navio);
//        }
//        for(String navio2 : navios2x1){
//            System.out.print(navio2);
//        }
//        System.out.println(" ");
//        for(String navios1 : navios) {
//            System.out.println(navios1);
//        }
    }

    
    public static void jogadas(){
        System.out.println("\nInforme uma letra (A, B, C, D, E, F, G, H, I, J) e um numero (1 a 10), Exemplo: A2\nCaso queira sair do jogo digite 0");
        coord = ms.nextLine().toUpperCase();
        
        for (int k = 0; k < 100; k++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if(coord.equals(batalha[i][j]) && coord.equals(navios[0])){
                        contNavio1++;
                        batalha[i][j] = " X";
                        System.out.print("\nAfundou\n\n");
                        imprimirBatalha();
                    }else if(coord.equals(batalha[i][j]) && coord.equals(navios[1])){
                        contNavio1++;
                        batalha[i][j] = " X";
                        System.out.print("\nAfundou\n\n");
                        imprimirBatalha();
                    }else if(coord.equals(batalha[i][j]) && coord.equals(navios[2])){
                        contNavio1++;
                        batalha[i][j] = " X";
                        System.out.print("\nAfundou\n\n");
                        imprimirBatalha();
                    }else if(coord.equals(batalha[i][j]) && coord.equals(navios[3])){
                        contNavio1++;
                        batalha[i][j] = " X";
                        System.out.print("\nAfundou\n\n");
                        imprimirBatalha();
                    }else if(coord.equals(batalha[i][j]) && coord.equals(navios[4])){
                        contNavio1++;
                        batalha[i][j] = " X";
                        System.out.print("\nAfundou\n\n");
                        imprimirBatalha();
                    }else if(coord.equals(batalha[i][j]) && coord.equals(navios[5])){
                        contNavio1++;
                        batalha[i][j] = " X";
                        System.out.print("\nAfundou\n\n");
                        imprimirBatalha();
                    }else if(coord.equals(batalha[i][j]) && coord.equals(navio2x1[0])){
                        contNavio2++;
                        batalha[i][j] = " X";
                        System.out.print("\nAcertou návio 2x1\n\n");
                        imprimirBatalha();
                    }else if(coord.equals(batalha[i][j]) && coord.equals(navio2x1[1])){
                        contNavio2++;
                        batalha[i][j] = " X";
                        System.out.print("\nAcertou návio 2x1\n\n");
                        imprimirBatalha();
                    }else if(coord.equals(batalha[i][j]) && coord.equals(navios2x1[0])){
                        contNavio2++;
                        batalha[i][j] = " X";
                        System.out.print("\nAcertou návio 2x1\n\n");
                        imprimirBatalha();
                    }else if(coord.equals(batalha[i][j]) && coord.equals(navios2x1[1])){
                        contNavio2++;
                        batalha[i][j] = " X";
                        System.out.print("\nAcertou návio 2x1\n\n");
                        imprimirBatalha();
                    }else if((coord.equals(batalha[i][j]))!= (coord.equals(batalha[i][j]) && coord.equals(navios[0])) ||coord.equals(batalha[i][j]) && coord.equals(navios[1]) || coord.equals(batalha[i][j]) && coord.equals(navios[2])
                            || coord.equals(batalha[i][j]) && coord.equals(navios[3]) || coord.equals(batalha[i][j]) && coord.equals(navios[4]) ||coord.equals(batalha[i][j]) && coord.equals(navios[5])
                            || coord.equals(batalha[i][j]) && coord.equals(navio2x1[0]) ||coord.equals(batalha[i][j]) && coord.equals(navio2x1[1]) || coord.equals(batalha[i][j]) && coord.equals(navios2x1[0]) ||coord.equals(batalha[i][j]) && coord.equals(navios2x1[1])){
                        batalha[i][j] = " O";
                        imprimirBatalha();
                    }
                }
            }
        }
        
    }
    
    public static void pontuacao(){
        
        if(tentativas >= 90 && tentativas <= 100){
            System.out.println("Sua pontuacao foi de 10");
            pontuacao = 10;
        }else if(tentativas >= 80 && tentativas < 90){
            System.out.println("Sua pontuacao foi de 20");
            pontuacao = 20;
        }else if(tentativas >= 70 && tentativas < 80){
            System.out.println("Sua pontuacao foi de 30");
            pontuacao = 30;
        }else if(tentativas >= 60 && tentativas < 70){
            System.out.println("Sua pontuacao foi de 40");
            pontuacao = 40;
        }else if(tentativas >= 50 && tentativas < 60){
            System.out.println("Sua pontuacao foi de 50");
            pontuacao = 50;
        }else if(tentativas >= 40 && tentativas < 50){
            System.out.println("Sua pontuacao foi de 60");
            pontuacao = 60;
        }else if(tentativas >= 30 && tentativas < 40){
            System.out.println("Sua pontuacao foi de 70");
            pontuacao = 70;
        }else if(tentativas >= 20 && tentativas < 30){
            System.out.println("Sua pontuacao foi de 80");
            pontuacao = 80;
        }else if(tentativas >= 10 && tentativas < 20){
            System.out.println("Sua pontuacao foi de 90");
            pontuacao = 90;
        }else if(tentativas == 10){
            System.out.println("Sua pontuacao foi de 100");
            pontuacao = 100;
        }else if(tentativas < 10){
            System.out.println("Sua pontuação foi de 0");
            pontuacao = 0;
        }

            
        for (int i = 0; i < pontos.length; i++) {
            pontos[i] = pontuacao;
            for (int j = 0; j < pontos.length; j++) {
                if (pontos[i] < pontos[j]) {
                    int aux = pontos[i];
                    pontos[i] = pontos[j];
                    pontos[j] = aux;
                }
            }
            media = pontuacao;
        }

        int ultimo = pontos[pontos.length-1];
        System.out.println("Pontuacao Menor: " + pontos[0]);
        System.out.println("Pontuacao Maior: " + pontos[ultimo]);
        double divisor = tentativas;
        System.out.printf("Pontuacao Media: %.2f", (media / divisor));
        System.out.println("");
    }
    
    public static void imprimirBatalha(){
        System.out.println("               ---BATALHA NAVAL---");
        System.out.println("     1"+"    2"+"    3"+"    4"+"    5"+"     6"+"    7"+"    8"+"    9"+"   10");
        System.out.println("_______________________________________________________");
        for (int i = 0; i < numeros.length; i++) {
            System.out.print(letras[i] + "  ");
            for (int j = 0; j < letras.length; j++) {
                System.out.print("|"+" "+batalha[i][j]+" ");
            }
            System.out.print("|\n");
        }
    }
    
    public static void partida(){
        partidas++;
        for (int m = 0; m < 1; m++) {
            gerarNavios();
            impressao();
            for (int i = 0; i <= 100; i++) {
                if(continua = true){
                    tentativas++;
                    jogadas();
                }else{
                    break;
                }
                if(coord.equals("0")){        
                    System.out.println("Jogo encerrado");
                    continua = false;
                    break;
                }else if(contNavio1 == 6 && contNavio2 == 4){
                    System.out.println("FIM");
                    continua = false;
                    break;
                }
            }
            pontuacao();
            System.out.println("Numero de tentativas: "+tentativas);
            System.out.println("Total de Partidas jogadas: " + partidas);
        }
    }
    
}