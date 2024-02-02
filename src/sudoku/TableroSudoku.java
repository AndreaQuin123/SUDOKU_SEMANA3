package sudoku;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class TableroSudoku{
    private int[][] tablero;

    public TableroSudoku() {
        this.tablero = new int[9][9];
        generarSudoku();
        imprimir();
    }

    private void generarSudoku() {
        
        inicializarTablero();
        llenarNumerosIniciales();
        if (resolverSudoku(0, 0)) {
            System.out.println("Sudoku generado correctamente.");
        } else {
            System.out.println("No se pudo generar un Sudoku válido.");
        }
    }

    private void inicializarTablero() {
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                tablero[fila][columna] = 0;
            }
        }
    }

    private void llenarNumerosIniciales() {
        Random random = new Random();
        for (int i = 1; i <= 9; i++) {
            int fila = random.nextInt(9);
            int columna = random.nextInt(9);
            int numero = i;
            if (esNumeroValido(numero, fila, columna)) {
                tablero[fila][columna] = numero;
            } else {
                i--; 
            }
        }
    }

    private boolean resolverSudoku(int fila, int columna) {
        if (fila == 9) {
            fila = 0;
            if (++columna == 9) {
                return true;
            }
        }

        if (tablero[fila][columna] != 0) {
            return resolverSudoku(fila + 1, columna);
        }

        for (int num = 1; num <= 9; num++) {
            if (esNumeroValido(num, fila, columna)) {
                tablero[fila][columna] = num;
                if (resolverSudoku(fila + 1, columna)) {
                    return true;
                }
                tablero[fila][columna] = 0; // Deshacer si no lleva a una solución válida
            }
        }

        return false; // No se encontró una solución válida
    }

    private boolean esNumeroValido(int numero, int fila, int columna) {
        return !enFila(numero, fila) && !enColumna(numero, columna) && !enSubCuadricula(numero, fila, columna);
    }

    private boolean enFila(int numero, int fila) {
        for (int i = 0; i < 9; i++) {
            if (tablero[fila][i] == numero) {
                return true;
            }
        }
        return false;
    }

    private boolean enColumna(int numero, int columna) {
        for (int i = 0; i < 9; i++) {
            if (tablero[i][columna] == numero) {
                return true;
            }
        }
        return false;
    }

    private boolean enSubCuadricula(int numero, int fila, int columna) {
        int inicioFila = (fila / 3) * 3;
        int inicioColumna = (columna / 3) * 3;

        for (int i = inicioFila; i < inicioFila + 3; i++) {
            for (int j = inicioColumna; j < inicioColumna + 3; j++) {
                if (tablero[i][j] == numero) {
                    return true;
                }
            }
        }
        return false;
    }

    private void imprimir() {
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                System.out.print("[" + tablero[fila][columna] + "] ");
            }
            System.out.println();
        }
    }

    public int[][] getTablero() {
        return tablero;
    }
    
}


