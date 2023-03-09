class Matriz {
    protected int[][] matriz;
    protected int filas;
    protected int columnas;

    public Matriz(int[][] matriz) {
        this.matriz = matriz;
        this.filas = matriz.length;
        this.columnas = matriz[0].length;
    }

    public int getFilas() {
        return this.filas;
    }

    public int getColumnas() {
        return this.columnas;
    }

    public int getElemento(int fila, int columna) {
        return this.matriz[fila][columna];
    }

    public void setElemento(int fila, int columna, int valor) {
        this.matriz[fila][columna] = valor;
    }

    public int[][] multiplicacionDeMatrices(Matriz otraMatriz) {
        if (this.getColumnas() != otraMatriz.getFilas()) {
            throw new IllegalArgumentException("Las matrices no son compatibles para la multiplicación");
        }

        int[][] resultado = new int[this.getFilas()][otraMatriz.getColumnas()];

        for (int i = 0; i < this.getFilas(); i++) {
            for (int j = 0; j < otraMatriz.getColumnas(); j++) {
                int suma = 0;
                for (int k = 0; k < this.getColumnas(); k++) {
                    suma += this.getElemento(i, k) * otraMatriz.getElemento(k, j);
                }
                resultado[i][j] = suma;
            }
        }

        return resultado;
    }
}

class MatrizCuadrada extends Matriz {

    public MatrizCuadrada(int[][] matriz) {
        super(matriz);
        if (this.getFilas() != this.getColumnas()) {
            throw new IllegalArgumentException("La matriz no es cuadrada");
        }
    }

    public int getDimension() {
        return this.filas;
    }

    @Override
    public int[][] multiplicacionDeMatrices(Matriz otraMatriz) {
        if (!(otraMatriz instanceof MatrizCuadrada)) {
            throw new IllegalArgumentException("La matriz no es cuadrada");
        }

        return super.multiplicacionDeMatrices(otraMatriz);
    }
}

class MatrizRectangular extends Matriz {

    public MatrizRectangular(int[][] matriz) {
        super(matriz);
        if (this.getFilas() == this.getColumnas()) {
            throw new IllegalArgumentException("La matriz no es rectangular");
        }
    }

    @Override
    public int[][] multiplicacionDeMatrices(Matriz otraMatriz) {
        if (otraMatriz instanceof MatrizCuadrada) {
            throw new IllegalArgumentException("Las matrices no son compatibles para la multiplicación");
        }

        return super.multiplicacionDeMatrices(otraMatriz);
    }
}