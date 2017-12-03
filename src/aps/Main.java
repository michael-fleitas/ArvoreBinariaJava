package aps;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        Arvore arvore = new Arvore();
        int num, aux;
        s = new Scanner(System.in);
        
        aux = menu();
        
        while(aux != 11) {
            switch(aux) {
                case 1:
                    num = entradaDeValores("\nNúmero a ser adicionado: ");
                    System.out.println("\nAdicionar número " + num + " = " + arvore.adicionar(num) + "\n");
                    arvore.imprimirEmOrdem();
                    arvore.imprimirArvore();
                    break;
                case 2:
                    if(arvore.getRaiz() == null) {
                        System.out.println("\nA arvore está vazia!\n");
                    } else {
                        num = entradaDeValores("\nNúmero a ser removido: ");
                        arvore.remover(num);
                        arvore.imprimirEmOrdem();
                        arvore.imprimirArvore();
                    }
                    break;
                case 3:
                    if(arvore.getRaiz() == null) {
                        System.out.println("\nA arvore está vazia!\n");
                    } else if(arvore.buscar() == null) {
                        System.out.println("Elemento não encontrado!!");
                    } else {
                        System.out.println("Elemento encontrado.");
                    }
                    break;
                case 4:
                    System.out.println(arvore.menorElemento());
                    break;
                case 5:
                    arvore.maiorElemento();
                    break;
                case 6:
                    if(arvore.altura() == -1) {
                        System.out.println("\nA arvore está vazia!\n");
                    } else {
                        System.out.println("\nAltura da arvore: " + arvore.altura() + "\n");
                    }
                    break;
                case 7:
                    if(arvore.contarNos() == 0) {
                        System.out.println("\nA arvore está vazia!\n");
                    } else {
                        System.out.println("\nQuantidade de nós: " + arvore.contarNos() + "\n");
                    }
                    break;
                case 8:
                    if(arvore.contarFolhas() == 0) {
                        System.out.println("\nA arvore está vazia!\n");
                    } else {
                        System.out.println("\nQuantidade de folhas: " + arvore.contarFolhas() + "\n");
                    }
                    break;
                case 9:
                    if(arvore.getRaiz() == null) {
                        System.out.println("\nA arvore está vazia!\n");
                    } else {
                        num = entradaDeValores("\nMostrar ancestrais de: ");
                        arvore.exibirAncestrais(num);
                    }
                    break;
                case 10:
                    if(arvore.getRaiz() == null) {
                        System.out.println("\nA arvore está vazia!\n");
                    } else { 
                        num = entradaDeValores("\nMostrar descendentes de: ");
                        arvore.exibirDescendentes(num);
                    }
                    break;
                default:
                    break;
            }
            aux = menu();
        }
    }
    
    public static int menu() {
        int resp = 0;
        
        do {
            System.out.println("\nEscolha alguma operação: ");
            System.out.println("-----------------------");
            System.out.println("1. Inserir elemento");
            System.out.println("2. Remover elemento");
            System.out.println("3. Buscar elemento");
            System.out.println("4. Encontrar menor elemento");
            System.out.println("5. Encontrar maior elemento");
            System.out.println("6. Ver altura da arvore");
            System.out.println("7. Ver quantidade de nós");
            System.out.println("8. Ver quantidade de folhas");
            System.out.println("9. Exibir cadastros ancestrais de um nó");
            System.out.println("10. Exibir cadastros descendentes de um nó");
            System.out.println("11. Sair");
            System.out.print("\nOpção: ");
            
            try {
                resp = s.nextInt();
            } catch(InputMismatchException ime) {
                s.nextLine();
            }
        } while(resp < 1 || resp > 11);
        return resp;
    }
    
    public static int entradaDeValores(String str) {
        int ret = 0;
        
        do {
            System.out.print(str);
            
            try {
                ret = s.nextInt();
            } catch(InputMismatchException ime) {
                s.nextLine();
            }
        } while(ret <= 0);
        
        return ret;
    }
    
}
