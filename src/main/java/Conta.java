/**
 * @author bruno
 *
 */
public class Conta {
    private int numero;
    private double saldo;
    private double limitless;
    private double limite;
    private double[] extrato = new double[10];

    public Conta(int numero, double saldoSemLimite) {
        this.numero = numero;
        this.limitless = saldoSemLimite;
        this.limite = 100;
        this.saldo = saldoSemLimite + limite;
        System.out.println("Conta criada!!");
        saldoLimite();  
    }

    public int getNumero() {
        return numero;
    }
    
    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public boolean sacar(double valor) {
        if(valor > saldo || valor < 0.00){
            System.out.println("Erro " + valor);
            return false;
        }else if(valor == saldo){
            saldo -= valor;
            limitless = 0.0;
            limite = 0.0;
            setExtrato(-valor);
            System.out.println("Sacou " + valor);
            saldoLimite();   
            return true;
        }else{
            if(valor <= limitless){
            	limitless -= valor;
                saldo -= valor;
                setExtrato(-valor);
                System.out.println("Sacou " + valor);
                saldoLimite();
                return true;
            }else{
                limite -= valor - limitless;
                limitless = 0.00;
                saldo -= valor;
                setExtrato(-valor);
                System.out.println("Sacou " + valor);
                saldoLimite();
                return true;
            }          
        }

    }
   
    public boolean depositar(double valor) {
        if(valor < 0.00){
            System.out.println("Erro " + valor);
            return false;
        }else{
            if(limite <= 100.00 && valor <= 100){
                if(limite + valor >= 100.00){
                	limitless += (limite + valor - 100.00);
                    limite = 100.00;
                    saldo = limite + limitless;
                    setExtrato(valor);
                    System.out.println("Depositou " + valor);
                    saldoLimite();
                    return true;    
                }else{
                    limite += valor;
                    saldo = limite + limitless;
                    System.out.println("Depositou " + valor);
                    setExtrato(valor);
                    saldoLimite();
                    return true;
                }
            }else{
            	limitless += valor;
                saldo = limite + limitless;
                setExtrato(valor);
                System.out.println("Depositou " + valor);
                saldoLimite();
                return true;    
            }
            
        }

    }
    
    public boolean transferir(Conta destino, double valor) {
        if(valor < 0.00 || valor > getSaldo()){
            System.out.println("Erro " + valor);
            return false;
        }else{
            sacar(valor);
            destino.depositar(valor);
            System.out.println("transferiu " + valor);
            saldoLimite();
            return true;
        }
    }

    public double[] verExtrato() {
        double[] verExtrato = new double[10] ;
        for (int i = 0; i < extrato.length; i++) {
            if(extrato[i] != 0) verExtrato[i] = extrato[i];
        }
        return verExtrato;
    }

    public void setExtrato(double valor){
        for (int i = 0; i < extrato.length; i++) {
            if(extrato[i] == 0 ){
                extrato[i] = valor;
                break;
            }
        }
    }

    public void saldoLimite(){
        System.out.println("Saldo: " + getSaldo() + ", Limite: " + getLimite());
    }

}