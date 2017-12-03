package aps;

import static aps.Main.entradaDeValores;
import java.util.ArrayList;
import java.util.Collections;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Arvore {
    private No raiz;
    Scanner s = new Scanner(System.in);

    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }
    
    public boolean adicionar(int valor) {
        if(this.raiz == null) {
            this.raiz = new No(valor);
            return true;
        } else {
            return adicionar(valor, this.raiz);
        }
    }
    
    private boolean adicionar(int valor, No no) {
        if(valor == no.getValor()) {
            return false;
        } else {
            if(valor > no.getValor()) {
                if(no.getDireita() != null) {
                    adicionar(valor, no.getDireita());
                } else {
                    no.setDireita(new No(valor, no));
                }
            } else {
                if(no.getEsquerda() != null) {
                    adicionar(valor, no.getEsquerda());
                } else {
                    no.setEsquerda(new No(valor, no));
                }
            }
            return true;
        }
    }
    
    public void remover(int valor) {
        raiz = remover(valor, raiz);
    }
    
    private No remover(int valor, No no) {
        if(no == null) return no;
        
        if(valor < no.getValor()) {
            no.setEsquerda(remover(valor, no.getEsquerda()));
        } else if(valor > no.getValor()) {
            no.setDireita(remover(valor, no.getDireita()));
        } else {
            if(no.getEsquerda() == null) {
                return no.getDireita();
            } else if(no.getDireita() == null) {
                return no.getEsquerda();
            }
            
            no.setValor(menorElemento(no.getDireita()));
            
            no.setDireita(remover(menorElemento(no.getDireita()), no.getDireita()));
        }
        return no;
    }
    
    public No buscar() {
        if(raiz == null) {
            return null;
        } else {
            int valor = 0;
            try {
                System.out.print("\nNúmero a ser buscado: ");
                valor = s.nextInt();
            } catch(InputMismatchException ime) {
                System.out.println("Digite apenas números!");
                s.nextLine();
                buscar();
            }
            return buscar(valor, raiz);
        }
    }
    
    private No buscar(int valor, No no) {
        if(valor == no.getValor()) {
            return no;
        } else {
            if(valor > no.getValor()) {
                if(no.getDireita() == null) {
                } else {
                    return buscar(valor, no.getDireita());
                }
            } else {
                if(no.getEsquerda() == null) {
                } else {
                    return buscar(valor, no.getEsquerda());
                }
            }
            return null;
        }
    }
    
    public No menorNo() {
        if(raiz == null) {
            return null;
        } else {
            return menorNo(raiz);
        }
    }
    
    private No menorNo(No no) {
        if(no.getEsquerda() == null) {
            return no;
        } else {
            return menorNo(no.getEsquerda());
        }
    }
    
    public int menorElemento() {
        if(raiz == null) {
            System.out.println("\nA árvore está vazia!\n");
            return -1;
        } else {
            return menorElemento(raiz);
        }
    }
    
    private int menorElemento(No no) {
        if(no.getEsquerda() == null) {
            System.out.println("\nMenor elemento: " + no.getValor() + "\n");
            return no.getValor();
        } else {
            return menorElemento(no.getEsquerda());
        }
    }
    
    public void maiorElemento() {
        if(raiz == null) {
            System.out.println("\nA árvore está vazia!\n");
        } else {
            maiorElemento(raiz);
        }
    }
    
    private void maiorElemento(No no) {
        if(no.getDireita() == null) {
            System.out.println("\nMaior elemento: " + no.getValor() + "\n");
        } else {
            maiorElemento(no.getDireita());
        }
    }
    
    public int contarNos() {
        return (raiz == null) ? 0 : contarNos(raiz);
    }
    
    private int contarNos(No no) {
        int soma = 1;
        
        if(no.getEsquerda() != null) soma += contarNos(no.getEsquerda());
        if(no.getDireita() != null) soma += contarNos(no.getDireita());
        
        return soma;
    }
    
    public int contarFolhas() {
        return (raiz == null) ? 0 : contarFolhas(raiz);
    }
    
    private int contarFolhas(No no) {
        if(no == null)
            return 0;
        if(no.getEsquerda() == null && no.getDireita() == null ) {
            return 1;
        } else {
            return contarFolhas(no.getEsquerda()) + contarFolhas(no.getDireita());
        }
    }
    
    public int altura() {
        return altura(raiz);
    }
    
    private int altura(No no) {
        if(no == null) {
            return -1;
        }

        int alturaEsquerda = altura(no.getEsquerda());
        int alturaDireita = altura(no.getDireita());

        if(alturaEsquerda > alturaDireita) {
            return alturaEsquerda + 1;
        } else {
            return alturaDireita + 1;
        }
    }
    
    public void exibirAncestrais(int valor) {
        if(buscar(valor, raiz) == null) {
            System.out.println("Elemento não encontrado!");
        } else {
            System.out.print("Ancestrais de " + valor + ": \n{ ");
            while(buscar(valor, raiz).getPai() != null) {
                System.out.print(buscar(valor, raiz).getPai().getValor() + " ");
                valor = buscar(valor, raiz).getPai().getValor();
            }
            System.out.println("}");
        }
    }
    
    public void exibirDescendentes(int valor) {
        if(buscar(valor, raiz) == null) {
            System.out.println("Elemento não encontrado!");
        } else {
            System.out.println("Descendentes de " + valor + ": ");
            int nivelMaximo = nivelMaximo(buscar(valor, raiz));
            
            imprimirEmOrdem(buscar(valor, raiz));
            System.out.println("\n");
            imprimirArvore(Collections.singletonList(buscar(valor, raiz)), 1, nivelMaximo);
        }
    }
    
    public void imprimirEmOrdem() {
        System.out.print("{ ");
        imprimirEmOrdem(raiz);
        System.out.println("}");
        System.out.println("\n");
    }
    
    private void imprimirEmOrdem(No no) {
        if(raiz != null) {
            if(no.getEsquerda() != null) {
                imprimirEmOrdem(no.getEsquerda());
            }
            System.out.print(no.getValor() + " ");
            
            if(no.getDireita() != null) {
                imprimirEmOrdem(no.getDireita());
            }
        } else {
            System.out.print("A árvore está vazia! ");
        }
    }
    
    
    //Créditos dos métodos abaixo para @michal.kreuzman https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
    
    public void imprimirArvore() {
        int nivelMaximo = nivelMaximo(raiz);
        System.out.println();
        imprimirArvore(Collections.singletonList(raiz), 1, nivelMaximo);
    }

    private void imprimirArvore(List<No> nos, int nivel, int nivelMaximo) {
        if (nos.isEmpty() || isAllElementsNull(nos))
            return;

        int base = nivelMaximo - nivel;
        int linhasDeBorda = (int) Math.pow(2, (Math.max(base - 1, 0)));
        int primeirosEspacos = (int) Math.pow(2, (base)) - 1;
        int espacosInternos = (int) Math.pow(2, (base + 1)) - 1;

        imprimirEspacoVazio(primeirosEspacos);

        List<No> newNodes = new ArrayList<No>();
        for (No node : nos) {
            if (node != null) {
                System.out.print(node.getValor());
                newNodes.add(node.getEsquerda());
                newNodes.add(node.getDireita());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            imprimirEspacoVazio(espacosInternos);
        }
        System.out.println("");

        for (int i = 1; i <= linhasDeBorda; i++) {
            for (int j = 0; j < nos.size(); j++) {
                imprimirEspacoVazio(primeirosEspacos - i);
                if (nos.get(j) == null) {
                    imprimirEspacoVazio(linhasDeBorda + linhasDeBorda + i + 1);
                    continue;
                }

                if (nos.get(j).getEsquerda() != null)
                    System.out.print(" /");
                else
                    imprimirEspacoVazio(1);

                imprimirEspacoVazio(i + i - 1);

                if (nos.get(j).getDireita() != null)
                    System.out.print("\\");
                else
                    imprimirEspacoVazio(1);

                imprimirEspacoVazio(linhasDeBorda + linhasDeBorda - i);
            }

            System.out.println("");
        }

        imprimirArvore(newNodes, nivel + 1, nivelMaximo);
    }

    private void imprimirEspacoVazio(int count) {
        for(int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private int nivelMaximo(No no) {
        if(no == null) return 0;
        
        return Math.max(nivelMaximo(no.getEsquerda()), nivelMaximo(no.getDireita())) + 1;
    }

    private boolean isAllElementsNull(List list) {
        for(Object object : list) {
            if (object != null)
                return false;
        }
        return true;
    }

}
