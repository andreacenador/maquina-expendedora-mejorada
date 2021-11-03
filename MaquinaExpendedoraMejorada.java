public class MaquinaExpendedoraMejorada {

    // El precio del billete
    private int precioBillete;
    // La cantidad de dinero que lleva metida el cliente actual
    private int balanceClienteActual;
    // El total de dinero almacenado en la maquina desde su ultimo vaciado
    private int totalDineroAcumulado;
    // El origen del billete
    private String estacionOrigen;
    // El destino del billete
    private String estacionDestino;
    // Contador de billetes
    private int contadorDeBilletes;
    //tipo de maquina true = Normal -- false = con Premio
    private boolean tipoDeMaquina;
    //maximo de billetes que queremos vender
    private int maximoDeBilletes;

    /**
     * Crea una maquina expendedora de billetes de tren con el 
     * precio del billete y el origen y destino dados. Se asume que el precio
     * del billete que se recibe es mayor que 0.
     */
    public MaquinaExpendedoraMejorada(int precioDelBillete, String origen, String destino, boolean maquinaNormal, int maximoDeBilletesVender) {
        precioBillete = precioDelBillete;
        balanceClienteActual = 0;
        totalDineroAcumulado = 0;
        estacionOrigen = origen;
        estacionDestino = destino;
        contadorDeBilletes = 0;
        tipoDeMaquina = maquinaNormal;
        maximoDeBilletes = maximoDeBilletesVender;
    }

    /**
     * Devuelve el precio del billete
     */
    public int getPrecioBillete() {
        return precioBillete;
    }
    
    /**
     * Devuelve la cantidad de dinero que el cliente actual lleva introducida
     */
    public int getBalanceClienteActual() {
        return balanceClienteActual;
    }

    /**
     * Simula la introduccion de dinero por parte del cliente actual
     */
    public void introducirDinero(int cantidadIntroducida) {
        if (contadorDeBilletes < maximoDeBilletes) {
            if (cantidadIntroducida > 0) {
                balanceClienteActual = balanceClienteActual + cantidadIntroducida;
            }
            else {
                System.out.println(cantidadIntroducida + " no es una cantidad de dinero valida.");
            }
        }
        else {
            System.out.println("Error, ya se han vendido el maximo de billetes y no se puede introducir mas dinero");
        }
    }

    /**
     * Imprime un billete para el cliente actual
     */
    public void imprimirBillete() {
        if (contadorDeBilletes < maximoDeBilletes){
            int cantidadDeDineroQueFalta;
            cantidadDeDineroQueFalta = precioBillete - balanceClienteActual;
            if (cantidadDeDineroQueFalta <= 0) {   
                // Simula la impresion de un billete
                System.out.println("##################");
                System.out.println("# Billete de tren:");
                System.out.println("# De " + estacionOrigen + " a " + estacionDestino);
                System.out.println("# " + precioBillete + " euros.");
                System.out.println("##################");
                System.out.println();
                if (tipoDeMaquina == false) {
                    int aplicarPremio = contadorDeBilletes % 3;
                    if (aplicarPremio == 0) {
                        int premioDeDescuento = 0;
                        premioDeDescuento = (precioBillete * 10) / 100;
                        System.out.println("PREMIO, descuento de " + premioDeDescuento + "€");
                    }
                }
        
                // Actualiza el total de dinero acumulado en la maquina
                totalDineroAcumulado = totalDineroAcumulado + precioBillete;
                // Reduce el balance del cliente actual dejandole seguir utilizando la maquina
                balanceClienteActual = balanceClienteActual - precioBillete;
                contadorDeBilletes = contadorDeBilletes + 1;
            }
            else {
                System.out.println("Necesitas introducir " + cantidadDeDineroQueFalta + " euros mas!");
        
            }
        }
        else {
            System.out.println("Error, ya has superado el limite de billetes");
        }
    
    }
    

    /**
     * Cancela la operacion de compra del cliente actual y le
     * devuelve al cliente el dinero que ha introducido hasta el momento
     */
    public int cancelarOperacionYDevolverDinero() {
        int cantidadDeDineroADevolver;
        cantidadDeDineroADevolver = balanceClienteActual;
        balanceClienteActual = 0;
        return cantidadDeDineroADevolver;
    } 
    
    public int vaciarDineroDeLaMaquina() {
        int vaciarDineroDeLaMaquina;
        vaciarDineroDeLaMaquina = -1;
        if (balanceClienteActual == 0) {
            vaciarDineroDeLaMaquina = balanceClienteActual + totalDineroAcumulado;
            totalDineroAcumulado = 0;
        }
        else {
            System.out.println("Error, la maquina tiene una operación en curso");
        }
        return vaciarDineroDeLaMaquina;
        
    }
    
    public int getNumeroBilletesVendidos(){
        return contadorDeBilletes;
    }
    
    public void imprimeNumeroBilletesVendidos(){
        System.out.println("Has imprimido " + contadorDeBilletes + " billetes");
    }
}
