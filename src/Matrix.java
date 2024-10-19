public class Matrix{
    int n;
    int m;
    Complex[][]array;
    public Matrix(int n, int m, Complex[][]array){
        this.m = m;
        this.n = n;
        this.array = array;
    }
    public void print(){
        for(int i = 0;i<this.n;++i){
            for(int j = 0;j<this.m;++j){
                this.array[i][j].print();
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
    public static Matrix matrix_sum(Matrix a,Matrix b){
        if((a.n!=b.n)||(a.m!=b.m)){
            throw new IllegalArgumentException("Размерности матриц не совпадают для сложения.");
        }
        else
        {
            Matrix result = new Matrix(a.n,b.m,null);
            result.array = new Complex[a.n][a.m];
            for(int i =0;i<a.n;++i){
                for(int j =0;j<a.m;++j){
                    result.array[i][j] = Complex.complex_sum(a.array[i][j],b.array[i][j]);
                }
            }
            return result;
        }
    }
    public static Matrix matrix_substract(Matrix a,Matrix b){
        if((a.n!=b.n)||(a.m!=b.m)){
            throw new IllegalArgumentException("Размерности матриц не совпадают для вычитания.");
        }
        else
        {
            Matrix result = new Matrix(a.n,b.m,null);
            result.array = new Complex[a.n][a.m];
            for(int i =0;i<a.n;++i){
                for(int j =0;j<a.m;++j){
                    result.array[i][j] = Complex.complex_substract(a.array[i][j],b.array[i][j]);
                }
            }
            return result;
        }
    }
    public static Matrix matrix_multiplie(Matrix a,Matrix b){
        if(a.m!=b.n){
            throw new IllegalArgumentException("Размерности матриц не подходят для умножения.");
        }
        else
        {
            Matrix result = new Matrix(a.n,b.m,null);
            result.array = new Complex[a.n][b.m];
            for(int i =0;i<a.n;++i){
                for(int j = 0;j<b.m;++j){
                    Complex temp = new Complex(0,0);
                    for(int k =0;k<a.m;++k){
                        Complex curr = Complex.complex_multiplie(a.array[i][k],b.array[k][j]);
                        temp = Complex.complex_sum(temp,curr);
                    }
                    result.array[i][j] = temp;
                }

            }
            return result;
        }
    }
    public static Matrix matrix_transpose(Matrix a){
        Matrix result = new Matrix(a.m,a.n,new Complex[a.m][a.n]);
        for(int i = 0;i<a.m;++i){
            for(int j = 0;j<a.n;++j){
                result.array[i][j] = a.array[j][i];
            }
        }
        return result;
    }
    public static Complex matrix_determinant(Matrix matrix) {
        if (matrix.n != matrix.m) {
            throw new IllegalArgumentException("Матрица должна быть квадратной для вычисления определителя.");
        }
        if (matrix.n == 2) {
            Complex a = matrix.array[0][0];
            Complex b = matrix.array[0][1];
            Complex c = matrix.array[1][0];
            Complex d = matrix.array[1][1];
            return Complex.complex_substract(Complex.complex_multiplie(a, d), Complex.complex_multiplie(b, c));
        }
        Complex result = new Complex(0, 0);
        for (int i = 0; i < matrix.m; i++) {
            Matrix subMatrix = matrix_sub_matrix(matrix, 0, i);
            Complex num = Complex.complex_multiplie(matrix.array[0][i], matrix_determinant(subMatrix));
            if (i % 2 == 0) {
                result = Complex.complex_sum(result, num);
            }
            else {
                result = Complex.complex_substract(result, num);
            }
        }
        return result;
    }
    public static Matrix matrix_sub_matrix(Matrix matrix, int excludingRow, int excludingCol) {
        Matrix result = new Matrix(matrix.n-1,matrix.m-1,null);
        Complex[][] array = new Complex[matrix.n - 1][matrix.m - 1];
        int row =0;
        int col = 0;
        for (int i = 0; i < matrix.n; i++) {
            if (i == excludingRow){
                continue;
            }
            col = 0;
            for (int j = 0; j < matrix.m; j++) {
                if (j == excludingCol){
                    continue;
                }
                array[row][col] = matrix.array[i][j];
                col+=1;
            }
            row+=1;
        }
        result.array = array;
        return result;
    }

    public static Matrix matrix_inverse(Matrix matrix) {
        Complex det = matrix_determinant(matrix);
        if (det.real == 0 && det.imag == 0) {
            throw new ArithmeticException("Матрица вырождена, обратной матрицы не существует.");
        }
        Matrix result = new Matrix(matrix.n, matrix.m, null);
        if (matrix.n == 2) {
            Complex[][] array = new Complex[2][2];
            array[0][0] = Complex.complex_divide(matrix.array[1][1], det);
            array[0][1] = Complex.complex_divide(new Complex(-matrix.array[0][1].real, -matrix.array[0][1].imag), det);
            array[1][0] = Complex.complex_divide(new Complex(-matrix.array[1][0].real, -matrix.array[1][0].imag), det);
            array[1][1] = Complex.complex_divide(matrix.array[0][0], det);
            result.array = array;
            return result;
        }
        Complex[][] invArray = new Complex[matrix.n][matrix.m];
        for (int i = 0; i < matrix.n; i++) {
            for (int j = 0; j < matrix.m; j++) {
                Matrix subMatrix = matrix_sub_matrix(matrix, i, j);
                Complex num = matrix_determinant(subMatrix);
                if ((i + j) % 2 != 0) {
                    num = new Complex(-num.real, -num.imag);
                }
                invArray[j][i] = Complex.complex_divide(num, det);
            }
        }
        result.array = invArray;
        return result;
    }
    public static Matrix matrix_divide(Matrix a, Matrix b) {
        Matrix invB = matrix_inverse(b);
        return matrix_multiplie(a, invB);
    }
}