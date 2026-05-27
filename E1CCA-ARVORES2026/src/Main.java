public class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
        arvoreBinaria.inserir(10);
        arvoreBinaria.inserir(5);
        arvoreBinaria.inserir(15);
        arvoreBinaria.inserir(17);
        arvoreBinaria.inserir(12);
        arvoreBinaria.inserir(2);
        arvoreBinaria.inserir(8);
        arvoreBinaria.inserir(4);
        arvoreBinaria.inserir(3);

        arvoreBinaria.percurso("Pre");
        
        arvoreBinaria.remover(10);

        arvoreBinaria.percurso("Pre");
    }
}
