public class ArvoreBinaria {
    No raiz;

    public ArvoreBinaria() {
        this.raiz = new No(null);
        System.out.println("Árvore criada com sucesso");
    }

//    public void inserir(Integer conteudo) {
//        No novoNo = new No(conteudo);
//        if(estaVazia()) {
//            this.raiz = novoNo;
//            System.out.println("Raiz criada com sucesso com valor: " + novoNo.getConteudo());
//        } else {
//            No aux = this.raiz;
//            while(true) {
//                if(aux.getConteudo() > novoNo.getConteudo()) {
////                    if(aux.getEsquerda() == null) {
////                        aux.setEsquerda(novoNo);
////                        System.out.println("Nó " + novoNo.getConteudo() + " inserido com sucesso.");
////                        return;
////                    } else {
////                        aux = aux.getEsquerda();
////                    }
////                } else if (aux.getConteudo() < novoNo.getConteudo()) {
////                    if(aux.getDireita() == null) {
////                        aux.setDireita(novoNo);
////                        System.out.println("Nó " + novoNo.getConteudo() + " inserido com sucesso.");
////                        return;
////                    } else {
////                        aux = aux.getDireita();
////                    }
////                } else {
////                    System.out.println("Não são permitidos nós repetidos na árvore binária. O " + novoNo.getConteudo() + " já existe na árvore.");
////                    return;
////                }
//            }
//        }
//    }

    public void inserir(Integer conteudo) {
        No novoNo = new No(conteudo);
        if(estaVazia()) {
            this.raiz = novoNo;
            System.out.println("Raiz criada com sucesso com valor: " + novoNo.getConteudo());
        } else {
            inserirRecursivo(novoNo, this.raiz);
        }
    }

    public void inserirRecursivo(No novoNo, No aux) {
        if(aux.getConteudo() > novoNo.getConteudo()) {
            if(aux.getEsquerda() == null) {
                aux.setEsquerda(novoNo);
                System.out.println("Nó " + novoNo.getConteudo() + " inserido com sucesso.");
                return;
            } else {
                inserirRecursivo(novoNo, aux.getEsquerda());
            }
        } else if (aux.getConteudo() < novoNo.getConteudo()) {
            if(aux.getDireita() == null) {
                aux.setDireita(novoNo);
                System.out.println("Nó " + novoNo.getConteudo() + " inserido com sucesso.");
                return;
            } else {
                inserirRecursivo(novoNo, aux.getDireita());
            }
        } else {
            System.out.println("Não são permitidos nós repetidos na árvore binária. O " + novoNo.getConteudo() + " já existe na árvore.");
            return;
        }
    }

    public void remover(Integer removerNo){ //remover(5)
        if(!seExiste(removerNo)){
            System.out.println("O Nó " + removerNo + " não existe na árvore");
            return;
        } 

        this.raiz = removerRecursivo(this.raiz, removerNo); //removerRecursivo(10, 5)
    }

    private No removerRecursivo(No aux, Integer valor){  //removerRecursivo(10, 5)(5, 5)
        if(valor < aux.getConteudo()){ 
            aux.setEsquerda(removerRecursivo(aux.getEsquerda(), valor)); //setEsquerda(removerRecursivo(5, 5))
        } else if(valor > aux.getConteudo()){ 
            aux.setDireita(removerRecursivo(aux.getDireita(), valor)); //setDireita(removerRecursivo())
        }
        else{
            if(aux.getEsquerda() == null && aux.getDireita() == null){
                System.out.println("No folha " + aux.getConteudo() + " removido com sucesso!");
                return null;
            } else if(aux.getEsquerda() == null){
                System.out.println("Nó filho " + aux.getConteudo() + " removido com sucesso!");
                return aux.getDireita();
            } else if(aux.getDireita() == null){
                System.out.println("Nó filho " + aux.getConteudo() + " removido com sucesso!");
                return aux.getEsquerda();
            }
            else { //aux = 5 , aux.getDireita = 8
                No paiDoSucessor = aux; //paiDoSucessor = 5
                No sucessor = aux.getDireita(); //sucessor = 8
                
                // 1. Desce tudo para a esquerda para achar o nó substituto (sucessor)
                while (sucessor.getEsquerda() != null) { //sucessor.getEsquerda = null
                    paiDoSucessor = sucessor; //paiDoSucessor = 8 
                    sucessor = sucessor.getEsquerda(); // sucessor = 6
                }
                
                // 2. O pai do sucessor precisa "soltar" ele antes da mudança
                if (paiDoSucessor != aux) { // paiDoSucessor = 8 e aux = 5
                    paiDoSucessor.setEsquerda(sucessor.getDireita()); // paiDoSucessor.setEsquerda(7)
                    // Se o sucessor tinha um filho na direita, o pai dele adota
                    sucessor.setDireita(aux.getDireita()); //sucessor.setDireita(8)
                }
                
                // 3. A MÁGICA: O sucessor herda o filho da esquerda do nó que está morrendo
                sucessor.setEsquerda(aux.getEsquerda()); //sucessor.setEsquerda(4)
                
                // 4. Retornamos o próprio nó sucessor!
                // Lembra que o pai do 'aux' está esperando esse retorno? 
                // Ao retornar o sucessor, o pai do aux passa a apontar direto para o sucessor!
                System.out.println("Nó com dois filhos " + aux.getConteudo() + " removido com sucesso!");
                return sucessor;
            }
        }
        return aux;
    }

    public boolean seExiste(Integer valor){ //seExiste(7)
        return buscarRecursivo(this.raiz, valor); //true
    }


    public boolean buscarRecursivo(No aux, Integer valor){ //buscarRecursivo(7, 7)
        if(aux == null){
            return false;
        }

        if(valor.equals(aux.getConteudo())){
            return true;
        }

        if(valor < aux.getConteudo()) {
            return buscarRecursivo(aux.getEsquerda(), valor); //true
        } 
        else{
            return buscarRecursivo(aux.getDireita(), valor); //true
        }
    }

    private boolean estaVazia () {
        if(this.raiz.getConteudo() == null) {
            return true;
        }
        else {
            return false;
        }
    }

    public void percurso(String percurso) {
        if(this.raiz == null) {
            System.out.println("A árvore não existe.");
            return;
        }

        switch (percurso) {
            case("Pre"):
                System.out.println("Executando a árvore em pré ordem.");
                this.preOrdem(this.raiz);
                break;
            case("Em"):
                System.out.println("Executando a árvore em ordem.");
                this.emOrdem(this.raiz);
                break;
            case("Pos"):
                System.out.println("Executando a árvore em pós ordem.");
                this.posOrdem(this.raiz);
                break;
            default:
                System.out.println("Percurso inexistente!");
                break;

        }
    }

    private void posOrdem(No no) {
        if(no == null) {
            return;
        }
        posOrdem(no.getEsquerda());
        posOrdem(no.getDireita());
        System.out.println(no.getConteudo());
    }

    private void preOrdem(No no) {
        if(no == null) {
            return;
        }
        System.out.println(no.getConteudo());
        preOrdem(no.getEsquerda());
        preOrdem(no.getDireita());
    }

    private void emOrdem(No no) {
        if(no == null) {
            return;
        }
        emOrdem(no.getEsquerda());
        System.out.println(no.getConteudo());
        emOrdem(no.getDireita());
    }
}
