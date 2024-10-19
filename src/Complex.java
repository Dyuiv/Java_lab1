public class Complex{
    double real;
    double imag;
    public Complex(double real, double imag){
        this.imag = imag;
        this.real = real;
    }
    public void print(){
        if(this.imag<0){
            System.out.printf("(%.3f - %.3fi)",this.real,(-this.imag));
        }
        else{
            System.out.printf("(%.3f + %.3fi)",this.real,this.imag);
        }
    }
    public static Complex complex_sum(Complex num1,Complex num2){
        Complex result = new Complex(0,0);
        result.imag = num1.imag + num2.imag;
        result.real = num1.real + num2.real;
        return result;
    }
    public static Complex complex_substract(Complex num1, Complex num2){
        Complex result = new Complex(0,0);
        result.real = num1.real - num2.real;
        result.imag = num1.imag - num2.imag;
        return result;
    }
    public static Complex complex_multiplie(Complex num1, Complex num2){
        Complex result = new Complex(0,0);
        result.real = num1.real * num2.real - num1.imag * num2.imag;
        result.imag = num1.real * num2.imag + num1.imag * num2.real;
        return result;
    }
    public static Complex complex_divide(Complex num1, Complex num2) {
        double denominator = num2.real * num2.real + num2.imag * num2.imag;
        if(denominator == 0){
            throw new ArithmeticException("Деление на ноль невозможно.");
        }
        Complex result = new Complex(0, 0);
        result.real = (num1.real * num2.real + num1.imag * num2.imag) / denominator;
        result.imag = (num1.imag * num2.real - num1.real * num2.imag) / denominator;
        return result;
    }
}