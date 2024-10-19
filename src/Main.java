public class Main {
    public static void main(String[] args) {
        try {
            Complex c1 = new Complex(1,4);
            Complex c2 = new Complex(2,-2);
            Complex c3 = new Complex(-3,1);
            Complex c4 = new Complex(4,3);
            Complex c5 = new Complex(5,-6);
            Complex c6 = new Complex(-9,4);
            Complex c7 = new Complex(12,2);
            Complex c8 = new Complex(15,1);
            Matrix matrix1 = new Matrix(2,2,new Complex[][]{{c1,c2},{c3,c4}});
            matrix1.print();
            Matrix matrix2 = new Matrix(2,2,new Complex[][]{{c5,c6},{c7,c8}});
            matrix2.print();
            Matrix matrix3 = Matrix.matrix_sum(matrix1,matrix2);//Сложениие матриц
            matrix3.print();
            Matrix matrix4 = Matrix.matrix_substract(matrix1,matrix2);//Вычитание матриц
            matrix4.print();
            Matrix matrix5 = Matrix.matrix_multiplie(matrix1,matrix2);//Умножение матриц
            matrix5.print();
            Matrix matrix6 = Matrix.matrix_transpose(matrix1);//Транспонирование матрицы
            matrix6.print();
            Matrix matrix7 = Matrix.matrix_divide(matrix1,matrix2);//Деление матриц
            matrix7.print();
            Complex det = Matrix.matrix_determinant(matrix1);//Вычисление определителя матрицы
            det.print();

        } catch (IllegalArgumentException | ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}