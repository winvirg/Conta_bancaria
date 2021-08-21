/**
 * @author bruno
 *
 */
public class Conta {
	
	private int numero;
	private double saldo;
	private double limite = 100;
	private double[] extrato;
	private int operacoes = 10; // A conta suporta no maximo 10 operações
	
    public Conta(int numero, double saldo) {
    	this.numero = numero;
    	this.saldo = saldo;
    }

    
    public int getNumero() {
        return this.numero;
    }
    
    public double getSaldo() {
    	if(this.limite == 100) {
    		return this.saldo+limite;
    	}else
        return saldo; 
    }

    
    public double getLimite() {
        return limite;
    }
    
    public void setExtrato(double valor){
        for(int i = 0; i >= extrato.length; i++){
            extrato[i]=valor;
        }
    }
    
    public boolean sacar(double valor) {
       if(valor<0||valor>saldo+limite||operacoes<1) {		//não pode sacar valor negativo; não pode sacar um valor maior que saldo+limite; teste se pode fazer operacoes
    	   return false;
       }else {
    	   if(saldo>valor) {				//saque com saldo
    		   saldo-=valor;
    		   operacoes--;
    		   setExtrato(valor);
    	   }else {							//saque sem saldo usando limite;
    		   double guardaSaldo = saldo;
    		   saldo=(saldo+limite)-valor;
    		   limite = saldo;
    		   operacoes--;
    		   setExtrato(valor);
    	   }
    	   return true;
       }
    }

   
    public boolean depositar(double valor) {
        if(valor<0||operacoes<1) {			//não pode sacar alor negativo nem fazer mais que 10 operacoes
        	return false;
        }else {
        	if(limite!=100) {				//deposito com limite ultilizado
	       		limite = 100;
	        	saldo+=valor;
		        operacoes--;
		        setExtrato(valor);
        	}else {							//deposito com limite intacto
        		saldo+=valor;
	        	operacoes--;
	        	setExtrato(valor);
        	}
        	return true;
        }
    }

    
    public boolean transferir(Conta destino, double valor) {
        if(valor<0||valor>saldo+limite||operacoes<1) {
        	return false;
        }else {
        	if(valor>saldo) {
        		saldo = (saldo+limite) - valor;
        		operacoes--;
        		setExtrato(valor);
        	}else {
        		saldo-=valor;
        		destino.saldo+=valor;
        		operacoes--;
        		setExtrato(valor);
        	}
        	return true;
        }
    }


    public double[] verExtrato() {
    	double[] verExtrato = new double[10];
        for (int i = 0; i <= extrato.length; i++) {
            verExtrato[i] = extrato[i];
        }
        return verExtrato;
    }
}